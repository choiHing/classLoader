package com.liang.classLoader;

/**
 * 封装加载类的信息
 * Created by liang.caixing on 2018/8/15.
 */
public class LoadInfo {
    //自定义类的加载器
    private MyClassLoader myClassLoader;
    //记录要加载的类的时间戳 -->加载的时间
    private Long loadTime;
    private BaseManager manager;

    public LoadInfo(MyClassLoader myClassLoader, Long loadTime) {
        super();
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }

    public MyClassLoader getMyClassLoader() {
        return myClassLoader;
    }

    public void setMyClassLoader(MyClassLoader myClassLoader) {
        this.myClassLoader = myClassLoader;
    }

    public Long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Long loadTime) {
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }
}
