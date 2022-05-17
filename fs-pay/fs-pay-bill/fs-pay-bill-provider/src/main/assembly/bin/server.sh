#!/bin/bash

root=$(
  cd $(dirname $(readlink $0 || echo $0))/..
  /bin/pwd
)

######################################################################################################
# Util fuctions
######################################################################################################
# set class path
function set_classpath()
{
    CLASSPATH=""
    jars=$(find ${LIB_DIR} -name *.jar)
	for i in ${jars}
	do
		tmpclasspath=${i}:${tmpclasspath}
	done
	CLASSPATH=${CLASSPATH}${tmpclasspath}
}

function create_dir()
{
    if [ ! -d "$1" ]
    then
        mkdir "$1"
    fi
}

function running()
{
  if [ -f "$1" ]
  then
    local PID=$(cat "$1" 2>/dev/null) || return 1
    kill -0 "$PID" 2>/dev/null
    return
  fi
  rm -f "$1"
  return 1
}


######################################################################################################
# Setup Var
######################################################################################################
CONF_DIR=${root}/conf
LIB_DIR=${root}/lib
PID_FILE=${root}/fs-pay-bill-provider.pid
LOG_DIR=${root}/logs
STDOUT_FILE=${LOG_DIR}/stdout.log
MAIN_CLASS=com.alibaba.dubbo.container.Main

create_dir "$LOG_DIR"

######################################################################################################
# Setup Dubbo Config
######################################################################################################
SERVER_NAME=`sed '/dubbo.application.name/!d;s/.*=//'  ${CONF_DIR}/dubbo.properties | tr -d '\r'`
SERVER_PROTOCOL=`sed '/dubbo.protocol.name/!d;s/.*=//' ${CONF_DIR}/dubbo.properties | tr -d '\r'`
SERVER_PORT=`sed '/dubbo.protocol.port/!d;s/.*=//'     ${CONF_DIR}/dubbo.properties | tr -d '\r'`



######################################################################################################
# Setup JAVA and JVM config
######################################################################################################
if [ -z "$JAVA" ]
then
  JAVA=$(which java)
fi

if [ -z "$JAVA" ]
then
  echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.8) in your PATH." >&2
  exit 1
fi

DATE=$(date +%Y%m%d%H%M%S)
HEAP="-Xms256M -Xmx256M"
HEAP="$HEAP -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$root/jvm-$DATE.hprof"

GC="-XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80"
GC="$GC -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark"

GC_LOG="-XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:$root/logs/gc.log"
GC_LOG="$GC_LOG -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

#MORNITOR="-Djava.rmi.server.hostname=localhost -Dcom.sun.management.jmxremote.port=5555"
#MORNITOR="$MORNITOR -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=29713,server=y,suspend=n"
OPT="-server"


function do_start()
{
    if running ${PID_FILE}
    then
        echo "Already Running $(cat ${PID_FILE})!"
        exit 1
    fi
    echo "Starting ${SERVER_NAME} ..."

    set_classpath
    echo "JAVA=$JAVA"
    echo "JVMOPT=$OPT"
    echo "JVMHEAP_OPT=$HEAP"
    echo "JVMGC_OPT=$GC"
    echo "JVMGC_LOG=$GC_LOG"
    echo "CLASSPATH=$CLASSPATH"

    ${JAVA} ${OPT} ${DEBUG} ${HEAP} ${GC} ${GC_LOG}  -cp ${CLASSPATH}:${CONF_DIR}  ${MAIN_CLASS} > ${STDOUT_FILE} 2>&1 &

    PID=$!
    echo ${PID} > ${PID_FILE}

    disown "$PID"
}

function do_stop()
{
    if [ ! -f "${PID_FILE}" ] ; then
        echo "ERROR: no pid found at ${PID_FILE}"
        exit 1
    fi

    local PID=$(cat "${PID_FILE}" 2>/dev/null)
    if [ -z "${PID}" ] ; then
        echo "ERROR: no pid id found in ${PID_FILE}"
        rm ${PID_FILE}
        exit 1
    fi
    kill "${PID}" 2>/dev/null
    TIMEOUT=30
    while running ${PID_FILE}; do
        if (( TIMEOUT-- == 0 )); then
          kill -KILL "$PID" 2>/dev/null
        fi
        sleep 1
    done

    if [ "$?"="0" ]
    then
        rm ${PID_FILE}
    fi
}

case "$1" in
    start)
        do_start
        echo "OK $(date)"
        ;;
    stop)
        do_stop
        echo "OK"
        ;;
    restart)
        ${0} stop
        ${0} start
        ;;
    *)
        echo "Usage: server.sh {start|stop|restart}" >&2
        exit 1
esac
