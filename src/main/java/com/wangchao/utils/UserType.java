package com.wangchao.utils;

/**
 * @author wangchao
 * @date 2019/2/21
 */
public enum  UserType {
    BUYER(0), SELLER(1);

    private int value;
    private UserType(int val){
        this.value = val;
    }

    public int getValue() {
        return value;
    }
}
