#!/bin/bash

# 进入当前脚本目录
SHELL_FOLDER=$(cd "$(dirname "$0")";pwd)
cd ${SHELL_FOLDER}

# 获取第一个命令作为动作
action=$1
[[ -z ${action} ]] && echo "you must choice one: [start/stop/restart]" && exit 1

if [[ ${action} != 'stop' ]]; then
    devToken=$2
    noteStoreUrl=$3
    dbPassword=$4

    [[ -z ${devToken} ]] && echo "devToken parameter not exist" && exit 1
    [[ -z ${noteStoreUrl} ]] && echo "noteStoreUrl parameter not exist" && exit 1
    [[ -z ${dbPassword} ]] && echo "dbPassword parameter not exist" && exit 1
fi

app_name='yinxiang'
jar_file='../yinxiang/build/libs/yinxiang-0.0.1-SNAPSHOT.jar'

[[ ! -e ${jar_file} ]] && echo "${jar_file} file not exist" && exit 1

pid=$(jps | grep ${app_name} | awk '{print $1}')

function start_service() {

    SIGNAL=${SIGNAL:-TERM}
    if [[ -z "$pid" ]]; then
        nohup ${jar_file} --yinxiang.dev-token=${devToken} --yinxiang.note-store-url=${noteStoreUrl} --spring.datasource.password=${dbPassword} >./log.txt 2>&1 &
        if [[ $? -eq 0 ]]; then
            echo "server start successful"
        else
            echo "server start unsuccessful"
        fi
    else
        echo "${app_name} server have been start"
        exit 1
    fi
}

function stop_service() {
    SIGNAL=${SIGNAL:-TERM}
    if [[ -z "$pid" ]]; then
        echo "No ${app_name} server need to stop"
        exit 1
    else
        kill -s ${SIGNAL} ${pid}
    fi
}

function restart_service() {
    stop_service
    start_service
}

case ${action} in
start)
    start_service
    ;;
stop)
    stop_service
    ;;
restart)
    restart_service
    ;;
*)
    echo default
    ;;
esac
