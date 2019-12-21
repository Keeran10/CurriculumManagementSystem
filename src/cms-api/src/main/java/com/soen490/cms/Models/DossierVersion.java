package com.soen490.cms.Models;

import lombok.Data;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

@Data
public class DossierVersion {

    private int id;
    private int rev;
    private String revtype;
    private byte[] pdf;
    private String modified_date;
    private String modified_by;

    public DossierVersion(int id, int rev, int revtype, byte[] pdf, BigInteger timestamp){

        this.id = id;
        this.rev = rev;

        if(revtype == 0)
            this.revtype = "create";
        else if(revtype == 1)
            this.revtype = "update";
        else if(revtype == 2)
            this.revtype = "remove";

        this.pdf = pdf;

        Instant instant = Instant.ofEpochMilli(timestamp.longValue());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.modified_date = fmt.format(instant.atZone(ZoneId.systemDefault()));
    }
}
