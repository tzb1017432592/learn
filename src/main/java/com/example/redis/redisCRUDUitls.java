package com.example.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class redisCRUDUitls {
    private static final Logger log= LoggerFactory.getLogger(redisCRUDUitls.class);
    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean setnx(String k,String v){
        try {
            if (StringUtils.isBlank(k)){
                throw new RuntimeException();
            }
            return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
                Boolean result = connection.setNX(k.getBytes(),String.valueOf(v).getBytes());
                return result;
            });
        }catch (Exception e){
            log.error("setnx erroe{}",k,e);
        }
        return false;
    }
    public String getStringByKey(String k){
        try {
            if (StringUtils.isBlank(k)){
                throw new RuntimeException();
            }
            return (String) redisTemplate.opsForValue().get(k);
        }catch (Exception e){
            log.error("setnx erroe{}",k,e);
        }
        return null;
    }

    public String getSet(String k, String v) {
        try {
            if (StringUtils.isBlank(k)){
                throw new RuntimeException();
            }
            Object result = redisTemplate.opsForValue().getAndSet(k, v);
            if (result!=null){
                return (String) result;
            }
        }catch (Exception e){
            log.error("setnx erroe{}",k,e);
        }
        return null;
    }

    public Boolean expire(String k, long time) {
        try {
            if (StringUtils.isBlank(k)){
                throw new RuntimeException();
            }
            return redisTemplate.expire(k, time, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.error("setnx erroe{}",k,e);
        }
        return false;
    }

    public boolean del(String k) {
        try {
            if (StringUtils.isBlank(k)){
                throw new RuntimeException();
            }
            return redisTemplate.delete(k);
        }catch (Exception e){
            log.error("setnx erroe{}",k,e);
        }
        return false;
    }
}
