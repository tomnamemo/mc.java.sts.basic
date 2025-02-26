package com.mc.basic.module.multipart;

import com.mc.basic.infra.response.ApiResponse;
import com.mc.basic.infra.util.FileDTO;
import com.mc.basic.module.multipart.request.MultipartRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("multipart")
@RequiredArgsConstructor //반드시필요 , 반드시 초기화 (final 붙은 애들)
public class MultipartController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MultipartService multipartService;


    @PostMapping("upload")
    public ResponseEntity<ApiResponse<List<FileDTO>>> upload(
            MultipartRequest request,
            List<MultipartFile> files
    ){
        log.info("{}",request);
        log.info("{}",files);
        List<FileDTO> fileDTOS = multipartService.upload(request, files);
        return ResponseEntity.ok(ApiResponse.success(fileDTOS));
    }

}
