package hashtable.chainning;

public class ChanningMap {

    // 버킷으로 할당할 수 있는 최대크기
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    // 버킷의 엔트리가 75%를 넘으면 버킷의 크기를 늘린다.
    private static final float ROAD_FACTOR_THRESHOLD = 0.75F;

}
