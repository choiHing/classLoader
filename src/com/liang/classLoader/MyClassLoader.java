package com.liang.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义java类加载器来实现java类的热加载
 * Created by liang.caixing on 2018/8/15.
 */
public class MyClassLoader extends ClassLoader{

    //要加载的java类的classpath路径
    private String classpath;
    public MyClassLoader(String classpath){
        super(ClassLoader.getSystemClassLoader());
        this.classpath = classpath;
    }

    /**
     * Finds the class with the specified <a href="#name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass <tt>loadClass</tt>} method after checking the
     * parent class loader for the requested class.  The default implementation
     * throws a <tt>ClassNotFoundException</tt>.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     * @since 1.2
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //return super.findClass(name);
        byte[] data = this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }

    /**
     * 加载class文件中的内容
     * @param name
     * @return
     */
    private byte[] loadClassData(String name){
        try {
            name = name.replace(".","//");
            FileInputStream is = new FileInputStream(new File(classpath+name+".class"));
            //字节数组输入流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = is.read()) !=-1){
                baos.write(b);
            }
            is.close();
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
