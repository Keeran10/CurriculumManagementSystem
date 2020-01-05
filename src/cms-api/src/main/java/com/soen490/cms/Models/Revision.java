package com.soen490.cms.Models;

import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
class Revision {

    private int id;
    private int rev;
    private String rev_type;
    private String modified_date;
    private String modified_by;

    Revision(int id, int rev, int revtype, BigInteger timestamp, User user){

        this.id = id;
        this.rev = rev;

        if(revtype == 0)
            this.rev_type = "create";
        else if(revtype == 1)
            this.rev_type = "update";
        else if(revtype == 2)
            this.rev_type = "remove";

        Instant instant = Instant.ofEpochMilli(timestamp.longValue());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.modified_date = fmt.format(instant.atZone(ZoneId.systemDefault()));

        if(user != null)
            this.modified_by = user.getEmail() + " (" + user.getFirstName() + " " + user.getLastName() + ")";
        else
            this.modified_by = "N/A";
    }
}
