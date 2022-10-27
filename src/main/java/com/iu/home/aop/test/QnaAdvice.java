package com.iu.home.aop.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.iu.home.board.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect//Advice를 언제(join-point) 누구에게(point-cut) 실행하는 것을 설정  
@Slf4j//로그기록
public class QnaAdvice {
	
//	//qnaService에서 get으로 시작하는 애들
//	@After("execution(* com.iu.home.board.qna,QnaService.get*(..))")
//	public void beforeTest(JoinPoint joinPoint)throws Throwable{
//		log.info("============= B E F O R E =============");
//		log.info("args : {}", joinPoint.getArgs());
//		log.info("kind : {}", joinPoint.getKind());
//		//시작 전에 하는거기 때문에 return이 없음
//	}
	
	//qnaService에서 set으로 시작하는 애들
//	@Around("execution(* com.iu.home.board.qna.QnaService.set*(..))")
	public Object aroundTest(ProceedingJoinPoint joinPoint)throws Throwable{
//		execution=실행
//		@Around("execution(*에서 *은 모든 리턴타입을 말한다 
//		set*(..))") set으로 시작하는 모든 메서드를 말한다
		//point-cut = 핵심로직 ex)setAdd가 핵심로직임
		
		log.info("============= B E F O R E =============");
		//point-cut의 클래스객체
		log.info("joinPoint.getTarget() : {}", joinPoint.getTarget());
		//point-cut의 클래스객체
		log.info("joinPoint.getTise() : {}", joinPoint.getThis());
		////point-cut으로 전달되는 매개변수의 인자값
		log.info("joinPoint.getArgs() : {}", joinPoint.getArgs());
		Object [] objs = joinPoint.getArgs();//매개변수가 몇개인지 알 수 없기에 반복문을 실행해본다
//		Args가 배열인 이유
//		setAdd의 매개변수가 지금은 하나이지만 여러개일 수 있기 때문임
		//objs의 타입은 Object타입이기 때문에 형변환을 해준다
		QnaVO qnaVO = (QnaVO)objs[0]; //다형성
		

		
		Object obj = joinPoint.proceed();
		//point-cut ex)setAdd를 실행시키는 것
		//setAdd를 가져와 객체로 만들어 joinPoint에 넣어 실행시키는 것이다"
		
		log.info("============= A F T E R =============");
		log.info("OBJ {}", obj);
//		OBJ 1
		
		return obj;
	}

}
