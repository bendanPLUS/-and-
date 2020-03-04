package com.mmall.concurrency.example.redis分布式锁;


import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class WatchDog extends Thread{
    //业务流程是否完成
    private boolean businessDone;

    private RedisTemplate redisTemplate;

    public WatchDog(boolean businessDone,RedisTemplate redisTemplate){
        this.businessDone = businessDone;
        this.redisTemplate = redisTemplate;
    }

    public boolean isBusinessDone() {
        return businessDone;
    }

    public void setBusinessDone(boolean businessDone) {
        this.businessDone = businessDone;
    }

    @Override
    public void run() {
        while(businessDone){
            //如果锁的剩余过期时间小于10s ，则将其重置
            if(redisTemplate.getExpire(DefaultLock.LOCK) < 10L){
                redisTemplate.expire(DefaultLock.LOCK,30L, TimeUnit.SECONDS);
            }
            //为避免空转太频繁，适当让线程sleep
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
