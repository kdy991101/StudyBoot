package com.iu.home.config;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.websocket.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer{
	// ***-context.xml
	// <bean class="" id="bean의 이름"> == new 생성자 (객체 생성)
	
	//따로 지정해 주지 않으면 string
//	@Bean
//	public String  getString() {
//		return new String();
//	}
	
	@Bean
	public LocaleResolver locale() {
//		locale => 각국에서 사용하는 언어,,,,,
		//session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
//		resolver.
		
//		return resolver;
		
		//Cookie
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		cResolver.setCookieName("lang");
		return cResolver;
	
	}
	//interceptor객체를 만들어줘야 함
//	/LocaleChangeInterceptor라이브러리
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		//setParamName => 쿠키에 저장하기 위해서 파라미터에 담아서 보내줘야 하는데 그걸 하는 역할
		//parameter를 받아 언어를 구분함
		//url?lang=ko
		return changeInterceptor;
	}
}
