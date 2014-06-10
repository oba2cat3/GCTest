package com.gc_test;


public class GCTestInt   extends GCTest{

	
	@Override
	public int getObjectSize() {
		return 64;
	}

	@Override
	public Object getNewObject() {
		return new int[]{1,2,3,4,5,6,7,8,9,10} ;
	}

	
}