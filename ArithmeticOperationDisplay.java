import java.util.Scanner;
class ArithmeticOperation {
    double a, b;
    void inputValues() {
        Scanner ob = new Scanner(System.in);
        System.out.print("Enter the value of a = ");
        a = ob.nextDouble();
        System.out.print("Enter the value of b = ");
        b = ob.nextDouble();
        ob.close();
    }
    void sum() {
        System.out.println("The sum of two numbers = " + (a + b));
    }
    void difference() {
        System.out.println("The difference of two numbers = " + (a - b));
    }
  void product() {
        System.out.println("The product of two numbers = " + (a * b));
    }
    void quotient() {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero");
        } else {
            System.out.println("The quotient of two numbers = " + (a / b));
        }
    }
}
public class ArithmeticOperationDisplay {
    public static void main(String[] args) {
        ArithmeticOperation operation = new ArithmeticOperation();
        operation.inputValues();
        operation.sum();
        operation.difference();
        operation.product();
        operation.quotient();
    }
}
