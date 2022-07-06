package com.woowoow.mayrose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringJapBasic {
    public static void main(String[] args) {
        SpringApplication.run(SpringJapBasic.class, args);
    }


}
