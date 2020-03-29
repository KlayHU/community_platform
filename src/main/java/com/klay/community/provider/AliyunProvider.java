package com.klay.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.UUID;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/27 13:33
 **/
public class AliyunProvider {
    public String upload(InputStream inputStream, String fileName) {
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            return null;
        }
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4FvegE4eKucgXMMJVNmZ";
        String accessKeySecret = "ZFZEuTRsdEccH2vzEnehpXnn2xNgK0";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject("community-platform", "community-platform/" + UUID.randomUUID().toString(),inputStream);
        System.out.println("上传成功:"+inputStream.toString());
        // 关闭OSSClient。
        ossClient.shutdown();
        return generatedFileName;
    }
}
