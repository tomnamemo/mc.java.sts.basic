package com.mc.core.infra.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.mc.core.infra.exception.exceptions.RestApiException;
import com.mc.core.infra.response.ResponseCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	
	@Value( "${upload.path}" )
	private String filePath;
	
	public List<FileDTO> upload(List<MultipartFile> files) {
		List<FileDTO> fileData = new ArrayList<FileDTO>();

		if(files == null) return fileData;
		
		if(!files.isEmpty() && !Objects.equals(files.get(0).getOriginalFilename(), "")) {
			for(MultipartFile mf : files) {
				String savePath = generateSavePath();
				String originFileName = mf.getOriginalFilename();
				String renameFileName = getRenameFileName(originFileName);
				
				FileDTO fileDTO = new FileDTO();
				fileDTO.setOriginFileName(originFileName);
				fileDTO.setRenameFileName(renameFileName);
				fileDTO.setSavePath(savePath);
				
				fileData.add(fileDTO);
				saveFile(mf,fileDTO);
			}
		}
		return fileData;	
	}
	
	private String generateSavePath() {
		LocalDate now = LocalDate.now();
		return filePath + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "/";
	}
	
	private String getRenameFileName(String originFileName) {
		 UUID renameFileID = UUID.randomUUID(); 
	     return renameFileID.toString() + originFileName.substring(originFileName.lastIndexOf("."));
	}

	private void saveFile(MultipartFile mf, FileDTO dto)  {
		File path = new File(dto.getSavePath());
		if(!path.exists()) {
			path.mkdirs();
		}
		
		File tempFile = new File(dto.getSavePath() + dto.getRenameFileName());
		
		try {
			mf.transferTo(tempFile);
			//throw new IOException();
		} catch (IllegalStateException | IOException e) {
			throw new RestApiException(ResponseCode.FILE_ERROR);
		}
	}
	
	public void deleteFile(String fullPath, String fileName) {
		File file = new File(fullPath + fileName);
		file.delete();
	}

}
