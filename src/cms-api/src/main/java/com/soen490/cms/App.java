// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

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
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@Log4j2
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
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
