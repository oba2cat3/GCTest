package com.gc_test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import android.os.Environment;
import android.util.Log;


public abstract class GCTest {

	Random myrand = null;
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd_M_yyyy");
	
	public final static long small_heap = 65*1024*1024L;

	public static boolean forceGc = true;

	public static boolean randomRemoval = true;

	public final static int forceGcIntercal = 10000;

	public static String descOrasc = "asc" ; //"asc"

	public static ArrayList<Object> data = new ArrayList<Object>();

	public abstract int getObjectSize();

	public abstract Object getNewObject();

	public String getObjectType() {
		return this.getClass().getName();
	}

	public void doWork(final HashMap<String , Object> params) throws Exception {
		if (params!=null){
			if (params.containsKey("randomRemoval")) {
				randomRemoval = (Boolean)params.get("randomRemoval");
			}
			if (params.containsKey("forceGc")) {
				forceGc = (Boolean)params.get("forceGc");
			}
			if (params.containsKey("descOrasc")) {
				descOrasc = (String)params.get("descOrasc");
			}
		}

		Thread th = new Thread (new Runnable() {

			@Override
			public void run() {

				long startObjectcount = 10 ;
				int object_size = getObjectSize();
				long max_multiplier = (small_heap/(object_size))-1 ;
				boolean direction=false;
				if (descOrasc.equals("asc")) {
					startObjectcount = 1000 ;
					direction=false;
				} else if (descOrasc.equals("desc")) {
					startObjectcount=max_multiplier;
					direction=true;
				}

				for (long alc=startObjectcount ; (direction&& alc>10)||(!direction&& alc<max_multiplier) ; alc=(direction ? alc/2 : alc*2)) {
					Log.i("dalvikvm" , String.format("test with type %s, %s objects, estimated obj size %s"  , getObjectType(), alc , object_size));
					for (int i=0;i<alc;i++) {
						data.add(getNewObject());
						if (i%100000 == 0) { 
							Log.i("gctest" , "Allocation # "+i);
						}
					}
					for (int i=0;i<alc;i++) {
						if (!randomRemoval) {
							data.remove(data.size()-1);
						} else {

							if (myrand==null) {
								myrand = new Random(System.currentTimeMillis()/2 + 2000) ;
							} else {
								data.remove(myrand.nextInt(data.size()));
							}
						}
						if (forceGc) {
							if (i%forceGcIntercal==0) {
								Log.i("gctest" ,"(Trying to) run garbage collection");
								Log.i("gctest" ,"Deallocation # "+i);
								System.gc();
							}
						}
					}
				}
				Log.i("dalvikvm" ,"Done");
			
				LogcatDump.DumpLogToFile(new File(Environment.getExternalStorageDirectory() , String.format("%s_%s" , this.getClass().getName() ,sdf.format(new Date()))));
			
			}
		}) ;

		th.start();
	}




}	
