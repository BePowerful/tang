package com.wcq.tang.service.impl;

import com.github.pagehelper.Page;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.FormatConversion;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.dto.TodayCollectDto;
import com.wcq.tang.mapper.OriginalMapper;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Todayop;
import com.wcq.tang.model.User;
import com.wcq.tang.service.CollectService;
import com.wcq.tang.service.MyOpService;
import com.wcq.tang.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:17
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CollectServiceImpl implements CollectService {
    private final static Logger logger = LoggerFactory.getLogger(CollectServiceImpl.class);
    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private OperationService operationService;
    @Autowired
    private MyOpService myOpService;
    @Value("${filePath}")
    private String FILE_PATH;
    @Override
    public Original upload(String title, String tags, String source, MultipartFile file, User user) throws IOException {
        Original original = new Original();
        original.setUploader(user.getUserId());
        original.setTitle(title);
        original.setTags(tags);
        original.setOriginalDate(new Date());
        original.setSource(source);
        File filePath = new File(FILE_PATH);
        if (!filePath.exists())//如果不存在就创建
            filePath.mkdirs();
        //1.得到文件后缀(扩展名.txt/.pdf...)
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //得到文件类型
        String originalFormat = fileSuffix.substring(1);
        //2.生成一个唯一的名字
        String fileName = UUID.randomUUID().toString() + fileSuffix;
        //3.封装一下文件
        //文件存储路劲
        String path = filePath +"/"+ fileName;
        File aimFile = new File(path);
        //4.上传
        file.transferTo(aimFile);
        original.setFormat(originalFormat);
        original.setCleaned(false);
        original.setAddress(path);
        //格式转换
        if(originalFormat.equals("txt")){
            original.setFormated(true);
            original.setTxtPath(path);
        }else{
            String txtPath = null;
            switch (originalFormat){
                case Constant.DOC:
                case Constant.DOCX: {
                    //返回的txt文件路径
                    txtPath = FormatConversion.docOrDocxToTxt(original.getAddress());
                    if(txtPath!=null){
                        original.setTxtPath(txtPath);
                        original.setFormated(true);
                    }else{
                        logger.error("用户{}的语料{}格式是{}，格式转换失败！{}",user.getUserName(),title,originalFormat,new Date());
                    }
                    break;
                }
                case Constant.PDF: {
                    txtPath = FormatConversion.pdfToTxt(original.getAddress());
                    if(txtPath!=null){
                        original.setTxtPath(txtPath);
                        original.setFormated(true);
                    }else{
                        logger.error("用户{}的语料{}格式是{}，格式转换失败！{}",user.getUserName(),title,originalFormat,new Date());
                    }
                    break;
                }
                case Constant.XLS:
                case Constant.XLSX: {
                    txtPath = FormatConversion.xlsOrXlsxToTxt(original.getAddress());
                    if(txtPath!=null){
                        original.setTxtPath(txtPath);
                        original.setFormated(true);
                    }else{
                        logger.error("用户{}的语料{}格式是{}，格式转换失败！{}",user.getUserName(),title,originalFormat,new Date());
                    }
                    break;
                }
                default: {
                    original.setFormated(false);
                    logger.error("用户{}的语料{}格式是{}，格式转换失败！{}",user.getUserName(),title,originalFormat,new Date());
                    break;
                }
            }
        }
        //各种属性设置完成，保存到数据库
        int i = originalMapper.insertSelective(original);
        //设置今天上传的语料
        originalToTodayOp(original,"成功");
        operationService.insertOperation(user,"上传一条语料",Constant.OPERATION_UPLOAD);
       return original;
    }
    /**
     * 语料收录接口
     * @param tags
     * @param source
     * @param content
     * @return
     */
    @Override
    public Original collect(String title,String tags, String source, String content,User user) {
        //创建原始语料模型
        Original original = new Original();
        //语料上传者
        original.setUploader(user.getUserId());
        //设置语料标题
        original.setTitle(title);
        //设置语料标签
        original.setTags(tags);
        //设置上传日期
        original.setOriginalDate(new Date());
        //设置来源
        original.setSource(source);
        //设置语料格式
        original.setFormat(Constant.TXT);
        File filePath = new File(FILE_PATH);
        if (!filePath.exists())//如果不存在就创建
            filePath.mkdirs();
        String fileName = UUID.randomUUID().toString() + ".txt";
        //3.封装一下文件
        //文件存储路劲
        String path = filePath +"/"+ fileName;
        //设置存储路径
        original.setAddress(path);
        //设置未清洗标志
        original.setCleaned(false);
        //设置格式转换
        original.setFormated(true);
        //设置txt文件路径
        original.setTxtPath(path);
        //写入本地磁盘
        boolean b = Utils.writerTxtFile(path, content);
        if(b){
            int i = originalMapper.insertSelective(original);
            //设置今天上传的语料
            originalToTodayOp(original,"成功");
        }else{
            original.setOriginalId(-1L);
            originalToTodayOp(original,"失败");
        }
        operationService.insertOperation(user,"收录一条语料",Constant.OPERATION_UPLOAD);
        return original;
    }

    @Override
    public Page<Todayop> getTodayOp(User user,Integer page,Integer limit) {
        Page<Todayop> todayOp = myOpService.getTodayOp(user, Constant.OP_KIND_COL,page,limit);
        return todayOp;
    }

    @Override
    public TodayCollectDto[] getTodayCollectDto(Todayop[] todayopList) {

        if(todayopList!=null){
            TodayCollectDto[] dtos = new TodayCollectDto[todayopList.length];
            for(int i=0;i<todayopList.length;i++){
                Todayop todayop = todayopList[i];
                TodayCollectDto dto = new TodayCollectDto();
                dto.setOriginalId(todayop.getId());
                String count = todayop.getCount();
                String[] split = count.split("/");
                dto.setTitle(split[0]);
                dto.setTags(split[1]);
                dto.setSource(split[2]);
                dto.setFormat(split[3]);
                dto.setDate(split[4]);
                dto.setState(split[5]);
                dtos[i] = dto;
            }
            return dtos;
        }else{
            return new TodayCollectDto[0];
        }
    }

    @Override
    public Todayop[] MaoPao(List<Todayop> todayopList) {
        Todayop[] todayops = myOpService.MoPao(todayopList);
        return todayops;
    }

    @Override
    public synchronized void deleteOriginal(Long originalId) {
        //删除实际表
        originalMapper.deleteByPrimaryKey(originalId);
        //删除暂存表
        myOpService.deleteTodayOp(originalId, Constant.OP_KIND_COL);
    }

    @Override
    public synchronized void updateOriginal(TodayCollectDto dto,User user) {
        //更新实际
        Original original = new Original();
        original.setOriginalId(dto.getOriginalId());
        original.setTitle(dto.getTitle());
        original.setTags(dto.getTags());
        original.setSource(dto.getSource());
        originalMapper.updateByPrimaryKeySelective(original);
        //更新暂存
        Todayop todayop = new Todayop();
        String count = dto.getTitle()+"/"+dto.getTags()+"/"+dto.getSource()+"/"+dto.getFormat()+"/"+dto.getDate()+"/"+dto.getState();
        todayop.setId(dto.getOriginalId());
        todayop.setOpKind(Constant.OP_KIND_COL);
        todayop.setOpUser(user.getUserId());
        todayop.setCount(count);
        myOpService.updateTodayNoOpId(todayop);
    }

    private Todayop originalToTodayOp(Original original, String state){
        Todayop todayop = new Todayop();
        todayop.setOpTime(new Date());
        todayop.setOpUser(original.getUploader());
        todayop.setOpKind(Constant.OP_KIND_COL);
        todayop.setId(original.getOriginalId());
        Date originalDate = original.getOriginalDate();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        String date=sdf.format(originalDate);
        String count = original.getTitle()+"/"+original.getTags()+"/"+original.getSource()+"/"+original.getFormat()+"/"+date+"/"+state;
        todayop.setCount(count);
        myOpService.insertTodayOp(todayop);
        return todayop;
    }
}
