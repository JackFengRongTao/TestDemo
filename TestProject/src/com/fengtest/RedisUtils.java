package com.fengtest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisUtils {
	// protected static Logger logger = Logger.getLogger(RedisUtil.class);

	// Redis服务器IP 测试：10.2.113.27",6379 正式：10.2.113.1
//	private static String IP = "127.0.0.1";
	private static String IP = "192.250.107.141";
	// Redis密码
	private static String PASSWORD = "abc123";

	// Redis的端口号
	private static int PORT = 6379;

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 100;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 20;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 5*60*1000;

	private static int TIMEOUT = 5*60*1000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	// 在return给pool时，是否提前进行validate操作；
	private static boolean TEST_ON_RETURN = true;

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月


	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, IP, PORT, TIMEOUT, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 同步获取Jedis实例
	 * 
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null){
				jedis.close();
			}
		}
		return jedis;
	}

	/**
	 * 新增List类型数据
	 * 添加一条或者多条记录，如果key-value（lsit）不存在，则更新
	 * 如果存在，则不操作
	 * @param key		新建的或者已存在的key
	 * @param members	values值，一条或者多条
	 * @return rs		新增value值的条数
	 */
	public static Long sadd(String key, String... members) {
		Jedis jedis = null;
		Long rs = null;
		try {
			jedis = getJedis();
			rs = jedis.sadd(key, members);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 获取value值为String类型的value值
	 * @param key 	要获取的key
	 * @return rs   如果key存在，则获取其值；如果key不存在，则返回null
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String rs = null;
		try {
			jedis = getJedis();
			rs = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 删除一个key及其value值
	 * @param key	要删除的key
	 * @return rs	如果key存在则返回1；如果key不存在则返回0
	 */
	public static Long  del(String key) {
		Jedis jedis = null;
		Long  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 判断key是否存在
	 * @param key 	要判断的key
	 * @return rs	true：存在；false：不存在
	 */
	public static Boolean exists(String key) {
		Jedis jedis = null;
		Boolean  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *添加一条记录 如果map的key存在 则更新value
	 *hset 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
	 *如果字段已经存在于哈希表中，旧值将被覆盖
	 * @param key		要添加的key
	 * @param field		map的key
	 * @param value		map的value
	 * @return	rs		1：添加或者更新成功；0：没有操作
	 */
	public static Long hset(String key,String field,String value) {
		Jedis jedis = null;
		Long  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.hset(key,field,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 批量添加记录
	 *hmset 同时将多个 field-value (域-值)对设置到哈希表 key 中。
	 *此命令会覆盖哈希表中已存在的域。
	 *如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
	 * @param  key 要添加的key
	 * @param  map 多个 field-value (域-值)对
	 * @return rs	OK:操作成功
	 */
	public static String hmset(String key,Map<String,String> map) {
		Jedis jedis = null;
		String  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.hmset(key,map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *删除hash中 field对应的值
	 *hdel 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
	 * 如果删除所有的域，此key也将被删除
	 * @param key		要删的key
	 * @param fields	要删的属性名
	 * @return rs		删除field-value的数量
	 */
	public static Long hdel(String key,String...fields) {
		Jedis jedis = null;
		Long  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.hdel(key,fields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *获取hash中 指定的field的值
	 *hmget 返回哈希表 key 中，一个或多个给定域的值。
	 *如果给定的域不存在于哈希表，那么返回一个 null 值。
	 *不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 null 值的表。
	 * @param key		要获取的key
	 * @param fields	要获取的一个或者多个属性
	 * @return rs		返回多个fields各自对应的值的集合
	 */
	public static List<String> hmget(String key,String...fields) {
		Jedis jedis = null;
		List<String>  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.hmget(key,fields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *获取hash中 所有的field value
	 * @param  key 	要获取的key
	 * @return rs	返回的Map类型的 field-value对
	 */
	public static Map<String, String>  hgetAll(String key) {
		Jedis jedis = null;
		Map<String, String>   rs = null;
		try {
			jedis = getJedis();
			rs = jedis.hgetAll(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *判断hash中 指定的field是否存在
	 * @param key		要判断的key
	 * @param field		指定的field
	 * @return rs		false：不存在；true：存在
	 */
	public static  Boolean hexists(String key, String field) {
		Jedis jedis = null;
		Boolean  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.hexists(key,field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 *获取hash的size
	 *hlen 获取哈希表中字段的数量
	 * @param key	要获取size的key
	 * @return rs	field-value的数量
	 */
	public static  Long hlen(String key) {
		Jedis jedis = null;
		Long  rs = null;
		try {
			jedis = getJedis();
			rs = jedis.hlen(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(exists("test"));
//		Long rs = sadd("key2","ceshi555","ceshi666","ceshi77");
//		System.out.println("返回值："+rs);
//		String s = get("qetTest");
//		System.out.println(s);
//		long delrs = del("zetTest");
//		System.out.println(delrs);
//		long delrs = hset("hsetTest444","33","333C");
//		System.out.println(delrs);

//		Map<String,String> map = new HashMap<>();
//		map.put("1","11");
//		map.put("2","2222");
//		String rs = hmset("fengrt",map);
//		System.out.println(rs);

//		Long rs = hdel("fengrt","2","1");
//		System.out.println(rs);

//		List<String> rs = hmget("hsetTest4445","33","1");
//		System.out.println(rs.toString());

//		Map<String, String>  rs = hgetAll("hsetTest");
//		System.out.println(rs.toString());

//		Long rs = hlen("hsetTest444");
//		System.out.println(rs);
	}


}
