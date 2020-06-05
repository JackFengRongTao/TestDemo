package com.fengcase3.utils.feng;
/*
 * �߳�ͬ��
 */
public class ThreadTest2 {
	
			public static void main(String[] args) {
			Bank bank = new Bank(10);
			Thread t1 = new Thread(bank);
			Thread t2 = new Thread(bank);
			Thread t3 = new Thread(bank);
		    t1.start();
		    t2.start();
		    t3.start();
		}	


}

/**
 * ʵ��runnable�ӿڵ��߳� 
 * ����1.ͬ�������  2.ͬ������
 * @author fengrongtao
 *
 */
class Bank implements Runnable {
	private  int count=0;
	
	public Bank(int count) {	
		this.count = count;
	}

	public void run() {	
		printout();
		
	}
	
	//2.ͬ������
	synchronized public void printout(){
		for(int i=0;i<5;i++){			
			count++;
			System.out.println("���������߳�"+Thread.currentThread().getName()+"		"+Thread.currentThread().getId()+"		"+Thread.currentThread().hashCode()+"		"+Thread.currentThread().getState()+"			"+count + "         "+i);							
		
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
		
		
	
}
