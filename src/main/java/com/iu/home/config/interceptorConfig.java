package com.iu.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.iu.home.interceptors.StudyInterceptor;
import com.iu.home.interceptors.TestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration//Configuration설정파일이다 라는 것을 알려주는 어너테이션
@Slf4j//로그기록
public class interceptorConfig implements WebMvcConfigurer{
//WebMvcConfigurer를 오버라이딩해야 함 그래야 context.xml역할을 함

	//의존성 주입이 필요하지만 오토와이어드로 설정해두면 spring이 대신 해줌 
	//내가 하는 것이 아닌 spring한테 제어권을 주는 것을 ioc
	//ioc=> Inversion Of Control
	@Autowired
	private TestInterceptor testInterceptor;
	@Autowired
	private StudyInterceptor studyInterceptor;
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
		@Override
		public void addInterceptors(InterceptorRegistry registry) {

		//어떤 요청이 왔을때 어떤 처리를 할 것인가를 등록해줘야 함
		//interceptor역할을 하는 애들 가져와야 함
//			testInterceptor가 handler를 구현했음
			registry.addInterceptor(testInterceptor)
//			interceptor에 적용할 url적용
			//method 체이닝 -> 메서드를 끊지않고 연결시키는 것 .으로 이음		
//			/밑에 qna, notice로 시작하는 모든 것들은 여기를 거쳐가라~~
			.addPathPatterns("/qna/**")
			.addPathPatterns("/notice/**")
//			interceptor에 제외할 특정 url적용
//			qna의 detail, write을 빼겠다
			.excludePathPatterns("/qna/detail")
			.excludePathPatterns("/qna/add");
			
//		==========================================================================추가 interceptor	
			registry.addInterceptor(studyInterceptor)
					.addPathPatterns("/qna/**");
			
//		WebMvcConfigurer.super.addInterceptors(registry);
			
			registry.addInterceptor(localeChangeInterceptor)
					.addPathPatterns("/**");// /로 시작하는 모든 애들은 여기를 지나가라
		}
}
