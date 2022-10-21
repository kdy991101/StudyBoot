package com.iu.home.aop.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferTest {

	@Autowired
	private TransePort transePort;

	@Autowired
	private Card card;
	
//	@Test
	void test() {

		transePort.getTaxi();
		
		transePort.takeBus();
		transePort.takeSubway();
	
	}

}
