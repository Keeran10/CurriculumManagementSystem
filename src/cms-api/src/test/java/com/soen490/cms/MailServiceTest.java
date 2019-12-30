package com.soen490.cms;

import com.soen490.cms.Models.User;
import com.soen490.cms.Services.MailService;
import com.soen490.cms.Services.PdfService;
import com.soen490.cms.Services.SearchService;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.mock;

public class MailServiceTest {

    User user;
    MailService mailService;
    JavaMailSender javaMailSender =  mock(JavaMailSender.class);
    PdfService pdfService =  mock(PdfService.class);
    SearchService searchService =  mock(SearchService.class);





















    


}