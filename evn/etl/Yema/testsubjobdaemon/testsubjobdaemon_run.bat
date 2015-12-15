%~d0
cd %~dp0
java -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/dom4j-1.6.1.jar;../lib/log4j-1.2.16.jar;testsubjobdaemon_0_1.jar;test2subjob_0_1.jar;test1subjob_0_1.jar yema.testsubjobdaemon_0_1.testsubjobdaemon --context=Default %* 