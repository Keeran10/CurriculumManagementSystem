package com.soen490.cms.Models;

import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Data
public class DossierVersion {

    private byte[] pdf;
    private String timestamp;

    public DossierVersion(byte[] pdf, Instant timestamp){

        this.pdf = pdf;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                        .withLocale( Locale.CANADA )
                        .withZone( ZoneId.systemDefault() );

        this.timestamp = formatter.format(timestamp);
    }
}
