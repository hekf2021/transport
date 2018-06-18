package com.xiaoka.socket.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Constants {

    public final static String TRANSFER="TRANSFER";//货运


    public static class TCP_MESSAGE{
        public final static String ACCOUNT_ID="accountId";//用户ID
        public final static String MODULE_NAME="moduleName";//模块名
        public final static String IS_SERVER_Driver="isServerDriver";//模块名
    }

    public static class TCP_SERVER_CONFIG{
        public final static String PORT="tcp.server.port";//TCP长连接监听端口
        public final static String THREAD_NUM="tcp.server.thread.num";//线程数
        public final static String BUFFER_SIZE="tcp.server.buffer.size";//缓存大小
        public final static String QUEUE_SIZE="tcp.server.queue.size";//排队队列大数
        public final static String NAMESRV_ADDR="tcp.server.namesrv.addr";//mq地址
    }


    public static Map<String, String> allMap = new ConcurrentHashMap<>();

    public static String getStringValue(String key){
        return allMap.get(key);
    }
    public static Integer getIntValue(String key){
        return Integer.valueOf(allMap.get(key));
    }
    /**
     * 读取配置文件内容，并初始化
     */
    public void initData() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            Properties systemProp = new Properties();
            systemProp.load(inputStream);
            Set<Object> setKey = systemProp.keySet();
            if (setKey != null) {
                for (Object object : setKey) {
                    String key = object.toString().trim();
                    String value = systemProp.getProperty(key).trim();
                    allMap.put(key, value);
                    //System.out.println("key="+key+"  value="+value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
