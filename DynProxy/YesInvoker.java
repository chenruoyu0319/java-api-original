package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author cry777
 * @program demo1
 * @description jdk动态代理: 首先通过实现InvocationHandler接口得到一个切面类
 * @create 2022-01-10
 */
public class YesInvoker implements InvocationHandler {

    /**
     * 要代理的目标对象
     */
    private Object target;
    
    /**
     * 利用Proxy根据目标类的类加载器、接口和切面类得到一个代理类
     */
    public Object newProxyInstance(Object target){
        // 传入目标对象
        this.target = target;
        // 返回代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    /**
     * 代理类的逻辑就是把所有接口方法的调用转发到切面类的invoke()方法上，然后根据反射调用目标类的方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用了代理类的逻辑");
        return method.invoke(this.target,args);
    }

    public static void main(String[] args) {
        // 获得代理类
        IAnimal animal = (IAnimal) new YesInvoker().newProxyInstance(new Animal());
        // 这个方法实际上被转发到了YesInvoker#invoke上，最后通过反射调用目标对象方法
        animal.shout();
    }

}


