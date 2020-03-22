package com.wcq.tang.config.listener;

import com.wcq.tang.bean.Constant;
import com.wcq.tang.bean.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/2/29 13:44
 */
@Component
public class MyHttpSessionListener implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

    /**
     * 记录当前在线用户
     */
    private Integer count = 0;
    private Long browseTimes = 0L;
    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求ip
        String ip = request.getRemoteAddr();
        logger.info("*****新用户上线了***ip:{}",ip);
        count++;
        browseTimes++;
        se.getSession().getServletContext().setAttribute(Constant.ON_LINE_USER,count);
        se.getSession().getServletContext().setAttribute(Constant.BROWSE_TIMES,browseTimes);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("*****用户下线了***{}", Utils.dateToString("yyyy-MM-dd HH:mm:ss",new Date()));
        count--;
        se.getSession().getServletContext().setAttribute(Constant.ON_LINE_USER,count);
    }
}
