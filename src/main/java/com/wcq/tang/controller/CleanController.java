package com.wcq.tang.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.CleanDto;
import com.wcq.tang.dto.TableCleanDto;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Todayop;
import com.wcq.tang.model.User;
import com.wcq.tang.service.CleanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/15 14:09
 */
@RestController
@RequestMapping("/func")
public class CleanController {
    @Autowired
    private CleanService cleanService;
    @GetMapping("/clean")
    public ModelAndView toClean(){return new ModelAndView("common/clean");}
    @GetMapping("/clean/will/data")
    public JsonResult getWillCleanData(@RequestParam(name = "limit",defaultValue = "5") Integer limit,
                                       @RequestParam(name = "page",defaultValue = "1") Integer page,
                                       HttpServletRequest request){
        System.err.println("请求页："+page);
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Original> resultPage = cleanService.getWillCleanDate(user,page,limit);
        List<TableCleanDto> tableCleanDtoList = cleanService.originalToTableCleanDto(resultPage.getResult());
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCount(resultPage.getTotal());
        jsonResult.setData(tableCleanDtoList);
        return jsonResult;
    }
    @GetMapping("/clean/now/data/{opkind}")
    public JsonResult getNowCleanData(@RequestParam(name = "limit",defaultValue = "5") Integer limit,
                                       @RequestParam(name = "page",defaultValue = "1") Integer page,
                                       @PathVariable(name = "opkind") Integer opkind,
                                       HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Todayop> resultPage = cleanService.getNowCleanData(user,opkind,page, limit);
        JsonResult jsonResult = new JsonResult();
        if(resultPage.size()>0 && resultPage.get(0)!=null){
            Todayop[] todayops = cleanService.MoPao(resultPage.getResult());
            jsonResult.setCount(resultPage.getTotal());
            jsonResult.setData(todayops);
        }else{
            jsonResult.setCount(0l);
            jsonResult.setData(new Todayop[0]);
        }
        return jsonResult;
    }
    @GetMapping({"/clean/now/detail","/clean/now/edit","/clean/will/detail","/clean/will/daoru"})
    public JsonResult yuLan(@RequestParam(name = "originalId")Long originalId){
        if(originalId == null){
            return new JsonResult(Constant.FAIL,"没有指定预览语料");
        }else {
            CleanDto cleanDto = cleanService.yuLan(originalId);
            return new JsonResult(cleanDto);
        }
    }
    @PostMapping("/clean")
    public JsonResult clean(@RequestBody JSONObject object){
        CleanDto cleanDto = object.toJavaObject(CleanDto.class);
        CleanDto resultCleanDto = cleanService.doClean(cleanDto);
        return new JsonResult(resultCleanDto);
    }
    @PostMapping("/clean/save")
    public JsonResult saveCleanResult(@RequestBody JSONObject object,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        CleanDto cleanDto = object.toJavaObject(CleanDto.class);
        cleanDto = cleanService.saveCleanResult(cleanDto,user);
        return new JsonResult(cleanDto);
    }
    @DeleteMapping("/clean/now/del")
    public JsonResult deleteResult(@RequestParam("originalId")Long originalId){
        cleanService.deleteClean(originalId);
        return new JsonResult();
    }
}
