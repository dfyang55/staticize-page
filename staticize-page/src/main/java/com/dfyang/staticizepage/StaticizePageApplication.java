package com.dfyang.staticizepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StaticizePageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticizePageApplication.class, args);
    }

}
