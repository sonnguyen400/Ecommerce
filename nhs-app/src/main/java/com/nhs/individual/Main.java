package com.nhs.individual;

import com.nhs.individual.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import(SwaggerConfig.class)
@EnableScheduling
@EnableAsync
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class,args);
    }
}