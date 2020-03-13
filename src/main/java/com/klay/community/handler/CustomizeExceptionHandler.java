package com.klay.community.handler;

import com.alibaba.fastjson.JSON;
import com.klay.community.dto.ResultDTO;
import com.klay.community.exception.CustomizeErrorCodeException;
import com.klay.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @description:
 * @author: KlayHu
 * @create: 2020/3/10 11:58
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request,
                  HttpServletResponse response){
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            //返回json
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            }
            //错误页面跳转
            else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCodeException.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
                //TODO 处理
            }
            return null;

        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCodeException.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
