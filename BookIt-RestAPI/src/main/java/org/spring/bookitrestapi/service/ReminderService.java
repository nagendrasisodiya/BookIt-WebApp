package org.spring.bookitrestapi.service;

import lombok.extern.slf4j.Slf4j;
import org.spring.bookitrestapi.model.IssuedBook;
import org.spring.bookitrestapi.repository.IssuedBookRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ReminderService {
    private final IssuedBookRepo issuedBookRepo;
    private final MailService mailService;
    public ReminderService(IssuedBookRepo issuedBookRepo, MailService mailService) {
        this.issuedBookRepo = issuedBookRepo;
        this.mailService = mailService;
    }
    @Scheduled(cron="0 0 9 * * ?")
    public void sendReminder() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<IssuedBook> bookDueTommorow=issuedBookRepo.findByReturnDate(tomorrow);
        for(IssuedBook issuedBook:bookDueTommorow) {
            try{
                String reminder=mailService.genrateRemainderMailBody(issuedBook.getBook().getTitle(),
                        issuedBook.getReturnDate());
                mailService.sendMail(
                        issuedBook.getUser().getEmail(),
                        "Remainder for return book",
                        reminder
                );
                log.info("Remainder for return book mail sent",
                        issuedBook.getBook().getTitle(), issuedBook.getReturnDate());
            }catch (Exception e) {
                log.error("Remainder for return book mail failed",
                        issuedBook.getBook().getTitle(), issuedBook.getReturnDate(),
                        e.getMessage());
            }
        }
        log.info("Completed Daily Return Remainder Check");
    }

}
