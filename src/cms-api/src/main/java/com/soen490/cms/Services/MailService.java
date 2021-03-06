// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
package com.soen490.cms.Services;

import com.soen490.cms.Models.User;
import com.soen490.cms.Services.PdfService.PdfService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Properties;

@Log4j2
@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    PdfService pdfService;
    @Autowired
    SearchService searchService;

    /**
     * Email service function
     * Sends email to the specified user based on package id and
     * user object specified
     *
     * @param packageId, user
     * @return Void message sent
     */
    public boolean sendMailService(int packageId, User user){
        try {
            sendEmailWithAttachment(packageId, user);
            log.info("EMAIL SENT");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.info("Messaging ERROR");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("IO ERROR");
        }
        return true;
    }

    /**
     * Sends email to the specified user based on package id and
     * user id specified
     *
     * @param packageId, userId
     * @return Void message sent
     */
    public void sendMailFromController(int packageId , int userId){
        User user = searchService.findUserById(userId);
        sendMailService(packageId, user);
    }

    /**
     * Sends email with attachement to the specified user
     * based on package id and
     * user id specified
     *
     * @param packageId, user
     * @return Void message sent
     */
    private void sendEmailWithAttachment(int packageId, User user) throws MessagingException, IOException {

        byte[] pdf_bytes = pdfService.getPDF(packageId);

        MimeMessage msg = javaMailSender.createMimeMessage();

        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(user.getEmail()));

        msg.setSubject("Mailing Service: Academic Change Request Review");

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Your review is highly required for the request package attached to this email.");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        //DataHandler dataHandler = new DataHandler(new InputStreamDataSource(pdf_bytes));
        ByteArrayDataSource rawData= new ByteArrayDataSource(pdf_bytes, "application/pdf");
        DataHandler data= new DataHandler(rawData);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(data);
        messageBodyPart.setFileName("Package");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        msg.setContent(multipart);

        javaMailSender.send(msg);

    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("concordia.CMS.soen490@gmail.com");
        mailSender.setPassword("soen490cms");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public void setServiceMock (JavaMailSender javaMailSender, PdfService pdfService, SearchService searchService ){
        this.javaMailSender = javaMailSender;
        this.pdfService = pdfService;
        this.searchService = searchService;
    }

}
