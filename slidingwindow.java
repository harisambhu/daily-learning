import java.util.Scanner;

public class slidingwindow {
    public static void main(String[] args) {
          Scanner sc = new Scanner(System.in);
    
    System.out.println("enter number of element:");
    int n = sc.nextInt();
    int[] arr = new int[n];
    System.out.println("enter array elements:");//[2,3,6,1,6]
    for(int i=0;i<n;i++){
        arr[i] = sc.nextInt();
    }
    int target = 3;
    int sum =0 ;
    int maxsum = 0;

    for(int i=0;i<target;i++){
      sum = sum+arr[i];
    }
    maxsum = sum;

    for(int i=target;i<arr.length;i++){
        sum = sum + arr[i] - arr[i-target];
                 //11+1-2  
                 //10+6-3
maxsum=Math.max(sum, maxsum );
    }
    System.out.println("maxsum is " + maxsum);
    }
}