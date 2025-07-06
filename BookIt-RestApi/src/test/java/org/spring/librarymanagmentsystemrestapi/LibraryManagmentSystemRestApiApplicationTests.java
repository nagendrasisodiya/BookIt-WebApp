package org.spring.librarymanagmentsystemrestapi;

import org.junit.jupiter.api.Test;
import org.spring.librarymanagmentsystemrestapi.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

@SpringBootTest
class LibraryManagmentSystemRestApiApplicationTests {
    @Autowired
    private MailService mailService;
    @Test
    void contextLoads() {
        mailService.sendMail(
                "3n3singh@gmail.com",
                "testing mail service",
                "whats the progress test1"
        );
    }

}
