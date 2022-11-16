package com.iu.home.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.iu.home.member.MemberVO;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

	@Component
	@Slf4j
	public class LogoutSuccessCustom implements LogoutSuccessHandler{
		
		@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
		private String id;
		
		@Value("${kakao.uri}")
		private String uri;
		@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				// TODO Auto-generated method stub
			MemberVO memberVO = (MemberVO)authentication.getPrincipal();//memberVO
			String social = memberVO.getSocial();
			
//			1
			if(social != null) {
					if(social.equals("kakao")) {
					
						response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+id+"&logout_redirect_uri="+uri);
//							response.sendRedirect("https://developers.kakao.com/");
						
//						RestTemplate restTemplate = new RestTemplate();
//			             //header X
//			            //parameter X
//			             MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//			             map.add("client_id", "f052ef7b0055bc9d855ba9a70d8c742d");
//			             
//			             
//			             log.info("kakao logout");
//			             ResponseEntity<String> res   = restTemplate.getForEntity("https://developers.kakao.com/logout", String.class, null);
//			             log.info("res => {}", res);
//			             response.sendRedirect("/");
					}else if(social.equals("google")) {
						
					}else {
						
					}
			}else {
				response.sendRedirect("/");				
			}
		}
	
	}
