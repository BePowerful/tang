package com.wcq.tang.controller;

import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.dto.IntroduceDto;
import com.wcq.tang.model.Indexmsg;
import com.wcq.tang.model.User;
import com.wcq.tang.service.IndexService;
import com.wcq.tang.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/4 13:16
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;
    @Autowired
    private UserService userService;
    @RequestMapping({"/","index","/index","index.html"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute(Constant.SESSION_USER);
        if(user==null){
            userService.verUserByCookie(request,response);
        }
        ModelAndView modelAndView = new ModelAndView();
        ServletContext servletContext = request.getServletContext();
        List<Indexmsg> indexmsgList = (List<Indexmsg>)servletContext.getAttribute("SCindexmsg");
        Indexmsg[] dontai = new Indexmsg[5];
        for(int i =0;i<5;i++){
            dontai[i]=indexmsgList.get(i);
        }
        Indexmsg introduce =indexmsgList.get(5);
        String msgContent = introduce.getMsgContent();
        String[] split = msgContent.split("/");
        IntroduceDto introduceDto = new IntroduceDto();
        introduceDto.setSysName(split[0]);
        introduceDto.setSysClub(split[1]);
        introduceDto.setSysFunc(split[2]);
        introduceDto.setSysYiyi(split[3]);
        introduceDto.setSysShuo(split[4]);
        Indexmsg about = indexmsgList.get(6);
        modelAndView.addObject("dontai",dontai);
        modelAndView.addObject("introduceDto",introduceDto);
        modelAndView.addObject("about",about);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
