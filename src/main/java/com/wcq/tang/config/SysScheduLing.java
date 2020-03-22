package com.wcq.tang.config;

import com.wcq.tang.service.MyOpService;
import com.wcq.tang.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/21 20:50
 */
@EnableScheduling
@Component
public class SysScheduLing {
    @Autowired
    private MyOpService myOpService;
    @Autowired
    private OperationService operationService;
    private final static Logger logger = LoggerFactory.getLogger(SysScheduLing.class);
    @Scheduled(cron = "0 59 23 * * ?")
    private void todayTasks() {
        int i = myOpService.deleteall();
        logger.info("******定时清空today****共删除数据{}条****本地时间{}",i,LocalDateTime.now());
    }
    @Scheduled(cron = "0 0 1 1 * ?")
    private void operationTask(){
        int i = operationService.deleteAll();
        logger.info("******定时清空operation****共删除数据{}条****本地时间{}",i,LocalDateTime.now());
    }
}
