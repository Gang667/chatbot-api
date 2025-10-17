package cn.ganggang.chatbot.api.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class ApiTest {

    @Test
    public void query_uncommented() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=1");
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        get.addHeader("cookie", "zsxq_access_token=56D8A2A7-7B61-4CA6-820A-AF0DE57921DE_2D9B3516DEAD833D; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22814885545522542%22%2C%22first_id%22%3A%2219927d2644d79a-045327f672c2d3c-26011051-3686400-19927d2644e1543%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTk5MjdkMjY0NGQ3OWEtMDQ1MzI3ZjY3MmMyZDNjLTI2MDExMDUxLTM2ODY0MDAtMTk5MjdkMjY0NGUxNTQzIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiODE0ODg1NTQ1NTIyNTQyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22814885545522542%22%7D%2C%22%24device_id%22%3A%2219927d2644d79a-045327f672c2d3c-26011051-3686400-19927d2644e1543%22%7D");
        get.addHeader("Content-Type", "application/json; charset=utf-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void comment() throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/22811584118485841/comments");

        post.addHeader("cookie", "zsxq_access_token=56D8A2A7-7B61-4CA6-820A-AF0DE57921DE_2D9B3516DEAD833D; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22814885545522542%22%2C%22first_id%22%3A%2219927d2644d79a-045327f672c2d3c-26011051-3686400-19927d2644e1543%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTk5MjdkMjY0NGQ3OWEtMDQ1MzI3ZjY3MmMyZDNjLTI2MDExMDUxLTM2ODY0MDAtMTk5MjdkMjY0NGUxNTQzIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiODE0ODg1NTQ1NTIyNTQyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22814885545522542%22%7D%2C%22%24device_id%22%3A%2219927d2644d79a-045327f672c2d3c-26011051-3686400-19927d2644e1543%22%7D");
        post.addHeader("Content-Type", "application/json; charset=utf-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"你好呀\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.deepseek.com/chat/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer sk-8dfceddddb9442a1889b20638482abb0");

        String paramJson = "{\n" +
                "        \"model\": \"deepseek-chat\",\n" +
                "        \"messages\": [\n" +
                "          {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},\n" +
                "          {\"role\": \"user\", \"content\": \"帮我写一个java冒泡排序\"}\n" +
                "        ],\n" +
                "        \"stream\": false\n" +
                "      }";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
