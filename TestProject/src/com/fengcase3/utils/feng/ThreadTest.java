package com.fengcase3.utils.feng;
/**
 * �����߳���д
 * @author fengrongtao
 *
 */
public class ThreadTest {
	public static void main(String args[]) {
		TestThread t =new  TestThread();
		t.start();	
		//mian����Ҳ��һ���߳�
		//�߳�֮������cpu��Դ��Դ
		int j=10000;
		while(j<11000){
			System.out.println("main�����߳�"+j);
			j++;			
		}
	}
}

/**
 * �̳��߳�����߳�
 * ������дalt+shift+s
 * @author fengrongtao
 *
 */
 class TestThread extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<1000;i++){
			System.out.println("�߳�����"+i);
		}
		
	}
	
}
