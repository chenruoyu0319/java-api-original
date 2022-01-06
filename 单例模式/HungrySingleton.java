package st;

/**
 * @author cry777
 * @program Singleton
 * @description 饿汉式单例
 * @create 2022-01-06
 */
public class HungrySingleton {

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){

    }

    public static HungrySingleton getHungrySingleton(){
        return hungrySingleton;
    }
}
