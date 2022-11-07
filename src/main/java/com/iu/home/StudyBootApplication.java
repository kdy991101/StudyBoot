package com.iu.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy//aop를 작동하기위한 어너테이션
//없어도 돌아가기는 하지만 혹시,,
@EnableTransactionManagement// 예외처리를 발생시키고 rollBack을 시키기 위한 어너테이션
@EnableScheduling//특정 시간이나 주기적으로 반복되는 작업을 해야 할 때
public class StudyBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyBootApplication.class, args);
	}

}
