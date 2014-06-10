GCTest
======

Android Garbage collector exercise. This Android code will generate a huge amount of Objects, and release them.
You should collect the generated logcat output, and process it using the logcat2memorygraph script.

Warning - the compiled Apk will crash a lot with an Out Of Memory exeption a lot.  

Be aware - The UI of this app is very simple, and will not show information while running to avoid influencing the GC test procedure.
Monitor the logcat output using adb or ddms.
