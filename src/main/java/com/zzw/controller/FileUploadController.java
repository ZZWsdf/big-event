package com.zzw.controller;

import com.zzw.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        //把文件内容存储到本地磁盘
        String originalFilename=file.getOriginalFilename();
        //保证名字唯一防止覆盖
        String filename= UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("E:\\Vue+Springboot\\big-event\\File\\"+filename));
        return Result.success("url访问地址");
    }
}
