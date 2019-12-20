package com.soen490.cms.Services;

import com.soen490.cms.Models.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

    public void sendMailFromController(int packageId , int userId){
        User user = searchService.findUserById(userId);
        sendPackageByMail(packageId, user);
    }

    public boolean sendPackageByMail (int packageId, User user){
        try {
            sendEmailWithAttachment(packageId);
            System.out.println("EMAIL SENT");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR2");
        }
        return true;
    }
    

    void sendEmailWithAttachment(int packageId) throws MessagingException, IOException {

        byte[] pdf_bytes = pdfService.getPDF(packageId);

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        //MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("georgebarsem@gmail.com"));

        msg.setSubject("Testing from Spring Boot");

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("This is message body");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        //DataHandler dataHandler = new DataHandler(new InputStreamDataSource(pdf_bytes));
        ByteArrayDataSource rawData= new ByteArrayDataSource(pdf_bytes, "application/pdf");
        DataHandler data= new DataHandler(rawData);
        // Part two is attachment

        messageBodyPart = new MimeBodyPart();
        //String filename = "/home/manisha/file.txt";
        //DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(data);
        messageBodyPart.setFileName("Package");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        msg.setContent(multipart);

        javaMailSender.send(msg);

    }
}
