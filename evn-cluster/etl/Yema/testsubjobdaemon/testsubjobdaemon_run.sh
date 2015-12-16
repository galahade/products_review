#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Xms256M -Xmx1024M -cp $ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/dom4j-1.6.1.jar:$ROOT_PATH/../lib/log4j-1.2.16.jar:$ROOT_PATH/testsubjobdaemon_0_1.jar:$ROOT_PATH/test2subjob_0_1.jar:$ROOT_PATH/test1subjob_0_1.jar yema.testsubjobdaemon_0_1.testsubjobdaemon --context=Default "$@" 