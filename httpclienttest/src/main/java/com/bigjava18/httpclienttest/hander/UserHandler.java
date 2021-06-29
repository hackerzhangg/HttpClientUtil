package com.bigjava18.httpclienttest.hander;

import com.bigjava18.httpclienttest.bean.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author zgp
 * @Since 2021 -06 -22 14 :16
 * @Description
 */
@Controller
//@RequestMapping("/httpclient")
public class UserHandler {

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    @ResponseBody
    public String test1(){

        return "hello!";
    }

    /**
     * 测试入参Get请求
     * @param userRequest
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    @ResponseBody
    public String test2(UserRequest userRequest, HttpServletRequest httpServletRequest){
        System.out.println("userRequestUsername = " + userRequest.getUsername());
        System.out.println("userRequestPassword = " + userRequest.getPassword());
        //获取所有请求头
        Enumeration<String> headerNames=httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headName=headerNames.nextElement();
            System.out.println("headName = " + httpServletRequest.getHeader(headName));
        }
        return "test2";
    }
}
