#!/bin/bash

unameOut="$(uname -s)"
case "${unameOut}" in
    Linux*)     machine=Linux
        cmd="./bin/centos-sql-migrate $1 -config=./dbconfig/game_dev.conf $2"
        echo ${cmd}
        eval ${cmd}
    ;;
    Darwin*)    machine=Mac
        cmd="./bin/mac-sql-migrate $1 -config=./dbconfig/game_dev.conf $2"
        echo ${cmd}
        eval ${cmd}
    ;;
    CYGWIN*)    machine=Cygwin;;
    MINGW*)     machine=MinGw;;
    *)          machine="UNKNOWN:${unameOut}"
esac
