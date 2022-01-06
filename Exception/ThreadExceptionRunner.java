package exception;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class ThreadExceptionRunner implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException("error !!!!");
    }
}

class ThreadExceptionDemo {
    public static void main(String[] args) {

        try {
            Thread thread = new Thread(new ThreadExceptionRunner());
            thread.start();
        } catch (Exception e) {
            System.out.println("========");
            e.printStackTrace();
        } finally {
        }
        System.out.println(123);
    }

}