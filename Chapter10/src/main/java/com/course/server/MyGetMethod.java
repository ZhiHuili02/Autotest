package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController//告诉Application扫描这个方法
@Api(value="/",description="这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)//访问的uri
    @ApiOperation(value="通过这个方法可以获得cookies",httpMethod="GET")

    public String getCookies(HttpServletResponse response) {//getCookies方法名
        //HttpServerletRequest 装请求信息
        //HttpServerletResponse 装响应信息
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }

    /*要客户端携带cookies访问
     *
     * */
    @RequestMapping(value = "/get/With/Cookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();//cookies是很多key,value组成的数组
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息来";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") &&
                    Objects.equals(cookie.getValue(), "true")) {
                return "访问成功!";
            }
        }
        return "你必须携带cookies信息来";
    }
    /*开发一个需要携带参数才能访问的get请求
     * 第一种实现方式url;key1=value1&key2=value2
     * 我们来模拟获取商品列表
     * */
    @RequestMapping(value = "/get/with/param",method =RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问get请求方法一",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        return myList;


    }
    @RequestMapping(value="/get/with/param/{start}/{end}")
    @ApiOperation(value="需要携带参数才能访问get请求方法一",httpMethod="GET")
    public Map myGetList (@PathVariable Integer start,
                          @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        return myList; }
}
