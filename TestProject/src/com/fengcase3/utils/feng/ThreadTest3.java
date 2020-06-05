package com.fengcase3.utils.feng;
/**
 * <p>�̲߳���</p>
 * @author fengrongtao
 *
 */
public class ThreadTest3 {
	public static void main(String[] args) {
		PrintThread pt = null;
		for(int i = 0;i<2;i++){			
			 pt = new PrintThread("��ӡ�߳�"+(i+1));
			 pt.start();
		}		
	}	
}


class PrintThread extends Thread{
	private String threadName;//�߳�����		
	public PrintThread(String threadName) {
		this.threadName = threadName;
	}	
	//�߳���������
	public void run() {	
		while(true){
			
			System.out.println("��ǰ�����̣߳�"+threadName);	
			
			try {
				Thread.sleep(5000);//�߳�����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void execute(){
				
	}
}



