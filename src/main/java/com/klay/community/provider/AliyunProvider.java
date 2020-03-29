package com.klay.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/27 13:33
 **/
@Slf4j
public class AliyunProvider {
    public String upload(InputStream inputStream, String fileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = sdf.format(new Date()) + "/" + finalFileName;
//        String[] filePaths = fileName.split("\\.");
//        if (filePaths.length > 1) {
//            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
//        } else {
//            return null;
//        }

        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        //RAM子账号
        String accessKeyId = "LTAI4FvegE4eKucgXMMJVNmZ";
        String accessKeySecret = "ZFZEuTRsdEccH2vzEnehpXnn2xNgK0";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        添加ContentType
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentDisposition("attachment;");
        //objectMetadata.setContentType(fileName.substring(fileName.lastIndexOf(".")));
        ossClient.putObject("community-platform", objectName, inputStream);

        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);

        URL url = ossClient.generatePresignedUrl("community-platform", objectName, expiration);

        log.info("图片已成功上传:" + inputStream.toString());
        // 关闭OSSClient
        ossClient.shutdown();
        return url.toString();
    }
}
