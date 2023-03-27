package com.hannah.springbootprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPracApplication.class, args);
    }

}
