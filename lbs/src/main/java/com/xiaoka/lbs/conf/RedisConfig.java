package com.xiaoka.lbs.conf;

import com.kiaoka.common.redis.service.RedisTemplateService;
import com.kiaoka.common.redis.service.impl.RedisTemplateServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration//把一个类作为一个IoC容器，它的某个方法头上如果注册了@Bean，就会作为这个Spring容器中的Bean。
public class RedisConfig {  
  
    private static Logger logger = Logger.getLogger(RedisConfig.class);  
      
    @Bean//将返回值 交给Spring托管
    @ConfigurationProperties(prefix="spring.redis")//从配置文件中获取spring.redis的配置  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }  
      
    @Bean  
    @ConfigurationProperties(prefix="spring.redis")  
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);  
        logger.info("JedisConnectionFactory bean init success.");  
        return factory;  
    }  

    @Bean  
    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = new StringRedisTemplate(getConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisTemplateService getRedisTemplateService(){
        RedisTemplateService redisTemplateService = new RedisTemplateServiceImpl();
        return redisTemplateService;
    }
}  