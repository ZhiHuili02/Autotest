package com.cource.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    String test2url;
    private CookieStore store;

    @Test
    public void testGetcookies() throws IOException {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        String url = bundle.getString("test.url");
        String uri = bundle.getString("getCookies.uri");
        url = url + uri;
        HttpGet get = new HttpGet(url);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");

        System.out.println(result);
        //获取cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println(name + "=" + value);
        }
    }
    @Test(dependsOnMethods="testGetcookies")
    public void testPostMethod() throws IOException {
      ResourceBundle bundle=ResourceBundle.getBundle("application",Locale.CHINA);
        String uri=bundle.getString("test.post.with.cookies.uri");
        String url=bundle.getString("test.url");
      //拼接最终的测试地址
      url=url+uri;
      //声明一个post对象
      HttpPost post=new HttpPost(url);
      //声明一个client对象
      DefaultHttpClient client=new DefaultHttpClient();
      //添加参数
      JSONObject param=new JSONObject();
      param.put("name","huhansan");
      param.put("age","18");
      //设置一个头信息header
      post.setHeader("Content-type","application/json");
      //将参数信息添加到方法中getEntity获得整个信息
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);//把字符整体放入Entity中

      //声明一个对象进行响应结果的储存
        String result;
      //设置一个cookies信息
        client.setCookieStore(this.store);//这里是执行

      //执行post方法
        HttpResponse response=client.execute(post);

      //获取响应结果
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

      //处理结果,判断结果是否符合预期
      //将返回的响应结果字符串转化成为json对象
        JSONObject resultJson=new JSONObject(result);
        //具体的判断返回结果的值
        //获取结果的值
        String c=(String) resultJson.get("huhansan");//(String)---强制转化成字符串
        Assert.assertEquals("success",c);

    }
}

