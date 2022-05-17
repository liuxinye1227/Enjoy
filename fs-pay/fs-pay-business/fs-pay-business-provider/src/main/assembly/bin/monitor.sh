#!/bin/bash

########################################
# corntab 配置
# */1 * * * *  export JAVA_HOME=/usr/local/java/jdk && export JRE_HOME=${JAVA_HOME}/jre        
#&& export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib && export PATH=${JAVA_HOME}/bin:$PATH
#&& sh /home/fsdevops/service/message-system/fs-open-msg-provider/bin/monitor.sh >> /home/fsdevops/service/message-system/monitor.log 2>&1
########################################

url=http://localhost:29251/ViewObjectRes//heartbeat%3Aname%3Dheartbeat
curr_path=$(cd `dirname $0`; pwd)
cd $curr_path

curl $url > result.log
result=''
result=`cat result.log | grep '<TD>2</TD>'`
if [[ "$result" = "" ]];then
    echo "get jmx result from $url , do not contain '<TD>2</TD>' , restart msg service"
    sh server.sh stop
	sh server.sh start 
else 
	echo "service is normal"
fi
