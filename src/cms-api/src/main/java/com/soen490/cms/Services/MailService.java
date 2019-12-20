package com.soen490.cms.Services;

import com.soen490.cms.Models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

@Log4j2
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    PdfService pdfService;
    @Autowired
    SearchService searchService;

    public boolean sendMailService(int packageId, User user){
        try {
            sendEmailWithAttachment(packageId, user);
            System.out.println("EMAIL SENT");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Messaging ERROR");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO ERROR");
        }
        return true;
    }

    public void sendMailFromController(int packageId , int userId){
        User user = searchService.findUserById(userId);
        sendMailService(packageId, user);
    }

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
}
