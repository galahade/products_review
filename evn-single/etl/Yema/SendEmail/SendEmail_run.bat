%~d0
cd %~dp0
java -Xms256M -Xmx1024M -cp .;../lib/routines.jar;../lib/log4j-1.2.16.jar;../lib/mail-1.4.jar;../lib/dom4j-1.6.1.jar;../lib/jaxen-1.1.1.jar;../lib/activation.jar;../lib/mysql-connector-java-5.1.30-bin.jar;../lib/xpathutil-1.0.0.jar;sendemail_0_1.jar; local_project.sendemail_0_1.SendEmail --context=linux %* 