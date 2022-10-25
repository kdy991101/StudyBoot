package com.iu.home.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)//들어가기 전
			throws Exception {
			log.info("=======================preHandle============================");
		return true;
		//리턴이 boolean타입
		//true면 정상 진행 false면 다른곳으로 강제로 보냄
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,//나오기 전
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
			log.info("===========================postHandle=======================");
	}
	
	
}
