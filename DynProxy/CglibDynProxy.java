package proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author cry777
 * @program demo1
 * @description CGLIB动态代理
 * @create 2022-01-10
 */
public class CglibDynProxy implements Callback{

    /**
     * 要代理的目标对象
     */
    private Object target;

    /**
     * 代理逻辑：  通过字节码生成目标类的子类
     */
    public Object proxy(){
        // 1.创建代理增强对象
        Enhancer enhancer = new Enhancer();
        // 2.设置弗雷，也就是代理目标类，CGLIB是通过生成代理类子类的方式来实现动态代理的
        enhancer.setSuperclass(target.getClass());
        // 3.设置回调函数，这个this其实就是代理逻辑实现类，也就是切面，等价于JDK动态代理的InvocationHandler
        enhancer.setCallback(this);
        // 4.创建代理对象，也就是目标类的子类
        return enhancer.create();
    }
}
