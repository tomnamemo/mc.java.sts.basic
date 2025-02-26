package com.mc.basic.module.multipart;

import com.mc.basic.infra.util.FileDTO;
import com.mc.basic.infra.util.FileUtil;
import com.mc.basic.module.multipart.request.MultipartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MultipartService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final FileUtil fileUtil;

    public MultipartService(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    public List<FileDTO> upload(MultipartRequest request, List<MultipartFile> files) {
        return fileUtil.upload(files);
    }
}
