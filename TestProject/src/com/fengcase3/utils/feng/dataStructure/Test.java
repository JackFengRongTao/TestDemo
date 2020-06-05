package com.fengcase3.utils.feng.dataStructure;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import com.impl.kafkaClient;

public class Test {
	public static void main(String[] args) {
		try {
			final kafkaClient kafkaClient = new kafkaClient("1", "caca8b173578470494d209af7b7cbe0c");
			
			///kafkaClient.produce("this is a test!", "KFK_TEST_1");
			//kafkaClient.consume("KFK_TEST_1", "1");
			//kafkaClient.updateConfigurationTiming(30000);
			new Thread(
					){
				@Override
				public void run(){
					try{
						Thread.sleep(15000);
						kafkaClient.close("1KFK_TEST_3_51");
						System.out.println("关闭消费者====================================================================");
						
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
			List<String> list = new ArrayList<String>();
			for(int i = 0;i < 2000 ; i ++){
				list.add("this is test "+i);
				
				
			}
			kafkaClient.produceList(list, "KFK_TEST_3_5");
			KafkaStream<byte[], byte[]> stream = null;
			ConsumerIterator<byte[],byte[]> it = null;
		     try {
		    	 stream = kafkaClient.consume("KFK_TEST_3_5", "1");
				 it = stream.iterator();    
				 System.out.println("*********Results********");    
				 while(it.hasNext()){
				        	String msg = new String(it.next().message())+"\r\n"; 
				        	System.out.println(msg);
				         try {    
				        	System.out.println("sleep 10");
				            Thread.sleep(10);    
				        } catch (Exception e) {
				        	e.printStackTrace();
				        	
				        }    
				 }
			} catch (Exception e) {
				it = null;
	        	stream = null;
			}				 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
