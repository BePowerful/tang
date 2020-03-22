package com.wcq.tang.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.ThreecupDto;
import com.wcq.tang.model.Threecup;
import com.wcq.tang.service.admin.ThreecupMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/12 12:40
 */
@RestController
@RequestMapping("admin/mangerThreecup")
public class ThreecupMangerController {
    @Autowired
    private ThreecupMangerService service;
    @GetMapping("/searchThreecup")
    public JsonResult searchUser(@RequestParam("condition")Integer condition,
                                 @RequestParam("param")String param,
                                 @RequestParam("limit") Integer limit,
                                 @RequestParam("page") Integer page){
        JsonResult result = new JsonResult();
        if(condition>3 || param.equals("none")){
            Page<Threecup> threecups = service.defaultValue(page, limit);
            List<ThreecupDto> dtoList = service.threecupListTothreecupDtoList(threecups);
            result.setCount(threecups.getTotal());
            result.setData(dtoList);
            return result;
        }
        switch (condition){
            case 1:{
                Page<Threecup> threecups = service.getThreecupBySubject(param,page, limit);
                List<ThreecupDto> dtoList = service.threecupListTothreecupDtoList(threecups);
                result.setCount(threecups.getTotal());
                result.setData(dtoList);
                break;
            }
            case 2:{
                Page<Threecup> threecups = service.getThreecupByPredicate(param,page, limit);
                List<ThreecupDto> dtoList = service.threecupListTothreecupDtoList(threecups);
                result.setCount(threecups.getTotal());
                result.setData(dtoList);
                break;
            }
            case 3:{
                Page<Threecup> threecups = service.getThreecupByObject(param,page,limit);
                List<ThreecupDto> dtoList = service.threecupListTothreecupDtoList(threecups);
                result.setCount(threecups.getTotal());
                result.setData(dtoList);
                break;
            }
            default:{
                System.err.println("查询用户的条件出错！");
                break;
            }
        }
        return result;

    }


    @DeleteMapping("/del")
    public JsonResult deleteThreecup(@RequestBody JSONObject object){
        Threecup threecup = object.toJavaObject(Threecup.class);
        if(threecup == null){
            return new JsonResult(Constant.FAIL,"操作失败");
        }else{
            service.deleteThreecup(threecup);
            return new JsonResult();
        }
    }

    /**
     * 修改用户
     * @param object
     * @return
     */
    @PutMapping("/editThreecup")
    public JsonResult updateUser(@RequestBody JSONObject object){
        Threecup threecup = object.toJavaObject(Threecup.class);
        if(service.updatethreecup(threecup)==1){
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"编辑失败");
        }
    }
}

