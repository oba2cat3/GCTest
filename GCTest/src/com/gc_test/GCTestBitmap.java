package com.gc_test;

import android.graphics.Bitmap;

public class GCTestBitmap extends GCTest{

	// 1955K / 10000
	
	@Override
	public int getObjectSize() {
		Bitmap bmp = Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888);
		int object_size = bmp.getRowBytes() * bmp.getHeight()*4 ;
		return object_size;
	}

	@Override
	public Object getNewObject() {
		return Bitmap.createBitmap(4, 4, Bitmap.Config.ARGB_8888);
	}


}