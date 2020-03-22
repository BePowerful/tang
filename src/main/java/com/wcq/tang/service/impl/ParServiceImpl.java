package com.wcq.tang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.ParticipleUtils;
import com.wcq.tang.dto.TableParDto;
import com.wcq.tang.mapper.CorpusMapper;
import com.wcq.tang.mapper.ThreecupMapper;
import com.wcq.tang.model.*;
import com.wcq.tang.service.MyOpService;
import com.wcq.tang.service.OperationService;
import com.wcq.tang.service.ParService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 12:01
 */
@Service
public class ParServiceImpl implements ParService {
    private final static Logger logger = LoggerFactory.getLogger(ParServiceImpl.class);
    @Autowired
    private CorpusMapper corpusMapper;
    @Autowired
    private ThreecupMapper threecupMapper;
    @Autowired
    private MyOpService myOpService;
    @Autowired
    private OperationService operationService;
    @Override
    public Page<Corpus> getWillParData(User user,Integer page,Integer limit) {
        Page<Corpus> corpusPage = PageHelper.startPage(page, limit);
        CorpusExample example = new CorpusExample();
        CorpusExample.Criteria criteria = example.createCriteria();
        criteria.andParticipledEqualTo(false);
        criteria.andUploaderEqualTo(user.getUserId());
        corpusMapper.selectByExample(example);
        return corpusPage;
    }

    @Override
    public List<TableParDto> corpusToTableParDto(List<Corpus> willParData) {
        List<TableParDto> tableParDtoList = new LinkedList<>();
        if(willParData.size()>0 && willParData.get(0)!=null){
            for (Corpus corpus:willParData
            ) {
                TableParDto dto= new TableParDto(corpus.getCorpusId(),corpus.getOriginalContent(),"","1");
                tableParDtoList.add(dto);
            }
        }
        return tableParDtoList;
    }

    @Override
    public TableParDto yuLan(Long corpusId) {
        Corpus corpus = corpusMapper.selectByPrimaryKey(corpusId);
        TableParDto tableParDto = new TableParDto();
        tableParDto.setParId(corpusId);
        tableParDto.setParContent(corpus.getOriginalContent());
        tableParDto.setParResult(corpus.getCixinContent());
        tableParDto.setParFunc("1");
        return tableParDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void deletePar(Long corpusId) {
        corpusMapper.deleteByPrimaryKey(corpusId);
        myOpService.deleteTodayOp(corpusId,Constant.OP_KIND_PAR);
    }

    @Override
    public List<Todaythree> getNowThreeData(User user) {
        return myOpService.getTodaythree(user);
    }

    @Override
    public Todaythree[] threeMoPao(List<Todaythree> todaythreeList) {
        return myOpService.ThreMoPao(todaythreeList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void deleteThree(Long threeId) {
        //删除实际的
        threecupMapper.deleteByPrimaryKey(threeId);
        //删除暂存的
        myOpService.deleteThree(threeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void updateThree(Threecup threecup) {
        //更新实际的
        threecupMapper.updateByPrimaryKeySelective(threecup);
        //更新暂时的
        Todaythree todaythree = new Todaythree();
        List<Todaythree> todaythreeByExample = myOpService.getTodaythreeByExample(threecup.getThreeId());
        if(todaythreeByExample.size()>0 && todaythreeByExample.get(0)!=null){
            todaythree =todaythreeByExample.get(0);
        }
        todaythree.setSubject(threecup.getSubject());
        todaythree.setPredicate(threecup.getPredicate());
        todaythree.setObject(threecup.getObject());
        myOpService.updateTodaythree(todaythree);
    }

    @Override
    public TableParDto doPar(TableParDto tableParDto) {
        ParticipleUtils utils = new ParticipleUtils();
        Integer funcSelect =Integer.valueOf(tableParDto.getParFunc());
        String content = tableParDto.getParContent();
        String participleResult = tableParDto.getParResult();
        switch (funcSelect) {
            case 1:
                participleResult = utils.standard(content);
                break;
            case 2:
                participleResult = utils.NLP(content);
                break;
            case 3:
                participleResult = utils.Short(content);
                break;
            case 4:
                participleResult = utils.NShort(content);
                break;
            case 5:
                participleResult = utils.crf(content);
                break;
            default:
                logger.error("****分词方法选择错误：{}",funcSelect);
                break;
        }
        tableParDto.setParResult(participleResult+utils.seeGoodResult(participleResult));
        return tableParDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized TableParDto saveResult(TableParDto tableParDto,User user) {
        //实际存储
        ParticipleUtils utils = new ParticipleUtils() ;
        Long corpusId = tableParDto.getParId();
        String participleResult = tableParDto.getParResult();
        String[] split = participleResult.split("\n");
        participleResult=split[0];
        Corpus corpus = new Corpus();
        corpus.setCixinContent(participleResult);
        corpus.setStandardContent(utils.standardResult(participleResult));
        corpus.setParticipled(true);
        //零时存储
        Todayop todayop = new Todayop();
        todayop.setCount(corpus.getCixinContent());
        todayop.setId(tableParDto.getParId());
        todayop.setOpKind(Constant.OP_KIND_PAR);
        todayop.setOpUser(user.getUserId());
        todayop.setOpTime(new Date());
        //
        if(corpusId != 0l){//导入
            corpus.setCorpusId(corpusId);
            logger.info("*******保存语料，id:{}",corpusId);
            corpusMapper.updateByPrimaryKeySelective(corpus);
            if(myOpService.hasTodayOp(tableParDto.getParId(),Constant.OP_KIND_PAR)){//true,存在，更新
                myOpService.updateTodayOp(todayop);
            }else{
                myOpService.insertTodayOp(todayop);
            }
        }else{//输入的
            corpus.setUploader(user.getUserId());
            corpus.setCorpusDate(new Date());
            corpus.setOriginalContent(tableParDto.getParContent());
            corpusMapper.insertSelective(corpus);
            tableParDto.setParId(corpus.getCorpusId());
            todayop.setId(tableParDto.getParId());
            myOpService.insertTodayOp(todayop);
        }
        operationService.insertOperation(user,"分词操作",Constant.OPERATION_PARTICIPLE);
        return tableParDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized int addThreecup(Threecup threecup,User user) {
        //检擦次三元组是否已经存在
        ThreecupExample example = new ThreecupExample();
        ThreecupExample.Criteria criteria = example.createCriteria();
        criteria.andSubjectEqualTo(threecup.getSubject());
        criteria.andPredicateEqualTo(threecup.getPredicate());
        criteria.andObjectEqualTo(threecup.getObject());
        List<Threecup> threecups = threecupMapper.selectByExample(example);
        if(threecups.size()>0 && threecups.get(0)!=null){
            return 1;
        }else{
            threecup.setBuildTime(new Date());
            threecup.setUploader(user.getUserId());
            int i = threecupMapper.insertSelective(threecup);
            //构建对象
            Todaythree today= new Todaythree();
            today.setOpUser(user.getUserId());
            today.setSubject(threecup.getSubject());
            today.setPredicate(threecup.getPredicate());
            today.setObject(threecup.getObject());
            today.setThreeId(threecup.getThreeId());
            today.setOpTime(new Date());
            myOpService.insertTodaythree(today);
            operationService.insertOperation(user,"添加三元组",Constant.OPERATION_THREE);
            return 0;
        }
    }

    @Override
    public List<Threecup> tiquThreecup(String parResult) {
        List<Threecup> threecupList = new LinkedList<>();
        String[] split = parResult.split("\n");
        parResult=split[0];
        String[] s = parResult.split(" ");
        Threecup threecup = new Threecup();
        threecup.setSubject("仅展示");
        threecup.setPredicate("效果");
        threecup.setObject("而已");
        //撒谎都没做
        threecupList.add(threecup);
        return threecupList;
    }
}
