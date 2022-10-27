package com.iu.home.board.qna;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.home.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("update")
	public ModelAndView setUpdate(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("QnaVO", qnaVO);
		mv.setViewName("board/update");
		return mv;	
	}
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int setFileDelete(QnaFileVO qnaFileVO)throws Exception{
		int result = qnaService.setFileDelete(qnaFileVO);
		
		return result;
	}
	
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		List<QnaVO> ar = qnaService.getList(pager);
		mv.addObject("list", ar);
		mv.addObject("Pager", pager);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("QnaVO", qnaVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	@PostMapping("add")
	public String setAdd(QnaVO qnaVO, RedirectAttributes redirectAttributes)throws Exception{
		int result = qnaService.setAdd(qnaVO);
		redirectAttributes.addAttribute("result", result);
		return "redirect:./list";
	}
	@GetMapping("add")
	public String setAdd()throws Exception{
		return "board/write";
	}
	
}
