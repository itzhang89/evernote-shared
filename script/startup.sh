#!/bin/bash

#nohup java -Dyinxiang.dev-token=${devToken} -Dyinxiang.note-store-url=${noteStoreUrl} -Dspring.datasource.password=${dbPassword} -jar yinxiang/build/libs/yinxiang-0.0.1-SNAPSHOT.jar > ./log.txt 2>&1 &
nohup yinxiang/build/libs/yinxiang-0.0.1-SNAPSHOT.jar --yinxiang.dev-token=${devToken} --yinxiang.note-store-url=${noteStoreUrl} --spring.datasource.password=${dbPassword} > ./log.txt 2>&1 &
echo $! > ./pid.file