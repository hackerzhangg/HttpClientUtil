package com.bigjava18.httpclient.demo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author zgp
 * @Since 2021 -06 -22 10 :49
 * @Description
 */
public class HttpClientTest {

    /**
     * 测试httpclient get连接
     */
    @Test
    public void GetTest() throws UnsupportedEncodingException {
        //可关闭的httpclient客户端，相当于你打开一个浏览器
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
        //请求的url
        //String urlString="http://www.zgp520.com/";
        String passwordParam="1212";
        //对文件参数进行编码
        passwordParam= URLEncoder.encode(passwordParam,StandardCharsets.UTF_8.name());
        String urlString="http://localhost:8080/test2?username=cat&password="+passwordParam;
        //构造http请求对象
        HttpGet httpGet=new HttpGet(urlString);
        //添加请求头
        //用于解决httpclient被认为不是真人行为
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36");
        //防盗链，value：防盗链的对应的网址
        httpGet.addHeader("Referer","http://www.zgp520.com/");
        //可关闭响应
        CloseableHttpResponse closeableHttpResponse=null;
        try{
            closeableHttpResponse= closeableHttpClient.execute(httpGet);
            //代表本次请求成功，或者失败得状态
            StatusLine statusLine = closeableHttpResponse.getStatusLine();
            //如果响应码等于200，表示接口响应成功
            if (HttpStatus.SC_OK == statusLine.getStatusCode()){
                System.out.println("接口响应成功："+statusLine.getStatusCode());
                //获取响应头
                Header[] allHeaders = closeableHttpResponse.getAllHeaders();
                for (Header header : allHeaders){
                    System.out.println("headerName = " + header.getName() + ":" + "headerValue = "+header.getValue());
                }
                //获取响应结果
                HttpEntity httpEntity=closeableHttpResponse.getEntity();
                //ContentType
                Header contentType = httpEntity.getContentType();
                System.out.println("contentType = " + contentType);
                //对HttpEntity 操作工具类
                String toStringRet = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                System.out.println("toStringRet = " + toStringRet);
                //确保流关闭
                EntityUtils.consume(httpEntity);
            }else{
                System.out.println("StatusCodeFail:"+statusLine.getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (closeableHttpClient !=null){
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (closeableHttpResponse !=null){
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用httpClient获取网络图片
     * @throws UnsupportedEncodingException
     */
    @Test
    public void GetTest2() throws UnsupportedEncodingException {
        //可关闭的httpclient客户端，相当于你打开一个浏览器
        CloseableHttpClient closeableHttpClient= HttpClients.createDefault();
        //请求的url
        //String urlString="http://www.zgp520.com/";
        String passwordParam="1212";
        passwordParam= URLEncoder.encode(passwordParam,StandardCharsets.UTF_8.name());
        //String urlString="http://localhost:8080/test2?username=cat&password="+passwordParam;
        String urlString="https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i4/2201484407030/O1CN01CgL9GX21nmspMQTYS_!!2201484407030.jpg_430x430q90.jpg";
        //构造http请求对象
        HttpGet httpGet=new HttpGet(urlString);
        //添加请求头
        //用于解决httpclient被认为不是真人行为
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36");
        //防盗链，value：放生防盗链的对应的网址
        //httpGet.addHeader("Referer","http://www.zgp520.com/");
        //可关闭响应
        CloseableHttpResponse closeableHttpResponse=null;
        try{
            closeableHttpResponse= closeableHttpClient.execute(httpGet);
            //代表本次请求成功，或者失败得状态
            StatusLine statusLine = closeableHttpResponse.getStatusLine();
            //如果响应码等于200，表示接口响应成功
            if (HttpStatus.SC_OK == statusLine.getStatusCode()){
                System.out.println("接口响应成功："+statusLine.getStatusCode());
                //获取响应头
                Header[] allHeaders = closeableHttpResponse.getAllHeaders();
                for (Header header : allHeaders){
                    System.out.println("headerName = " + header.getName() + ":" + "headerValue = "+header.getValue());
                }
                //获取响应结果
                HttpEntity httpEntity=closeableHttpResponse.getEntity();
                //ContentType
                String contentTypeValue = httpEntity.getContentType().getValue();
                String suffix=null;
                if (contentTypeValue.contains("jpg") || contentTypeValue.contains("jpeg")){
                    suffix=".jpg";
                }else if(contentTypeValue.contains("bmp") || contentTypeValue.contains("bitmap")){
                    suffix=".bmp";
                }else if(contentTypeValue.contains("png")){
                    suffix=".png";
                }else if(contentTypeValue.contains("gif")){
                    suffix=".gif";
                }
                //获取文件字节流
                byte[] bytes = EntityUtils.toByteArray(httpEntity);
                String localPath="C:\\upload\\"+"image1"+suffix;
                FileOutputStream fileOutputStream=new FileOutputStream(localPath);
                fileOutputStream.write(bytes);
                fileOutputStream.close();
                //对HttpEntity 操作工具类
                String toStringRet = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                System.out.println("toStringRet = " + toStringRet);
                //确保流关闭
                EntityUtils.consume(httpEntity);
            }else{
                System.out.println("StatusCodeFail:"+statusLine.getStatusCode());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (closeableHttpClient !=null){
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (closeableHttpResponse !=null){
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
