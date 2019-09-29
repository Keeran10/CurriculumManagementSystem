package com.soen490.cms;

import com.soen490.cms.FeatureFlagTest.FeatureFlagOff;
import com.soen490.cms.FeatureFlagTest.FeatureFlagOn;
import com.soen490.cms.FeatureFlagTest.IFeatureFlagTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
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
		log.info("Running CMS app");
		SpringApplication.run(App.class, args);
	}

}
