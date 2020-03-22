package com.wcq.tang.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.bean.VerifyCode;
import com.wcq.tang.model.User;
import com.wcq.tang.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public ModelAndView toReg(){return new ModelAndView("user/reg");}
    @PostMapping("/reg/canUseEmail")
    public JsonResult canUseEmail(@RequestParam String email){
        if(Utils.isEmail(email)){
            if(userService.haveEmail(email)){
                return new JsonResult(Constant.FAIL,"邮箱已经被注册了，可以尝试找回密码！");
            }else{
                return new JsonResult(Constant.SUCCESS,"该邮箱可以被注册！");
            }
        }else {
            return new JsonResult(Constant.FAIL,"邮箱格式错误！");
        }

    }
    @PostMapping("/reg/getVercode")
    public JsonResult getVercode(@RequestParam String email,HttpServletRequest request){
        HttpSession session = request.getSession();
        if(userService.getVercode(email,session)){
            return new JsonResult();
        }else{
            return new JsonResult(Constant.SUCCESS,"验证码发送失败！");
        }
    }
    @PostMapping("/reg")
    public JsonResult regUser(@RequestBody JSONObject object,HttpServletRequest request){
        String email = object.getString("email");
        String username = object.getString("username");
        String pass = object.getString("pass");
        String vercode = object.getString("vercode");
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(Constant.VERCODE);
        if(!code.equals(vercode)){
            return new JsonResult(Constant.FAIL,"验证码错误");
        }else{

        }
        return userService.regUser(email,username,pass);
    }

    //*************************登录
    @GetMapping("/login")
    public ModelAndView toLogin(HttpServletRequest request,HttpServletResponse response){
        userService.verUserByCookie(request,response);
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constant.SESSION_USER);
        if(user != null){
            logger.info("session中有用户存在：{}",user.getUserName());
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }else{
            modelAndView.setViewName("user/login");
            return modelAndView;
        }
    }
    //*********************密码找回
    @GetMapping("/forget")
    public ModelAndView toForget(){return new ModelAndView("user/forget");}
    @GetMapping("/resetpwd")
    public ModelAndView toResetpwd(){return new ModelAndView("user/resetpwd");}
    /**
     * 用户验证
     * @param email
     * @return
     */
    @PostMapping("/forget/isUser")
    public JsonResult isUser(@RequestParam String email,HttpServletRequest request){
        User user = userService.isUser(email);
        if(user!=null){
            request.getSession().setAttribute(Constant.SESSION_USER,user);
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"你还不是本系统用户！你可以先注册本系统！");
        }
    }

    /**
     * 提交身份验证验证码
     * @param object
     * @return
     */
    @PutMapping("/forget/sub")
    public JsonResult checkUser(@RequestBody JSONObject object,HttpServletRequest request){
        String vercode = object.getString("vercode");
        String sessionVercode = (String) request.getSession().getAttribute(Constant.VERCODE);
        if(sessionVercode.equals(vercode)){
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"验证码错误！请仔细核对你的邮件验证码！");
        }
    }
    @PutMapping("/forget/reset")
    public JsonResult updataPwd(@RequestBody JSONObject object,HttpServletRequest request){
        String pass = object.getString("pass");
        String repass = object.getString("repass");
        if(pass.equals(repass)){
            User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);
            userService.resetUserPwd(pass,user);
            return new JsonResult();
        }else{
            return new JsonResult(Constant.FAIL,"两次密码不一致！");
        }

    }

    /**
     * 生成验证码
     * @param response
     * @param request
     */
    @GetMapping("/login/getVerifyCode")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest request){
        try{
            int width = 200,height = 60;
            BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            String randomText = VerifyCode.drawRandomText(width,height,verifyImg);
            request.getSession().setAttribute("verifyCode", randomText);
            response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = response.getOutputStream(); //获取文件输出流
            ImageIO.write(verifyImg,"png",os);//输出图片流
            os.flush();
            os.close();//关闭流
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @PostMapping("/login")
    public JsonResult login(@RequestBody JSONObject object, HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        String email = object.getString("email");
        String pass = object.getString("pass");
        String l_vercode = object.getString("vercode");
        String remberme = object.getString("remberme");
        String verifyCode = (String)session.getAttribute("verifyCode");

        Boolean flag = l_vercode.equalsIgnoreCase(verifyCode);
        if(!flag){//验证码错误
            return new JsonResult(Constant.FAIL,"验证码错误！");
        }else{
            if(remberme == null){
                remberme = "off";
            }
            return userService.userLogin(email,pass,remberme,request,response);
        }
    }

    //退出
    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute(Constant.SESSION_USER);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies
             ) {
            if(cookie.getName().equals("cookie_2")){
                logger.info("***********删除cookie");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
        return new ModelAndView("redirect:/");
    }
}
