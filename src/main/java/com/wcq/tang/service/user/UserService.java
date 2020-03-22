package com.wcq.tang.service.user;

import com.wcq.tang.bean.JsonResult;
import com.wcq.tang.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:16
 */
public interface UserService {
    JsonResult regUser(String email, String username, String pass);

    boolean haveEmail(String email);

    boolean getVercode(String email, HttpSession session);

    User isUser(String email);
    JsonResult userLogin(String email, String pass, String remberme, HttpServletRequest request, HttpServletResponse response);

    void resetUserPwd(String pass,User user);
    void verUserByCookie(HttpServletRequest request,HttpServletResponse response);
}
