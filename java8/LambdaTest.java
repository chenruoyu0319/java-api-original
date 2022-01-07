package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-07
 */
public class LambdaTest {

    static List<String> names = Arrays.asList("dabin", "tyson", "sophia");

    public static void main(String[] args) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        List<String> names = Arrays.asList("dabin", "tyson", "sophia");

        // 简化写法一
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        // 简化写法二, 省略入参类型, Java编译器能够根据类型推断机制判断出参数类型
        names.sort((a, b) -> b.compareTo(a));
    }

}
