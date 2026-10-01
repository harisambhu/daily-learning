public import java.util.Scanner;

public class minimum_subarray
 {
    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
    
    System.out.println("enter number of element:");
    int n = sc.nextInt();
    int[] arr = new int[n];
    System.out.println("enter array elements:");//[2,3,6,1,6]
    for(int i=0;i<n;i++){
        arr[i] = sc.nextInt();
    }
    int target = 7;
    int sum =0;
    int left=0;
    }
} 