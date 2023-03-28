
import java.util.Arrays;

public class MinHeap {

    // 속성 배열, 사이즈;
    int[] queue;
    int size = 0;

    public MinHeap() {
        this.queue = new int[4];
    }

    // 1. 추가
    public void add(int key) {

        // 1.배열이 꽉차 있는지 확인한다. 곽차있으면 배열의 길이를 늘린다.
        if (queue.length <= size) {
            queue = Arrays.copyOf(queue, size + 4);
        }

        // 2.값을 맨 마지막 배열에 추가한다.
        queue[size++] = key;

        // 3.부모노드와 우선순위를 비교하고 올린다.
        UpHeap(size - 1);

    }

    // 2. 삭제

    public int remove() {

        // 1. 배열이 비어있는지 확인한다.
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap에 노드가 존재하지 않습니다.");
        }

        // 2. 루트노드의 값을 추출하여 따로 복사해둡니다.
        int rootKey = queue[0];

        // 3. 루트노드에 최말단 리프 노드를 배치합니다. 그리고 말단 리프노드를 삭제하고 전체 사이즈 값을 줄입니다.
        swap(0, size - 1);
        queue[size - 1] = 0;
        --size;

        // 4. 루트부터 시작하여 자식노드와 비교 및 배치를 반복하여 우선순위 정렬을 완료합니다.
        downHeap(0);

        // 5. 배치가 완료됬다면 루트노드의 값을 반환한다.
        return rootKey;
    }

    private void downHeap(int index) {

        // 1.왼쪽 자식 노드가 존재하는지 확인합니다. 완전 이진 트리이기때문에 왼쪽 자식노드가 없다면 말단 리프 노드인걸 알 수 있습니다.
        if (!isExistLeftChild(index)) {
            return;
        }

        // 2. 인덱스 노드의 자식노드들을 추출합니다.
        int leftChildIndex = getLeftChild(index);
        int rightChildIndex = getRightChild(index);

        // 3. 자식노드중 우선순위가 가장 큰것과 비교하여 배치를 우선순위 정렬을 완료합니다.(참고:힙 트리는 완전 트리이기때문에 중간에 비어있지
        // 않습니다.)
        /**
         * 3.1 오른쪽 자식노드가 존재하는지 확인합니다. 존재한다면 왼쪽자식과 오른쪽자식 모두 비교합니다.
         * 3.2 오른쪽 자식노드가 존재하지 않는다면, 왼쪽 자식노드만 비교하여 배치를 합니다.
         */

        //
        if (isExistRightChild(index)) {

            int priority = Math.min(queue[index], Math.min(queue[leftChildIndex], queue[rightChildIndex]));

            if (priority == queue[leftChildIndex]) {

                swap(leftChildIndex, index);
                downHeap(leftChildIndex);

            } else if (priority == queue[rightChildIndex]) {
                swap(index, rightChildIndex);
                downHeap(rightChildIndex);
            }

        } else {
            int priority = Math.min(queue[leftChildIndex], queue[index]);

            if (priority == queue[leftChildIndex]) {
                swap(leftChildIndex, index);
                downHeap(leftChildIndex);
            }

        }

    }

    // 필수기능
    // 1. 힙이 비어있는지 확인
    public boolean isEmpty() {
        return 0 == size;
    }

    // 2. 부모노드 인덱스 추출
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    // 3. 좌측 자식노드 추출 및 유무 확인
    private int getLeftChild(int index) {
        return (index) * 2 + 1;
    }

    private boolean isExistLeftChild(int index) {
        return size > getLeftChild(index);
    }

    // 4. 우측 자식 노드 추출 및 유무 확인

    private int getRightChild(int index) {
        return (index) * 2 + 2;
    }

    private boolean isExistRightChild(int index) {
        return size > getRightChild(index);
    }

    private boolean isExistIndex(int index) {
        return size > index;
    }

    // 5. 우선순위가 가장 높은 루트노드값 추출
    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap에 노드가 존재하지 않습니다.");
        }
        return queue[0];
    }

    // 6. 인덱스의 값들을 서로 바꾸기
    private void swap(int indexA, int indexB) {

        int temp = queue[indexA];
        queue[indexA] = queue[indexB];
        queue[indexB] = temp;

    }

    // 7.부모 노드와 비교
    private void UpHeap(int index) {
        if (index <= 0) {
            return;
        }

        int parentIndex = getParentIndex(index);
        int priority = Math.min(queue[parentIndex], queue[index]);

        if (priority == queue[index]) {
            swap(index, parentIndex);
            UpHeap(parentIndex);
        }

    }

    // 부가기능

    private void printHelper(int visitIndex, String indent, boolean last) {
        if (isEmpty()) {
            System.out.println("Heap is Empty");
            return;
        } else if (size <= visitIndex) {
            return;
        }

        System.out.print(indent);
        if (last) {
            System.out.print("R----");
            indent += "     ";
        } else {
            System.out.print("L----");
            indent += "|    ";
        }

        System.out.println(queue[visitIndex]);
        printHelper(visitIndex * 2 + 1, indent, false);
        printHelper(visitIndex * 2 + 2, indent, true);

    }

    public void printTree() {
        printHelper(0, "", true);
    }

    public static void main(String[] args) {

        MinHeap minHeap = new MinHeap();
        minHeap.add(4);
        minHeap.add(1);
        minHeap.add(5);
        minHeap.add(2);
        minHeap.add(3);
        minHeap.add(6);
        System.out.println("--Min Heap--");
        minHeap.printTree();
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.remove());
        }
    }
}
