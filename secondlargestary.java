
public class Main {
    public static void main(String[] args) {
        int[] arr = {10, 25, 8, 45, 32, 45};

        Integer largest = null;
        Integer second = null;

        for (int n : arr) {
            if (largest == null || n > largest) {
                if (largest != null) {
                    second = largest;
                }
                largest = n;
            } else if (n != largest &&
                       (second == null || n > second)) {
                second = n;
            }
        }

        if (second == null) {
            System.out.println("No second-largest distinct value");
        } else {
            System.out.println("Second largest: " + second);
        }
    }
}
