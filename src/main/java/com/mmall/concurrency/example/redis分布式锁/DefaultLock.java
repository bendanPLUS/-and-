package com.mmall.concurrency.example.redis分布式锁;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Component
@Slf4j
//redis分布式锁的实现
public class DefaultLock implements Lock {
    public static final String LOCK = "lock";
    //默认锁过期时间30秒，后期优化做成可配置
    private static long DEFAULT_EXPIRE_TIME = 30L;
    @Autowired
    private RedisTemplate redisTemplate;

    private WatchDog watchDog;

    @Override
    public void lock() {
        //1.这里应对和本地jvm锁一样竞争的场景，如果竞争失败，则自旋
        while (!tryLock()) {
            log.info("线程{}获取分布式锁失败", Thread.currentThread().getName());
        }
        //2.这里应对有些需要没有获取到锁直接返回失败的场景，后期会做一个策略优化
//        Boolean re = redisTemplate.opsForValue().setIfAbsent(LOCK, UUID.randomUUID(), DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
//        if(Boolean.FALSE.equals(re)){
//            throw new LockCompititionFailException("获取分布式锁失败，当前线程ID："+Thread.currentThread().getId());
//        }
        //走到这里说明获取分布式锁成功，开启线程监视key过期时间，防止业务流程还没结束就释放锁的情况   成功获取锁以后执行WatchDog.run()方法
        startWatchDog();
    }

    private void startWatchDog() {
        watchDog = new WatchDog(true, redisTemplate);
        watchDog.start();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        UUID uuid = UUID.randomUUID();
        if (redisTemplate.opsForValue().setIfAbsent(LOCK,uuid)) {
            redisTemplate.expire(uuid, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
            // 上锁成功结束请求
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        //业务流程结束，释放锁
        redisTemplate.delete(LOCK);
        //将watchdog线程停止
        watchDog.setBusinessDone(false);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}