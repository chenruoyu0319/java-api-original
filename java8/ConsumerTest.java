package java8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<Integer> consumer = x -> {
            int a = x + 6;
            System.out.println(a);
            System.out.println("cry" + a);
        };
        consumer.accept(660);


        Consumer<Integer> consumer1 = x -> System.out.println("first x : " + x);
        Consumer<Integer> consumer2 = x -> {
            System.out.println("second x : " + x);
            throw new NullPointerException("throw exception second");
        };
        Consumer<Integer> consumer3 = x -> System.out.println("third x : " + x);
        consumer1.andThen(consumer2).andThen(consumer3).accept(1);
    }


}
