package com.bquan.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Resource(name = "redisUtil")
public class RedisUtil {
	@Autowired()
	@Qualifier("shardedJedisPool")
	private ShardedJedisPool shardedJedisPool;
	
	/** 加锁标志 */
	public static final String LOCKED = "TRUE";
	/** 毫秒与毫微秒的换算单位 1毫秒 = 1000000毫微秒 */
	public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
	/** 默认超时时间（毫秒） */
	public static final long DEFAULT_TIME_OUT = 1000;
	public static final Random RANDOM = new Random();
	/** 锁的超时时间（秒），过期删除 */
	public static final int EXPIRE = 3 * 60;
	
	private String key;
	// 锁状态标志
	private boolean locked = false;

	/**
	 * 向redis存入key和value,并释放连接资源
	 * 如果key已经存在 则覆盖
	 * @param key
	 * @param value
	 * @return 成功 返回OK 失败返回 0
	 */
	public String set(String key,String value){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			
			return jedis.set(key, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return "0";
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}
	
	/**
	 * 通过key获取储存在redis中的value
	 * 并释放连接
	 * @param key
	 * @return 成功返回value 失败返回null
	 */
	public String get(String key){
		ShardedJedis jedis = null;
		String value = null;
		try {
			jedis = shardedJedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return value;
	}
	
	/**
	 * 删除指定的key
	 * @param keys 一个key
	 * @return 返回删除成功的个数 
	 */
	public Long del(String key){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.del(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return 0L;
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}
	
	/**
	 * 查看redis中剩余的生存时间
	 * @param key
	 * @return
	 */
	public Long ttl(String key){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.ttl(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return 0L;
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}
	
	/**
	 * 向redis存入key和序列化的object,并释放连接资源
	 * 如果key已经存在 则覆盖
	 * @param key
	 * @param object
	 * @return 成功 返回OK 失败返回 0
	 */
	public String setos(String key, Object object) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.set(key.getBytes(), SerializeUtil.serialize(object));
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return "0";
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}
	
	/**
	 * 向redis存入key和序列化的object,并释放连接资源,指定过期时间
	 * 如果key已经存在 则覆盖
	 * @param key
	 * @param object
	 * @return 成功 返回OK 失败返回 0
	 */
	public String setosex(String key, Object object, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.setex(key.getBytes(), seconds, SerializeUtil.serialize(object));
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return "0";
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}
	
	/**
	 * 通过字节数组key获取储存在redis中的反序列化的Object
	 * 并释放连接
	 * @param key 字节数组
	 * @return 成功返回value 失败返回null
	 */
	public Object getos(String key) {
		ShardedJedis jedis = null;
		byte[] value = null;
		try {
			jedis = shardedJedisPool.getResource();
	        value = jedis.get(key.getBytes());
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return SerializeUtil.unserialize(value);
	}
   
	/**
	 * 删除指定的字节数组key
	 * @param key 字节数组
	 * @return 返回删除成功的个数 
	 */
    public long del(byte[] key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.decr(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return 0L;
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
    }
    
	/**
	* 向缓存中设置对象(json格式)
	* 
	* @param key
	* @param object
	* @return
	*/
    public String setoj(String key,Object o){
    	ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.set(key.getBytes(), serialize(o));
		}catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return "0";
		}finally {
			returnResource(shardedJedisPool, jedis);
		}
    }
    
	/**
	 * 根据key 获取对象(json格式)
	 * 
	 * @param key
	 * @return
	 */
    public Object getoj(String key){
    	ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			byte[] byt=jedis.get(key.getBytes());
	        Object obj=unserizlize(byt);
	        return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			returnResource(shardedJedisPool, jedis);
		}
    }
	
	/**
	 * 通过key向指定的value值追加值
	 * @param key 
	 * @param str 
	 * @return 成功返回 添加后value的长度 失败 返回 添加的 value 的长度  异常返回0L
	 */
	public Long append(String key ,String str){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.append(key, str);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return 0L;
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return true OR false
	 */
	public Boolean exists(String key){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return false;
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}
	
	/**
	 * 设置key value,如果key已经存在则返回0,nx==> not exist
	 * @param key
	 * @param value
	 * @return 成功返回1 如果存在 和 发生异常 返回 0
	 */
	public Long setnx(String key ,String value){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.setnx(key, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return 0L;
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}
	
	/**
	 * 设置key value并制定这个键值的有效期
	 * @param key
	 * @param value
	 * @param seconds 单位:秒
	 * @return 成功返回OK 失败和异常返回null
	 */
	public String setex(String key,String value,int seconds){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.setex(key, seconds, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key 和offset 从指定的位置开始将原先value替换
	 * 下标从0开始,offset表示从offset下标开始替换
	 * 如果替换的字符串长度过小则会这样
	 * example:
	 * value : bigsea@zto.cn
	 * str : abc 
	 * 从下标7开始替换  则结果为
	 * RES : bigsea.abc.cn
	 * @param key
	 * @param str
	 * @param offset 下标位置
	 * @return 返回替换后  value 的长度
	 */
	public Long setrange(String key,String str,int offset){
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.setrange(key, offset, str);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
			return 0L;
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
	}

	/**
	 * 设置key的值,并返回一个旧值
	 * @param key
	 * @param value
	 * @return 旧值 如果key不存在 则返回null
	 */
	public String getset(String key,String value){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.getSet(key, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过下标 和key 获取指定下标位置的 value
	 * @param key
	 * @param startOffset 开始位置 从0 开始 负数表示从右边开始截取
	 * @param endOffset 
	 * @return 如果没有返回null 
	 */
	public String getrange(String key, int startOffset ,int endOffset){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.getrange(key, startOffset, endOffset);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
	 * @param key
	 * @return 加值后的结果
	 */
	public Long incr(String key){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.incr(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key给指定的value加值,如果key不存在,则这是value为该值
	 * @param key
	 * @param integer
	 * @return
	 */
	public Long incrBy(String key,Long integer){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.incrBy(key, integer);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 对key的值做减减操作,如果key不存在,则设置key为-1
	 * @param key
	 * @return
	 */
	public Long decr(String key) {
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.decr(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 减去指定的值
	 * @param key
	 * @param integer
	 * @return
	 */
	public Long decrBy(String key,Long integer){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.decrBy(key, integer);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key获取value值的长度
	 * @param key
	 * @return 失败返回null 
	 */
	public Long serlen(String key){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hlen(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key给field设置指定的值,如果key不存在,则先创建
	 * @param key
	 * @param field 字段
	 * @param value
	 * @return 如果存在返回0 异常返回null
	 */
	public Long hset(String key,String field,String value) {
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hset(key, field, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public Long hsetnx(String key,String field,String value){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hsetnx(key, field, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key同时设置 hash的多个field
	 * @param key
	 * @param hash
	 * @return 返回OK 异常返回null
	 */
	public String hmset(String key,Map<String, String> hash){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hmset(key, hash);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 通过key 和 field 获取指定的 value
	 * @param key
	 * @param field
	 * @return 没有返回null
	 */
	public String hget(String key, String field){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hget(key, field);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key 和 fields 获取指定的value 如果没有对应的value则返回null</p>
	 * @param key
	 * @param fields 可以使 一个String 也可以是 String数组
	 * @return 
	 */
	public List<String> hmget(String key,String...fields){
		ShardedJedis jedis = null;
		List<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hmget(key, fields);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key给指定的field的value加上给定的值</p>
	 * @param key
	 * @param field
	 * @param value 
	 * @return
	 */
	public Long hincrby(String key ,String field ,Long value){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key和field判断是否有指定的value存在</p>
	 * @param key
	 * @param field
	 * @return
	 */
	public Boolean hexists(String key , String field){
		ShardedJedis jedis = null;
		Boolean res = false;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hexists(key, field);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回field的数量</p>
	 * @param key
	 * @return
	 */
	public Long hlen(String key){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hlen(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
		
	}
	
	/**
	 * <p>通过key 删除指定的 field </p>
	 * @param key
	 * @param fields 可以是 一个 field 也可以是 一个数组
	 * @return
	 */
	public Long hdel(String key ,String...fields){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hdel(key, fields);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回所有的field</p>
	 * @param key
	 * @return
	 */
	public Set<String> hkeys(String key){
		ShardedJedis jedis = null;
		Set<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hkeys(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回所有和key有关的value</p>
	 * @param key
	 * @return
	 */
	public List<String> hvals(String key){
		ShardedJedis jedis = null;
		List<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hvals(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key获取所有的field和value</p>
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetall(String key){
		ShardedJedis jedis = null;
		Map<String, String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.hgetAll(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key向list头部添加字符串</p>
	 * @param key
	 * @param strs 可以使一个string 也可以使string数组
	 * @return 返回list的value个数
	 */
	public Long lpush(String key ,String...strs){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.lpush(key, strs);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key向list尾部添加字符串</p>
	 * @param key
	 * @param strs 可以使一个string 也可以使string数组
	 * @return 返回list的value个数
	 */
	public Long rpush(String key ,String...strs){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.rpush(key, strs);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key在list指定的位置之前或者之后 添加字符串元素</p>
	 * @param key 
	 * @param where LIST_POSITION枚举类型
	 * @param pivot list里面的value
	 * @param value 添加的value
	 * @return
	 */
	public Long linsert(String key, LIST_POSITION where,
		    String pivot, String value){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.linsert(key, where, pivot, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key设置list指定下标位置的value</p>
	 * <p>如果下标超过list里面value的个数则报错</p>
	 * @param key 
	 * @param index 从0开始
	 * @param value
	 * @return 成功返回OK
	 */
	public String lset(String key ,Long index, String value){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.lset(key, index, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key从对应的list中删除指定的count个 和 value相同的元素</p>
	 * @param key 
	 * @param count 当count为0时删除全部
	 * @param value 
	 * @return 返回被删除的个数
	 */
	public Long lrem(String key,long count,String value){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.lrem(key, count, value);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key保留list中从strat下标开始到end下标结束的value值</p>
	 * @param key
	 * @param start
	 * @param end
	 * @return 成功返回OK
	 */
	public String ltrim(String key ,long start ,long end){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.ltrim(key, start, end);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key从list的头部删除一个value,并返回该value</p>
	 * @param key
	 * @return 
	 */
	public String lpop(String key){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.lpop(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key从list尾部删除一个value,并返回该元素</p>
	 * @param key
	 * @return
	 */
	public String rpop(String key){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.rpop(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key获取list中指定下标位置的value</p>
	 * @param key
	 * @param index
	 * @return 如果没有返回null
	 */
	public String lindex(String key,long index){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.lindex(key, index);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回list的长度</p>
	 * @param key
	 * @return
	 */
	public Long llen(String key){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.llen(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key获取list指定下标位置的value</p>
	 * <p>如果start 为 0 end 为 -1 则返回全部的list中的value</p>
	 * @param key 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key,long start,long end){
		ShardedJedis jedis = null;
		List<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.lrange(key, start, end);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key向指定的set中添加value</p>
	 * @param key
	 * @param members 可以是一个String 也可以是一个String数组
	 * @return 添加成功的个数
	 */
	public Long sadd(String key,String...members){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.sadd(key, members);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key删除set中对应的value值</p>
	 * @param key
	 * @param members 可以是一个String 也可以是一个String数组
	 * @return 删除的个数
	 */
	public Long srem(String key,String...members){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.srem(key, members);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key随机删除一个set中的value并返回该值</p>
	 * @param key
	 * @return
	 */
	public String spop(String key){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.spop(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key获取set中value的个数</p>
	 * @param key
	 * @return
	 */
	public Long scard(String key){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.scard(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key判断value是否是set中的元素</p>
	 * @param key
	 * @param member
	 * @return
	 */
	public Boolean sismember(String key,String member){
		ShardedJedis jedis = null;
		Boolean res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.sismember(key, member);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key获取set中随机的value,不删除元素</p>
	 * @param key
	 * @return
	 */
	public String srandmember(String key){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.srandmember(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key获取set中所有的value</p>
	 * @param key
	 * @return
	 */
	public Set<String> smembers(String key){
		ShardedJedis jedis = null;
		Set<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.smembers(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key向zset中添加value,score,其中score就是用来排序的</p>
	 * <p>如果该value已经存在则根据score更新元素</p>
	 * @param key
	 * @param scoreMembers 
	 * @return
	 */
	public Long zadd(String key,Map<Double, String> scoreMembers){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zadd(key, scoreMembers);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key向zset中添加value,score,其中score就是用来排序的</p>
	 * <p>如果该value已经存在则根据score更新元素</p>
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public Long zadd(String key,double score,String member){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zadd(key, score, member);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key删除在zset中指定的value</p>
	 * @param key
	 * @param members 可以使一个string 也可以是一个string数组
	 * @return
	 */
	public Long zrem(String key,String...members){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zrem(key, members);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key增加该zset中value的score的值</p>
	 * @param key
	 * @param score 
	 * @param member 
	 * @return
	 */
	public Double zincrby(String key ,double score ,String member){
		ShardedJedis jedis = null;
		Double res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zincrby(key, score, member);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回zset中value的排名</p>
	 * <p>下标从小到大排序</p>
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrank(String key,String member){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zrank(key, member);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回zset中value的排名</p>
	 * <p>下标从大到小排序</p>
	 * @param key
	 * @param member
	 * @return
	 */
	public Long zrevrank(String key,String member){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zrevrank(key, member);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key将获取score从start到end中zset的value</p>
	 * <p>socre从大到小排序</p>
	 * <p>当start为0 end为-1时返回全部</p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Set<String> zrevrange(String key ,long start ,long end){
		ShardedJedis jedis = null;
		Set<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回指定score内zset中的value</p>
	 * @param key 
	 * @param max 
	 * @param min 
	 * @return
	 */
	public Set<String> zrangebyscore(String key,String max,String min){
		ShardedJedis jedis = null;
		Set<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zrevrangeByScore(key, max, min);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回指定score内zset中的value</p>
	 * @param key 
	 * @param max  
	 * @param min 
	 * @return
	 */
	public Set<String> zrangeByScore(String key ,double max,double min){
		ShardedJedis jedis = null;
		Set<String> res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zrevrangeByScore(key,max,min);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>返回指定区间内zset中value的数量</p>
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Long zcount(String key,String min,String max){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zcount(key, min, max);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key返回zset中的value个数</p>
	 * @param key
	 * @return
	 */
	public Long zcard(String key){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zcard(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key获取zset中value的score值</p>
	 * @param key
	 * @param member
	 * @return
	 */
	public Double zscore(String key,String member){
		ShardedJedis jedis = null;
		Double res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zscore(key, member);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key删除给定区间内的元素</p>
	 * @param key 
	 * @param start 
	 * @param end
	 * @return
	 */
	public Long zremrangeByRank(String key ,long start, long end){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key删除指定score内的元素</p>
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public Long zremrangeByScore(String key,double start,double end){
		ShardedJedis jedis = null;
		Long res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.zremrangeByScore(key, start, end);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * <p>通过key判断值得类型</p>
	 * @param key
	 * @return
	 */
	public String type(String key){
		ShardedJedis jedis = null;
		String res = null;
		try {
			jedis = shardedJedisPool.getResource();
			res = jedis.type(key);
		} catch (Exception e) {
			shardedJedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(shardedJedisPool, jedis);
		}
		return res;
	}
	
	/**
	 * 返还到连接池
	 * 
	 * @param shardedJedisPool
	 * @param redis
	 */
	public static void returnResource(ShardedJedisPool pool, ShardedJedis jedis) {
		if (jedis != null) {
			pool.returnResource(jedis);
		}
	}
	
	//-----------------------------------------------------带锁
	/**
	 * 加锁
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用 
	 * @param timeout 超时时间
	 * @return 成功或失败标志
	 */
	public boolean lock(String key,long timeout) {
		this.key = key + "_lock";
		long nano = System.nanoTime();
		timeout *= MILLI_NANO_CONVERSION;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			while ((System.nanoTime() - nano) < timeout) {
				if (jedis.setnx(this.key, LOCKED) == 1) {
					jedis.expire(this.key, EXPIRE);
					this.locked = true;
					return this.locked;
				}
				// 短暂休眠，避免出现活锁
				Thread.sleep(3, RANDOM.nextInt(500));
			}
		} catch (Exception e) {
			throw new RuntimeException("Locking error", e);
		}
		return false;
	}

	/**
	 * 加锁
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用
	 * @param timeout 超时时间
	 * @param expire 锁的超时时间（秒），过期删除
	 * @return 成功或失败标志
	 */
	public boolean lock(String key,long timeout, int expire) {
		this.key = key + "_lock";
		long nano = System.nanoTime();
		timeout *= MILLI_NANO_CONVERSION;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			while ((System.nanoTime() - nano) < timeout) {
				if (jedis.setnx(this.key, LOCKED) == 1) {
					jedis.expire(this.key, expire);
					this.locked = true;
					return this.locked;
				}
				// 短暂休眠，避免出现活锁
				Thread.sleep(3, RANDOM.nextInt(500));
			}
		} catch (Exception e) {
			throw new RuntimeException("Locking error", e);
		}
		return false;
	}

	/**
	 * 加锁
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用
	 * @return 成功或失败标志
	 */
	public boolean lock(String key) {
		return lock(key,DEFAULT_TIME_OUT);
	}

	/**
	 * 解锁
	 * 无论是否加锁成功，都需要调用unlock
	 * 应该以：
	 * lock();
	 * try {
	 * 		doSomething();
	 * } finally {
	 * 		unlock()；
	 * }
	 * 的方式调用
	 */
	public void unlock(String key) {
		this.key = key + "_lock";
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			if (this.locked) {
				jedis.del(this.key);
			}
		} finally {
			this.shardedJedisPool.returnResource(jedis);
		}
	}
	
	//序列化 
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
        
        return null;
    }
    
}
