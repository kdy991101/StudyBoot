package com.iu.home.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManager extends AbstractView {
	
	@Value("${app.download.base}")
	private String base;
	
//	AbstractView를 상속받아 renderMergedOutputModel를 오버라이딩 한것
	@Override//다운로드를 걸어주는 메서드임
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		  log.info("========================================");
		  log.info("File DownLoad View");
		
		QnaFileVO qnaFileVO = (QnaFileVO)model.get("fileVO");//모델에서 fileVO를 꺼냄
		String path = (String)model.get("path");
		log.info("FIELVO{}"+ qnaFileVO);
//		File file = new File("D:/result/upload/"+path, qnaFileVO.getFileName());//배포하면 여기가 경로가 바뀜... 수정해줘야 함
		File file = new File(base+path, qnaFileVO.getFileName());
		
		//한글처리
		response.setCharacterEncoding("UTF-8");//인코딩 처리
		
		//총 파일의 크기
		response.setContentLengthLong(file.length());
		
		//다운로드시 파일의 이름을 인코딩
		//파일의 이름을 업로드시 이름으로 받으려 하는 것
		String oriName = URLEncoder.encode(qnaFileVO.getOriName(), "UTF-8");	
		
		//header 설정 
		response.setHeader("Content-Disposition", "attachment;fileName=\""+oriName+"\"");
							//attachment -> 첨부
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//HDD에서 파일을 읽고  FileInputStream->0,1로 읽어옴
		//읽어오는 것 inPut
		FileInputStream fi = new FileInputStream(file);
		
		//client로 전송하는 코드를 만들어 줘야 함
		//내보내는 것 outPut
		OutputStream os = response.getOutputStream();
		//////////////////////////////////연결 끝///////////////////////////////////////
		
		//									전송
		FileCopyUtils.copy(fi, os);
		////////////////////////////////전송이 끝나고 자원 해제
		//역순으로
		os.close();
		fi.close();
	}

	//HDD에 파일 저장 단계
	//UUID.randomUUID()는 api 랜덤으로 문자를 생성해줌/데이터 타입은 String이 아니기 때문에 toString해줌
	public String saveFile(MultipartFile multipartFile, String path) throws Exception {
		//중복되지않기위해 우리가 만들어 준 것
		String fileName = UUID.randomUUID().toString();
		
			//StringBuffer는 객체를 만드는 것이 아닌 기존 문자에 더해주는 것이다
			//StringBuffer는 String타입이 아닌 StringBuffer타입이다
		StringBuffer sb = new StringBuffer();
		sb.append(fileName);
		sb.append("_");
		fileName = sb.toString();
		
		
		String ex = multipartFile.getOriginalFilename();//아이유.jpg
		ex = ex.substring(ex.lastIndexOf("."));
		sb.append(ex);

		File file = new File(path, fileName);
		multipartFile.transferTo(file);
		return fileName;
	}
	
//	public boolean deleteFile( String path, QnaFileVO qnaFileVO)throws Exception{
//		File file = new File(path, qnaFileVO.getFileName());
//		return file.delete();
//	}
}
