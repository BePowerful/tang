package com.wcq.tang.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.EChartsDto;
import com.wcq.tang.dto.IntroduceDto;
import com.wcq.tang.dto.SysMsgDto;
import com.wcq.tang.model.Indexmsg;
import com.wcq.tang.model.Operation;
import com.wcq.tang.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/28 17:56
 */
@RestController()
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PutMapping("/mangerIndex/updateIntroduce")
    public JsonResult mangerI(@RequestBody JSONObject object){
        IntroduceDto dto = object.toJavaObject(IntroduceDto.class);
        String msgContent = dto.getSysName()+"/"+dto.getSysClub()+"/"+dto.getSysFunc()+"/"+dto.getSysYiyi()+"/"+dto.getSysShuo();
        Indexmsg indexmsg = new Indexmsg();
        indexmsg.setMsgId(6);
        indexmsg.setMsgContent(msgContent);
        int i = adminService.updateIndexMsg(indexmsg);
        if(i == 1){
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"修改失败");
        }
    }

    @PutMapping("/mangerIndex/updateAbout")
    public JsonResult mangerA(@RequestBody JSONObject object){
        return getResult(object);
    }
    @PutMapping("/mangerIndex/updateDT")
    public JsonResult mangerDT(@RequestBody JSONObject object){
        return getResult(object);
    }
    private JsonResult getResult(@RequestBody JSONObject object) {
        Indexmsg indexmsg = object.toJavaObject(Indexmsg.class);
        int i = adminService.updateIndexMsg(indexmsg);
        if(i == 1){
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"修改失败");
        }

    }

    /**
     *删除动态
     * @param object
     * @return
     */
    @PostMapping("/manDTdel")
    public JsonResult manDTdel(@RequestBody JSONObject object){
        Indexmsg indexmsg = object.toJavaObject(Indexmsg.class);
        if(adminService.delIndexmsg(indexmsg)==1){
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"修改失败");
        }
    }

    /**
     * 获取关于我们
     * @return
     */
    @GetMapping("/mangerAbout")
    public JsonResult initAValue(){
        List<Indexmsg> indexmsgs = adminService.getIndexMsg();
        if(indexmsgs==null){
            return new JsonResult(Constant.FAIL,"服务器接口异常！");
        }else{
            return new JsonResult(indexmsgs.get(6));
        }
    }

    /**
     * 获取网站介绍
     * @return
     */
    @GetMapping("/mangerIntroduce")
    public JsonResult initIValue(){
        List<Indexmsg> indexmsgs = adminService.getIndexMsg();
        Indexmsg indexmsg = indexmsgs.get(5);
        String msgContent = indexmsg.getMsgContent();
        String[] split = msgContent.split("/");
        IntroduceDto introduceDto = new IntroduceDto();
        introduceDto.setSysName(split[0]);
        introduceDto.setSysClub(split[1]);
        introduceDto.setSysFunc(split[2]);
        introduceDto.setSysYiyi(split[3]);
        introduceDto.setSysShuo(split[4]);
        return new JsonResult(introduceDto);
    }

    /**
     * 获取动态信息
     * @return
     */
    @GetMapping("/mangerIndexData")
    public JsonResult initDonTaiValue(){
        List<Indexmsg> indexmsgs = adminService.getIndexMsg();
        if(indexmsgs == null){
            return new JsonResult(Constant.FAIL,"动态信息获取失败！");
        }else{
            Indexmsg[] msgarray = new Indexmsg[5];
            for(int i=0;i<5;i++){
                msgarray[i]=indexmsgs.get(i);
            }
            return new JsonResult(msgarray);
        }
    }

    /**
     * re如果是0，那就去主页面，如果是1，那就是刷新系统数据
     * @param re
     * @param response
     * @param request
     * @return
     */
    @GetMapping("/main/{re}")
    public ModelAndView toMain(@PathVariable(name = "re") Integer re,
                               HttpServletResponse response,
                               HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        SysMsgDto sysMsgDto;
        if(re==0){//指前往主页，
            sysMsgDto = adminService.getSysMsgDto(request, response,false);
        }else{//指在主页刷新内容
            sysMsgDto = adminService.getSysMsgDto(request, response,true);
        }
        //操作记录
        List<List<Operation>> operations = adminService.getOperations();
        List<Operation> uploadop = operations.get(0);
        List<Operation> cleanop = operations.get(1);
        List<Operation> participleop = operations.get(2);
        List<Operation> otherop = operations.get(3);
        List<Operation> threeop = operations.get(4);
        modelAndView.addObject("uploadop",uploadop);
        modelAndView.addObject("cleanop",cleanop);
        modelAndView.addObject("participleop",participleop);
        modelAndView.addObject("otherop",otherop);
        modelAndView.addObject("threeop",threeop);
        modelAndView.addObject("sysMsgDto",sysMsgDto);
        modelAndView.setViewName("admin/main");
        return modelAndView;
    }
    @GetMapping("/manageIndex")
    public ModelAndView toHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/manIndex");
        return modelAndView;
    }


    @GetMapping("/manageUser")
    public ModelAndView toManUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/manUser");
        return modelAndView;
    }
    @GetMapping("/main/ChartsDatas")
    public JsonResult buildECharts(){
        EChartsDto eChartsDto = adminService.getEChartsDto();
        return new JsonResult(eChartsDto);
    }
    @GetMapping("/manageThreecup")
    public ModelAndView toThreecup(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/manthreecup");
        return modelAndView;
    }
}
