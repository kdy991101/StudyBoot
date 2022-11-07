package com.iu.home;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.member.MemberVO;
import com.iu.home.util.Pager;
import com.nimbusds.jose.proc.SecurityContext;

@Controller
public class HomeController {
	
//	@Value("${my.message.hi}")
	private String message;
	
	@Value("${my.default}")
	private String app;
	
//	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());

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
	
	@GetMapping("/")
	public String home(HttpSession session) throws Exception {
		log.info("===========================================================");
		log.info("===========================================================");
		Enumeration<String> en = session.getAttributeNames();
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
		SecurityContextImpl context = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
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