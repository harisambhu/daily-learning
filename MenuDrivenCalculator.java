import java.util.Scanner;
class MenuCalculator
{
        Scanner sc = new Scanner(System.in);
        int choice;
        double num1, num2, result;
        void InputValue()
        {
            System.out.print("Enter first numbers: ");
            num1 = sc.nextDouble();
            System.out.print("Enter second numbers: ");
            num2 = sc.nextDouble();
            sc.close();
        }
        void calculation()
        {
            switch (choice) {
                case 1:
                    result = num1 + num2;
                    System.out.println("Result = " + result)
                    break;
                case 2:
                    result = num1 - num2;
                    System.out.println("Result = " + result);
                    break;
                case 3:
                    result = num1 * num2;
                    System.out.println("Result = " + result);
                    break;
                case 4:
                    if (num2 != 0) {
                        result = num1 / num2;
                        System.out.println("Result = " + result);
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
         }
}
public class MenuDrivenCalculator {
        public static void main(String[] args) {
            MenuCalculator obj = new MenuCalculator();
            Scanner sc = new Scanner(System.in);
            System.out.println("Menu:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.print("Enter your choice: ");
            obj.choice = sc.nextInt();
            obj.InputValue();
            obj.calculation();
            sc.close();
        }
}

