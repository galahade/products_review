package com.yyang.test;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;

import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;

public class ConsistencyTester {
	
	public ConsistencyTester(RedisClusterClient redisClusterClient) {
		this.redisClusterClient = redisClusterClient;
		this.redisClusterClient.setDefaultTimeout(100, TimeUnit.MILLISECONDS);
		prefix= String.join("|", ManagementFactory.getRuntimeMXBean().getName(),String.valueOf(new Date().getTime()));
		cached = new HashMap<String,String>();
		errtime = new HashMap<>();

	}
	
	private RedisClusterClient redisClusterClient;
	
	private int workingSet = 1000;
	
	private int keySpace = 10000;
	
	private int writes = 0;
	
	private int reads = 0;
	
	private int failed_writes = 0;
	
	private int failed_reads = 0;
	
	private int lost_writes = 0;
	
	private int not_ack_writes = 0;
	
	private int delay = 1000;
	
	private String prefix;
	
	private Map<String, String> cached;
	
	private Map<String, Long> errtime;
	//private errtime = ;
	
	public String genKey() {
		
		double tmp = RandomUtils.nextDouble(0.0, 1.0);
		
		int ks = tmp > 0.5 ? keySpace : workingSet;
		
		return prefix + "|key_"+RandomUtils.nextInt(0, ks);
		
	}
	
	private void checkConsistency(String key, String value) {
		String expected = cached.get(key);
		if(null == expected) {
			return ;
		}
		int iExpected = Integer.parseInt(expected);
		int iValue = Integer.parseInt(value);
		
		if(iExpected > iValue) {
			this.lost_writes += iExpected - iValue;
		} else if(iExpected < iValue) {
			this.not_ack_writes += iValue - iExpected;
		}
	}
	
	private void log(String msg) {
		if(!errtime.containsKey(msg) || errtime.get(msg) != (new Date().getTime())) {
			System.out.println(msg);
		}
		errtime.put(msg, new Date().getTime());
	}
	
	@SuppressWarnings("resource")
	public void test() {
		RedisAdvancedClusterCommands<String,String> commands = redisClusterClient.connect().sync();
		long lastReport = new Date().getTime();
		//int count = 1000000;
		while(true) {
			String key = genKey();
			
			//Read
			try{
				String value = commands.get(key);
				if(null != value) {
					checkConsistency(key, value);
				}
				this.reads++;
			} catch(Exception e) {
				log("Reading : " + e.getMessage());
				failed_reads++;
				commands = refreshClusterCommands();
			}
			
			//Write
			try{
				this.cached.put(key, commands.incr(key).toString());
				this.writes ++;
			} catch(Exception e) {
				log("Writing : " + e.getMessage());
				failed_writes++;
				commands = refreshClusterCommands();
			}
			
			//Report
			try {
				Thread.sleep(delay);
				if(new Date().getTime() != lastReport) {
//					#{@reads} R (#{@failed_reads} err) | " +
//                    "#{@writes} W (#{@failed_writes} err) | "
					String report = String.format("%d R (%d err) | %d W (%d err) |", reads, failed_reads, writes, failed_writes);
					if(lost_writes > 0) {
						report += String.format("#{%d lost | ", lost_writes);
					}
					if(not_ack_writes > 0) {
						report += String.format("#{%d noack | ", not_ack_writes);
					}
					lastReport = new Date().getTime();
					log(report);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	private RedisAdvancedClusterCommands<String,String> refreshClusterCommands() {
		try {
		redisClusterClient.reloadPartitions();
		StatefulRedisClusterConnection<String,String> connection = redisClusterClient.connect();
		 return connection.sync();
		} catch (Exception e) {
			e.printStackTrace();
			redisClusterClient.shutdown();
			System.exit(0);
			
		}
		return null;
	}
	

}
