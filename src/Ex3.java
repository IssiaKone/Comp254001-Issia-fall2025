import java.util.ArrayList;
import java.util.NoSuchElementException;

 class HeapPriorityQueue<E extends Comparable<E>> {

    private ArrayList<E> heap = new ArrayList<>();

    public HeapPriorityQueue() { }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void insert(E element) {
        heap.add(element);            // add at the end
        upheap(heap.size() - 1);      // restore heap property
    }

    public E min() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap.get(0);
    }

    public E removeMin() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");

        E answer = heap.get(0);
        E last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            downheap(0);
        }
        return answer;
    }

    // ---- index helpers ----
    private int parent(int j) { return (j - 1) / 2; }
    private int left(int j) { return 2 * j + 1; }
    private int right(int j) { return 2 * j + 2; }

    private boolean hasLeft(int j) { return left(j) < heap.size(); }
    private boolean hasRight(int j) { return right(j) < heap.size(); }

    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    // EXERCISE 3 PART: RECURSIVE UPHEAP METHOD
    private void upheap(int j) {
        if (j == 0) return;  // at root, done

        int p = parent(j);
        // if child key < parent key → swap and recurse
        if (heap.get(j).compareTo(heap.get(p)) < 0) {
            swap(j, p);
            upheap(p);       // RECURSIVE CALL
        }
    }

    // standard downheap using a loop
    private void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;

            if (hasRight(j)) {
                int rightIndex = right(j);
                if (heap.get(rightIndex).compareTo(heap.get(leftIndex)) < 0) {
                    smallChildIndex = rightIndex;
                }
            }

            if (heap.get(smallChildIndex).compareTo(heap.get(j)) >= 0) {
                break; // heap property satisfied
            }

            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    // Optional: for debugging
    public void printHeapArray() {
        System.out.println(heap);
    }
}

class Ex3 {
    public static void main(String[] args) {
        HeapPriorityQueue<Integer> pq = new HeapPriorityQueue<>();

        System.out.println("Inserting elements: 20, 5, 15, 3, 17, 10");
        pq.insert(20);
        pq.insert(5);
        pq.insert(15);
        pq.insert(3);
        pq.insert(17);
        pq.insert(10);

        System.out.print("Heap internal array after insertions: ");
        pq.printHeapArray();

        System.out.println("\nRemoving elements in increasing order:");
        while (!pq.isEmpty()) {
            System.out.println("min = " + pq.removeMin());
        }
    }
}
