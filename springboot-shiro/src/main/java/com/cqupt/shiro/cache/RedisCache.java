package com.cqupt.shiro.cache;

import com.cqupt.shiro.utils.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.Set;

// 自定义redis缓存的实现
public class RedisCache<K,V> implements Cache<K,V> {
    @Override
    public V get(K k) throws CacheException {
        System.out.println("从Redis中取数据");
        return (V) getRedisTemplate().opsForValue().get(k.toString());
    }

    @Override
    public V put(K k, V v) throws CacheException {
        System.out.println("向Redis存入数据");
        getRedisTemplate().opsForValue().set(k.toString(), v);
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return (V) getRedisTemplate().delete(k.toString());
    }

    @Override
    public void clear() throws CacheException {
        getRedisTemplate().discard();
    }

    @Override
    public int size() {
        return getRedisTemplate().keys("*").size();
    }

    @Override
    public Set<K> keys() {
        return getRedisTemplate().keys("*");
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    public RedisTemplate getRedisTemplate() {
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
