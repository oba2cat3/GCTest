package com.gc_test;


public class GCTestByte   extends GCTest{

	@Override
	public int getObjectSize() {
		return 24;
	}

	@Override
	public Object getNewObject() {
		return new byte[]{1};
	}
	

}