package com.iu.home.aop.test;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j//로그 기록을 찍기 위한 어너테이션
public class TransePort {

	public void takeBus() {
		log.info("-------버스 탑승------");
	}
	public void takeSubway() {
		log.info("-------지하철 탑승------");
	}
	
	public void getTaxi() {
		log.info("------택시탑승---------");
	}
	
	public void airPlane() {
		log.info("------비행기 타기---------");
	}
	
}
