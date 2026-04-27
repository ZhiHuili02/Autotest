package com.cource.httpclient.cookies;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class testng1 {
    private String url;
    private String uri;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforetest() throws IOException {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
        uri = bundle.getString("getCookies.uri");
        url = url + uri;
    }

    @Test
    public void testGetcookies() throws IOException {
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


            //通过cookie找response
            @Test(dependsOnMethods={"testGetcookies"})
            public void testGetWithcookie() throws IOException {
             String uri=bundle.getString("getCookies.uri");
                url = url + uri;
                HttpGet get = new HttpGet(url);//单独get这里带着url一起赋值
                DefaultHttpClient client = new DefaultHttpClient();//特立独行的client
                //设置cookies信息
                client.setCookieStore(this.store);
                HttpResponse response = client.execute(get);//带着get一起输出新response
                //获取状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println(statusCode);
                if (statusCode == 400) {
                    String result = EntityUtils.toString(response.getEntity(), "UTF-8");

                    System.out.println(result);

                }





            }

            }






