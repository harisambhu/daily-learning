import java.util.Scanner; 
class operator{
    int a,b;
    void inputValues() {
        Scanner ob = new Scanner(System.in);
        System.out.print("Enter the value of a = ");
        a = ob.nextInt();
        System.out.print("Enter the value of b = ");
        b = ob.nextInt();
        ob.close();
    }
    void relationalOperators() {
        System.out.println("\nRelational Operator Results:");
        System.out.println("a > b  = " + (a > b));
        System.out.println("a < b  = " + (a < b));
        System.out.println("a >= b = " + (a >= b));
        System.out.println("a <= b = " + (a <= b));
        System.out.println("a == b = " + (a == b));
        System.out.println("a != b = " + (a != b));
    }
    void logicalAnd()
    {
        if(a>0 && b>0)
        {
            System.out.println("Both a and b are positive");
        }
        else
        {
            System.out.println("Either a or b is not positive");
        }
    }
    void logicalOr()
    {
        if(a>0 || b>0)
        {
            System.out.println("Either a or b is positive");
        }
        else
        {
            System.out.println("Both a and b are not positive");
        }
    }
    void logicalNot()
    {
        if(!(a>0))
        {
            System.out.println("a is not positive");
        }
        else
        {
            System.out.println("a is positive");
        }
    }
}
public class LogicalOperator {
    public static void main(String[] args) {
        operator obj=new operator();
        obj.inputValues();
        obj.relationalOperators();
        obj.logicalAnd();
        obj.logicalOr();
        obj.logicalNot();
    }  
} 
