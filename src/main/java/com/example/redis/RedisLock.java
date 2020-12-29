package com.example.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RedisLock {
    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private static final long TIMEOUT = 5000;

    private static final String LOCK = "LOCK";

    @Autowired
    private redisCRUDUitls redisCRUDUitls;

    public void RedisLockTest() {
        logger.info("开始获取锁");
        Boolean setnx = redisCRUDUitls.setnx(LOCK, String.valueOf(System.currentTimeMillis() + TIMEOUT));
        if (setnx) {
            //成功获取锁并执行业务
            getLockAndDoService(LOCK);
        } else {
            String lockTimeout = redisCRUDUitls.getStringByKey(LOCK);
            if (StringUtils.isNotBlank(lockTimeout)
                    && System.currentTimeMillis() > Long.parseLong(lockTimeout)) {
                long newTimerOut = System.currentTimeMillis() + Long.valueOf(lockTimeout);
                //利用getset原子操作，set新的过期时间的同时获取旧的过期时间。
                String oldTimerOut = redisCRUDUitls.getSet(LOCK, String.valueOf(newTimerOut));
                if (StringUtils.isBlank(oldTimerOut) ||
                        (StringUtils.isNotBlank(oldTimerOut) &&
                                StringUtils.equals(lockTimeout, oldTimerOut))) {
                    //成功获取锁并执行业务
                    getLockAndDoService(LOCK);
                } else {
                    logger.info("没有获取到分布式锁:{}", LOCK);
                }
            }
        }
    }
    public void getLockAndDoService(String lockName){
        //设置过期时间防止程序执行业务时服务器出问题也不会死锁
        redisCRUDUitls.expire(lockName,TIMEOUT);
        logger.info("获取{}锁成功,ThreadName:{}",LOCK,Thread.currentThread().getName());
        service();
        //释放锁
        redisCRUDUitls.del(lockName);
        logger.info("释放{}锁,ThreadName:{}",LOCK,Thread.currentThread().getName());
        logger.info("===============================");
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
