//package com.fengtest.feng;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.impl.KafkaClient;
//
//public class test {
//
//	/**
//	 * @Title: main
//	 * @Description: TODO
//	 * @param @param args    设定文件
//	 * @return void    返回类型
//	 * @throws
//	 */
//
//	public static void main(String[] args){
//		KafkaClient kc;
//		try {
////			kc = new KafkaClient("123", "8896ed6e5645492abb466e2e2c892823");
//			kc = new KafkaClient("123", "8896ed6e5645492abb466e2e2c892823");
////			File file = new File("E:\\asiainfo_doucument\\呼叫中心业务综合管理系统-简要操作手册V0.1 - 副本.doc");
////			InputStream in = new FileInputStream(file);
//			List list = new ArrayList();
//			for(int i=0;i<500;i++){
//				StringBuffer sb =  new StringBuffer();
//				for(int k = 0;k<1000;k++){
//
//					sb.append("message");
//				}
//				list.add(sb.toString());
//			}
//			kc.produce(list, "KFK_TEST_2010499");
////			System.out.println(i);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
