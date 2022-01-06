package jb;

import java.lang.reflect.Method;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class ReflectTest {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("jb.ReflectTest");
        // 调用Person类中的run方法
        Method method = clazz.getMethod("run");
        method.invoke(clazz.newInstance());
        // Java 反射机制 - 调用某个类的方法1.
        // 调用Person的Speak方法
        method = clazz.getMethod("Speak", int.class, String.class);
        method.invoke(clazz.newInstance(), 22, "小明");
        // Java 反射机制 - 调用某个类的方法2.
        // age -> 22. name -> 小明
    }

    public void run(){
        System.out.println("调用ReflectTest类的run方法");
    }

    public void Speak(int age, String name){
        System.out.println("调用ReflectTest类的Speak方法");
        System.out.println("age -> " + age + ". name -> " + name);
    }
}
