package com.iu.home.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Value("${social.kakao.admin}")
	private String adminKey;
	
	public int setDelete(MemberVO memberVO)throws Exception{
		//1. WebClient생성
		WebClient webClient = WebClient.builder()
									   .baseUrl("https://kapi.kakao.com/")
									   .build();
		//2. parameter
		// key, value형식임
		//VO 는 변수명과 값이 있기 때문에 key, value형식
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("target_id_type", "user_id");
		map.add("target_id", memberVO.getId());
		
		Mono<String> res=webClient.post()
				 .uri("v1/user/unlink")
				 .body(BodyInserters.fromFormData(map))
				 .header("Authorization", "KakaoAK "+adminKey)
				 .header("Content-Type", "application/x-www-form-urlencoded")
				 .retrieve()
				 .bodyToMono(String.class);
		
		log.info("WebClientResult ==>> {}", res.block());
		return 1;
	}
	
	public int setDelete2(MemberVO memberVO)throws Exception{
		int result = 0;
		RestTemplate restTemplate = new RestTemplate();//요청..
		// Header
		//header만드는 방법 여러가지임
		HttpHeaders headers = new HttpHeaders();
//		headers에 넣는 방법은 3가지 add, set, ??
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);// == application/x-www-form-urlencoded임
		headers.add("Authorization", "KakaoAK "+adminKey);//add는 key, value형식임
	
		//Parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		//넣는작업 put이 아닌 add
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getId());
		
		//전송 / --요청 객체
//		MultiValueMap를 HttpEntity에 담음
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
		
		//전송 후 결과 처리 
//		최종적으로 보내는 애는 restTeamplate
		ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/unlink", req, String.class);
		
		//res자체는 String이 아님 내가 원하는건 타입임 타입을 꺼내야 함 꺼내려면 getBody
		log.info("res ==>> {}", res.getBody());//응답으로 이 사람의 아이디가 오면 성공
		
		if (res.getBody() != null) {
			result = 1;
		}
		return result;
	}
	
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
 