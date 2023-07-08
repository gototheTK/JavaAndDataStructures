package hashtable;

import java.util.LinkedList;

class Entry {
    Object key;
    Object value;

    Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}

public class HashTable {

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final float ROAD_FACTOR_THRESHOLD = 0.75F;

    private LinkedList<Entry>[] bucket;
    private int bucketSize;

    public HashTable() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("올바른 해시테이블의 크기를 입력하여주세요.");
        }
        this.bucket = new LinkedList[tableSizeFor(capacity)];
    }

    private int tableSizeFor(int capacity) {

        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 15;

        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private int hash(Object key) {
        int h;
        h = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return h & (bucket.length);

    }

    private int probing(Object key) {
        return hash(key);
    }

    public void put(Object key, Object value) {
        putValue(key, value);
    }

    private void putValue(Object key, Object value) {

        int i = probing(key);

        LinkedList<Entry> list = bucket[i];

        if (null == list) {
            list = new LinkedList<>();
            ++bucketSize;
        }

        boolean isExistKey = false;

        for (Entry entry : list) {

            if (entry.key.equals(key)) {
                isExistKey = true;
                entry.value = value;
                break;
            }

        }

        if (!isExistKey) {
            list.add(new Entry(key, value));
        }
        resizeBucket();
    }

    public Object get(Object key) {

        int index = probing(key);

        if (null == bucket[index]) {
            return null;
        }

        LinkedList<Entry> list = bucket[index];

        for (Entry entry : list) {

            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }

    public void remove(int key) {

        int index = probing(key);
        if (null != bucket[index]) {

            LinkedList<Entry> list = bucket[index];

            for (Entry entry : list) {

                if (entry.key.equals(key)) {
                    list.remove(entry);
                    break;
                }

            }

            if (list.isEmpty()) {
                bucket[index] = null;
                --bucketSize;
            }

        }

    }

    @SuppressWarnings("unchecked")
    private void resizeBucket() {

        int prevBucketSize = bucket.length;

        if (ROAD_FACTOR_THRESHOLD <= prevBucketSize) {
            return;
        }

        if ((prevBucketSize * 0.1F) / MAXIMUM_CAPACITY > ROAD_FACTOR_THRESHOLD) {

            int newBucketSize = tableSizeFor(prevBucketSize << 1);

            LinkedList<Entry>[] tempList = bucket;
            bucket = new LinkedList[newBucketSize];
            bucketSize = 0;

            for (LinkedList<Entry> list : tempList) {

                if (null != list) {

                    while (!list.isEmpty()) {

                        Entry entry = list.removeFirst();
                        putValue(entry.key, entry.value);

                    }

                }

            }

        }

    }

}