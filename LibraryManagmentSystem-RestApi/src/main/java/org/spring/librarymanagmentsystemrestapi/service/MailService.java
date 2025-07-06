package org.spring.librarymanagmentsystemrestapi.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendMail(String to, String subject, String text) {
        log.info("Sending mail to " + to);
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            log.info("Mail sent");
        }catch(Exception e){
            log.error("mail not sent "+e.getMessage());
        }
    }
    public String genrateIssueMailBody(String bookName, LocalDate returnDate) {
        String emailBody = String.format(
                "Dear User,\n\n" +
                        "We would like to inform you that the book \"%s\" you have checked out is currently in use. " +
                        "Please remember that the return date for this book is %s.\n\n" +
                        "Thank you for using our library services!\n\n" +
                        "Best regards,\n" +
                        "BookIt",
                bookName, returnDate
        );
        return emailBody;
    }
    public String genrateRemainderMailBody(String bookName, LocalDate returnDate) {
        return String.format(
                "Dear User,\n\n" +
                        "This is a friendly reminder that the book \"%s\" you borrowed from our library is due for return tomorrow on %s.\n\n" +
                        "Please ensure you return the book on time to avoid any late fees.\n\n" +
                        "Thank you for using our library services!\n\n" +
                        "Best regards,\n" +
                        "BookIt",
                bookName, returnDate
        );
    }

}
