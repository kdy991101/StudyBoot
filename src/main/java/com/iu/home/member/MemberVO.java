package com.iu.home.member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class MemberVO implements UserDetails, OAuth2User{
	


	@NotBlank
	private String id;
	@NotBlank
	@Size(max = 10, min = 4)
	private String pw;
	private String pwCheck;
	//비번의 두개가 맞는지 틀린지 검증하는 어너테이션은 없음
	@NotBlank
	private String name;
	@Email
	@NotBlank
	private String email;
	private boolean enabled;
	private List<RoleVO> roleVOs;
	
	//OAuth2User, Token정보를 저장하는 attr
	private Map<String, Object> attributes;
	
	@Range(max = 150, min = 0)
	private int age;
	
	@Past
	private Date birth;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		// ? any extends상속 GrantedAuthority
		// <? super T> super - 부모, = T나 ,  T의 부모들을 상속받겠다,,?
		// list, set이 상속받ㅇ ㅁ list도 컬렉션타입임..
		//list는 인터페이스라서 객체를 만들지 못함
		//getAuthorities는 이 유저의 권한에 대한 정보를 담아야 함
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
				
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		// PW 반환
		return this.pw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		// ID 반환
		return this.id;
	}
	@Override
	public boolean isAccountNonExpired() {//Expired = 만료됨  //자바에 is는 boolean "계정이 만료되지 않았습니꺼어억"
		// TODO Auto-generated method stub
		//사용자 계정의 만료 여부 
		//true : 만료x
		//false : 만료
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {// 계정이 잠기지 않았나요
		// TODO Auto-generated method stub
		//계정의 잠김 여부
		//true : 계정이 잠기지 않음
		//false : 계정 잠김, 로그인 불가
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호의 만료여뷰 기간이 끝난 PW인지 아닌지 (임시 PW)
		// TODO Auto-generated method stub
		//true : 만료 안됨
		//false : 만료됨, 로그인 불가
		return true;
	}
	
	//isEnabled = 계정 사용 여부
	//true = 계정 활성화
	//false = 계정 비활성화
	public boolean isEnabled() {
		return true;
	}
	//카카오, 구글, 네이버 회사 이름을 넣을거임 카카오만
	private String social;
	
	@Override
	public Map<String, Object> getAttributes() {
		//
		// TODO Auto-generated method stub
		return this.attributes;
	}
}
