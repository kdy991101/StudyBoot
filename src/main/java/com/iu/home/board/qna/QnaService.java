package com.iu.home.board.qna;

import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.home.util.FileManager;
import com.iu.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")//spEL
	private String path;
	
	public List<QnaVO> getList(Pager pager) throws Exception {
		return qnaMapper.getList(pager);
	}

//	@Transactional(rollbackFor = Exception.class)//class선언부에도 가능 모든 클래스가 rollback적용 받음
	public int setAdd(QnaVO qnaVO)throws Exception{
		int result = qnaMapper.setAdd(qnaVO);
		//파일 객체 생성
		File file = new File(path);
		//파일이 있는지 없는지 확인
		//존재하지 않으면~
		//mkdirs에서 s를 붙이지 않으면 지정해준 경로의 해당되는 폴더들을 다 만드는 것이 아닌 폴더들을 만들지 않음
		if(!file.exists()) {
			boolean check=file.mkdirs();
		}
		
		//파일을 넣는 작업
		//isEmpty 비었는지 물어보는 것 없으면 true
		//
		for(MultipartFile f : qnaVO.getFiles()) {
			if(f.isEmpty()) {
				log.info("=============================================Exception발생");
				throw new Exception();//만약 파일이 비어있다면 Exception을 강제로 발생
			}
			
			if(!f.isEmpty()) {
				String fileName = fileManager.saveFile(f, path);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(f.getOriginalFilename());
				qnaFileVO.setNum(qnaVO.getNum());
				qnaMapper.setFileAdd(qnaFileVO);
			}
		}
		return result;
	}
	//이미지가 먼저 들어가면 안댐
	//정보를 insert하는 메서드를 실행시키고 이미지
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception {
		return qnaMapper.getDetail(qnaVO);
	}
	
}
