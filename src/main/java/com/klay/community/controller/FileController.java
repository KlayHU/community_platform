package com.klay.community.controller;

import com.klay.community.dto.FileDTO;
import com.klay.community.provider.AliyunProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/18 15:58
 **/
@Controller
public class FileController {

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(){
//        FileDTO fileDTO = new FileDTO();
//        fileDTO.setSuccess(1);
//        fileDTO.setUrl("/bootstrap-3.3.7-dist/images/githubAvatar.png");
        AliyunProvider aliyunProvider = new AliyunProvider();
        aliyunProvider.upload();
        return fileDTO;
    }
}
