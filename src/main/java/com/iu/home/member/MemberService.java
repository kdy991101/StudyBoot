package com.iu.home.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	public int setAdd(MemberVO memberVO)throws Exception{
		int result =0;
		int result1= memberMapper.setAdd(memberVO);
		int result2= memberMapper.setRoleAdd(memberVO);
		
		if(result1 == 1 && result2 == 1) {
			result = 1;
		}
		if (result<1) {
			throw new Exception();
		}
		return result;
	}
	
	//로그인ㅊ ㅓ리는 security에서 할거라 이제 여기서 안씀
//	public MemberVO getLogin(MemberVO memberVO)throws Exception{
//		return memberMapper.getLogin(memberVO);
//	}
	
	public int getIdCheck(MemberVO memberVO)throws Exception{
		return memberMapper.getIdCheck(memberVO);
	}
//	--------------------------------- 사용자 정의 검증 메서드
	// 어너테이션으로 검증한 결과를 담는 것 BindingResult
	// 검증한 결과물을 BindingResult 에 담음
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean check = false;
		// check가 false면 에러가 없다~ 검증에 성공했다 라는 것
		// check가 true이면 두개의 pw가 맞지 않다는 것

		//1.어너테이션으로 검증
		check = bindingResult.hasErrors();//true가 들어감
		
		//2.password 일치하는지 검증
		if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
			//같지 않다면 ~
			check = true;
			//에러메세지
			//1.
//			bindingResult.rejectValue("멤버변수명(Path)", "properties의 key를 작성해준다");
			bindingResult.rejectValue("pwCheck", "member.password.notEqual");
		}
		
		//3.id가 중복인지 검증해볼 것
		if(this.getIdCheck(memberVO)>0) {
			check = true;
			bindingResult.rejectValue("id", "member.id.notId");
		}
		return check;
		//이거를 어디서 호출하냐 memberController에서 함
	}

	


}
 