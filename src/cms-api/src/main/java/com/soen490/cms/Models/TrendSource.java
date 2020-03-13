package com.soen490.cms.Models;

import com.kwabenaberko.newsapilib.models.Source;
import lombok.Data;

@Data
public class TrendSource {

    private String id;
    private String name;

    TrendSource(Source source){
        this.id = source.getId();
        this.name = source.getName();
    }
}
