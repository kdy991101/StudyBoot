package com.iu.home.aop.test;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect//언제 실행할건지 설정
class Card {
	
//	@Before("execution(* com.iu.home.aop.test.Transeport.airplane())")
//	public void before() {
//		log.info(" ------------Before --------- ");
//	}
//	@After("execution(* com.iu.home.aop.test.Transeport.get*())")
//	public void after() {
//		log.info(" ------------After --------- ");
//	}
//	
//	
//	@Around("execution(* com.iu.home.aop.test.Transeport.take*())")
//	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
//		log.info("--- 삐빅 승차 입니다 -----");
//		Object obj = joinPoint.proceed();
//		log.info("--- 삐빅 하차 입니다 -----");
//		return obj;
//		
//	}
//	@Before("execution(* com.iu.home.aop.test.TransePort.airPlane())")
//	public void before() {
//		log.info("before");
//	}
//
//	@After("execution(* com.iu.home.aop.test.TransePort.get*())")
//	public void cardCheck() {
//		log.info("8900원입니다");
//	}
	
//	@AfterThrowing("execution(* com.iu.home.board.*.*Service.set*())")
//	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable{
//		log.info("삐빅어린이입니다");
//		Object obj = joinPoint.proceed();
//		log.info("하차입니다");
//		return obj;
//	}
	
//	@Around("execution(* com.iu.home.test.TransePort.take*)")
//	public Object cardCheck1(ProceedingJoinPoint joinPoint) throws Throwable{
//		log.info("삐빅어린이입니다");
//		Object obj = joinPoint.proceed();
//		log.info("하차입니다");
//		return obj;
//	}
}
