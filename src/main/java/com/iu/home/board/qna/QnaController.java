package com.iu.home.board.qna;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.home.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@GetMapping("hack")
	@ResponseBody
	public int hack(QnaVO qnaVO)throws Exception{
		qnaService.setAdd(qnaVO);
		
		return 1;
	}
	
	@PostMapping("summerFileDelete")
	@ResponseBody
	public boolean setSummerFileDelete(String fileName)throws Exception{
		log.info("fileName ===>>> {}",fileName);
		return qnaService.setSummerFileDelete(fileName);
	}
	
	@PostMapping("summerFile")
	@ResponseBody
	public String setSummerFile(MultipartFile file)throws Exception{
		log.info("files ==>> {}", file);
		
		return file.getOriginalFilename();
	}
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("update")
	public ModelAndView setUpdate(QnaVO qnaVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("QnaVO", qnaVO);
		mv.setViewName("board/update");
		return mv;	
	}
	
//	@PostMapping("fileDelete")
//	@ResponseBody
//	public int setFileDelete(QnaFileVO qnaFileVO)throws Exception{
//		int result = qnaService.setFileDelete(qnaFileVO);
//		
//		return result;
//	}
	
	
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
	
	@GetMapping("add")
	public String setAdd(@ModelAttribute QnaVO qnaVO)throws Exception{
		return "board/write";
	}

	@PostMapping("add")
	public ModelAndView setAdd( @Valid QnaVO qnaVO, BindingResult bindingResult, RedirectAttributes redirectAttributes)throws Exception{
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			//검증에 실패하면 회원가입하는 jsp로 이동
			mv.setViewName("board/write");
			return mv;
		}
		int result = qnaService.setAdd(qnaVO);
		redirectAttributes.addAttribute("result", result);
		mv.setViewName("redirect:./list");
		return mv;
	

}

	
}
