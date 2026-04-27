package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.CookieStore;

@RestController//springboot的控制器注解
@Api(value="/",description="这是我全部的post请求")//SwaggerUI接口文档
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量是用来装cookies信息的
    private static Cookie cookie;
    //用户获取cookies信息,再用来访问其它接口获取到列表
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ApiOperation(value="登录接口,成功后获取cookies信息",httpMethod="POST")//名称,方法
    public String login(HttpServletResponse response,
                        @RequestParam(value="userName",required=true) String userName,
                        @RequestParam(value="password",required=true) String password) {
        if (userName.equals("zhangsan") && password.equals("123456")) {
            cookie = new Cookie("zhangsan", "123456");
            response.addCookie(cookie);
            return "恭喜你登陆成功!";
        }
        return "用户名或者密码错误!";
    }
    //根据传入登录,获取信息
    @RequestMapping(value="/getUserList",method= RequestMethod.POST)
    @ApiOperation(value="获取用户列表",httpMethod="POST")
    public  String getUserList(HttpServletRequest request,
                            @RequestBody User u){
        User user;
        //获取cookies
        Cookie[] cookies = request.getCookies();
        //验证cookies是否合法
        for(Cookie c:cookies){
            if(c.getName().equals("login")&&
                    c.getValue().equals("true")
            && u.getUserName().equals("zhangsan")
                && u.getPassword().equals("123456")){
                 user=new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
               return user.toString();
            }

            }
        return "参数不合法";

    }


    }






