package java8;

import java.util.function.Predicate;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-07
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("dabin"); // true
    }
}
