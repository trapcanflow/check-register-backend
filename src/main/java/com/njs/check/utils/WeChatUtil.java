package com.njs.check.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Component
@EnableScheduling
public class WeChatUtil {
    private static final String appId = "wx989edf78ca692fd3";

    private static final String appSecret = "f354b6f27837ded94e419a26cc73c2c6";

    public static String ACCESS_TOKEN;



    @Scheduled(cron = "0 0/40 * * * ?")
    @PostConstruct
    public void getAccessToken() {
        String Url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId
                + "&secret=" + appSecret + "";
            try {
                //请求access_token地址
                //创建提交方式
                HttpGet httpGet = new HttpGet(Url);
                //获取到httpclien
                HttpClient httpClient = new DefaultHttpClient();
                //发送请求并得到响应
                HttpResponse response = httpClient.execute(httpGet);
                //判断请求是否成功
                if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    //将得到的响应转为String类型
                    String str = EntityUtils.toString(response.getEntity(), "utf-8");
                    //字符串转json
                    JSONObject jsonObject =  JSONObject.parseObject(str);
                    //输出access_token
//                    System.out.println((String) jsonObject.get("access_token"));
                    //给静态变量赋值，获取到access_token
                    ACCESS_TOKEN = (String) jsonObject.get("access_token");
                    System.out.println(ACCESS_TOKEN);
            }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }

//    @Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    public void configureTasks() {
//        System.out.println(1);
//    }

    public static String sendTemplate1(String touser, String templateId, String url, String[] fillData) {
        String tepUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
                + ACCESS_TOKEN;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(tepUrl);
        // 装配post请求参数
        JSONObject json = new JSONObject();
        json.put("touser", touser);
        json.put("template_id", templateId);
        json.put("url",url);
        JSONObject dataJson = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value","您好，有待审批事件需处理如下");
        first.put("color","#173177");
        dataJson.put("first",first);
        for (int i = 0; i < fillData.length; i++) {
            JSONObject sonDateJson = new JSONObject();
            sonDateJson.put("value", fillData[i]);
            sonDateJson.put("color","#173177");
            dataJson.put("keyword" + (i + 1), sonDateJson);
        }
        JSONObject remark = new JSONObject();
        remark.put("value","请您尽快处理");
        remark.put("color","#173177");
        dataJson.put("remark",remark);
        json.put("data", dataJson);
        String resultStr = "发送失败";
        try {
            StringEntity myEntity = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);

            // 设置post求情参数
            httpPost.setEntity(myEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 发送成功
//                String resutlEntity = EntityUtils.toString(httpResponse.getEntity());
//                ResultTemplateDate resultTemplateDate = JSONObject.parseObject(resutlEntity, ResultTemplateDate.class);
//                if (resultTemplateDate.getErrcode().equals("40037")) {
//                    resultStr = "template_id不正确";
//                }
//                if (resultTemplateDate.getErrcode().equals("41028")) {
//                    resultStr = "form_id不正确，或者过期";
//                }
//                if (resultTemplateDate.getErrcode().equals("41029")) {
//                    resultStr = "form_id已被使用";
//                }
//                if (resultTemplateDate.getErrcode().equals("41030")) {
//                    resultStr = "page不正确";
//                }
//                if (resultTemplateDate.getErrcode().equals("45009")) {
//                    resultStr = "接口调用超过限额（目前默认每个帐号日调用限额为100万）";
//                }
                resultStr = "ok";
                return resultStr;
            } else {
                // 发送失败
                return resultStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    // 释放资源
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }

    public static String sendTemplate2(String touser, String templateId, String url, String[] fillData) {
        String tepUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
                + ACCESS_TOKEN;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(tepUrl);
        // 装配post请求参数
        JSONObject json = new JSONObject();
        json.put("touser", touser);
        json.put("template_id", templateId);
        json.put("url",url);
        JSONObject dataJson = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value","您的出差申请已通过");
        first.put("color","#173177");
        dataJson.put("first",first);
        for (int i = 0; i < fillData.length; i++) {
            JSONObject sonDateJson = new JSONObject();
            sonDateJson.put("value", fillData[i]);
            sonDateJson.put("color","#173177");
            dataJson.put("keyword" + (i + 1), sonDateJson);
        }
        JSONObject remark = new JSONObject();
        remark.put("value","详情");
        remark.put("color","#173177");
        dataJson.put("remark",remark);
        json.put("data", dataJson);
        String resultStr = "发送失败";
        try {
            StringEntity myEntity = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);

            // 设置post求情参数
            httpPost.setEntity(myEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 发送成功
//                String resutlEntity = EntityUtils.toString(httpResponse.getEntity());
//                ResultTemplateDate resultTemplateDate = JSONObject.parseObject(resutlEntity, ResultTemplateDate.class);
//                if (resultTemplateDate.getErrcode().equals("40037")) {
//                    resultStr = "template_id不正确";
//                }
//                if (resultTemplateDate.getErrcode().equals("41028")) {
//                    resultStr = "form_id不正确，或者过期";
//                }
//                if (resultTemplateDate.getErrcode().equals("41029")) {
//                    resultStr = "form_id已被使用";
//                }
//                if (resultTemplateDate.getErrcode().equals("41030")) {
//                    resultStr = "page不正确";
//                }
//                if (resultTemplateDate.getErrcode().equals("45009")) {
//                    resultStr = "接口调用超过限额（目前默认每个帐号日调用限额为100万）";
//                }
                resultStr = "ok";
                return resultStr;
            } else {
                // 发送失败
                return resultStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    // 释放资源
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultStr;
    }


}

