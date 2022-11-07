package com.iu.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSecurityService  implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;//DI/의존한다~~ 여기에 주입을 해야 함 우리가 직접하는 것은 아님 IOC기법을 사용할 거임 이것이 오토와이드
	
	//인터페이스는 추상메서드가 있음
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		MemberVO memberVO = new MemberVO();
//		memberVO.setId(username);
		log.info("=========================================================");
		log.info("=====================로그인 시도 중==========================");
		log.info("=========================================================");
		MemberVO memberVO = memberMapper.getLogin(username);
		log.info("MemebrVO {}", memberVO);
		return memberVO;
	}
}
//여기까지는 pw를 검증하지 않음//로그인이 되어있는지 판단이 불가능한거임 최종적으로 pw까지 검증하는 것은 filter에서 함