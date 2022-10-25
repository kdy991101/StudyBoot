package com.iu.home.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		return memberMapper.getLogin(memberVO);
	}
	


}
