#!/bin/bash
#变量和引用--位置变量&特殊变量

#位置变量：$0,$1,$2,$3……$9
echo "Number of parameters is" $#
echo "Program 0 name is" $0
echo "Program 1 name is" $1
echo "Parameters as a single string is" $*

#===========================================
echo $@  #所有脚本的命令行参数，它们分别被双引号引住，如"$1"  "$2"  “$3” ...例如可以用在for循环中的in后面。
echo $$  #脚本运行的当前进程ID号
echo $!  #后台运行的最后一个进程的ID号
echo $?  #显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。




