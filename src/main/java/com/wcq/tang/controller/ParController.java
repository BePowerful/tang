package com.wcq.tang.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.TableParDto;
import com.wcq.tang.model.Corpus;
import com.wcq.tang.model.Threecup;
import com.wcq.tang.model.Todaythree;
import com.wcq.tang.model.User;
import com.wcq.tang.service.ParService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 11:57
 */
@RestController
@RequestMapping("/func")
public class ParController {
    @Autowired
    private ParService parService;

    /**
     * 前往分词页
     * @return
     */
    @GetMapping("/participle")
    public ModelAndView toParticiple(){return new ModelAndView("common/participle");}

    /**
     * 获取待分词语料
     * @param limit
     * @param page
     * @param request
     * @return
     */
    @GetMapping("/par/will/data")
    public JsonResult getWillParData(@RequestParam(name = "limit",defaultValue = "5") Integer limit,
                                     @RequestParam(name = "page",defaultValue = "1") Integer page,
                                     HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Corpus> corpusPage = parService.getWillParData(user,page, limit);
        List<TableParDto> tableParDtoList = parService.corpusToTableParDto(corpusPage.getResult());
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCount(corpusPage.getTotal());
        jsonResult.setData(tableParDtoList);
        return jsonResult;
    }

    /**
     *编辑今天分词的语料
     * @param corpusId
     * @return
     */
    @GetMapping("/par/now/edit")
    public JsonResult yuLan(@RequestParam(name = "corpusId")Long corpusId){
        if(corpusId == null){
            return new JsonResult(Constant.FAIL,"没有指定预览语料");
        }else {
            TableParDto tableParDto = parService.yuLan(corpusId);
            return new JsonResult(tableParDto);
        }
    }

    /**
     * 删除今天分词的语料
     * @param corpusId
     * @return
     */
    @DeleteMapping("/par/now/del")
    public JsonResult deleteResult(@RequestParam("corpusId")Long corpusId){
        parService.deletePar(corpusId);
        return new JsonResult();
    }

    /**
     * 今天提取的三元组
     * @param limit
     * @param page
     * @param request
     * @return
     */
    @GetMapping("/par/now/three")
    public JsonResult getNowThreeDate(@RequestParam(name = "limit",defaultValue = "5") Integer limit,
                                      @RequestParam(name = "page",defaultValue = "1") Integer page,
                                      HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Todaythree> resultPage = PageHelper.startPage(page, limit);
        List<Todaythree> todaythreeList = parService.getNowThreeData(user);
        JsonResult jsonResult = new JsonResult();
        if(todaythreeList.size()>0 && todaythreeList.get(0)!=null){
            Todaythree[] Todaythrees = parService.threeMoPao(todaythreeList);
            jsonResult.setCount(resultPage.getTotal());
            jsonResult.setData(Todaythrees);
        }else {
            jsonResult.setCount(0l);
            jsonResult.setData(new Todaythree[0]);
        }
        return jsonResult;
    }

    /**
     * 删除今天提取的三元组
     * @param threeId
     * @return
     */
    @DeleteMapping("/par/now/delThree")
    public JsonResult deleteThree(@RequestParam("threeId")Long threeId){
        parService.deleteThree(threeId);
        return new JsonResult();
    }

    /**
     * 更新今天提取的三元组
     * @param object
     * @param request
     * @return
     */
    @PutMapping("/par/now/upThree")
    public JsonResult updateThree(@RequestBody JSONObject object,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Threecup threecup = object.toJavaObject(Threecup.class);
        threecup.setUploader(user.getUserId());
        threecup.setBuildTime(new Date());
        parService.updateThree(threecup);
        return new JsonResult();
    }

    /**
     * 分词
     * @param object
     * @return
     */
    @PostMapping("/par")
    public JsonResult doPar(@RequestBody JSONObject object){
        TableParDto tableParDto = object.toJavaObject(TableParDto.class);
        tableParDto = parService.doPar(tableParDto);
        return new JsonResult(tableParDto);
    }

    /**
     * 保存分词
     * @param object
     * @param request
     * @return
     */
    @PostMapping("/par/save")
    public JsonResult saveResult(@RequestBody JSONObject object,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        TableParDto tableParDto = object.toJavaObject(TableParDto.class);
        tableParDto = parService.saveResult(tableParDto,user);
        return new JsonResult(tableParDto);
    }

    /**
     * 添加三元组
     * @param object
     * @param request
     * @return
     */
    @PostMapping("/addthreecup")
    public JsonResult addThreecup(@RequestBody JSONObject object,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Threecup threecup = object.toJavaObject(Threecup.class);
        int i = parService.addThreecup(threecup, user);
        if(i==1){
            return new JsonResult(Constant.FAIL,"三元组已经存在");
        }else{
            return new JsonResult();
        }
    }

    /**
     * 提取三元组
     * @param parResult
     * @return
     */
    @GetMapping("/par/three/tiqu")
    public JsonResult tiquThreecup(@RequestParam("parResult") String parResult){
        List<Threecup> threecupList = parService.tiquThreecup(parResult);
        return new JsonResult(threecupList.toArray());
    }
}
