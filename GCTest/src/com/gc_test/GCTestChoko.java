package com.gc_test;

import java.util.Random;


public class GCTestChoko extends GCTest{

	
	Random myRnd = new Random(System.currentTimeMillis()-1000);
	
	private class Innerchoko{

		int bla = 3000;
		long blo = 15000L ;

		public Innerchoko (int bla,long blo) {
			this.bla = bla;
			this.blo = blo;
		}


	}

	private class Choko{

		private Innerchoko inC = null;
		private byte[] mybytes = new byte[10] ; 
		public Choko(int bla, long blo, byte[] my) {
			mybytes = my;
			inC = new Innerchoko(bla,blo);
		}


	}


	@Override
	public int getObjectSize() {
		
		return 100; //???
	}


	@Override
	public Object getNewObject() {
		return new Choko(myRnd.nextInt(1000),myRnd.nextInt(10000)*1000, new byte[]{0,1,1,2,2,2,2,3,3,3});
	}

	


}