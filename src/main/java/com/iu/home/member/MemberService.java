package com.iu.home.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		return memberMapper.getLogin(memberVO);
	}
	


}
