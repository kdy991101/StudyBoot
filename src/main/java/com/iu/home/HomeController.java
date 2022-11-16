package com.iu.home;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.iu.home.board.qna.PostVO;
import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;
import com.iu.home.util.Pager;
import com.iu.home.util.TestInterface;
import com.nimbusds.jose.Header;
import com.nimbusds.jose.proc.SecurityContext;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
	
//	@Value("${my.message.hi}")
	private String message;
	
	@Value("${my.default}")
	private String app;
	
//	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/arrow")
	public void arrow() {
		//Lamda식(JS Arrow Function)
		TestInterface t = (m)->System.out.println(m);
		t.info("test");
		
	}

	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "admin_role";
	}
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "manager_role";
	}
	@GetMapping("/member")
	@ResponseBody
	public String member() {
		return "member_role";
	}	 
	
	@GetMapping("/web")
	public String webClientTest() {
		//객체를 만들지 않고 사용가능한 메서드..?
//		Calendar ca = Calendar.getInstance();
//		WebClient webClient =  WebClient.create();
//		// 또는
//     	webClient = WebClient.create("http://localhost:81");
//		// base url 형식 = http://localhost:81
//		
//     	webClient = WebClient.builder()
//     						 .baseUrl("")
//     						 .defaultHeader("Key", "value")
//     						 .defaultCookie("key", "value")
//     						 .build();
//     	
//     	Map<String, Object> map = new HashMap<>();
//     	map.put("page", 1);
//     	map.put("kind", "title");
//     	
//     	PostVO postVO = new PostVO();
//     	postVO.setId("1");
//
//     	webClient.get()
//     			 .uri("/posts/1", postVO)
//     			 .header("key", "value")
//     			 .cookie("key", "value");
//     	
//     	webClient.post()
//     			 .uri("")
//     			 .body
		WebClient webClient = WebClient.builder()
				   .baseUrl("https://jsonplaceholder.typicode.com/")
				   .build();
		Flux<PostVO> res = webClient.get()
						.uri("posts")
						.retrieve()
						.bodyToFlux(PostVO.class);

		PostVO postVO = res.blockFirst();
		
		//메서드를 만드려면 선언을 해야함 public void test(PostVO postVO){}
		//a.test(postVO)
		
		//화살표 함수
		//subscribe = 반복문 같은거임 res는 여러개임 여러개 중에 하나를 꺼내 s에 담으라는 것
		res.subscribe((s)->{
			PostVO pvo = s;
			log.info("ID ==>> {}", s.getId());
		});
		
		log.info("Result => {}", postVO);
     	
		return "";
	}
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
	      
	      //세션에 저장되있는 키값을 가져온다(get)
	      //Spring Security에 있는 세션
	      Enumeration<String> en = session.getAttributeNames();
	      
	      //1. Headers
	      HttpHeaders headers = new HttpHeaders();
	      // header : Key:Value
//	      headers.add("key", "value");
//	      headers.set헤더명("값");
	      
	      //2. Parameter
	      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	      params.add("key", "value");
	      
	      //3. 요청 정보 객체(1,2 번을 모음)
	      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, headers);
	      
	      //4. 전송 후 결과
	      List<PostVO> posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class, request);
//	      PostVO postVO = response.getBody();
	      
	      log.info("posts ==>> {} ", posts);
		
		log.info("===========================================================");
		log.info("===========================================================");
		while(en.hasMoreElements()) {
			String key = en.nextElement();//있으면 그 요소를 꺼내달라
			log.info("key => {} ", key);//key => SPRING_SECURITY_CONTEXT라는 이름으로 무언가 들어가있다~~
			//session에 무슨 속성들이 들어가있을까...
		}
//		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT")	;
//		log.info("obj -> {}", obj);//SecurityContextImpl<=타입..? [Authentication=UsernamePasswordAuthenticationToken 
//									[Principal=MemberVO(id=admin1, pw=$2a$10$Hv5UPXsf2qVfeROdqMlz3.lhpjbaFFZhnPuiGqmrm3TR/g/fQ.Tpe, pwCheck=null, 
//									name=admin1, email=admin1@gmail.com,enabled=true, roleVOs=[RoleVO(num=1, roleName=ROLE_ADMIN), RoleVO(num=2, 
//									roleName=ROLE_MANAGER), RoleVO(num=3,roleName=ROLE_MEMBER)], age=0, birth=null), Credentials=[PROTECTED], "PROTECTED=보호되고있다~"
//									Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=65EE6FD53CEED4D4BCDDA2110A7119E6],
//									Granted Authorities=[ROLE_ADMIN, ROLE_MANAGER, ROLE_MEMBER]]] <= 권한에 대한 정보
		
		SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");//사용자 정보가 담겨있음
		if(context != null) {			
			log.info("context -> {}", ((MemberVO)context.getAuthentication().getPrincipal()).getId());
		}
		
		log.info("message {}", message);
		log.info("default {}", app);
		log.info("===========================================================");
		log.info("===========================================================");
		Pager pager = new Pager();
		
//		List<QnaVO> ar = qnaMapper.getList(pager);
//		
//		log.info("list : {}  size : {}", ar, ar.size());//문자열만 넣어야 함
//		//중괄호 안에 ar을 넣어라~~
//		
//		//프린트 문
//		System.out.println("sysout list : "+ar+"size : "+ar.size());
		return "index";
	}

}