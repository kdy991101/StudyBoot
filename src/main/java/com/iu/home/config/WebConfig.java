package com.iu.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration//어떤 설정파일
@Slf4j//로그기록을 찍어볼 때
public class WebConfig implements WebMvcConfigurer {
//	spring은 어떠 특정한 메서드를 호출할 것임
//	mvc->디자인 패턴...?
//	WebMvcConfigurer
	
	@Value("${app.upload.base}")//spEL
	private String filePath;
	
	@Value("${app.url.path}")
	private String urlPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("=========================================================");
		log.info("filePath {} "+filePath);
		log.info("urlPath {} "+urlPath);
		log.info("=========================================================");
		
//		registry -> 회원가입, 등록, 추가 ~~
		registry.addResourceHandler(urlPath) //어떤 요청 주소를 작성해주는 것, mapping작업과 동일
		.addResourceLocations(filePath);
		
	}

}
