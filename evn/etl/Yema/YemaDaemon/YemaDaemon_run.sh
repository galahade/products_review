#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Xms256M -Xmx1024M -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/log4j-1.2.16.jar:$ROOT_PATH/../lib/activation.jar:$ROOT_PATH/../lib/jax-qname.jar:$ROOT_PATH/../lib/jaxp-api.jar:$ROOT_PATH/../lib/jaxp-ri.jar:$ROOT_PATH/../lib/saaj-api.jar:$ROOT_PATH/../lib/saaj-impl.jar:$ROOT_PATH/../lib/jaxen-1.1.1.jar:$ROOT_PATH/../lib/mail-1.4.jar:$ROOT_PATH/../lib/ini4j-0.5.1.jar:$ROOT_PATH/../lib/talendcsv.jar:$ROOT_PATH/../lib/talend_file_enhanced_20070724.jar:$ROOT_PATH/../lib/jaxen-1.1.1.jar:$ROOT_PATH/../lib/activation.jar:$ROOT_PATH/../lib/saxon9.jar:$ROOT_PATH/../lib/jdom-1.1.jar:$ROOT_PATH/../lib/jsch-0.1.51.jar:$ROOT_PATH/../lib/dom4j-1.6.1.jar:$ROOT_PATH/../lib/jakarta-oro-2.0.8.jar:$ROOT_PATH/../lib/talend-soap.jar:$ROOT_PATH/../lib/mysql-connector-java-5.1.30-bin.jar:$ROOT_PATH/../lib/filecopy.jar:$ROOT_PATH/../lib/xpathutil-1.0.0.jar:$ROOT_PATH/yemadaemon_0_3.jar:$ROOT_PATH/sendemail_0_1.jar:$ROOT_PATH/updateorderstatus_0_5.jar:$ROOT_PATH/getorders_0_5.jar: yema.yemadaemon_0_3.YemaDaemon --context=linux "$@" 