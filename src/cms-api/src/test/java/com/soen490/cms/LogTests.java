package com.soen490.cms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class LogTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void logTest() {
		log.info("This is a log test");
	}

}
