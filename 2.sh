#!/bin/bash
#变量和引用--环境变量
export DOMAIN="chinaitlab.com"
echo $DOMAIN
#显示环境变量
#env

#清除环境变量

unset DOMAIN
echo $DOMAIN
