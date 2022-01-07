package java8;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-07
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

public class FunctionalInterfaceTest {
    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("666");
        System.out.println(converted);
    }
    /**
     * output
     * 666
     */
}
