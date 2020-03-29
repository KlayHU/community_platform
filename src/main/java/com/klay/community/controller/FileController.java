package com.klay.community.controller;

import com.klay.community.dto.FileDTO;
import com.klay.community.provider.AliyunProvider;
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
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        AliyunProvider aliyunProvider = new AliyunProvider();
        try {
            aliyunProvider.upload(file.getInputStream(),file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/bootstrap-3.3.7-dist/images/githubAvatar.png");
        return fileDTO;
    }
}
