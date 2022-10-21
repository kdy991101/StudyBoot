package com.iu.home.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController {
//	@GetMapping("/fileDown/qna")
//	public ModelAndView fileDown(QnaFileVO qnaFileVO)throws Exception{
//		ModelAndView mv = new ModelAndView();
//		//db에서 정보를 조회해야 함
//		qnaFileVO.setFileName("134dbc18-9179-4277-9459-1e08859db576_.jpg");
//		qnaFileVO.setOriName("pp.jpg");
//		mv.addObject("fileVO",qnaFileVO);
//		mv.addObject("path", "qna");
//		mv.setViewName("fileManager");
//		return mv;
//	}
//	@GetMapping("/fileDown/notice")
//	public ModelAndView fileDownNotice(QnaFileVO qnaFileVO)throws Exception{
//		ModelAndView mv = new ModelAndView();
//		//db에서 정보를 조회해야 함
//		qnaFileVO.setFileName("yy.jpg");
//		qnaFileVO.setOriName("yy.jpg");
//		mv.addObject("fileVO",qnaFileVO);
//		mv.addObject("path", "notice");
//		mv.setViewName("fileManager");
//		return mv;
//	}
//	=========================================위에를 하나로..
	@GetMapping("/fileDown/{path}")//RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable String path, QnaFileVO qnaFileVO)throws Exception{
//		@PathVariable=경로변수
//		@GetMapping("/fileDown/{path}") path안에있는 값을 변수로 사용하겠다~
		log.info("path : {} "+path);
		
		ModelAndView mv = new ModelAndView();
		
		qnaFileVO.setFileName("gg.jpg");
		qnaFileVO.setOriName("gg.jpg");
		
		mv.addObject("fileVO",qnaFileVO);
		mv.addObject("path", path);
		mv.setViewName("fileManager");
		
		return mv;
	}
}
