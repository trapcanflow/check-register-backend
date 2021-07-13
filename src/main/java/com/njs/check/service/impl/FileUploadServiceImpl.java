package com.njs.check.service.impl;

import com.njs.check.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();

        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件，上传文件的文件名:{},上传的路径：{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try{
            file.transferTo(targetFile);
        }catch (IOException e){
            log.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

}
