package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {
    @Test(/*dependsOnGroups="loginTrue",*/description="添加用户接口")
    public void addUser() throws IOException {
        SqlSession session= DatabaseUtil.getSqlSession();
        System.out.println("SqlSession对象"+session);
        AddUserCase addUserTest =session.selectOne("com.course.model.AddUserCase.addUserCase",1);
        System.out.println(addUserTest.toString());
        System.out.println(TestConfig.addUserUrl);
    }
}
