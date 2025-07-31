package org.spring.bookitrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookItRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookItRestApiApplication.class, args);
    }

}
