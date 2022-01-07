package java8;

import java.util.Comparator;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-07
 */
public class ComparatorTest {
    public static void main(String[] args) {
        Comparator<Person> comparator = Comparator.comparing(p -> p.firstName);

        Person p1 = new Person("dabin", "wang");
        Person p2 = new Person("xiaobin", "wang");

        // 打印-20
        System.out.println(comparator.compare(p1, p2));
        // 打印20
        System.out.println(comparator.reversed().compare(p1, p2));
    }

}

class Person {
    public String firstName;
    public String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
