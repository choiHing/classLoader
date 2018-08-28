package com.liang.classLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载manager的工厂
 * Created by liang.caixing on 2018/8/15.
 */
public class ManagerFactory {
    //记录热加载类的加载信息
    private static final Map<String,LoadInfo> loadTimeMap = new HashMap<String,LoadInfo>();
    //要加载的类的classpath
    private static final String CLASS_PATH = "D:/ideaworkPlaces/classLoader/out/production/classLoader";
    //实现热加载的类的全名称（包名+类名）
    public static final String MY_MANAGER = "com.liang.classLoader.MyManager";

    public static BaseManager getManager(String className){
        File loadFile = new File(CLASS_PATH+MY_MANAGER.replaceAll("\\.","/")+".class");
        Long lastModifed = loadFile.lastModified();

        //loadTimeMap不包含className为key的LoafInfo信息。证明这个类没有被加载，那么需要加载
        if(loadTimeMap.get(className) == null){
            load(className,lastModifed);
        }else if(loadTimeMap.get(className).getLoadTime() != lastModifed){
            //加载类的时间戳变化了,我们同样要重新加载这个类别到jvm
            load(className,lastModifed);
        }
        return loadTimeMap.get(className).getManager();
    }

    private static void load(String className,Long lastModified){
        MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH);

        Class<?> loadClass = null;
        try {
            loadClass = myClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        BaseManager manager = newInstance(loadClass);
        LoadInfo loadInfo = new LoadInfo(myClassLoader,lastModified);
        loadInfo.setManager(manager);

        loadTimeMap.put(className,loadInfo);
    }

    //以反射的方式创建BaseManager子类对象
    private static BaseManager newInstance(Class<?> loadClass){
        try {
            return (BaseManager)loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }




}
