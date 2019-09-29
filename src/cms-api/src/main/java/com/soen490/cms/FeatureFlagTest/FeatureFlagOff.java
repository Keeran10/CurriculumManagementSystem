package com.soen490.cms.FeatureFlagTest;

public class FeatureFlagOff implements IFeatureFlagTest{
    public String getData(){
        return "Feature Flag Off";
    }
}