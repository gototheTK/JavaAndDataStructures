package hashtable;

public class Entry {
    Object key;
    Object value;
    // remove 메서드의 내용을 살펴본다.
    boolean isDeleted = false;

    Entry(Object key, Object value, boolean isDeleted) {
        this.key = key;
        this.value = value;
        this.isDeleted = isDeleted;
    }
}
