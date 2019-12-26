package com.web;

public class DevelopTestApplication {

	public static void main(String[] args) {
		MyThread thread = new MyThread();


		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				int i = 1/ 0;
			}
		};

		try{
			runnable.run();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("catchDDDDDD");
		}
	}

	static class  MyThread implements Runnable{

		@Override
		public void run(){
			int i = 1/ 0;
		}
	}
}

