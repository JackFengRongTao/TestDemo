package com.fengcase3.utils.feng.dataStructure;

import java.util.Scanner;

/**
 * ���Ա�֮˳���ṹ
 * @author fengrongtao
 */
public class SequenceListTest {
	public static void main(String[] args) {
		SLType sl = new SLType();
		DATA data;
		System.out.println("---------------˳�����ʾ---------");
		System.out.println("-------��װ����--------");		
		Scanner input = new Scanner(System.in);
		sl.SLInit(sl);
		do{
			DATA dt = new  DATA();
			System.out.println("-------���������ݣ�ѧ�š����֡�����(���������ֵ)--------");	
			String key = input.next();
			String name = input.next();
			int age = input.nextInt();
			dt.key = key;
			dt.name = name;
			dt.age = age;
			if(age!=0){//���ж�				
				if(sl.SLAdd(sl, dt)==0){
					break;			
				}				
			}else{
				break;
			}			
		}while(true);		
		System.out.println("----------˳�������ʾ��������---------");
		sl.SLAll(sl);
/*		//do{
			System.out.println("-------����Ų��ҽڵ�--------");	
			System.out.println("-------������Ҫ���ҵĽڵ�--------");	
			int i = input.nextInt();
			DATA dt = sl.SLFindByNum(sl, i);
			if(dt!=null){
				System.out.println("���ҵ���"+i+"���ڵ���:"+dt.key+"		"+dt.name+"		"+dt.age);
			}			
		//}while(true);
			System.out.println("-------������Ҫ���ҵĽڵ�Ĺؼ���--------");	
			String key = input.next();
			if(sl.SLFindByKey(sl, key)!=0){
				int num = sl.SLFindByKey(sl, key);
				System.out.println("���ҵ��Ľڵ���:"+num);
			}*/				
	/*	System.out.println("--------����ڵ�-------");	
		System.out.println("--------������Ҫ����Ľڵ�-------");	
		int num2 = input.nextInt();
		DATA data2 = new DATA();	
		data2.key = "x";
		data2.name = "xx";
		data2.age = 256;
		sl.SLInsert(sl, num2, data2);	
		System.out.println("----------˳�������ʾ��������---------");
		sl.SLAll(sl);*/
		
		System.out.println("--------ɾ���ڵ�-------");	
		System.out.println("--------������Ҫɾ���Ľڵ�-------");	
		int num2 = input.nextInt();
		sl.SLDel(sl, num2);
		System.out.println("----------˳�������ʾ��������---------");
		sl.SLAll(sl);
		
	}
	
	
}

/*
 *����
 */
class DATA{
	String key;
	String name;
	int age;	
}

/*
 *˳���
 */
class SLType{
	static final int MAXLEN = 100;	
	DATA[] ListData = new DATA[MAXLEN+1];
	int ListLen;
	
	/*
	 *��ʼ��˳���
	 */
	public void SLInit(SLType sl){
		sl.ListLen = 0;		
	}
	
	/*
	 *����˳���ĳ���
	 */
	public int SLLength(SLType sl){
		return sl.ListLen;	
	}
	
	
	/*
	 *����ڵ�
	 */
	public int SLInsert(SLType sl,int n,DATA data){
		if(sl.ListLen>=MAXLEN){
			System.out.println("˳��������������ٲ��룡");
			return 0;
		}		
		if(n<1||n>sl.ListLen){
			System.out.println("����Ԫ����Ŵ��󣬲��ܲ���Ԫ�أ�");
			return 0;
		}
		for(int i = sl.ListLen;i >= n;i--){
			sl.ListData[i+1] = sl.ListData[i];
		}
		sl.ListData[n] = data;
		sl.ListLen++;		
		return 1;
	}
	
	/*
	 *׷�ӽڵ�
	 */
	public int SLAdd(SLType sl,DATA data){
		if(sl.ListLen>=MAXLEN){
			System.out.println("˳��������������ٲ��룡");
			return 0;
		}	
		sl.ListData[++sl.ListLen] = data;
		return 1;	
	}
	
	/*
	 *ɾ���ڵ�
	 */
	public int SLDel(SLType sl,int n){
			
		if(n<1||n>sl.ListLen){
			System.out.println("ɾ��Ԫ����Ŵ��󣬲���ɾ��Ԫ�أ�");
			return 0;
		}
		for(int i = n;i < sl.ListLen;i++){
			sl.ListData[i] = sl.ListData[i+1];
		}
		sl.ListLen--;	
		return 1;
	}
	
	/*
	 *������Ų��ҽڵ�
	 */
	public DATA SLFindByNum(SLType sl,int n){			
		if(n<1||n>sl.ListLen){
			System.out.println("������Ŵ��󣬲��Ҳ���Ԫ�أ�");
			return null;
		}	
		return sl.ListData[n];	
	}
	
	/*
	 *���չؼ��ֲ��ҽڵ�
	 */
	public int SLFindByKey(SLType sl,String key){		
		for(int i = 1;i < sl.ListLen;i++){			
			if(sl.ListData[i].key.compareTo(key)==0){
					return i;			
			}
		}		
		return 0;
	}
	
	/*
	 * ��ʾ���нڵ�
	 */
	public int SLAll(SLType sl){		
		for(int i = 1;i <= sl.ListLen;i++){			
			System.out.println(sl.ListData[i].key+","+sl.ListData[i].name+","+sl.ListData[i].age);
		}		
		return 0;
	}
	
}
