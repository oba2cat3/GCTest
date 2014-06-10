package com.gc_test;


public class GCTestString extends GCTest{

	
	@Override
	public int getObjectSize() {
		return "abcdefg".length()*2;
	}

	@Override
	public Object getNewObject() {
		return new String("abcdefg");
	}

}