package com.wcq.tang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.CorpusDto;
import com.wcq.tang.dto.SeaOriDto;
import com.wcq.tang.dto.SearchCorpusDto;
import com.wcq.tang.model.Corpus;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Threecup;
import com.wcq.tang.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:24
 */
@RestController
public class SearchController {
    private final static Logger logger = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    private SearchService searchService;
    /*
    * 游客查询即将废除
    * */
    @GetMapping("/see")
    public ModelAndView toSee(){
        ModelAndView modelAndView = new ModelAndView();
        List<CorpusDto> corpusDTOS = new ArrayList<>();
        modelAndView.addObject("searchResult",corpusDTOS);
        modelAndView.addObject("searchHow","Original");
        modelAndView.setViewName("common/see");
        return modelAndView;
    }
    /**
     * 查找方法
     * @param content
     * @param kind
     * @return
     */
    @PostMapping("/searchCorpus")
    public ModelAndView searchCorpus(
            @RequestParam(name="searchContent")String content,
            @RequestParam(name = "searchKind")Integer kind){
        ModelAndView modelAndView = new ModelAndView();
        List<CorpusDto> corpusDTOS = new ArrayList<>();
        if(kind == 1){
            corpusDTOS = searchService.searchCorpus(content);
            modelAndView.addObject("searchHow","Corpus");
        }else if(kind==2){
            corpusDTOS = searchService.searchOriginal(content);
            modelAndView.addObject("searchHow","Original");
        }else{
            modelAndView.setViewName("common/threecup");
            return modelAndView;
        }
        modelAndView.addObject("searchResult",corpusDTOS);
        modelAndView.setViewName("common/see");
        return modelAndView;
    }
    //以上方法可废除

    /**
     * 细语料预览
     * @param corpusId
     * @return
     */
    @GetMapping("/preview/corpus/{id}")
    public ModelAndView perviewCorpus(@PathVariable("id") Long corpusId){
        CorpusDto dto = searchService.previewCorpus(corpusId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto",dto);
        modelAndView.setViewName("common/preview");
        return modelAndView;
    }

    /**
     * 粗语料预览
     * @param originalId
     * @return
     */
    @GetMapping("/preview/original/{id}")
    public ModelAndView perviewOriginal(@PathVariable("id") Long originalId){
        CorpusDto dto = searchService.previewOriginal(originalId);
        String content = searchService.getContent(dto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto",dto);
        modelAndView.addObject("content",content);
        modelAndView.setViewName("common/preview");
        return modelAndView;
    }

    /**
     * 粗语料下载
     * @param originalId
     * @param response
     */
    @GetMapping("/preview/download/{id}")
    public void downloadOriginal(@PathVariable(name = "id") Long originalId, HttpServletResponse response){
        searchService.downloadFile(response,originalId);
    }

    /**
     * 预览界面删除粗语料
     * @param originalId
     * @return
     */
    @GetMapping("/preview/del/original")
    public JsonResult delOriginal(@RequestParam Long originalId){
        int i = searchService.deleteOriginalById(originalId);
        JsonResult jsonResult = new JsonResult(Constant.SUCCESS,"删除成功！");
        if(i!=1){
            jsonResult.setCode(Constant.FAIL);
            jsonResult.setMsg("删除失败");
        }
        return jsonResult;
    }

    /**
     * 预览界面删除细语料
     * @param corpusId
     * @return
     */
    @GetMapping("/preview/del/corpus")
    public JsonResult delCorpus(@RequestParam Long corpusId){
        int i = searchService.deleteCorpusById(corpusId);
        JsonResult jsonResult = new JsonResult(Constant.SUCCESS,"删除成功！");
        if(i!=1){
            jsonResult.setCode(Constant.FAIL);
            jsonResult.setMsg("删除失败");
        }
        return jsonResult;
    }
    /**
     * 前往查询主页
     * @return
     */
    @GetMapping("/search")
    public ModelAndView toSearch(){return new ModelAndView("common/search");}

    /**
     * 查询细语料
     * @param page
     * @param limit
     * @param param
     * @param model
     * @return
     */
    @GetMapping("/search/corpus")
    public JsonResult searchCorpus(@RequestParam("page") int page,
                                   @RequestParam("limit") int limit,
                                   @RequestParam(name = "param") String param,
                                   @RequestParam(name = "model",required = false) Integer model){
        JsonResult result = new JsonResult();
        if(param.equals("default")){
            List<SearchCorpusDto> dtoList = searchService.getDefaultSearchCorpusDto();
            result.setCount(1L);
            result.setData(dtoList);
            return result;
        }else{
            Page<Corpus> resultPage = PageHelper.startPage(page, limit);
            List<SearchCorpusDto> dtoList=searchService.searchCorpusByParam(param,model);
            result.setCount(resultPage.getTotal());
            result.setData(dtoList);
            return result;
        }
    }

    /**
     * 细语料单个详情
     * @param corpusId
     * @return
     */
    @GetMapping("/search/corpus/one")
    public JsonResult searchCorpusById(@RequestParam("corpusId") Long corpusId){
        SearchCorpusDto dto = searchService.searchCorpusById(corpusId);
        return new JsonResult(dto);
    }

    /**
     * 查询三元组
     * @param page
     * @param limit
     * @param param
     * @param condition
     * @return
     */
    @GetMapping("/search/three")
    public JsonResult searchThree(@RequestParam("page") int page,
                                   @RequestParam("limit") int limit,
                                   @RequestParam(name = "param") String param,
                                   @RequestParam(name = "condition",required = false) Integer condition){
        JsonResult result = new JsonResult();
        if(param.equals("default")){
            List<Threecup> threecupList = searchService.getDefaultSearchThree();
            result.setCount(1L);
            result.setData(threecupList);
            return result;
        }else {
            Page<Threecup> resultPage = PageHelper.startPage(page, limit);
            List<Threecup> threecupList = searchService.searchThree(param,condition);
            result.setCount(resultPage.getTotal());
            result.setData(threecupList);
            return result;
        }
    }

    /**
     * 查询粗语料
     * @param page
     * @param limit
     * @param param
     * @param condition
     * @return
     */
    @GetMapping("/search/original")
    public JsonResult searchOriginal(@RequestParam("page") int page,
                                  @RequestParam("limit") int limit,
                                  @RequestParam(name = "param") String param,
                                  @RequestParam(name = "condition",required = false) Integer condition){
        JsonResult result = new JsonResult();
        if(param.equals("default")){
            List<SeaOriDto> defaultSearchOriginal = searchService.getDefaultSearchOriginal();
            result.setCount(1L);
            result.setData(defaultSearchOriginal);
            return result;
        }else {
            Page<Original> resultPage = PageHelper.startPage(page, limit);
            List<SeaOriDto> seaOriDtoList = searchService.searchOriginalByParam(param,condition);
            result.setCount(resultPage.getTotal());
            result.setData(seaOriDtoList);
            return result;
        }
    }

    /**
     * 查询界面粗语料预览
     * @param originalId
     * @return
     */
    @GetMapping("/search/original/yulan")
    public JsonResult originalYulan(@RequestParam("originalId") Long originalId){
        String con = searchService.originalYulan(originalId);
        return new JsonResult(con);
    }

    /**
     * 前往浏览界面
     * @return
     */
    @GetMapping("/func/lulan")
    public ModelAndView toLulan(){return new ModelAndView("common/lulan");}

    /**
     * 获取全部细语料
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/func/search/corpus/all")
    public JsonResult searchAllCorpus(@RequestParam("page") int page,
                                      @RequestParam("limit") int limit){
        JsonResult result = new JsonResult();
        Page<Corpus> resultPage = searchService.getAllCorpus(page,limit);
        result.setCount(resultPage.getTotal());
        result.setData(resultPage.getResult());
        return result;
    }

    /**
     * 获取全部三元组
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/func/search/three/all")
    public JsonResult searchAllThreecups(@RequestParam("page") int page,
                                      @RequestParam("limit") int limit){
        JsonResult result = new JsonResult();
        Page<Threecup> resultPage = searchService.getAllThree(page,limit);
        result.setCount(resultPage.getTotal());
        result.setData(resultPage.getResult());
        return result;
    }

    /**
     * 获取全部粗语料
     * @param page
     * @param limit
     * @param format
     * @return
     */
    @GetMapping("/func/search/original/all")
    public JsonResult searchAllThreecups(@RequestParam("page") int page,
                                         @RequestParam("limit") int limit,
                                         @RequestParam(value = "format",defaultValue = "all") String format){
        JsonResult result = new JsonResult();
        Page<Original> originalPage = searchService.getAllOriginal(page,limit,format);
        result.setCount(originalPage.getTotal());
        List<Original> result1 = originalPage.getResult();
        List<SeaOriDto> seaOriDtoList = searchService.originalToSeaOriDto(result1,"");
        result.setData(seaOriDtoList);
        return result;
    }
}
