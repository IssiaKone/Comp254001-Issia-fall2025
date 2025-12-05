package Ex2;
import java.util.LinkedList;
import java.util.Queue;

class BottomUpMergeSort {

    public static Queue<Integer> merge(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> result = new LinkedList<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek() <= q2.peek())
                result.add(q1.remove());
            else
                result.add(q2.remove());
        }

        while (!q1.isEmpty()) result.add(q1.remove());
        while (!q2.isEmpty()) result.add(q2.remove());

        return result;
    }

    // Bottom-up merge sort
    public static Queue<Integer> mergeSort(int[] arr) {
        Queue<Queue<Integer>> queueOfQueues = new LinkedList<>();

        for (int value : arr) {
            Queue<Integer> single = new LinkedList<>();
            single.add(value);
            queueOfQueues.add(single);
        }

        while (queueOfQueues.size() > 1) {
            Queue<Integer> q1 = queueOfQueues.remove();
            Queue<Integer> q2 = queueOfQueues.remove();

            Queue<Integer> merged = merge(q1, q2);
            queueOfQueues.add(merged);
        }

        return queueOfQueues.remove();
    }

    public static void main(String[] args) {
        int[] data = {38, 27, 43, 3, 9, 82, 10};

        Queue<Integer> sorted = mergeSort(data);

        System.out.println("Sorted result:");
        while (!sorted.isEmpty())
            System.out.print(sorted.remove() + " ");
    }
}
