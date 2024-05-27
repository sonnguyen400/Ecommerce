package com.nhs.individual;

import com.nhs.individual.Config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class,args);
    }
}