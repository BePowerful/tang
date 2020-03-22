package com.wcq.tang.controller;

import com.wcq.tang.bean.CixinTable;
import com.wcq.tang.bean.CixinTableStatic;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.dto.CixinDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/16 15:41
 */
@RestController
public class HelpController {
    @GetMapping("/help")
    public ModelAndView toHelp(){return new ModelAndView("common/help");}
    @GetMapping("/help/cixinTable")
    public JsonResult getCixiTable(@RequestParam(name = "limit",defaultValue = "10") Integer limit,
                                   @RequestParam(name = "page",defaultValue = "1") Integer page){
        List<CixinDto> cixinDtoList = CixinTableStatic.cixinDtoList;
        List<CixinDto> resList = new LinkedList<>();
        int start = (page-1)*limit;
        if(page == 15){
            limit = 7;
        }
        for(int i=0;i<limit;i++){
            resList.add(cixinDtoList.get(start+i));
        }
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCount(147L);
        jsonResult.setData(resList);
        return jsonResult;
    }
}
