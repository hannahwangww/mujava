//
// Created by andilyliao on 17-7-26.
//
//函数返回值占位符

#include <iostream>
#include <vector>
#include <typeinfo>
using namespace std;

template<class T, class U>
auto add(T t, U u) -> decltype(t + u)
{
    return t + u;
}

int autotest2()
{
    cout<<add(101, 1.1)<<endl;
}
