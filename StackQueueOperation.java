import java.util.*;
class StackQueue {
    // Stack
    public void stackMethod(Scanner sc) {
        Stack<Integer> stack = new Stack<>();
        System.out.println("Enter 5 elements for Stack:");
        for (int i = 0; i < 5; i++) {
            stack.push(sc.nextInt());
        }
        stack.pop();
        stack.pop();
        System.out.println("Stack: " + stack);
        System.out.println("Top element: " + stack.peek());
    }
    // Queue
    public void queueMethod(Scanner sc) {
        Queue<Integer> queue = new LinkedList<>();
        System.out.println("\nEnter 5 elements for Queue:");
        for (int i = 0; i < 5; i++) {
            queue.add(sc.nextInt());
        }
        queue.remove();
        queue.remove();
        System.out.println("Queue: " + queue);
        System.out.println("Front element: " + queue.peek());
    }
}
public class StackQueueOperation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StackQueue obj = new StackQueue();
        obj.stackMethod(sc);
        obj.queueMethod(sc);
        sc.close();
    }
}
