package com.iu.home.board.qna;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class QnaVO {

	private Long num;
	@NotBlank(message = "당장 제목을 써")
	private String title;
	@NotBlank(message = "당장 니 닉네임 적어")
	private String writer;
	@NotBlank(message = "당장 내용 적어 안그럼 디이져잉")
	private String contents;
	private Long hit;
	private Date regDate;
	private Long ref;
	private Long step;
	private Long depth;
	
	private MultipartFile[] files;
	private List<QnaFileVO> qnaFileVOs;
	
}
