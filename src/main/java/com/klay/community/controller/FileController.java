package com.klay.community.controller;

import com.klay.community.dto.FileDTO;
import com.klay.community.provider.AliyunProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/18 15:58
 **/
@Controller
@Slf4j
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        AliyunProvider aliyunProvider = new AliyunProvider();
        try {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            String uploadFile = aliyunProvider.upload(file.getInputStream(), file.getOriginalFilename());
            fileDTO.setUrl(uploadFile);
            return fileDTO;
        } catch (IOException e) {
            log.error("upload error", e);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            return new FileDTO();
        }
    }
}
