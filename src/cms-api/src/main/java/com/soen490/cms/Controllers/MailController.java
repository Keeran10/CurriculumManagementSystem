package com.soen490.cms.Controllers;

import com.soen490.cms.Services.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {

    @Autowired
    MailService mailService;

    /**
     * endpoint of: /Mail
     * takes as parameter the request of package Id and user id
     * this end point is for testing purpose only
     *
     * @param packageId, userId
     * @return mail sent
     */
    @GetMapping(value = "/Mail")
    public boolean sendPackageByMail(@RequestParam int packageId,@RequestParam int userId){
        log.info("Sending Package", packageId);
        mailService.sendMailFromController(packageId, userId);
        return true;
    }
}
