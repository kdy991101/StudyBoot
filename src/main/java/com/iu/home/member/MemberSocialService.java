package com.iu.home.member;

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
//		userRequest.getAccessToken();
//		userRequest.getAdditionalParameters();
//		userRequest.getClass();
//		userRequest.getClientRegistration();
		return null;
	}

}
