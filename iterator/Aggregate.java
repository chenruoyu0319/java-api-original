package collections;

/**
 * @author cry777
 * @program demo1
 * @description Aggregate接口是索要遍历的集合的接口。实现了该接口的类将称为一个可以保持多个元素的集合。
 * @create 2022-01-18
 */
public interface Aggregate {

    Iterator iterator();
}