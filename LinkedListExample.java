import java.util.LinkedList;
import java.util.Iterator;
public class LinkedListExample {
    public static void main(String[] args) {
        // 1. Create a LinkedList of Strings
        LinkedList<String> list = new LinkedList<>();
        // Adding elements
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Mango");
        System.out.println("Original List: " + list);
        // 2. Add elements at first and last positions
        list.addFirst("Orange");
        list.addLast("Pineapple");
        System.out.println("After adding at first and last: " + list);
        // 3. Remove first and last elements
        list.removeFirst();
        list.removeLast();
        System.out.println("After removing first and last: " + list);
        // 4. Display elements in reverse order
        System.out.println("\nReverse Order:");
        Iterator<String> reverseIterator = list.descendingIterator();
        while (reverseIterator.hasNext()) {
            System.out.print(reverseIterator.next() + " ");
        }
        // 5. Performance comparison (insertion at beginning)
        LinkedList<Integer> linkedList = new LinkedList<>();
        java.util.ArrayList<Integer> arrayList = new java.util.ArrayList<>();
        long startTime, endTime;
        // LinkedList insertion at beginning
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            linkedList.addFirst(i);
        }
        endTime = System.nanoTime();
        System.out.println("\n\nLinkedList insertion time: " + (endTime - startTime) + " ns");
        // ArrayList insertion at beginning
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(0, i);
        }
        endTime = System.nanoTime();
        System.out.println("ArrayList insertion time: " + (endTime - startTime) + " ns");
    }
}
