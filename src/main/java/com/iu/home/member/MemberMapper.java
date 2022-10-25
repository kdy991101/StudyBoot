package com.iu.home.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	
	public int setAdd(MemberVO memberVO)throws Exception;
	
	public int setRoleAdd(MemberVO memberVO)throws Exception;
	
	public MemberVO getLogin(MemberVO memberVO)throws Exception;
	

}
