package com.soen490.cms;

import com.soen490.cms.FeatureFlagTest.FeatureFlagOff;
import com.soen490.cms.FeatureFlagTest.FeatureFlagOn;
import com.soen490.cms.FeatureFlagTest.IFeatureFlagTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class App {

	@Bean
	@Primary
	@ConditionalOnProperty(prefix = "Feature.toggle", name = "featureFlagTest", havingValue="false")
	public IFeatureFlagTest getFlagOff(){
		return new FeatureFlagOff();
	}

	@Bean
	@Primary
	@ConditionalOnProperty(prefix = "Feature.toggle", name = "featureFlagTest", havingValue="true")
	public IFeatureFlagTest getFlagOn(){
		return new FeatureFlagOn();
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
