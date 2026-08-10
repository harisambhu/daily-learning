interface Display {
    void showDetails();
}
class Circle implements Display {
    public void showDetails() {
        System.out.println("This is a Circle.");
    }
}
class Rectangle implements Display {
    public void showDetails() {
        System.out.println("This is a Rectangle.");
    }
}
public class TestInterface {
    public static void main(String[] args) {
        Circle c = new Circle();
        Rectangle r = new Rectangle();
        c.showDetails();
        r.showDetails();
    
}
