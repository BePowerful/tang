package com.wcq.tang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.bean.ParticipleUtils;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.dto.CorpusDto;
import com.wcq.tang.dto.SeaOriDto;
import com.wcq.tang.dto.SearchCorpusDto;
import com.wcq.tang.mapper.CorpusMapper;
import com.wcq.tang.mapper.OriginalMapper;
import com.wcq.tang.mapper.ThreecupMapper;
import com.wcq.tang.mapper.UserMapper;
import com.wcq.tang.model.*;
import com.wcq.tang.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:18
 */
@Service
public class SearchServiceImpl implements SearchService {
    private final static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Autowired
    private CorpusMapper corpusMapper;
    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ThreecupMapper threecupMapper;
    /**
     * 查询细语料
     * @param content
     * @return
     */
    public synchronized List<CorpusDto> searchCorpus(String content) {
        String parameter = "%" + content + "%";
        List<CorpusDto> corpusDtoList = new ArrayList<>();
        CorpusExample example = new CorpusExample();
        CorpusExample.Criteria criteria = example.createCriteria();
        criteria.andOriginalContentLike(parameter);
        criteria.andCixinContentIsNotNull();
        List<Corpus> corpuses = corpusMapper.selectByExample(example);
        //
        ParticipleUtils participleUtils = new ParticipleUtils();
        String standard = participleUtils.standard(content);
        String s = participleUtils.standardResult(standard);
        System.out.println(s);
        String[] s1 = s.split(" ");
        for(String s2 : s1){
            CorpusExample corpusExample = new CorpusExample();
            CorpusExample.Criteria criteria1 = corpusExample.createCriteria();
            criteria1.andOriginalContentLike("%"+s2+"%");
            criteria1.andCixinContentIsNotNull();
            List<Corpus> corpusList = corpusMapper.selectByExample(corpusExample);
            if(corpusList.size()>0 && corpusList.get(0)!=null){
                for(int j =0;j<corpusList.size();j++){
                    Corpus corpus = corpusList.get(j);
                    int i;
                    for(i=0;i<corpuses.size();i++){
                        if(corpuses.get(i).getCorpusId() == corpus.getCorpusId()){
                            break;
                        }
                    }
                    if(i>=corpuses.size()){
                        corpuses.add(corpus);
                    }
                }
            }
        }
        //
        String res,tagRes;
        if (corpuses.size() > 0 && corpuses.get(0) != null) {
            for (Corpus corpus : corpuses) {
                CorpusDto dto = new CorpusDto();
                //获取分词结果
                res = corpus.getCixinContent();
                tagRes = "<span style=\"color:red\">"+content+"</span>";
                res = res.replace(content,tagRes);
                corpus.setCixinContent(res);
                //获取原文
                res = corpus.getOriginalContent();
                tagRes = "<span style=\"color:red\">"+content+"</span>";
                res = res.replace(content,tagRes);
                corpus.setOriginalContent(res);
                if(corpus.getOriginalId() !=null){//不空设置原始语料相关信息
                    Original original = originalMapper.selectByPrimaryKey(corpus.getOriginalId());
                    BeanUtils.copyProperties(original,dto);
                }else{//空
                    dto.setOriginalId(0l);
                    dto.setTags("作者自传语料");
                }
                dto.setCorpusId(corpus.getCorpusId());
                dto.setUser(userMapper.selectByPrimaryKey(corpus.getUploader()));
                dto.setCorpusDate(corpus.getCorpusDate());
                dto.setCixinContent(corpus.getCixinContent());
                dto.setOriginalContent(corpus.getOriginalContent());
                corpusDtoList.add(dto);
            }
        }
        return corpusDtoList;
    }

    /**
     * 查询原始语料
     * @param content
     * @return
     */
    public synchronized List<CorpusDto> searchOriginal(String content){
        String parameter = "%" + content + "%";
        List<CorpusDto> corpusDtoList = new ArrayList<>();
        OriginalExample example = new OriginalExample();
        OriginalExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike(parameter);
        OriginalExample.Criteria criteria2 = example.createCriteria();
        criteria2.andTagsLike(parameter);
        example.or(criteria2);
        List<Original> originals = originalMapper.selectByExample(example);
        //
        ParticipleUtils participleUtils = new ParticipleUtils();
        String standard = participleUtils.standard(content);
        String s = participleUtils.standardResult(standard);
        String[] s1 = s.split(" ");
        for(String s2 : s1){
            OriginalExample originalExample = new OriginalExample();
            OriginalExample.Criteria criteria1 = originalExample.createCriteria();
            criteria1.andTitleLike("%"+s2+"%");
            OriginalExample.Criteria criteria3 = originalExample.createCriteria();
            criteria3.andTagsLike("%"+s2+"%");
            originalExample.or(criteria3);
            List<Original> originalList = originalMapper.selectByExample(originalExample);
            if(originalList.size()>0 && originalList.get(0)!=null){
                for(int j =0;j<originalList.size();j++){
                    Original original = originalList.get(j);
                    int i;
                    for(i=0;i<originals.size();i++){
                        if(originals.get(i).getOriginalId() == original.getOriginalId()){
                            break;
                        }
                    }
                    if(i>=originals.size()){
                        originalList.add(original);
                    }
                }
            }
        }
        //
        if(originals.size()>0 && originals.get(0)!=null){
            for(Original original:originals){
                CorpusDto dto = new CorpusDto();
                BeanUtils.copyProperties(original,dto);
                dto.setUser(userMapper.selectByPrimaryKey(dto.getUploader()));
                String res = dto.getTitle();
                if(res.contains(content)){
                    res=res.replace(content,"<span style=\"color:red\">"+content+"</span>");
                    dto.setTitle(res);
                }
                String ress = dto.getTags();
                if(ress.contains(content)){
                    ress=ress.replace(content,"<span style=\"color:red\">"+content+"</span>");
                    dto.setTags(ress);
                }
                corpusDtoList.add(dto);
            }
        }
        return corpusDtoList;
    }

    @Override
    public CorpusDto previewCorpus(Long corpusId) {
        CorpusDto corpusDto = new CorpusDto();
        Corpus corpus = corpusMapper.selectByPrimaryKey(corpusId);
        if(corpus.getOriginalId()!=null){
            Original original = originalMapper.selectByPrimaryKey(corpus.getOriginalId());
            BeanUtils.copyProperties(original,corpusDto);
        }else{
            corpusDto.setOriginalId(0L);
            corpusDto.setCorpusDate(corpus.getCorpusDate());
        }
        corpusDto.setCorpusId(corpusId);
        corpusDto.setUser(userMapper.selectByPrimaryKey(corpus.getUploader()));
        corpusDto.setCixinContent(corpus.getCixinContent()+"<br>"+"<hr class=\"layui-bg-blue\" >");
        corpusDto.setOriginalContent(corpus.getOriginalContent());
        return corpusDto;
    }

    @Override
    public CorpusDto previewOriginal(Long originalId) {
        CorpusDto corpusDto = new CorpusDto();
        Original original = originalMapper.selectByPrimaryKey(originalId);
        BeanUtils.copyProperties(original,corpusDto);
        corpusDto.setUser(userMapper.selectByPrimaryKey(original.getUploader()));
        return corpusDto;
    }

    @Override
    public String getContent(CorpusDto dto) {

        String path;
        if(dto.getCleaned()){
            path = dto.getCleanPath();
        }else{
            path = dto.getTxtPath();
        }
        String s = Utils.readTxtFile(path);
        return Utils.makeStringToHTML(s);
    }

    void download(HttpServletResponse response,String url,String title){
        File file = new File(url);
        if (file.exists()) {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            //response.setContentType("application/force-download");// 设置强制下载不打开
            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(title, "UTF-8"));
                //response.setHeader("Content-Disposition", "attachment;fileName=" + new String(title.getBytes("GB2312"), "ISO-8859-1"));
                byte[] buffer = new byte[1024];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void downloadFile(HttpServletResponse response, Long originalId) {
        String url = null;
        String title = null;
        Original original = originalMapper.selectByPrimaryKey(originalId);
        url = original.getAddress();
        title = original.getTitle()+"."+original.getFormat();
        download(response,url,title);
        logger.info("******原始语料下载完毕");
        url = original.getCleanPath();
        title = original.getTitle()+"(清洗过)"+".txt";
        if(url!=null){
            download(response,url,title);
            logger.info("******清洗语料下载完毕");
        }
    }
    @Override
    public List<SearchCorpusDto> getDefaultSearchCorpusDto() {
        Corpus corpus = corpusMapper.selectByPrimaryKey(1L);
        List<Corpus> corpusList = new LinkedList<>();
        corpusList.add(corpus);
        List<SearchCorpusDto> dtoList = corpusToSearchCorpusDto(corpusList);
        SearchCorpusDto dto = dtoList.get(0);
        String html = "<a class='layui-table-link' href='/preview/corpus/"+dto.getCorpusId()+"'target='_blank'>"+corpus.getOriginalContent()+"</a>";
        dto.setOriginalContent(html);
        dtoList.remove(0);
        dtoList.add(dto);
        return dtoList;
    }
    private String toHtml(String content,String param,Long id,Boolean isCorpus,Boolean isZhengCi){
        String html="";
        if(isZhengCi && content.contains(param)){
            content = content.replace(param,"<span style = 'color: red;'>"+param+"</span>");
        }
        if(isCorpus){
            html = "<a class='layui-table-link' href='/preview/corpus/"+id+"'target='_blank'>"+content+"</a>";
        }else{
            html = "<a class='layui-table-link' href='/preview/original/"+id+"'target='_blank'>"+content+"</a>";
        }
        return html;
    }
    @Override
    public List<SearchCorpusDto> searchCorpusByParam(String param,Integer model) {
        List<SearchCorpusDto> dtoList ;
        if(model == 1){//整此匹配
            CorpusExample corpusExample = new CorpusExample();
            CorpusExample.Criteria criteria = corpusExample.createCriteria();
            criteria.andOriginalContentLike("%"+param+"%");
            criteria.andCixinContentIsNotNull();
            List<Corpus> corpusList = corpusMapper.selectByExample(corpusExample);
            dtoList = corpusToSearchCorpusDto(corpusList);
            for (SearchCorpusDto dto:dtoList
                 ) {
                dto.setOriginalContent(toHtml(dto.getOriginalContent(),param,dto.getCorpusId(),true,true));
            }
            return dtoList;
        }else{
            ParticipleUtils participleUtils = new ParticipleUtils();
            String standard = participleUtils.standard(param);
            String s = participleUtils.standardResult(standard);
            String[] s1 = s.split(" ");
            List<Corpus> corpuses = null;
            for(String s2 : s1){
                CorpusExample corpusExample = new CorpusExample();
                CorpusExample.Criteria criteria1 = corpusExample.createCriteria();
                criteria1.andOriginalContentLike("%"+s2+"%");
                criteria1.andCixinContentIsNotNull();
                List<Corpus> corpusList = corpusMapper.selectByExample(corpusExample);
                if(corpusList.size()>0 && corpusList.get(0)!=null){
                    if(corpuses == null){
                        corpuses = corpusList;
                    }else{
                        for(int j =0;j<corpusList.size();j++){
                            Corpus corpus = corpusList.get(j);
                            int i;
                            for(i=0;i<corpuses.size();i++){
                                if(corpuses.get(i).getCorpusId() == corpus.getCorpusId()){
                                    break;
                                }
                            }
                            if(i>=corpuses.size()){
                                corpuses.add(corpus);
                            }
                        }
                    }
                }
            }
            dtoList=corpusToSearchCorpusDto(corpuses);
            for (SearchCorpusDto dto:dtoList
            ) {
                dto.setOriginalContent(toHtml(dto.getOriginalContent(),param,dto.getCorpusId(),true,false));
            }
            return dtoList;
        }
    }

    @Override
    public SearchCorpusDto searchCorpusById(Long corpusId) {
        List<Corpus> corpusList = new LinkedList<>();
        Corpus corpus = corpusMapper.selectByPrimaryKey(corpusId);
        corpusList.add(corpus);
        List<SearchCorpusDto> dtoList = corpusToSearchCorpusDto(corpusList);
        return dtoList.get(0);
    }

    private List<SearchCorpusDto> corpusToSearchCorpusDto(List<Corpus> corpusList){
        List<SearchCorpusDto> dtoList = new LinkedList<>();
        for(int i=0;i<corpusList.size();i++){
            SearchCorpusDto dto = new SearchCorpusDto();
            Corpus corpus = corpusList.get(i);
            User user = userMapper.selectByPrimaryKey(corpus.getUploader());
            dto.setUsername(user.getUserName());
            dto.setCorpusId(corpus.getCorpusId());
            dto.setCorpusDate(dateToString("yyyy-MM-dd",corpus.getCorpusDate()));
            dto.setOriginalContent(corpus.getOriginalContent());
            dto.setCixinContent(corpus.getCixinContent());
            if(corpus.getOriginalId()!=null){//有原始语料
                Original original = originalMapper.selectByPrimaryKey(corpus.getOriginalId());
                dto.setTags(original.getTags());
                dto.setSource(original.getSource());
            }else{
                dto.setTags("无");
                dto.setSource("作者自撰");
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<Threecup> getDefaultSearchThree() {
        List<Threecup> threecupList = new LinkedList<>();
        Threecup threecup = threecupMapper.selectByPrimaryKey(1L);
        threecupList.add(threecup);
        return threecupList;
    }

    @Override
    public List<Threecup> searchThree(String param, Integer condition) {
        List<Threecup> threecupList ;
        ThreecupExample example = new ThreecupExample();
        switch (condition){
            case 0:{//全部
                threecupList=threecupMapper.selectByExample(example);
                return threecupList;
            }
            case 1:{//主语
                ThreecupExample.Criteria criteria = example.createCriteria();
                criteria.andSubjectLike("%"+param+"%");
                threecupList=threecupMapper.selectByExample(example);
                return threecupList;
            }
            case 2:{//宾语
                ThreecupExample.Criteria criteria = example.createCriteria();
                criteria.andObjectLike("%"+param+"%");
                threecupList=threecupMapper.selectByExample(example);
                return threecupList;
            }
            default:
                return new LinkedList<>();
        }
    }

    @Override
    public List<SeaOriDto> getDefaultSearchOriginal() {
        Original original = originalMapper.selectByPrimaryKey(1L);
        List<Original> originalList = new LinkedList<>();
        originalList.add(original);
        List<SeaOriDto> seaOriDtoList = originalToSeaOriDto(originalList,original.getTitle());
        return  seaOriDtoList;
    }

    @Override
    public List<SeaOriDto> originalToSeaOriDto(List<Original> originalList,String param) {
        List<SeaOriDto> seaOriDtoList = new LinkedList<>();
        for (int i=0;i<originalList.size();i++){
            SeaOriDto dto = new SeaOriDto();
            Original original = originalList.get(i);
            User user = userMapper.selectByPrimaryKey(original.getUploader());
            dto.setUsername(user.getUserName());
            dto.setOriginalId(original.getOriginalId());
            dto.setTitle(toHtml(original.getTitle(),param,original.getOriginalId(),false,true));
            dto.setTags(original.getTags());
            if(dto.getTags().contains(param)){
                dto.setTags(original.getTags().replace(param,"<span style = 'color: red;'>"+param+"</span>"));
            }
            dto.setSource(original.getSource());
            dto.setFormat(original.getFormat());
            dto.setDate(dateToString("yyyy-MM-dd",original.getOriginalDate()));
            seaOriDtoList.add(dto);
        }
        return seaOriDtoList;
    }

    @Override
    public List<SeaOriDto> searchOriginalByParam(String param, Integer condition) {
        String con = "%"+param+"%";
        OriginalExample example = new OriginalExample();
        OriginalExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike(con);
        OriginalExample.Criteria criteria2 = example.createCriteria();
        criteria2.andTagsLike(con);
        example.or(criteria2);
        switch (condition){
            case 0:{//全部类型
                List<Original> originalList = originalMapper.selectByExample(example);
                return originalToSeaOriDto(originalList,param);
            }
            case 1:{
                //txt
                criteria.andFormatEqualTo("txt");
                criteria2.andFormatEqualTo("txt");
                List<Original> originalList = originalMapper.selectByExample(example);
                return originalToSeaOriDto(originalList,param);
            }
            case 2:{
                //pdf
                criteria2.andFormatEqualTo("pdf");
                criteria.andFormatEqualTo("pdf");
                List<Original> originalList = originalMapper.selectByExample(example);
                return originalToSeaOriDto(originalList,param);
            }
            case 3:{
                //xla/xlsx
                criteria.andFormatLike("xls%");
                criteria2.andFormatLike("xls%");
                List<Original> originalList = originalMapper.selectByExample(example);
                return originalToSeaOriDto(originalList,param);
            }
            case 4:{
                //doc
                criteria.andFormatEqualTo("doc%");
                criteria2.andFormatLike("doc%");
                List<Original> originalList = originalMapper.selectByExample(example);
                return originalToSeaOriDto(originalList,param);
            }
            default:{
                return new LinkedList<>();
            }
        }
    }

    @Override
    public String originalYulan(Long originalId) {
        Original original = originalMapper.selectByPrimaryKey(originalId);
        if(original.getCleanPath()!=null){
            String s = Utils.readTxtFile(original.getCleanPath());
            return Utils.makeStringToHTML(s);
        }else{
            String s = Utils.readTxtFile(original.getTxtPath());
            return Utils.makeStringToHTML(s);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized int deleteOriginalById(Long originalId) {
        CorpusExample example = new CorpusExample();
        CorpusExample.Criteria criteria = example.createCriteria();
        criteria.andOriginalIdEqualTo(originalId);
        corpusMapper.deleteByExample(example);
        return originalMapper.deleteByPrimaryKey(originalId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized int deleteCorpusById(Long corpusId) {
        return corpusMapper.deleteByPrimaryKey(corpusId);
    }

    @Override
    public Page<Corpus> getAllCorpus(Integer page, Integer limit) {
        Page<Corpus> corpus = PageHelper.startPage(page, limit);
        corpusMapper.selectByExample(new CorpusExample());
        return corpus;
    }

    @Override
    public Page<Threecup> getAllThree(int page, int limit) {
        Page<Threecup> three = PageHelper.startPage(page, limit);
        threecupMapper.selectByExample(new ThreecupExample());
        return three;
    }

    @Override
    public Page<Original> getAllOriginal(int page, int limit, String format) {
        Page<Original> originalPage = PageHelper.startPage(page, limit);
        OriginalExample example = new OriginalExample();
        OriginalExample.Criteria criteria = example.createCriteria();
        if(format.equals("all")){
            originalMapper.selectByExample(example);
        }else{
            criteria.andFormatLike(format+"%");
            originalMapper.selectByExample(example);
        }
        return originalPage;
    }

    private String dateToString(String pattern, Date date){
        return Utils.dateToString(pattern,date);
    }
}
