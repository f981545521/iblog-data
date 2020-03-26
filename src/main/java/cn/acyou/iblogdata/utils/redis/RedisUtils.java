package cn.acyou.iblogdata.utils.redis;


import cn.acyou.iblogdata.utils.Md5Util;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands.Limit;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-21 下午 10:44]
 **/
@Component
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    //加锁时间，单位纳秒， 即：加锁时间内执行完操作，如果未完成会有并发现象
    private static final Long LOCK_TIMEOUT = 10000000000L;

    //等待超时时间 3秒
    private static final Long LOCAK_WAITING = 3000L;

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    //PX 命令执行有问题，所以这里用EX。 PX命令 返回ok，但是实际缺没有插入值。
    private static final String SET_WITH_EXPIRE_TIME = "EX";

    // 要确保上述操作是原子性的,要使用Lua语言来实现.
    // 首先获取锁对应的value值，检查是否与token相等，如果相等则删除锁（解锁）
    private static final String LUA_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 通过key查询缓存中对应的String类型value
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    public String get(String key) {
        String strValue = redisTemplate.opsForValue().get(key);
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K-V： " + key + "=" + strValue);
        }
        return strValue;
    }

    public void set(String key, String value, Long timeout, TimeUnit unit) {
        if (logger.isDebugEnabled()) {
            logger.debug("{}|{}|{}|{}|{}", "set方法入参：", "键:" + key, "值:" + value, "存活时间:" + timeout, "时间单位:" + unit);
        }
        if (timeout != null) {
            redisTemplate.opsForValue().set(key, value, timeout, unit != null ? unit : TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    public void set(String key, String value, long timeout) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K-V： " + key + "=" + value);
        }
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 通过key插入缓存中对应的String类型value
     */
    public void set(String key, String value) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K-V： " + key + "=" + value);
        }
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 根据key删除缓存中的记录
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 根据多个key批量删除缓存中的value
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 出栈
     *
     * @param key     key
     * @param timeout 出栈操作的连接阻塞保护时间,时间单位为秒
     * @return 返回的是承载执行结果和数据的对象
     */
    public String leftPop(String key, long timeout) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForList().leftPop(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 压栈
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    public Long leftPush(String key, String value) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 批量压栈
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    public Long leftPushAll(String key, Collection<String> values) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    /**
     * 出队
     *
     * @param key     key
     * @param timeout 出队操作的连接阻塞保护时间,时间单位为秒
     * @return 返回的是承载执行结果和数据的对象
     */
    public String rightPop(String key, long timeout) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 出队
     *
     * @param key key
     * @return 返回的是承载执行结果和数据的对象
     */
    public String rightPop(String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForList().rightPop(key);
    }

    public <T> T rightPop2Object(String key, Class<T> clazz) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        try {
            String strValue = redisTemplate.opsForList().rightPop(key);
            if (StringUtils.isNotEmpty(strValue)) {
                return JSON.parseObject(strValue, clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Json转对象失败!", e);
            return null;
        }
    }

    /**
     * 压队
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    public Long rightPush(String key, String value) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K-V： " + key + "=" + value);
        }
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 批量压队
     *
     * @return 返回的是承载执行结果和数据的对象
     */
    public Long rightPushAll(String key, Collection<String> values) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key);
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 获取大小
     *
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long getListSize(String key) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key);
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 对Set的pop操作
     *
     * @param key 键
     * @return 返回的是承载执行结果和数据的对象
     */
    public String pop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 对Set的添加操作
     *
     * @param key
     * @param values 插入Set的String数组
     * @return Long
     */
    public Long sadd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 移除集合key中的一个或多个member元素，不存在的member元素会被忽略。
     *
     * @param key    键
     * @param values 值
     */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 随机获取多个key无序集合中的元素（去重），count表示个数
     *
     * @param key
     * @param count
     * @return
     */
    public Set<String> sGets(String key, long count) {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     * 根据hashKey获取对应key对应的HashMap,
     *
     * @param key
     * @param hashKey
     * @return
     */
    public <T> T hGet(String key, String hashKey, Class<T> clazz) {
        String o = (String) redisTemplate.opsForHash().get(key, hashKey);
        return JSON.parseObject(o, clazz);
    }


    /**
     * 根据hashKey获取对应key对应的HashMap,
     *
     * @param key
     * @return
     */

    public List<Object> hMultiGet(String key, Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 返回hashkey对应的所有field的值
     *
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<Object, Object> hGetAll(String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 根据hashkey向key对应的HashMap中添加value
     *
     * @param key
     * @param hashKey 对应的HashMap中的
     * @param value
     * @return
     */

    public void hPut(String key, Object hashKey, Object value) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key + " HashMap中的key： " + hashKey);
        }
        redisTemplate.opsForHash().put(key, hashKey, JSON.toJSONString(value));
    }

    /**
     * 根据key向缓存中插入整个HashMap
     *
     * @param key
     * @param map 要插入的HashMap对象
     * @return
     */

    public void hPutAll(String key, Map<? extends Object, ? extends Object> map) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 根据hashKeys批量删除key对应的HashMap中的记录
     *
     * @param key
     * @param hashKeys
     * @return
     */

    public Long hDel(String key, String... hashKeys) {
        Long counts = 0L;
        if (hashKeys.length > 1) {
            Object[] objKeys = new Object[hashKeys.length];
            objKeys = hashKeys.clone();
            counts = redisTemplate.opsForHash().delete(key, objKeys);
        } else {
            counts = redisTemplate.opsForHash().delete(key, (Object) hashKeys[0]);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return counts;
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 根据key获取Map中所有的记录条数
     *
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Long hLen(String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * ZADD操作
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean zadd(String key, String value, double score) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key + "值： " + value + "");
        return redisTemplate.opsForZSet().add(key, value, score);
    }
    /**
     * ZSET incrementScore
     *
     * @param key
     * @param value
     * @param deal
     * @return
     */
    public Double zincrementScore(String key, String value, double deal) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key + "值： " + value + "");
        return redisTemplate.opsForZSet().incrementScore(key, value, deal);
    }
    /**
     * zrangeByScoreWithScores
     *
     * @param key key
     * @return
     */
    public List<ZSetItem> zrangeByScoreWithScores(String key) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key);
        Set<TypedTuple<String>> sset = redisTemplate.opsForZSet().
                reverseRangeByScoreWithScores(key, Double.MIN_VALUE, Double.MAX_VALUE, 0, 8);
        List<ZSetItem> result = new ArrayList<>();
        Iterator<TypedTuple<String>> it = sset.iterator();
        result = this.buildZSetList(result, it);
        return result;
    }

    /**
     * ZREM操作
     *
     * @param key
     * @return
     */
    public Long zRem(String key, Object... values) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key + "值： " + values + "");
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 由索引返回一个成员范围的有序集合。
     */
    public Set<String> range(String key, long start, long end) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key);
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 返回一个成员范围的有序集合（由字典范围）
     *
     * @param key
     * @param range
     * @param limit
     * @return
     */
    public Set<String> rangeByLex(String key, Range range, Limit limit) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key);
        return redisTemplate.opsForZSet().rangeByLex(key, range, limit);
    }

    /**
     * 按分数返回一个成员范围的有序集合。
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    public Set<String> rangeByScore(String key, double min, double max, long offset, long count) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key);
        return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
    }

    /**
     * 返回一个成员范围的有序集合，通过索引，以分数排序，从高分到低分
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> reverseRange(String key, long start, long end) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 返回一个成员范围的有序集合，按分数，以分数排序从高分到低分
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     */
    public Set<String> reverseRangeByScore(String key, double min, double max, long offset, long count) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
    }

    /**
     * <返回有序集key中成员member的排名，其中有序集成员按score值从大到小排列>
     * <排名以0为底，也就是说，score值最大的成员排名为0。>
     *
     * @param key
     * @param member
     * @return
     */
    public Long zRevRank(String key, String member) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForZSet().reverseRank(key, member);
    }

    /**
     * <返回有序集key中成员member的排名。><br>
     * <其中有序集成员按score值递增(从小到大)顺序排列。排名以0为底，也就是说，score值最小的成员排名为0。使用ZREVRANK命令可以获得成员按score值递减(从大到小)排列的排名。>
     *
     * @param key
     * @param member
     * @return 如果member是有序集key的成员，返回integer-reply：member的排名。<br>
     * 如果member不是有序集key的成员，返回bulk-string-reply: nil。
     */
    public Long zRank(String key, String member) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForZSet().rank(key, member);
    }

    /**
     * 返回有序集key中，成员member的score值。
     * 如果member元素不是有序集key的成员，或key不存在，返回nil。
     *
     * @param key
     * @param member
     * @return member成员的score值（double型浮点数）
     */
    public Double zScore(String key, String member) {
        if (logger.isDebugEnabled())
            logger.debug("接口调用详情：参数K： " + key);
        return redisTemplate.opsForZSet().score(key, member);
    }

    /**
     * 有序集key中给指定成员member增加score值
     * 如果member元素不是有序集key的成员或key不存在则返回nil
     *
     * @param key
     * @param member
     * @return member成员的score值（double型浮点数）
     */
    public Double incrementScore(String key, String member, double score) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForZSet().incrementScore(key, member, score);
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<ZSetItem> zRangeWithScores(String key, long start, long end) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        List<ZSetItem> result = new ArrayList<>();
        Set<TypedTuple<String>> sset = redisTemplate.opsForZSet().rangeWithScores(key, start, end);
        Iterator<TypedTuple<String>> it = sset.iterator();
        result = this.buildZSetList(result, it);
        return result;
    }

    /**
     * @param key
     * @return
     */
    public List<ZSetItem> rangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key + ",min:" + min + ",max:" + max + ",offset:" + offset + ",count:" + count);
        }

        List<ZSetItem> result = new ArrayList<ZSetItem>();
        Set<TypedTuple<String>> sset =
                redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
        Iterator<TypedTuple<String>> it = sset.iterator();
        result = this.buildZSetList(result, it);

        return result;
    }

    /**
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<ZSetItem> zRevRangeWithScores(String key, long start, long end) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        List<ZSetItem> result = new ArrayList<>();
        Set<TypedTuple<String>> sset = redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
        Iterator<TypedTuple<String>> it = sset.iterator();
        result = this.buildZSetList(result, it);
        return result;
    }

    /**
     * 判定当前key是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.hasKey(key);
    }

    /**
     * Get the size of sorted set with {@code key}.
     *
     * @return
     */
    public Long zCard(String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForZSet().zCard(key);
    }

    /**
     * 返回列表key的长度。<br>
     * 如果key不存在，则key被解释为一个空列表，返回0. 如果key不是列表类型，返回一个错误。<br>
     *
     * @param key
     * @return
     */
    public Long lLen(String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 返回列表key中指定区间内的元素，区间以偏移量start和stop指定。
     * <下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。 你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。>
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lRange(String key, long start, long end) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + key);
        }
        return redisTemplate.opsForList().range(key, start, end);
    }

    public Boolean setNx(String key, String value, long timeout) {
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (b) {
            Boolean expire = redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            return b;
        }
        return b;
    }

    public Set<String> delKeysInPattern(String pattern) {
        if (logger.isDebugEnabled()) {
            logger.debug("{}|{}|{}", "获取所有匹配pattern参数的Keys", "[KEYS pattern]:", pattern);
        }

        Set<String> keySets = redisTemplate.keys(pattern);
        if (!CollectionUtils.isEmpty(keySets)) {
            redisTemplate.delete(keySets);
        } else {
            logger.info("{}|{}|{}", "根据匹配pattern参数获取的Keys集合为空", "[KEYS pattern]:" + pattern, keySets.size());
        }

        return keySets;
    }

    private List<ZSetItem> buildZSetList(List<ZSetItem> result, Iterator<TypedTuple<String>> it) {
        if (result == null || it == null) {
            return null;
        }
        while (it.hasNext()) {
            ZSetItem item = new ZSetItem();
            TypedTuple<String> typedTuple = it.next();
            String value = typedTuple.getValue();
            Double score = typedTuple.getScore();
            item.setMember(value);
            item.setScore(score);
            result.add(item);
        }
        return result;
    }

    /**
     * 自增 / 自减
     *
     * @param key   key
     * @param value 1自增1 -1减少1
     * @return
     */
    public Long increment(String key, long value) {
        if (logger.isDebugEnabled()) {
            logger.debug("{}|{}|{}", "increment接口开始调用：", "key:" + key, "value:" + value);
        }
        return redisTemplate.opsForValue().increment(key, value);
    }

    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
        if (logger.isDebugEnabled()) {
            logger.debug("{}|{}|{}|{}", "expire接口开始调用：", "key:" + key, "timeout:" + timeout, "timeUnit:" + timeUnit);
        }
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public Set<String> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Long zadd(String key, Set<TypedTuple<String>> typedTuples) {
        return redisTemplate.opsForZSet().add(key, typedTuples);
    }

    public Long zRemRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    public Long zRemRange(String key, Long start, Long end) {
        long startValue = start != null ? start : 0;
        long endValue = end != null ? end : -1;
        return redisTemplate.opsForZSet().removeRange(key, startValue, endValue);
    }

    /**
     * 根据表达式查询所有key
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Set<String> keys(String pattern) {
        if (logger.isDebugEnabled()) {
            logger.debug("接口调用详情：参数K： " + pattern);
        }
        Set<String> keys = redisTemplate.keys(pattern);
        return keys;

    }

    /*************************************重写锁*****************************/
    /**
     * 加锁
     * 取到锁加锁，取不到锁，则等待超时时间1秒，如果取不到，则返回
     *
     * @param lockKey 锁Key
     * @return
     */
    public Long lock(String lockKey) {
        Long waitingStartTime = System.nanoTime();
        try {
            do { //循环获取锁
                //锁时间
                Long lockTimeout = System.nanoTime() + LOCK_TIMEOUT + 1;
                if (setnx(lockKey, lockTimeout.toString())) {
                    //设置超时时间，释放内存
                    redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.NANOSECONDS);
                    return lockTimeout;
                } else {
                    //获取redis里面的时间
                    String result = redisTemplate.opsForValue().get(lockKey);
                    Long currtLockTimeoutStr = result == null ? null : Long.parseLong(result);
                    //锁已经失效
                    if (currtLockTimeoutStr != null && currtLockTimeoutStr < System.nanoTime()) {
                        //判断是否为空，不为空时，说明已经失效，如果被其他线程设置了值，则第二个条件判断无法执行
                        //获取上一个锁到期时间，并设置现在的锁到期时间
                        Long oldLockTimeoutStr =
                                Long.valueOf(redisTemplate.opsForValue().getAndSet(lockKey, lockTimeout.toString()));
                        if (oldLockTimeoutStr != null && oldLockTimeoutStr.equals(currtLockTimeoutStr)) {
                            //多线程运行时，多个线程签好都到了这里，但只有一个线程的设置值和当前值相同，它才有权利获取锁
                            //设置超时间，释放内存
                            redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.NANOSECONDS);
                            //返回加锁时间
                            return lockTimeout;
                        }
                    }
                }
            } while ((System.nanoTime() - waitingStartTime) < LOCAK_WAITING);
        } catch (Throwable e) {
            return null;
        }
        return null;
    }

    /**
     * 解锁
     *
     * @param lockKey
     * @param lockValue
     */
    public void unlock(String lockKey, Long lockValue) {
        if (lockValue == null) {
            return;
        }
        //获取redis中设置的时间
        String result = redisTemplate.opsForValue().get(lockKey);
        Long currtLockTimeoutStr = result == null ? null : Long.valueOf(result);
        //如果是加锁者，则删除锁， 如果不是，则等待自动过期，重新竞争加锁
        if (currtLockTimeoutStr != null && currtLockTimeoutStr.longValue() == lockValue.longValue()) {
            redisTemplate.delete(lockKey);
        }
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public boolean setnx(final String key, final String value) {
        BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(key);
        return boundValueOperations.setIfAbsent(value);
    }

    /**
     * 尝试获取锁，默认设置锁的超时时间为30s，获取到锁返回token；获取不到锁，返回null
     *
     * @param key
     * @return
     */
    public String tryLock(String key) {
        return tryLock(key, 30, TimeUnit.SECONDS);
    }

    /**
     * 尝试获取锁，获取到锁返回token；获取不到锁，返回null
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public String tryLock(String key, long timeout, TimeUnit unit) {
        Jedis jedis = null;
        try {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            long millseconds = timeUnit.convert(timeout, unit);
            // 加锁失败, 抛出异常
            long nowTime = System.currentTimeMillis();
            jedis = getJedis();
            String token = Md5Util.getMD5(key + "_" + nowTime);
            Assert.notNull(jedis, "can not get jedis connection");
            String result = jedis.set(key, token, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, millseconds);
            if (LOCK_SUCCESS.equals(result)) {
                return token;
            }
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据token释放锁
     *
     * @param lockKey
     * @param token
     */
    public boolean releaseLockByToken(String lockKey, String token) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Object result = jedis.eval(LUA_SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(token));
            return RELEASE_SUCCESS.equals(result);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    /**
     * 获取jedis 对象
     */
    private Jedis getJedis() {
        RedisConnection jedisConnection = redisTemplate.getConnectionFactory().getConnection();
        Jedis jedis = (Jedis) jedisConnection.getNativeConnection();
        return jedis;
    }
}
