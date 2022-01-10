package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author cry777
 * @program demo1
 * @description JDK动态代理原理
 * @create 2022-01-10
 */
public class JdkDynProxy {

    private static Method m;

    /**
     * JDK动态代理类会先在静态代码块中通过反射把所有方法都拿到并存在静态变量中
     */
    static {
        try {
            m = Class.forName("proxy.IAnimal")
                    .getMethod("shout",new Class[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private InvocationHandler getInvocationHandler(){
        return (InvocationHandler) new JdkDynProxy();
    }

    public final void shout() throws Throwable {
        InvocationHandler invocationHandler = getInvocationHandler();
        invocationHandler.invoke(this,m,new Object[]{});
    }
}
