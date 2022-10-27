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

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/*")
public class MemberContoller {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	
	// 회원가입
	@GetMapping("add")
	public void join()throws Exception{
		
	}
	
	@PostMapping("add")
	public String join(MemberVO memberVO)throws Exception{
		memberService.setAdd(memberVO);
		return "redirect:./login";
	}
	
	@GetMapping("login")
	public void login()throws Exception{
		
	}
	
	// 로그인
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.getLogin(memberVO);
		session.setAttribute("member", memberVO);
		return "redirect:/";
		}
	
	@GetMapping("idCheck")
	@ResponseBody
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberService.getIdCheck(memberVO);
	}

}
