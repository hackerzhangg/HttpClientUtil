package com.bigjava18.httpclient.jdk;

import org.junit.Test;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @Author zgp
 * @Since 2021 -06 -22 10 :08
 * @Description 使用原生jdk来获取api网页
 */
public class HttpClientJdkTest {

    @Test
    public void demo1() throws IOException {

        String urlString="http://www.zgp520.com/";
        URL url=new URL(urlString);
        URLConnection urlConnection=url.openConnection();
        HttpURLConnection httpURLConnection= (HttpURLConnection) urlConnection;
        // 设置请求类型
        /**
         * 请求行
         * 空格
         * 请求头
         * 请求体
         */
        //设置请求类型get post
        httpURLConnection.setRequestMethod("GET");
        //设置请求头
        httpURLConnection.setRequestProperty("Accept-Charset","utf-8");
        try {
            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) !=null){
                System.out.println("line = " + line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("信息获取完成！");
        }
    }
}
