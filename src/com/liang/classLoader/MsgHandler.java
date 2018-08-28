package com.liang.classLoader;

/**
 * 后台启动一条线程不断刷新重新加载实现了热加载的类
 * Created by liang.caixing on 2018/8/24.
 */
public class MsgHandler implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (true){
            BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
            manager.logic();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
