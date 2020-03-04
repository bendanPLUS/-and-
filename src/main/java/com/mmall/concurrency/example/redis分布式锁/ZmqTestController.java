package com.mmall.concurrency.example.redis分布式锁;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
public class ZmqTestController {
    @Autowired
    DefaultLock defaultLock;

    //模拟商品数量
    private Integer count = 50;

    @RequestMapping("/decrease")
    @ResponseBody
    public String testDistributeLock() {
        try {
            //加上锁
            defaultLock.lock();
            if (count < 1) {
                log.info("库存不足");
                return "失败";
            }
            count--;
            log.info("当前线程：{},库存扣减成功，剩余库存：{}", Thread.currentThread().getId(), count);
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            //释放锁
            defaultLock.unlock();
        }
        return "成功";
    }
}
