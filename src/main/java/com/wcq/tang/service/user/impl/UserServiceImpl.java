package com.wcq.tang.service.user.impl;

import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.bean.Utils;
import com.wcq.tang.config.EmailConfig;
import com.wcq.tang.mapper.OperationMapper;
import com.wcq.tang.mapper.UserMapper;
import com.wcq.tang.model.Operation;
import com.wcq.tang.model.User;
import com.wcq.tang.model.UserExample;
import com.wcq.tang.service.OperationService;
import com.wcq.tang.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:17
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private OperationService operationService;

    /**
     * 检擦是否有邮箱
     * @param email
     * @return
     */
    @Override
    public boolean haveEmail(String email) {
        if(isUser(email)!=null)
            return true;
        else
            return false;
    }
    /**
     * 获取验证码
     * @param email
     * @param session
     * @return
     */
    @Override
    public boolean getVercode(String email, HttpSession session) {
        String sendTo = email;
        String title = "唐卡语料库注册码";
        Integer code = new Integer(new Random().nextInt(10000));
        if(code<1000)
            code+=1000;
        String content = "欢迎注册唐卡语料库，你的验证码是："+code.toString();
        sendSimpleMail(sendTo,title,content);
        session.setAttribute(Constant.VERCODE,code.toString());
        return true;
    }
    /**
     * 注册用户
     * @param email
     * @param username
     * @param pass
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized JsonResult regUser(String email, String username, String pass) {
        User user = new User();
        //密码加密
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(pass)){
            return new JsonResult(Constant.FAIL,"密码或用户名为空!");
        }
        String pwd = Utils.MD5encode(email+pass);
        user.setEmail(email);
        user.setUserName(username);
        user.setPassword(pwd);
        user.setRole(false);
        user.setUnuse(false);
        int i = userMapper.insertSelective(user);
        if(i == 1){
            operationService.insertOperation(user,"注册了本系统",Constant.OPERATION_OTHER);
            return new JsonResult(Constant.SUCCESS,"注册成功!");
        }else {
            logger.error("用户注册失败！");
            return new JsonResult(Constant.FAIL,"系统异常，请联系管理员!");
        }
    }

    /**
     * 登录接口
     * @param email
     * @param pass
     * @return
     */
    public JsonResult userLogin(String email, String pass, String remberme, HttpServletRequest request, HttpServletResponse response) {
        User user = isUser(email);
        if (user.getUnuse()){
            return new JsonResult(Constant.FAIL,"你已经被封号");
        }
        if(user == null){
            return new JsonResult(Constant.FAIL,"你还没有注册哦！");
        }
        String password = Utils.MD5encode(email + pass);
        boolean equals = user.getPassword().equals(password);
        if(equals){
            request.getSession().setAttribute(Constant.SESSION_USER,user);
            if(remberme.equals("on")){
                Cookie cookie;
                String s = Utils.MD5encode(email+"tang");
                String value = s+"/"+email;
                cookie = new Cookie("cookie_2",value);
                cookie.setPath("/");
                cookie.setMaxAge(168*60*60);
                response.addCookie(cookie);
            }
            operationService.insertOperation(user,"登录",Constant.OPERATION_OTHER);
            return new JsonResult(Constant.SUCCESS,"欢迎回来！");
        }else{
            return new JsonResult(Constant.FAIL,"密码错误！");
        }
    }

    /**
     * 重置用户密码
     * @param pass
     * @param user
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void resetUserPwd(String pass,User user) {
        String password = Utils.MD5encode(user.getEmail() + pass);
        user.setPassword(password);
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i !=1)
            logger.error("用户密码重置数据库异常！");
    }

    @Override
    public void verUserByCookie(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length>0){
            for (Cookie cookie:cookies
            ) {
                if(cookie.getName().equals("cookie_2")){
                    logger.info("**************找到cookie");
                    String value = cookie.getValue();
                    String[] split = value.split("/");
                    String s = split[0];
                    String email = split[1];
                    String s1 = Utils.MD5encode(email + "tang");
                    if(s.equals(s1)){
                        User user = isUser(email);
                        if(user.getUnuse()){
                            logger.info("{}已经被封号，不可用！",user.getUserName());
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                            return ;
                        }
                        operationService.insertOperation(user,"登录（cookie）",Constant.OPERATION_OTHER);
                        request.getSession().setAttribute(Constant.SESSION_USER,user);
                    }
                    break;
                }
            }
        }
    }

    /**
     * 简单邮件发送
     * @param sendTo
     * @param title
     * @param content
     */
    public synchronized void sendSimpleMail(String sendTo, String title, String content) {
        //构造邮件的实体类：SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者
        message.setFrom(emailConfig.getEmailFrom());
        //接收者
        message.setTo(sendTo);
        //主题
        message.setSubject(title);
        //内容
        message.setText(content);
        //发送邮件
        javaMailSender.send(message);
    }

    /**
     * 检查数据库中是否有此邮箱
     * @param email
     * @return
     */
    @Override
    public User isUser(String email){
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(example);
        if(users.size()>0&&users.get(0)!=null)
            return users.get(0);
        else
            return null;
    }
}
