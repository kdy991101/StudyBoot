package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSuccess implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//onhandler는 대부분 ~~가 발생했을 때에를 말함
		//Authentication = 인증, 인가 == 로그인
		//security에서 호출할거임
							// ex) /member == /member에서만 사용이 가능함
		
//		========================================아이디 기억하기===================================
//		Cookie checkCookie = new Cookie("idCheck", request.getParameter("rememberId"));
//		log.info("check ==>>> {}", checkCookie.getValue());
		
		String checkId = request.getParameter("rememberId");

		if (checkId != null && checkId.equals("on")) {
			log.info("===================== 로 그 인 성 공 ===================");
			log.info("Authentication => {}", authentication);
			log.info("ID ==>> {} ", request.getParameter("id"));
			Cookie cookie = new Cookie("userId", request.getParameter("id"));
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60);//최대 나이 == cookie를 client에 얼마동안 저장할 것인지 초단위임
			cookie.setPath("/");//cookie를 같은 도메인 내에서 어느 url에 사용할 것인지 지정하는 것 
			
			
			log.info("checkIc ==>> {}", checkId);
//		=====================================================================================
		
		response.addCookie(cookie);//id를 쿠키에 담아 client로 보냄
		}else {
			Cookie[] cks = request.getCookies();
			for(Cookie cookie : cks) {
				if(cookie.getName().equals("userId")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");//쿠키 삭제 시 쿠키를 만들때의 path와 동일해야 한다
					response.addCookie(cookie);
					break;
				}
			}
			
		}
		response.sendRedirect("/");
		
	}
	

}
//else {
//Cookie cookie = new Cookie("userId", request.getParameter("id"));
//cookie.setHttpOnly(true);
//cookie.setMaxAge(0);//최대 나이 == cookie를 client에 얼마동안 저장할 것인지 초단위임
//cookie.setPath("/");//cookie를 같은 도메인 내에서 어느 url에 사용할 것인지 지정하는 것 
//
//response.addCookie(cookie);//id를 쿠키에 담아 client로 보냄
//}
