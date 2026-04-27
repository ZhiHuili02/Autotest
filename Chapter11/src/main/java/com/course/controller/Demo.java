package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value ="v1",description="这是我的第一个版本demo")
@RequestMapping("v1")
public class Demo {
    //首先获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value="/getUserCount",method= RequestMethod.GET)
    @ApiOperation(value="可以获得用户数",httpMethod="GET")
    public int getUserCount() {
        return template.selectOne("getUserCount");//直接执行mysql的id;
    }
    @RequestMapping(value="/addUser",method= RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        int result = template.insert("addUser", user);
        return result;/*增加一行*/
    }
    @RequestMapping(value="/updateUser",method= RequestMethod.POST)
    public int updateUser(@RequestBody User user){
        int result=template.update("updateUser",user);
        return result;/*更新*/

        }
    @RequestMapping(value="/delUser",method= RequestMethod.POST)
    public int delUser(@RequestParam int id){/*此处requestparam/requestbody都可以,只是为了和上面区分*/
       int result=template.delete("delUser",id);
       return result;
    }
    }





