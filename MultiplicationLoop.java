import java.util.Scanner;
class Loop
{
   int num;
    void InputValue()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        num = sc.nextInt();
         sc.close();
    }
    void Loopmultiplication()
    {
        for(int i=1;i<=10;i++)
        {
            System.out.println(num+" x "+i+" = "+num*i);
        }
    }
}
public class MultiplicationLoop 
{
    public static void main(String[] args) 
    {
        Loop obj=new Loop();
        obj.InputValue();
        obj.Loopmultiplication();
    }
}

