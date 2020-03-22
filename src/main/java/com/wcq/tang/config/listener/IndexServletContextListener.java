package com.wcq.tang.config.listener;

import com.wcq.tang.model.Indexmsg;
import com.wcq.tang.service.IndexService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/4 13:53
 */
@Component
public class IndexServletContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 先获取到application上下文
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        // 获取对应的service
        IndexService indexService = applicationContext.getBean(IndexService.class);
        List<Indexmsg> indexmsg = indexService.getIndexmsg();
        // 获取application域对象，将查到的信息放到application域中
        ServletContext application = applicationContext.getBean(ServletContext.class);
        application.setAttribute("SCindexmsg",indexmsg);
    }
}
