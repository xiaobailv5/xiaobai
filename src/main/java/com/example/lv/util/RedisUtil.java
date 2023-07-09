package com.example.lv.util;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description redis 工具类
 * @date 2023/6/18 10:02:05
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * Integer String 实体类
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setObject(final String key,final T value){
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     *  有效期
     * @param key
     * @param value
     * @param timeout 有效期
     * @param timeUnit 时间颗粒 秒，分钟 等
     * @param <T>
     */
    public <T> void setObject(final String key,final T value,final Integer timeout,final TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    /**
     * 设置有效时间
     * @param key
     * @param timeout 默认秒
     * @return
     */
    public boolean expire(final String key,final long timeout){
        return expire(key,timeout,TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean expire(final String key,final long timeout,final TimeUnit unit){
        return redisTemplate.expire(key,timeout,unit);
    }

    /**
     * 获取对象
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getObject(final String key){
        ValueOperations<String,T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     * @param key
     * @return
     */
    public boolean deleteObject(final String key){
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     * @param collection
     * @return
     */
    public long deleteObject(final Collection<Object> collection){
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存list
     * @param key
     * @param dataList
     * @param <T>
     * @return
     */
    public <T> long setList(final String key,final List<T> dataList){
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count==null ? 0 : count;
    }

    /**
     * 获取list
     * @param key
     * @param <T>
     * @return
     */
    public <T> List<T> getList(final String key){
        return redisTemplate.opsForList().range(key,0,-1);
    }

    /**
     * 缓存set
     * @param key
     * @param dataSet
     * @param <T>
     * @return
     */
    public <T>BoundSetOperations<String,T> setSet(final String key,final Set<T> dataSet){
        BoundSetOperations<String,T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> iterator = dataSet.iterator();
        while (iterator.hasNext()){
            setOperation.add(iterator.next());
        }
        return setOperation;
    }

    /**
     * 获取set
     * @param key
     * @param <T>
     * @return
     */
    public <T> Set<T> getSet(final String key){

        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存hash
     * @param key
     * @param hKey
     * @param value
     * @param <T>
     */
    public <T> void setHash(final String key,final String hKey,final T value){
        redisTemplate.opsForHash().put(key,hKey,value);
    }

    /**
     * 获取hash
     * @param key
     * @param hKey
     * @param <T>
     * @return
     */
    public <T> T getHash(final String key,final String hKey){
        HashOperations<String,String,T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key,hKey);
    }

    /**
     * 删除 hash
     * @param key
     * @param hKey
     */
    public void delHash(final String key,final String hKey){
        HashOperations<Object,Object,Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key,hKey);
    }

    /**
     * 获取多个hash的值
     * @param key
     * @param hKeys
     * @param <T>
     * @return
     */
    public <T> List<T> getHashList(final String key,final Collection<Object> hKeys){
        return redisTemplate.opsForHash().multiGet(key,hKeys);
    }

    /**
     * 获取缓存的基本对象列表
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern){
        return redisTemplate.keys(pattern);
    }
}
