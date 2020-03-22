package com.wcq.tang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.wcq.tang.mapper")
@EnableCaching
public class TangApplication {

    public static void main(String[] args) {
        SpringApplication.run(TangApplication.class, args);
    }

}
