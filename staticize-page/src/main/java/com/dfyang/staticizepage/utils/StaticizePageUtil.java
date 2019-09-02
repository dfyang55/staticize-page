package com.dfyang.staticizepage.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;
import java.io.*;

/**
 * 静态化页面工具类
 */
public class StaticizePageUtil {

    /** 生成静态文件的根路径 */
    private static String basePath;

    static {
        try {
            basePath = ResourceUtils.getFile("classpath:templates").getAbsolutePath() + "\\static\\";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成静态页面
     * @param url 生成静态页面的url
     * @param filePath 生成静态页面的路径
     */
    public static void staticize(String url, String filePath) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        Writer writer = null;
        try {
            // 1) 通过HttpClient访问url
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if (entity != null) {
                String entityStr = EntityUtils.toString(entity); // 将请求获取到的页面信息转换为字符串
                File file = new File(basePath + filePath);
                File baseDirs = new File(basePath);
                if (!baseDirs.exists())
                    baseDirs.mkdirs(); // 如果生成文件夹不存在则创建
                if (!file.exists())
                    file.createNewFile(); // 如果目标文件夹不存在则创建
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
                // 2) 将访问url获取的内容输出到指定文件
                writer.write(entityStr);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 3) 关闭资源
            try {
                if (entity != null)
                    EntityUtils.consume(entity);
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
