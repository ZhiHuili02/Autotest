package com.course.cases;

import com.course.config.TestConfig;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

@Test(dependsOnGroups = "loginTrue",description="获取性别为男的用户")
public class GetUserInfoListTest {
    public void getUserInfoList() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoListTest getUserInfoListTest = session.selectOne("getUserListCase",1);
        System.out.println(getUserInfoListTest.toString());
        System.out.println(TestConfig.getUserListUrl);
    }
}




