package com.soen490.cms.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@Data
public class DossierRevision extends Revision{

    private byte[] pdf;

    public DossierRevision(int id, int rev, int revtype, BigInteger timestamp, User user, byte[] pdf){
        super(id, rev, revtype, timestamp, user);
        this.pdf = pdf;
    }
}
