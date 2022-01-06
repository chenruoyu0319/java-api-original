package st;

/**
 * @author cry777
 * @program Singleton
 * @description 懒汉式单例
 * @create 2022-01-06
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    /**
     * 1、适用于单线程环境（不推荐）
     */
    public static LazySingleton getInstanceA() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 2、适用于多线程环境，但效率不高（不推荐）
     */
    public static synchronized LazySingleton getInstanceB() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 3、双重检查加锁（推荐）
     */
    public static LazySingleton getInstanceC() {
        // 先判断实例是否存在，若不存在再对类对象进行加锁处理
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
