import java.util.Scanner;

class AverageCalculator {
    double a, b;

    void inputValues() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        a = sc.nextDouble();
        System.out.print("Enter the second number: ");
        b = sc.nextDouble();
        sc.close();
    }

    void calculateAverage() {
        double average = (a + b) / 2;
        System.out.println("The average of the two numbers is = " + average);
    }
}

public class AverageDisplay {
    public static void main(String[] args) {
        AverageCalculator obj = new AverageCalculator();
        obj.inputValues();
        obj.calculateAverage();
    }
}
