package java8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-07
 */
public class StreamTest {

    public static void main(String[] args) {
        filterTest();
        sortedTest();
    }

    /**
     * Filter的入参是一个Predicate，用于筛选出我们需要的集合元素。原集合不变。filter 会过滤掉不符合特定条件的，下面的代码会过滤掉nameList中不以大彬开头的字符串。
     *
     * @param
     * @return void
     */
    public static void filterTest() {
        List<String> nameList = new ArrayList<>();
        nameList.add("cry1");
        nameList.add("cry2");
        nameList.add("aaa");
        nameList.add("bbb");

        nameList
                .stream()
                .filter((s) -> s.startsWith("cry"))
                .forEach(System.out::println);
        /**
         * output
         * cry1
         * cry2
         */
    }

    /**
     * 自然排序，不改变原集合，返回排序后的集合。
     *
     * @param
     * @return void
     */
    public static void sortedTest() {
        List<String> nameList = new ArrayList<>();
        nameList.add("cry3");
        nameList.add("cry2");
        nameList.add("cry1");
        nameList.add("aaa");
        nameList.add("bbb");

        nameList
                .stream()
                .filter((s) -> s.startsWith("cry"))
                .sorted()
                .forEach(System.out::println);
        //逆序排序：nameList.stream().sorted(Comparator.reverseOrder());
        //对元素某个字段排序：list.stream().sorted(Comparator.comparing(Student::getAge).reversed());
        //list.stream().sorted(Comparator.comparing(Student::getAge));
        /**
         * output
         * cry1
         * cry2
         * cry3
         */
    }

    /**
     * Map 转换: 将每个字符串转为大写。
     *
     * @param
     * @return void
     */
    public static void MapTest() {
        List<String> nameList = new ArrayList<>();
        nameList.add("aaa");
        nameList.add("bbb");

        nameList
                .stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
        /**
         * output
         * AAA
         * BBB
         */

    }

    /**
     * Match 匹配: 验证 nameList 中的字符串是否有以"cry"开头的。
     *
     * @param
     * @return void
     */
    public static void MatchTest() {
        List<String> nameList = new ArrayList<>();
        nameList.add("cry1");
        nameList.add("cry2");

        boolean startWithDabin =
                nameList
                        .stream()
                        .map(String::toUpperCase)
                        .anyMatch((s) -> s.startsWith("cry"));

        System.out.println(startWithDabin);
        /**
         * output
         * true
         */
    }

    /**
     * Count 计数: 统计 stream 流中的元素总数，返回值是 long 类型。
     *
     * @param
     * @return void
     */
    public static void countTest() {
        List<String> nameList = new ArrayList<>();
        nameList.add("cry1");
        nameList.add("cry2");
        nameList.add("aaa");

        long count =
                nameList
                        .stream()
                        .map(String::toUpperCase)
                        .filter((s) -> s.startsWith("大彬"))
                        .count();

        System.out.println(count);
        /**
         * output
         * 2
         */
    }

    /**
     * Reduce 类似拼接。可以实现将 list 归约成一个值。它的返回类型是 Optional 类型。
     *
     * @param
     * @return void
     */
    public static void reduceTest() {
        List<String> nameList = new ArrayList<>();
        nameList.add("大彬1");
        nameList.add("大彬2");

        Optional<String> reduced =
                nameList
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        /**
         * output
         * 大彬1#大彬2
         */
    }

    /**
     * flatMap: flatMap 用于将多个Stream连接成一个Stream。下面的例子，把几个小的list转换到一个大的list。
     *
     * @param
     * @return void
     */
    public static void flatMapTest() {
        List<String> team1 = Arrays.asList("cry1", "cry2", "cry3");
        List<String> team2 = Arrays.asList("cry4", "cry5");

        List<List<String>> players = new ArrayList<>();
        players.add(team1);
        players.add(team2);

        List<String> flatMapList = players.stream()
                .flatMap(pList -> pList.stream())
                .collect(Collectors.toList());

        System.out.println(flatMapList);
        /**
         * output
         * [cry1, cry2, cry3, cry4, cry5]
         */
    }

    /**
     * 下面的例子中，将words数组中的元素按照字符拆分，然后对字符去重。
     *
     * @param
     * @return void
     */
    public static void flatMapTest2() {
        List<String> words = new ArrayList<String>();
        words.add("cry最强");
        words.add("cry666");

        //将words数组中的元素按照字符拆分，然后对字符去重
        List<String> stringList = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());
        stringList.forEach(e -> System.out.print(e + ", "));
        /**
         * output
         * c, r, y,最, 强, 6,
         */
    }

    /**
     * 下面的例子中，将words数组中的元素按照字符拆分，然后对字符去重。
     *
     * @param
     * @return void
     */
    public static void parallelStreamsTest2() {
        int max = 100;
        List<String> strs = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            strs.add(uuid.toString());
        }

        List<String> sortedStrs = strs.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedStrs);
        /**
         * output
         * [029be6d0-e77e-4188-b511-f1571cdbf299, 02d97425-b696-483a-80c6-e2ef51c05d83, 0632f1e9-e749-4bce-8bac-1cf6c9e93afa, ...]
         */
    }


}
