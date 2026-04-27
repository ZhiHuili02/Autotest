package com.cource.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle= ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
    }
@Test
public void testGetCookies() throws IOException {
        String result;
        //从配置文件中拼接测试的url
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        //测试逻辑代码书写
        HttpGet get=new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    //获取cookie的key和value
        this.store=client.getCookieStore();
    List<Cookie> cookielist=store.getCookies();
    for(Cookie cookie:cookielist){
        String name=cookie.getName();
        String value=cookie.getValue();
        System.out.println(name+"="+value);
    }




    }
//通过cookie带入找返回值
private String bundle;
    @Test(dependsOnMethods={"testGetCookies"})
     public void cookiesTest() throws IOException {
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
        String uri=bundle.getString("/get/with/cookies");
        url=url+uri;
        HttpGet get=new HttpGet(url);
        DefaultHttpClient client=new DefaultHttpClient();
        client.setCookieStore(this.store);
        String result;
        HttpResponse response=client.execute(get);
        result=EntityUtils.toString(response.getEntity(),"utf-8");



    }
}
