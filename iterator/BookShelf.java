package collections;

/**
 * @author cry777
 * @program demo1
 * @description BookShelf类是用来表示书架的类，将该类作为Book的集合进行处理，实现了Aggregate接口。
 * @create 2022-01-18
 */
public class BookShelf implements Aggregate {

    /**
     * 数组
     */
    private Book[] books;

    /**
     * 数组索引
     */
    private int last = 0;

    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }

    public Book getBookAt(int index) {
        return books[index];
    }

    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    public int getLength() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
