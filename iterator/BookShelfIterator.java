package collections;

/**
 * @author cry777
 * @program demo1
 * @description BookShelfIterator类是一个迭代器的实现，它持有一个将要遍历的集合BookShelf书架。
 * @create 2022-01-18
 */
public class BookShelfIterator implements Iterator {
    
    /**
     * 集合类
     */
    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext(){
        if(index < bookShelf.getLength()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next(){
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
