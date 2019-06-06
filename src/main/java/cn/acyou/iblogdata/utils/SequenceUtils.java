package cn.acyou.iblogdata.utils;

import cn.acyou.iblog.utility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Redis 自增主键工具
 *
 * @author youfang
 * @version [1.0.0, 2019-05-16 21:04]
 **/
@Component
public class SequenceUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取公证书编号
     * @return
     */
    public String getGzsbh(String jgjc){
        String year = DateUtil.getCurrentDateFormat("yyyy");
        String key = "SEQ:" + year + jgjc + ":key";
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long increment = entityIdCounter.getAndIncrement();
        if (increment == 0){
            increment = entityIdCounter.getAndIncrement();
        }
        return "(" + year + ")" + jgjc + "证字第" + increment + "号";
    }

    public String getInceId() {
        String formatDate = DateUtil.getCurrentDateShortFormat();
        String key = "SEQ:" + formatDate + "key";
        long incr = getIncr(key, getCurrent2TodayEndMillisTime());
        if (incr == 0) {
            //从000001开始
            incr = getIncr(key, getCurrent2TodayEndMillisTime());
        }
        //4位序列号
        DecimalFormat df = new DecimalFormat("0000");
        return formatDate + df.format(incr);
    }

    private long getIncr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long increment = entityIdCounter.getAndIncrement();
        //初始设置过期时间
        if (increment == 0 && liveTime > 0) {
            //单位毫秒
            entityIdCounter.expire(liveTime, TimeUnit.MILLISECONDS);
        }
        return increment;
    }

    /**
     * 现在到今天结束的毫秒数
     */
    private Long getCurrent2TodayEndMillisTime() {
        Calendar todayEnd = Calendar.getInstance();
        // Calendar.HOUR 12小时制
        // HOUR_OF_DAY 24小时制
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis() - System.currentTimeMillis();
    }


}
