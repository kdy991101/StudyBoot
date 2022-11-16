package com.iu.home.board.qna;

import lombok.Data;

@Data
public class PostVO {
	private String userId;
	private String id;
	private String title;
	private String body;
// json으로 오는 결과물을 담으려고하는 것
}
