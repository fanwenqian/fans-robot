package com.fans.fansrobot;

import love.forte.simbot.spring.autoconfigure.EnableSimbot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 *
 * @author fans
 * @date 2021/12/31
 */
@EnableSimbot
@SpringBootApplication
public class FansRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(FansRobotApplication.class, args);
    }
}
