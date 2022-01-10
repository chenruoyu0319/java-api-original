package immutableClass;

/**
 * @author cry777
 * @program demo1
 * @description 不可变类
 * @create 2022-01-10
 */
public final class ImmutableString {

    private final char value[];

    public ImmutableString() {
        value = new char[0];
    }

    public ImmutableString(int capacity) {
        value = new char[capacity];
    }

    public ImmutableString(char buf[]) {
        value = buf;
    }

    public ImmutableString replace(char oldChar, char newChar) {
        // ...
        if (oldChar == newChar) {
            return this;
        }
        int len = value.length;
        int i = -1;
        char[] val = value;
        while (++i < len) {
            if (val[i] == oldChar) {
                break;
            }
        }
        char buf[] = new char[len];
        if (i < len) {
            for (int j = 0; j < i; j++) {
                buf[j] = val[j];
            }
            while (i < len) {
                char c = val[i];
                buf[i] = (c == oldChar) ? newChar : c;
                i++;
            }

        }
        return new ImmutableString(buf);
    }

}
