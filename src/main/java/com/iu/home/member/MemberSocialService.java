package com.iu.home.member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSocialService extends DefaultOAuth2UserService {
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("social login 시도");
		log.info("UserRequest => {}", userRequest);
		log.info("getAccessToken => {}", userRequest.getAccessToken());
		log.info("getAdditionalParameters => {}", userRequest.getAdditionalParameters());
		log.info("getClass => {}", userRequest.getClass());
		log.info("getClientRegistration => {}", userRequest.getClientRegistration());
		log.info("===================================사 용 자 정 보==========================");
		String social = userRequest.getClientRegistration().getRegistrationId();		
		OAuth2User auth2User = this.socialJoinCheck(userRequest);
//		if(social.equals("kakao")) {
//			
//		}
		return auth2User;
//		return null;
	}
	
	private OAuth2User socialJoinCheck(OAuth2UserRequest userRequest){
		//회원가입 유무
		OAuth2User auth2User = super.loadUser(userRequest);
		log.info("Name => {} ", auth2User.getName());
		log.info("Attr => {} ", auth2User.getAttributes());
		log.info("auth => {} ", auth2User.getAuthorities());
		
		Map<String, Object> map = auth2User.getAttributes();
		
		Iterator<String> keys = map.keySet().iterator();//map을 열거형으로 변환하여 꺼내기
		//리스트는 아니기때문에 몇개가 있는지 알 수가 없기 때문에 while문을 사용한다
		while(keys.hasNext()) {
			String key = keys.next();
			log.info("key => {}", key);
//			map.get(key)
		}
		//		log.info("ClassName => {}", auth2User.getAttribute("properties").getClass());
		Map<String, String> lm = auth2User.getAttribute("properties");
		Map<String, Object> ka = auth2User.getAttribute("kakao_account");
		MemberVO memberVO = new MemberVO();
		memberVO.setId(auth2User.getName());//id
		//pw가 없으므로 비워두거나 랜덤한 값으로 생성한다
		//memberVO.setPw(null);
		memberVO.setName(lm.get("nickname"));
		memberVO.setEmail(ka.get("email").toString());
		
		memberVO.setSocial(userRequest.getClientRegistration().getRegistrationId());
		memberVO.setAttributes(auth2User.getAttributes());
		
		//Role 임의 작성
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		list.add(roleVO);
		memberVO.setRoleVOs(list);
		
		return memberVO;
	};
}
