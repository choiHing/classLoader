package com.liang.classLoader;

/**
 * 测试java类的热加载
 * Created by liang.caixing on 2018/8/24.
 */
public class ClassLoadTest {
    public static void main(String[] args){
        new Thread(new MsgHandler()).start();
    }
}
