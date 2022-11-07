package com.iu.home.member.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginFail implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		log.info("================ 로 그 인 실 패 ===============");
		log.info("LocalMessage ==>> {}", exception.getLocalizedMessage());
		log.info("Cause ==>> {}", exception.getCause());
		log.info("Message ==>> {}", exception.getMessage());

//		String name = exception.getClass().toString();
//		name = name.substring(name.lastIndexOf("."));
//		if(name.equals(".BadCredentialsException")) {
//			name = "비번이 틀렸쟈납";
//		}
		
		//모든 class는 데이터 타입이다
		//참조변수 instanceof class명 ->
		String result = null;
		if(exception instanceof BadCredentialsException) {
//			exception이 BadCredentialsException로 만든아이입니까?
			result = "비번이 틀려쪙";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			result = "아이디가 없쪙";
		}else {
			result = "로 그 인 실 패";
		}
//		result = URLEncoder.encode(result, "UTF-8");
//		response.sendRedirect("/member/login?error=true&message="+result);
		//jsp로 바로 찾아감
//		request.setAttribute("msg", result);
//		request.getRequestDispatcher("/member/login.jsp").forward(request, response);
		request.setAttribute("msg", result);
		request.getRequestDispatcher("/member/login?message="+result).forward(request, response);
	}

}
