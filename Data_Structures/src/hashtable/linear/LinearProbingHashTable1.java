package hashtable.linear;

public class LinearProbingHashTable1 {

    /**
     * 버킷으로 할당할 수 있는 최대 크기
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 버킷의 엔트리가 75%를 넘으면 버킷의 크기를 늘린다.
     */
    private static final float ROAD_FACTOR_THRESHOLD = 0.75F;

    private Entry[] bucket;
    private int bucketSize;
    private final Entry dummyNull = new Entry(null, null, true);

    public LinearProbingHashTable1() {
        this(16);
    }

    public 

}
