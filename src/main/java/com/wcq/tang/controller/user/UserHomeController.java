package com.wcq.tang.controller.user;

import com.github.pagehelper.Page;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.model.Corpus;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Threecup;
import com.wcq.tang.model.User;
import com.wcq.tang.service.user.UserHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:25
 */
@RestController
@RequestMapping("/user")
public class UserHomeController {
    @Autowired
    private UserHomeService userHomeService;
    @GetMapping("/mycorpus")
    public ModelAndView toUserHome(){return new ModelAndView("user/mycorpus"); }
    @GetMapping("/myoriginal")
    public ModelAndView tomyOriginals(){ return new ModelAndView("user/myoriginal");}
    @GetMapping("/mythree")
    public ModelAndView tomyThree(){
        return new ModelAndView("user/mythree");
    }
    @GetMapping("/mythree/data")
    public JsonResult myThree(@RequestParam(name = "page",defaultValue ="1") Integer page,
                              @RequestParam(name = "limit",defaultValue = "10") Integer limit,
                              HttpServletRequest request){
        System.out.println("page:"+page);
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Threecup> threecupPage = userHomeService.getUserThreecups(user,page,limit);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCount(threecupPage.getTotal());
        jsonResult.setData(threecupPage.getResult());
        return jsonResult;
    }
    @GetMapping("/mycorpus/data")
    public JsonResult myCorpus(@RequestParam(name = "page",defaultValue ="1") Integer page,
                                 @RequestParam(name = "limit",defaultValue = "10") Integer limit,
                                 HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Corpus> corpusPage = userHomeService.getUserCorpus(user, page, limit);
        JsonResult result = new JsonResult();
        result.setCount(corpusPage.getTotal());
        result.setData(corpusPage.getResult());
        return result;
    }
    @GetMapping("/myoriginal/data")
    public JsonResult myOriginal(@RequestParam(name = "page",defaultValue ="1") Integer page,
                                 @RequestParam(name = "limit",defaultValue = "10") Integer limit,
                                 HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
        Page<Original> originalPage = userHomeService.getUserOriginals(user, page, limit);
        JsonResult result = new JsonResult();
        result.setCount(originalPage.getTotal());
        result.setData(originalPage.getResult());
        return result;
    }
    @GetMapping("/mymsg")
    public ModelAndView toUserMsg(){return new ModelAndView("user/mymsg");}
    @GetMapping("/otherset")
    public ModelAndView toOtherSet(){return new ModelAndView("user/otherset");}
}
