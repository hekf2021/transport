package com.xiaoka.lbs.service.impl;



import com.kiaoka.common.redis.service.RedisTemplateService;
import com.xiaoka.lbs.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisTemplateService redisTemplateService;
    @Autowired
    private RedisTemplate redisTemplate;

    public void test(String key,String value){
        redisTemplateService.stringSetString(key,value);
    }



    public Long getkey(){
        String s = String.valueOf("abc".hashCode());
        return incrementHash("order",s,1L);
    }

    /**
     * 获取唯一Id
     * @param key
     * @param hashKey
     * @param delta 增加量（不传采用1）
     * @return
     * @throws BusinessException
     */
    public Long incrementHash(String key,String hashKey,Long delta){
        try {
           // if (null == delta) {
                delta=1L;
           // }
            return redisTemplate.opsForHash().increment(key, hashKey, delta);
        } catch (Exception e) {//redis宕机时采用uuid的方式生成唯一id
            int first = new Random(10).nextInt(8) + 1;
            int randNo=UUID.randomUUID().toString().hashCode();
            if (randNo < 0) {
                randNo=-randNo;
            }
            return Long.valueOf(first + String.format("%16d", randNo));
        }
    }
}
