package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.iu.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler{
	
	@Override
		public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
			// TODO Auto-generated method stub
			request.getSession().invalidate();
			//authentication = 사용자의 인증, 인가에 관련되어있는 정보가 담겨있음
			//1. 일반 로그인한 사람인지 socialLogin을 이용하는 사람인지 구분을해야 함
			log.info("Auth => {}", authentication);
			

			
//			2
//			if(social != null && social.equals("kakao")) {
//				
//			}else if(social != null && social.equals("google")) {
//				
//			}else {
//				
//			}
			
			request.getSession().invalidate();
		}

}
