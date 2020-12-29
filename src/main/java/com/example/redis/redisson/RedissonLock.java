package com.example.redis.redisson;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedissonLock {

    private static final Logger log = LoggerFactory.getLogger(RedissonLock.class);
    private static final String LOCK = "LOCK";
    @Autowired
    private RedissonClient redissonClient;

    public void RedisLockTest() {
        log.info("开始获取锁");
        //设置要加锁的key
        RLock lock = redissonClient.getLock(LOCK);
        boolean getLock = false;
        try {
            //trylock表示尝试去加锁
            //第一个参数表示等待时间，
            // 第二个参数表示锁的失效时间，
            // 加锁成功,返回true，继续执行true下面代码；
            // 如果加锁失败，它会等待第一个参数设置的时间，如果加锁成功返回true,失败返回false
            if(getLock = lock.tryLock(0,5000, TimeUnit.MILLISECONDS)){
                log.info("Redisson获取到分布式锁:{},ThreadName:{}",LOCK,Thread.currentThread().getName());
                //执行业务
                service();
            }else{
                log.info("Redisson没有获取到分布式锁:{},ThreadName:{}",LOCK,Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            log.error("Redisson分布式锁获取异常",e);
        } finally {
            if(!getLock){
                return;
            }
            lock.unlock();
            log.info("Redisson分布式锁释放锁");
        }
    }
    public void service(){
        try {
            System.out.println("正在执行业务");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
