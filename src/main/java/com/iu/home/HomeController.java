package com.iu.home;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iu.home.board.qna.QnaMapper;
import com.iu.home.board.qna.QnaVO;
import com.iu.home.util.Pager;

@Controller
public class HomeController {
	
//	@Value("${my.message.hi}")
	private String message;
	
	@Value("${my.default}")
	private String app;
	
//	private final Logger log = LoggerFactory.getLogger(HomeController.class);
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QnaMapper qnaMapper;
	
	
	
	@GetMapping("/")
	public String home() throws Exception {
		log.info("===========================================================");
		log.info("===========================================================");
		log.info("message {}", message);
		log.info("default {}", app);
		log.info("===========================================================");
		log.info("===========================================================");
		Pager pager = new Pager();
		
		List<QnaVO> ar = qnaMapper.getList(pager);
		
		log.info("list : {}  size : {}", ar, ar.size());//문자열만 넣어야 함
		//중괄호 안에 ar을 넣어라~~
		
		//프린트 문
		System.out.println("sysout list : "+ar+"size : "+ar.size());
		return "index";
	}

}