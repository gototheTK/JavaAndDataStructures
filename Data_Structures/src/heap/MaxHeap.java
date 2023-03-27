
import java.util.Arrays;

/**
 * Heap
 * 우선순위를 형제가 아닌 부모노드와 비교하여, 배치하는 트리형태의 자료구조
 * 보통 구현은 트리가 아닌 배열 형태로 구현한다.
 * 
 * 노드 추가
 * 
 * 1.마지막 리프노드가 되는 자리에 배치한다.
 * 2.배치된 자리에서 부모노드와 우선순위를 비교하여 바꾼다.
 * 3. 우선순위가 큰 부모노드를 만날때까지 2를 반복한다.
 * 
 * 
 * 노드 삭제
 * 
 * 1. 루트노드를 삭제하여 반환하고, 마지막 리프노드를 루트자리에 배치한다.
 * 2. 루트노드에 배치된 리프노드를 자식 노드들과 비교하여 우선순위가 더 높은 자식노드와 배치를 바꾼다.
 * 3. 우선순위가 작은 부모노드를 만날때까지 2를 반복한다.
 * 
 * 필수 기능
 * 1. 부모노드와 비교
 * 2. 자식노드와 비교
 * 4. 노드들의 배치를 바꾸기
 * 5. 부모노드 인덱스 추출
 * 6. 오른쪽 자식노드 인덱스 추출
 * 7. 왼쪽 자식노드 인덱스 추출
 * 8. 오른쪽 자식노드가 존재하는지 확인
 * 9. 왼쪽 자식노드가 존재하는지 확인
 * 10. Peek()
 * 11. 힙이 비어있는지 확인
 */

public class MaxHeap {

    int[] queue;
    int size = 0;

    public MaxHeap() {
        this.queue = new int[4];
    }

    public void add(int data) {
        if (size == queue.length) {
            queue = Arrays.copyOf(queue, size + 4);
        }
        queue[size++] = data;
        // TODO : upHeap연산 실행
        upHeap(size - 1);
    }

    private void upHeap(int index) {
        if (index <= 0) {
            return;
        }
        int parentIndex = parentIndex(index);
        if (queue[index] > queue[parentIndex]) {
            swap(index, parentIndex);
            upHeap(parentIndex);
        }

    }

    public int remove() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Heap is Empty");
        }
        int data = queue[0];
        swap(0, size - 1);
        queue[size - 1] = 0;
        --size;
        // TODO : downHeap 연산 실행
        downHeap(0);
        return data;
    }

    public int peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Heap is Empty");
        }
        return queue[0];
    }

    public boolean isEmpty() {
        return 0 == size;
    }

    private void swap(int index, int index2) {
        int tempValue = queue[index];
        queue[index] = queue[index2];
        queue[index2] = tempValue;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private boolean isExistRightChild(int index) {
        return size > rightChildIndex(index);
    }

    private boolean isExistLeftChild(int index) {
        return size > leftChildIndex(index);
    }

    private void downHeap(int index) {
        if (!isExistLeftChild(index)) {
            return;
        }
        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);

        if (isExistRightChild(index)) {
            // TODO : 좌/우측 자식 노드가 존재하는 경우
            int maxValue = Math.max(queue[index], Math.max(queue[leftChildIndex], queue[rightChildIndex]));
            if (maxValue == queue[leftChildIndex]) {
                swap(leftChildIndex, index);
                downHeap(leftChildIndex);
            } else if (maxValue == queue[rightChildIndex]) {
                swap(rightChildIndex, index);
                downHeap(rightChildIndex);
            }
        } else {
            // TODO : 좌측 자식 노드만 존재하는 경우
            int maxValue = Math.min(queue[index], queue[leftChildIndex]);
            if (maxValue == queue[leftChildIndex]) {
                swap(leftChildIndex, index);
                downHeap(leftChildIndex);
            }
        }
    }

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

        // TODO : 최대 Heap
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.add(4);
        maxHeap.add(1);
        maxHeap.add(5);
        maxHeap.add(2);
        maxHeap.add(3);
        maxHeap.add(6);
        System.out.println("--Max Heap--");
        maxHeap.printTree();
        while (!(maxHeap.isEmpty())) {
            System.out.println(maxHeap.remove());
        }

    }

}