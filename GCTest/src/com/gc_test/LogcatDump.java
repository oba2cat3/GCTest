package com.gc_test;

import java.io.File;

public class LogcatDump {


	public static void DumpLogToFile(File fl){
		
		try {
			Process p = Runtime.getRuntime().exec(new String []{"logcat" , "-d" , "-s" , "\"dalvikvm\"" ,"," ,"\"dalvikvm-heap\""  , ">" , fl.getAbsolutePath()});
			p.waitFor();
		} catch (Exception e) {
			
		}
		
		
		
	}
	
	
	
}
