package com.wcq.tang.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.UserMangerDto;
import com.wcq.tang.model.User;
import com.wcq.tang.service.admin.UserMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/1 13:51
 */
@RestController
@RequestMapping("admin/mangerUser")
public class UserMangerController {
    @Autowired
    private UserMangerService service;

    /**
     * 封停用户
     * @param object
     * @return
     */
    @PutMapping("/unuse")
    public JsonResult setUnuse(@RequestBody JSONObject object){
        User user = object.toJavaObject(User.class);
        if(user == null){
            return new JsonResult(Constant.FAIL,"封停操作失败");
        }else{
            user.setUnuse(true);
            service.updateUser(user);
            return new JsonResult();
        }
    }

    /**
     * 修改用户
     * @param object
     * @return
     */
    @PutMapping("/editUser")
    public JsonResult updateUser(@RequestBody JSONObject object){
        User user = object.toJavaObject(User.class);
        if(service.updateUser(user)==1){
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"编辑用户失败");
        }
    }

    @GetMapping("/searchUser")
    public JsonResult searchUser(@RequestParam("condition")Integer condition,
                             @RequestParam("param")String param,
                             @RequestParam("limit") Integer limit,
                             @RequestParam("page") Integer page){
        JsonResult result = new JsonResult();
        if(condition>3 || param.equals("none")){
            Page<User> userPage = service.getDefaultUser(page, limit);
            List<UserMangerDto> userMangerDtos = service.userListToUMD(userPage);
            result.setCount(userPage.getTotal());
            result.setData(userMangerDtos);
            return result;
        }
        List<UserMangerDto> dtoList = new ArrayList<>();
        switch (condition){
            case 0:{
                Page<User> userPage = service.getDefaultUser(page, limit);
                List<UserMangerDto> userMangerDtos = service.userListToUMD(userPage);
                result.setCount(userPage.getTotal());
                result.setData(userMangerDtos);
                break;
            }
            case 1:{
                UserMangerDto dto = service.getUserMangerDtoByEmail(param);
                if(dto != null){
                    dtoList.add(dto);
                    result.setData(dtoList);
                    result.setCode(0);
                }else{
                    result.setCode(1);
                }
                break;
            }
            case 2:{
                Page<User> userPage = service.getUserMangerDtoByName(param, page, limit);
                List<UserMangerDto> userMangerDtos = service.userListToUMD(userPage);
                result.setCount(userPage.getTotal());
                result.setData(userMangerDtos);
                break;
            }
            case 3:{
                UserMangerDto dto = service.getUserMangerDtoById(Long.valueOf(param));
                if(dto != null){
                    dtoList.add(dto);
                    result.setData(dtoList);
                    result.setCode(0);
                }else{
                    result.setCode(1);
                }
                break;
            }
            default:{
                System.err.println("查询用户的条件出错！");
                break;
            }
        }
        return result;

    }

}
