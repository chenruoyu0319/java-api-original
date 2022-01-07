package java8;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cry777
 * @program demo1
 * @description Java8 针对 map 操作增加了一些方法，非常方便
 * @create 2022-01-07
 */
public class MapTest {

    public static void main(String[] args) {
        mapTraverseTest();
    }

    /**
     * 如果指定的key存在则删除
     * @param
     * @return void
     */
    public static void removeIfTest(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "cry1");
        map.put(2, "cry2");

        //删除value没有含有1的键值对
        map.values().removeIf(value -> !value.contains("1"));

        System.out.println(map);
        /**
         * output
         * {1=dabin1}
         */
    }


    /**
     * 如果指定的 key 不存在，则 put 进去。
     * @param
     * @return void
     */
    public static void putIfAbsentTest(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "cry1");

        for (int i = 0; i < 3; i++) {
            map.putIfAbsent(i, "大彬" + i);
        }
        map.forEach((id, val) -> System.out.print(val + ", "));
        /**
         * output
         * cry0, cry1, cry2
         */
    }

    /**
     * map转换
     * @param
     * @return void
     */
    public static void mapConvertTest(){
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);

        Map<String, String> newMap = map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> "cry" + String.valueOf(e.getValue())));

        System.out.println(newMap);
        newMap.forEach((key, val) -> System.out.print(val + ", "));
    }
    /**
     * output
     * cry1, cry2,
     */

    /**
     * map转换
     * @param
     * @return void
     */
    public static void mapTraverseTest(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "cry1");
        map.put(2, "cry2");

        //方式1
        map.keySet().forEach(k -> {
            System.out.print(map.get(k) + ", ");
        });

        //方式2
        map.entrySet().iterator().forEachRemaining(e -> System.out.print(e.getValue() + ", "));

        //方式3
        map.entrySet().forEach(entry -> {
            System.out.print(entry.getValue() + ", ");
        });

        //方式4
        map.values().forEach(v -> {
            System.out.print(v + ", ");
        });
    }
}
