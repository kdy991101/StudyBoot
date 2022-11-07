package com.iu.home.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.iu.home.member.MemberSecurityService;
import com.iu.home.member.MemberSocialService;
import com.iu.home.member.security.LoginFail;
import com.iu.home.member.security.LoginSuccess;
import com.iu.home.member.security.LogoutCustom;
import com.iu.home.member.security.LogoutSuccessCustom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   @Autowired
   private LoginSuccess loginSuccess;
   @Autowired
   private LoginFail loginFail;
   @Autowired
   private LogoutCustom logoutCustom;
   @Autowired
   private LogoutSuccessCustom logoutSuccessCustom;
   @Autowired
   private MemberSecurityService memberSecurityService;
   @Autowired
   private MemberSocialService memberSocialService;
   
   @Bean
   //public을 선언하면 default로 바꾸라는 메세지 출력
   WebSecurityCustomizer  webSecurityCustomizer() {
      
      
      //Security에서 무시해야하는 URL 패턴 등록
      return web -> web
            .ignoring()
            .antMatchers("/images/**")   //보안에서 무시하세요
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/favicon/**")
            .antMatchers("/resources/**")
            .antMatchers("/file/**");
   }
   
   @Bean
   SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
      
      httpSecurity
      		   .csrf()
      		   .disable()
      		   .cors()
               .configurationSource(this.configurationSource())
               .and()
            .authorizeRequests()   //인가 요청
//               .antMatchers("/").permitAll()   //인덱스("/")페이지는 누구한테나 접근을 허용하겠다
               .antMatchers("/login").permitAll()
               .antMatchers("/logout").permitAll()
               .antMatchers("/admin").hasRole("ADMIN")
               .antMatchers("/manager").hasRole("MANAGER")
               .antMatchers("/qna/list").permitAll()
               //.antMatchers("/qna/**").hasRole("MEMBER")   //Role이 MEMBER인 사람만 qna로 시작하는 모든 경로 접근을 허용하겠다.
//               .anyRequest().authenticated()   //위에 입력한 antMatchers를 제외하고는
               .anyRequest().permitAll()
               .and()
//            httpSecurity.formLogin()   //and()에 세미콜론을 쓰고 httpSecurity 변수를 사용해 다시 새로 코드 작성가능
            .formLogin()            //로그인 폼 인증 설정
            .loginPage("/member/login") //내장된 로그인폼을 사용하지 않고 개발자가 만든 폼 요청 URL
//            .loginProcessingUrl("login")   //로그인을 진행 요청할 form 태그의 action의 주소 지정
            .passwordParameter("pw")   //패스워드에 해당하는 파라미터 이름은 password이지만, 개발자가 다른이름으로 파라미터를 사용할 때
            .usernameParameter("id")   //아이디에 해당하는 파라미터 이름은 username이지만, 개발자가 다른이름으로 파라미터를 사용할 때
            .defaultSuccessUrl("/")      //인증에 성공할 경우 요청할 URL
            .successHandler(loginSuccess)   //로그인에 성공할 경우 loginSuccess 클래스로 보냄
            .failureHandler(loginFail)
            //.failureUrl("/member/login?error=true&&message=LoginFail")   //로그인 인증에 실패 했을경우 "/member/login"으로 보냄
               .permitAll()
               .and()
            .logout()
               .logoutUrl("/member/logout")
               //.logoutSuccessUrl("/")
               .logoutSuccessHandler(logoutSuccessCustom)
               .addLogoutHandler(logoutCustom)
               .invalidateHttpSession(true)   //세션을 지울거면 true 아니면 false
               .deleteCookies("JESSIONID")
               .permitAll()
               //rememberMe
               .and()
            .rememberMe()//rememberMe 설정 start
            	.rememberMeParameter("rememberMe")//여기서 파라미터를 설정 어떤 파라미터가 넘어오면 설정할거니..("파라미터 명")
            	.tokenValiditySeconds(300)//평생 자동로그인을 해줄 수 없기 때문에 언제동안 자동로그인을 시켜줄 것인지/초단위임/로그인 유지 시간 session유지시간이 아님
            	//토큰이란,,,
            	.key("rememberMe")// 인증받은 사용자의 정보로 토큰 생성시 필요, 필수이다~
            	.userDetailsService(memberSecurityService)//인증 절차를 실행할 userDetailService, 필수이다~ 
            	//공공장소에는 쓰면 안댐
            	.authenticationSuccessHandler(loginSuccess)//로그인 성공했을 때 Handler
            	.and()
            .oauth2Login()//Social Login 설정
            	.userInfoEndpoint()
            	.userService(memberSocialService)
            	;
      
      return httpSecurity.build();
   }
   
   //평문(Clear Text)을 암호화 시켜주는 객체생성
   @Bean
   public PasswordEncoder getEncoder() {
      
      return new BCryptPasswordEncoder();
   }
   
   
   CorsConfigurationSource configurationSource() {
	   CorsConfiguration configuration = new CorsConfiguration();
	   configuration.setAllowedOrigins(Arrays.asList("http://192:168.1.2:5500", "*"));//List<T> = 타입을 말한다
	   //Allowed = 허락하겠다
	   configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
	   
	   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   source.registerCorsConfiguration("/**", configuration);
	   return source;
   }
   
}