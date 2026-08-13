import java.util.Scanner;
class Area
{
    double length, breadth;
    void inputValues() 
    {
        Scanner ob = new Scanner(System.in);
        System.out.print("Enter the value of length = ");
        length = ob.nextDouble();
        System.out.print("Enter the value of breadth = ");
        breadth = ob.nextDouble();
        ob.close();
    }
    void AreaCalculous()
    {
        double area= length*breadth;
        System.out.println("The area of rectangle is = " +area);
    }
}
public class RetctangleArea 
{
    public static void main(String[] args) 
    {
        Area obj = new Area();
        obj.inputValues();
        obj.AreaCalculous();
    } 
}
