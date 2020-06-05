
	 package com.fengcase3.utils.feng;
	 /**
	  * ����instanceof
	  * @author fengrongtao
	  *
	  */	 
	 class TestInstanceof {
	  public static void main(String[] args){
	     A a=null;
	     B b=null;
	     boolean res; 
	     System.out.println("instanceoftest test case 1: ------------------");
	     res = a instanceof A; 
	     System.out.println("a instanceof A: " + res);
	     res = b instanceof B;
	     System.out.println("b instanceof B: " + res);
	       
	     System.out.println("/instanceoftest test case 2: ------------------");   
	     a=new B();
	     b=new B();
	     
	     res = a instanceof A; 
	     System.out.println("a instanceof A: " + res);
	     
	     res = a instanceof B;
	     System.out.println("a instanceof B: " + res);
	     res = b instanceof A;
	     System.out.println("b instanceof A: " + res);
	     
	     res = b instanceof B;
	     System.out.println("b instanceof B: " + res);
	    
	     System.out.println("/instanceoftest test case 3: ------------------");
	     B b2=(C)new C();
	     
	     res = b2 instanceof A;
	     System.out.println("b2 instanceof A: " + res);
	     
	     res = b2 instanceof B;
	     System.out.println("b2 instanceof B: " + res);
	     
	     res = b2 instanceof C;
	     System.out.println("b2 instanceof C: " + res);
	  }
	}

	/*
	result:

	instanceoftest test case 1: ------------------
	a instanceof A: false
	b instanceof B: false
	instanceoftest test case 2: ------------------
	a instanceof A: true
	a instanceof B: true
	b instanceof A: true
	b instanceof B: true
	instanceoftest test case 3: ------------------
	b2 instanceof A: true
	b2 instanceof B: true
	b2 instanceof C: true
	 
	*/
	 
	 interface A{}
	 class B implements A{	  
	 }
	 class C extends B {	  
	 }