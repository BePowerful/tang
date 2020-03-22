package com.wcq.tang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.ParticipleUtils;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.dto.CleanDto;
import com.wcq.tang.dto.TableCleanDto;
import com.wcq.tang.mapper.CorpusMapper;
import com.wcq.tang.mapper.OriginalMapper;
import com.wcq.tang.model.*;
import com.wcq.tang.service.CleanService;
import com.wcq.tang.service.CollectService;
import com.wcq.tang.service.MyOpService;
import com.wcq.tang.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/15 14:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CleanServiceImpl implements CleanService {
    private final static Logger logger = LoggerFactory.getLogger(CleanServiceImpl.class);
    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private MyOpService myOpService;
    @Autowired
    private CorpusMapper corpusMapper;
    @Autowired
    private CollectService collectService;
    @Autowired
    private OperationService operationService;
    @Value("${filePath}")
    private String FILE_PATH;
    @Override
    public Page<Original> getWillCleanDate(User user,Integer page,Integer limit) {
        Page<Original> resultPage = PageHelper.startPage(page, limit);
        OriginalExample example = new OriginalExample();
        OriginalExample.Criteria criteria = example.createCriteria();
        //还必须是已经转换未txt的
        criteria.andFormatedEqualTo(true);
        //本用户自己的语料
        criteria.andUploaderEqualTo(user.getUserId());
        criteria.andCleanedEqualTo(false);
        originalMapper.selectByExample(example);
        return resultPage;
    }
    @Override
    public List<TableCleanDto> originalToTableCleanDto(List<Original> originalList) {
        List<TableCleanDto> tableCleanDtoList = new LinkedList<>();
        if(originalList.size()>0 && originalList.get(0)!=null){
            for (Original original:originalList
            ) {
                TableCleanDto dto =new TableCleanDto(original.getOriginalId(),original.getTitle(),original.getTags());
                tableCleanDtoList.add(dto);
            }
        }
        return tableCleanDtoList;
    }



    @Override
    public CleanDto yuLan(Long originalId) {
        Original original = originalMapper.selectByPrimaryKey(originalId);
        CleanDto cleanDto = new CleanDto();
        cleanDto.setCleanId(originalId);
        cleanDto.setCleanContent(Utils.readTxtFile(original.getTxtPath()));
        if(original.getCleaned()){
            cleanDto.setCleanResult(Utils.readTxtFile(original.getCleanPath()));
        }else{
            cleanDto.setCleanResult("");
        }
        return cleanDto;
    }

    @Override
    public synchronized CleanDto doClean(CleanDto cleanDto) {
        String cleanResult = Utils.cleanFunction(cleanDto);
        if (cleanResult != null) {//清洗成功，设置语料清洗标志位
            if (cleanDto.getSelect().contains("3")) {//需要繁体转简体就转一下
                cleanResult = ParticipleUtils.ftoJ(cleanResult);
            }
            if(cleanDto.getSelect().contains("1")){
                //分句
                cleanResult = Utils.getSentence(cleanResult);
            }
        } else{
            cleanResult="清洗失败";
        }
        cleanDto.setCleanResult(cleanResult);
        return cleanDto;
    }

    @Override
    public synchronized CleanDto saveCleanResult(CleanDto cleanDto,User user) {
        String resultContent = cleanDto.getCleanResult();
        String cleanContent = cleanDto.getCleanContent();
        Long originalId = cleanDto.getCleanId();
        Original original ;
        Todayop todayop = new Todayop();
        if(originalId == 0){//输入的，先存起来，然后获取original对象
            int length = resultContent.length();
            String title;
            if(length>10){
                title = resultContent.substring(0, 10);
            }else{
                title = resultContent;
            }
            original = collectService.collect(title, "NULL", "自撰", cleanContent, user);
            originalId = original.getOriginalId();
            cleanDto.setCleanId(originalId);
        }else {//否则直接查询获取original对象
            original = originalMapper.selectByPrimaryKey(originalId);
        }
        if(original.getCleanPath() == null ||original.getCleanPath().equals("")){//没有保存过清洗结果
            //存放original的清洗结果
            File filePath = new File(FILE_PATH);
            if (!filePath.exists())//如果不存在就创建
                filePath.mkdirs();
            String fileName = UUID.randomUUID().toString() + ".txt";
            String cleanedPath = filePath + "/" + fileName;
            if (Utils.writerTxtFile(cleanedPath, resultContent)) {
                //设置清洗后的语料存储位置
                original.setCleanPath(cleanedPath);
                //设置语料已经清洗过了
                original.setCleaned(true);
                //更新原始语料
                originalMapper.updateByPrimaryKeySelective(original);
                //创建刚刚清洗的对象
                todayop.setId(originalId);
                todayop.setOpTime(new Date());
                todayop.setOpUser(user.getUserId());
                todayop.setCount(original.getTitle());
                todayop.setOpKind(Constant.OP_KIND_CLEAN);
                myOpService.insertTodayOp(todayop);
                int i = originalToCorpus(resultContent, originalId, user);
                if(i!=1){
                    logger.error("****将原始语料分句存入corpus中失败");
                }
            } else {
                logger.error("*****保存清洗结果出错！");
            }
        }else{//更新
            //原始语料cleanpath内容更新
            if (Utils.writerTxtFile(original.getCleanPath(), resultContent)){
                originalMapper.updateByPrimaryKeySelective(original);
            }else{
                logger.error("*****更新清洗结果出错！");
            }
            //先把之前由这个原始语料分句得到的corpus删除，在插入
            CorpusExample example = new CorpusExample();
            CorpusExample.Criteria criteria = example.createCriteria();
            criteria.andOriginalIdEqualTo(originalId);
            corpusMapper.deleteByExample(example);
            int i = originalToCorpus(resultContent, originalId, user);
            if(i!=1){
                logger.error("****将原始语料分句存入corpus中失败");
            }
        }
        operationService.insertOperation(user,"清洗了一条语料",Constant.OPERATION_CLEAN);
        return cleanDto;
    }
    private int originalToCorpus(String resultContent,Long originalId,User user){
        String[] split = resultContent.split("\n");
        int count = 0;
        for(String str:split){
            Corpus corpus = new Corpus();
            corpus.setOriginalId(originalId);
            corpus.setUploader(user.getUserId());
            corpus.setCorpusDate(new Date());
            corpus.setOriginalContent(str);
            corpus.setParticipled(false);
            int i = corpusMapper.insertSelective(corpus);
            count +=i;
        }
        if(count == split.length){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public synchronized void deleteClean(Long originalId) {
        int i = originalMapper.deleteByPrimaryKey(originalId);
        myOpService.deleteTodayOp(originalId,Constant.OP_KIND_CLEAN);
        CorpusExample example = new CorpusExample();
        CorpusExample.Criteria criteria = example.createCriteria();
        criteria.andOriginalIdEqualTo(originalId);
        corpusMapper.deleteByExample(example);
    }

    @Override
    public Page<Todayop> getNowCleanData(User user, Integer opkind,Integer page,Integer limit) {
        return myOpService.getTodayOp(user,opkind,page,limit);
    }

    @Override
    public Todayop[] MoPao(List<Todayop> todayopList) {
        return myOpService.MoPao(todayopList);
    }
}
