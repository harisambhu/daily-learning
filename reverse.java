import java.util.Scanner;
public class reverse {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a no ");
        int a = sc.nextInt();
        int original = a;
        int reverse = 0;
        while(a>0)
            {
                int digit=a%10;
                reverse= reverse*10+digit;
                a=a/10;

            }
            System.out.println("reverse is "+ reverse);
           
    }
}