package com.klay.community.dto;

import com.klay.community.exception.CustomizeErrorCodeException;
import com.klay.community.exception.CustomizeException;
import lombok.Data;

/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/12 14:13
 **/
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorsOf(Integer code, String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
    //封装好枚举类用于处理
    public static ResultDTO errorOf(CustomizeErrorCodeException errorCode) {
        return errorsOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDTO errorOf(CustomizeException e) {
        return errorsOf(e.getCode(),e.getMessage());
    }

    //返回成功
    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功!");
        return resultDTO;
    }
    public static <T> ResultDTO okOf(T t){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功!");
        resultDTO.setData(t);
        return resultDTO;
    }
}
