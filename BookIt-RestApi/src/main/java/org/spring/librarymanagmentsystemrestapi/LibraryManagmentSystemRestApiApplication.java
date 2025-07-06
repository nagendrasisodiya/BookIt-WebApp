package org.spring.librarymanagmentsystemrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibraryManagmentSystemRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagmentSystemRestApiApplication.class, args);
    }

}
