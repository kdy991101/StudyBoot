package com.iu.home.board.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.iu.home.util.Pager;

@Mapper
public interface QnaMapper {
	//DAO는 사실상 역할이 없음...
	//mapper에 있는 namespace와 동일한 이름을 작성해줄 것
	//mapper에 있는 동일한 메서드 이름으로 작성해줄 것
	public List<QnaVO> getList(Pager pager)throws Exception;
	
	public Long getCount(Pager pager)throws Exception;
	
	public int setAdd(QnaVO qnaVO)throws Exception;
	
	public int setQnaFile(QnaFileVO qnaFileVO) throws Exception;
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception;
	
	public int setFileAdd(QnaFileVO qnaFileVO)throws Exception;
}
