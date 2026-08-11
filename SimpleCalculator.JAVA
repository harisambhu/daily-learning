import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {

    TextField tf;
    String operator = "";
    double num1 = 0, num2 = 0, result = 0;

    SimpleCalculator() {
        setTitle("AWT Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());

        tf = new TextField();
        tf.setEditable(false);
        add(tf, BorderLayout.NORTH);

        Panel p = new Panel();
        p.setLayout(new GridLayout(5, 4, 5, 5));

        String buttons[] = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String b : buttons) {
            Button btn = new Button(b);
            btn.addActionListener(this);
            p.add(btn);
        }

        add(p, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9.]")) {
            tf.setText(tf.getText() + input);
        } 
        else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(tf.getText());
            operator = input;
            tf.setText("");
        } 
        else if (input.equals("=")) {
            num2 = Double.parseDouble(tf.getText());

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0)
                        result = num1 / num2;
                    else {
                        tf.setText("Error");
                        return;
                    }
                    break;
            }

            tf.setText("" + result);
        } 
        else if (input.equals("C")) {
            tf.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}