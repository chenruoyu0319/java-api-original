package st;

/**
 * @author cry777
 * @program Singleton
 * @description 枚举类实现单例
 * @create 2022-01-06
 */
public class EnumSingleton {
    public static void main(String[] args) {
        Single single = Single.SINGLE;
        single.print();
    }

    enum Single {
        SINGLE;

        private Single() {
        }

        public void print() {
            System.out.println("hello world");
        }
    }
}
