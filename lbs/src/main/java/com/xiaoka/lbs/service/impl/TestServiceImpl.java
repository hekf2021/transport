package com.xiaoka.lbs.service.impl;



import com.kiaoka.common.redis.service.RedisTemplateService;
import com.xiaoka.lbs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisTemplateService redisTemplateService;

    public void test(String key,String value){
        redisTemplateService.stringSetString(key,value);
    }


}
