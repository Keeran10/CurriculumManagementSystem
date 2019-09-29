package com.soen490.cms.Controllers;

import com.soen490.cms.FeatureFlagTest.IFeatureFlagTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureFlagTestController {
    private final IFeatureFlagTest flagData;

    @Autowired
    public FeatureFlagTestController(IFeatureFlagTest flagData){
        this.flagData = flagData;
    }

    @GetMapping(value = "/featureFlagTest")
    public @ResponseBody
    String featureFlagTest(){
        return flagData.getData();
    }
}