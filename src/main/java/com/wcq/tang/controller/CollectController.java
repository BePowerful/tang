package com.wcq.tang.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.TodayCollectDto;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Todayop;
import com.wcq.tang.model.User;
import com.wcq.tang.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:24
 */
@RestController
@RequestMapping("/func")
public class CollectController {
    @Autowired
    private CollectService collectService;
    @GetMapping("/collect")
    public ModelAndView toCollect1(){
        return new ModelAndView("common/collect");
    }
    /**
     * 语料上传
     * @param file
     * @param title
     * @param tags
     * @param source
     * @return
     */
    @PostMapping(value = "/upload")
    public synchronized JsonResult upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("select") String tags,
            @RequestParam("source") String source,
            HttpServletRequest request) throws IOException {
        if(title.isEmpty() || tags.isEmpty() || source.isEmpty()){
            return new JsonResult(Constant.FAIL,"请填写所有语料参数");
        } else{
            User user =(User) request.getSession().getAttribute(Constant.SESSION_USER);
            Original upload = collectService.upload(title, tags, source, file, user);
            if(upload.getOriginalId()>0){
                return new JsonResult();
            }else{
                return new JsonResult(Constant.FAIL,"上传失败！");
            }
        }
    }

    @PostMapping("/collect")
    public synchronized JsonResult collect(
            @RequestParam("title") String title,
            @RequestParam("select") String tags,
            @RequestParam("source") String source,
            @RequestParam("content") String content,
            HttpServletRequest request){
        if(title.isEmpty()||tags.isEmpty()||source.isEmpty()||content.isEmpty()){
            return new JsonResult(Constant.FAIL,"请填写所有语料参数");
        }else{
            User user =(User) request.getSession().getAttribute(Constant.SESSION_USER);
            Original upload = collectService.collect(title,tags,source,content,user);
            if(upload.getOriginalId()>0){
                return new JsonResult();
            }else{
                return new JsonResult(Constant.FAIL,"收录失败！");
            }
        }
    }
    @GetMapping("/collect/now/data")
    public JsonResult todayOp(@RequestParam(name = "limit",defaultValue = "5") Integer limit,
                              @RequestParam(name = "page",defaultValue = "1") Integer page,
                              HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Todayop> resultPage = collectService.getTodayOp(user,page, limit);
        Todayop[] todayops = collectService.MaoPao(resultPage.getResult());
        TodayCollectDto[] todayCollectDto = collectService.getTodayCollectDto(todayops);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCount(resultPage.getTotal());
        jsonResult.setData(todayCollectDto);
        return jsonResult;
    }
    @DeleteMapping("/collect/now/del")
    public  JsonResult deleteOriginal(@RequestParam("originalId") Long originalId){
        collectService.deleteOriginal(originalId);
        return new JsonResult();
    }
    @PutMapping("/collect/now/edit")
    public JsonResult updateOriginal(@RequestBody JSONObject object,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        TodayCollectDto dto = object.toJavaObject(TodayCollectDto.class);
        collectService.updateOriginal(dto,user);
        return new JsonResult();
    }
}
