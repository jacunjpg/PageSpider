package cn.web;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class ConnectRedis {
	
	public static void conn(){
		 Jedis jedis = new Jedis("192.168.20.47"); 
	     System.out.println("Connection to server sucessfully"); 
	     //check whether server is running or not 
	     System.out.println("Server is running: "+jedis.ping()); 
//	     jedis.set("z", "zhu");
//	     jedis.set("j", "jia");
//	     jedis.set("c", "cun");
//	     System.out.println("Stored string in redis:: "+ jedis.get("z"));
	     
//	     jedis.lpush("tutorial-list", "z");
//	     jedis.lpush("tutorial-list", "j");
//	     jedis.lpush("tutorial-list", "c");
//	     List<String> list = jedis.lrange("tutorial-list", 0 ,5); 
//
//	      for(int i = 0; i<list.size(); i++) { 
//	         System.out.println("Stored string in redis:: "+list.get(i)); 
//	      } 
	     
	     Set<String> list = jedis.keys("*"); 

	      for(int i = 0; i<list.size(); i++) { 
	         System.out.println("List of stored keys:: "+list); 
	      } 
	      
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		conn();
	}

}
