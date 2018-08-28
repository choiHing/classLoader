package com.liang.classLoader;

/**
 * BaseManager的子类，此类需要实现java类的热加载功能
 * Created by liang.caixing on 2018/8/15.
 */
public class MyManager implements BaseManager{
    @Override
    public void logic() {
        System.out.println("实现java类的热加载案例1");
        System.out.println("实现java类的热加载案例2");
        //System.out.println("----------------------");
    }
}
