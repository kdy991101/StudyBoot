//package com.iu.home.member;
//
//import java.util.List;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/member/*")
//public class MemberController {
//	@Autowired
//	private MemberService memberService;
//	
//	@GetMapping("add")
//	public String setAdd()throws Exception{
//		return "member/add";
//	}
//	
//	@PostMapping("add")
//	public String setAdd(MemberVO memberVO)throws Exception{
//		int result = memberService.setAdd(memberVO);
//		return "redirect:./login";
//	}
//	
//	@GetMapping("login")
//	public void getLogin()throws Exception{
//		
//	}
//	@PostMapping("login")
//	public String getLogin(MemberVO memberVO, HttpSession session)throws Exception{
//		memberVO = memberService.getLogin(memberVO);
//		session.setAttribute("member", memberVO);
//		return "redirect:/"; 
//		
//	}
//	
//	@GetMapping("logout")
//	public String getLogout(HttpSession session)throws Exception{
//		session.invalidate();//비유효하게 하겠다 session을 종료하겠다~~
//		return "redirect:/";
//	}
//	
////	@GetMapping("idCheck")
////	@ResponseBody
////	public int getIdCheck(MemberVO memberVO)throws Exception{
////		int result = memberService.getIdCheck(memberVO);
////		return result;
////	}
//	@GetMapping("idCheck")
//	@ResponseBody
//	public int getIdCheck(MemberVO memberVO)throws Exception{
//		return memberService.getIdCheck(memberVO);
//	}
//	
//
//}
package com.iu.home.member;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/*")
public class MemberContoller {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("logoutResult")
	public String socialLogout() throws Exception{
		return "redirect:../";
	}

//	@GetMapping("logout")
//	public String logout(HttpSession session) throws Exception {
//		log.info("========내가만든 logout 메서드");
//		session.invalidate();
//		return "redirect:/";
//	}

	// 회원가입
	@GetMapping("add")
	public void join(@ModelAttribute MemberVO memberVO) throws Exception {

	}

	@PostMapping("add")
	public ModelAndView join(ModelAndView mv, @Valid MemberVO memberVO, BindingResult bindingResult) throws Exception {
		
//		if (bindingResult.hasErrors()) {
//			// 검증에 실패하면 회원가입하는 jsp로 이동
//			mv.setViewName("member/add");
//			return mv;
//		}
		
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		
		if (check) {
			// check가 true라면
			if (bindingResult.hasErrors()) {//어너테이션만 검증하는 것임 그래서 pw를 검증하는 메서드를 호출해줘야 함
				// 검증에 실패하면 회원가입하는 jsp로 이동
				mv.setViewName("member/add");
				
				//============================================
				List<FieldError> errors = bindingResult.getFieldErrors();
				
				for(FieldError fieldError:errors) {
					log.info("=========================================================");
					log.info("fieldError : {}", fieldError);
					log.info("field = {} ", fieldError.getField());
					log.info("Message = {}", fieldError.getRejectedValue());
					log.info("Default = {}", fieldError.getDefaultMessage());
					log.info("Code = {} ", fieldError.getCode());
					mv.addObject(fieldError.getField(), fieldError.getDefaultMessage());
					log.info("=========================================================");
				}
				return mv;
			}
		}
		
		int result = memberService.setAdd(memberVO);
		mv.setViewName("redirect:../");
		return mv;
	}

	@GetMapping("login")
	public void login(@RequestParam(defaultValue = "false",required = false) boolean error, String message, Model model) throws Exception {
		//Controller에서 받아 jsp로 다시 보내도 되고 또는 
		if(error) {
			model.addAttribute("msg", "ID또는 PW를 확인해봐");
		}
	}

	// 로그인
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session) throws Exception {
		return "member/login";
	}

	@GetMapping("idCheck")
	@ResponseBody
	public int getIdCheck(MemberVO memberVO) throws Exception {
		return memberService.getIdCheck(memberVO);
	}
	
	@GetMapping("myPage")
	public void myPage()throws Exception{
	}
	
	@GetMapping("delete")
	public ModelAndView setDelete(HttpSession httpSession, String pw)throws Exception{
		//1, social, 일반 구분해야 함
		//session에서 속성을 가지고 오아야 함
		ModelAndView mv = new ModelAndView();

		SecurityContextImpl context = (SecurityContextImpl) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication authentication =context.getAuthentication();//인증에 관련되어있는 정보
		MemberVO memberVO=(MemberVO) authentication.getPrincipal();//memberVO가 나옴
		log.info("AUthen => {}", authentication);
		log.info("Member =++++++++++++++++++++++> {}", memberVO);

		int	result=memberService.setDelete(memberVO);
		
		if (result > 0) {
			//성공했으면 로그아웃
			mv.setViewName("redirect:/member/logout");
		}else {
			//탈퇴 실패했을때에는 탈퇴실패, 관리자문의 메시지
		}
		return mv;
		
		
	}
}
