package hashtable;

import java.util.LinkedList;

public class ChanningHashTable {
    // 버킷으로 할당할 수 있는 최대 크기
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    // 버킷의 엔트리가 75%를 넘으면 버킷의 크기를 늘린다
    private static final float ROAD_FACTOR_THRESHOLD = 0.75F;

    private LinkedList<Entry>[] bucket;
    private int bucketSize;

    public ChanningHashTable() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public ChanningHashTable(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("정상적인 해시 테이블의 크기를 정해주세요.");
        }
        bucket = new LinkedList[tableSizeFor(capacity)];
    }

    private int tableSizeFor(int capacity) {
        // TODO : 2의 제곱수로 bucket 사이즈를 상향식으로 보정한다.(예: 100 -> 128)
        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 15;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private int hash(Object key) {
        /**
         * 해시 함수 : h'(k) -> {0, 1, 2, .. m - 1}
         */
        int h;
        h = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return h & (bucket.length - 1);
    }

    private int probing(Object key) {
        return hash(key);
    }

    public Object get(Object key) {
        int index = probing(key);
        if (null == bucket[index]) {
            return null;
        }
        // TODO : 엔트리 리스트에서 키를 찾는다
        LinkedList<Entry> list = bucket[index];
        for (Entry entry : list) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public void put(Object key, Object value) {
        putValue(key, value);
    }

    private void putValue(Object key, Object value) {
        /**
         * 조사된 버킷의 위치에 저장된 값이
         * null이 아니고 이미 동일한 키가 존재하면 값을 덮어쓴다.
         * null이면 해당 자리에 키와 값을 저장한다.
         */
        int index = probing(key);
        if (null == bucket[index]) {
            bucket[index] = new LinkedList<>();
            ++bucketSize;
        }
        boolean isExistKey = false;
        for (Entry entry : bucket[index]) {
            // TODO : 키가 이미 존재하면 값만 덮어쓴다
            if (entry.key.equals(key)) {
                isExistKey = true;
                entry.value = value;
                break;
            }
        }
        // TODO : 키가 존재하지 않으면 새롭게 추가한다
        if (!isExistKey) {
            bucket[index].add(new Entry(key, value));
        }
        resizeBucket();
    }

    public void remove(Object key) {
        int index = probing(key);
        if (null != bucket[index]) {
            LinkedList<Entry> list = bucket[index];
            for (Entry entry : list) {
                if (entry.key.equals(key)) {
                    list.remove(entry);
                    break;
                }
            }
            // TODO : 엔트리 리스트에 엔트리가 비어있으면 버킷을 비워준다
            if (list.isEmpty()) {
                bucket[index] = null;
                --bucketSize;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeBucket() {
        // TODO : 적재율이 임계치를 넘으면 버킷의 크기를 늘린다.
        int prevBucketSize = bucket.length;
        if (MAXIMUM_CAPACITY <= prevBucketSize) {
            return;
        }
        if (bucketSize * 1.0F / bucket.length > ROAD_FACTOR_THRESHOLD) {
            int newBucketSize = tableSizeFor(prevBucketSize << 1);

            LinkedList<Entry>[] tempList = bucket;
            bucket = new LinkedList[newBucketSize];
            bucketSize = 0;
            // TODO : 새로운 버킷이 할당되면 노드의 bucket 해시를 재계산하여 다시 할당한다.
            for (LinkedList<Entry> list : tempList) {
                if (null != list) {
                    // TODO : 리스트에 있는 엔트리를 꺼내서 새롭게 삽입한다.
                    while (!list.isEmpty()) {
                        Entry entry = list.removeFirst();
                        putValue(entry.key, entry.value);
                    }
                }
            }
        }
    }

    public void printHashTable() {
        System.out.println("-------------");
        for (int i = 0; i < bucket.length; i++) {
            if (null != bucket[i]) {
                System.out.printf("| %d | %s %s\n", i, "List", printNode(bucket[i]));
            } else {
                System.out.printf("| %d | %s\n", i, "null");
            }
        }
        System.out.println("-------------");
    }

    private String printNode(LinkedList<Entry> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("head -> ");
        for (Entry entry : list) {
            builder.append("(").append(entry.key).append(",").append(entry.value).append(") -> ");
        }
        builder.append("null");
        return builder.toString();
    }
}
