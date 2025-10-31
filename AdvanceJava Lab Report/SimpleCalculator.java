//LAB 1: WAP that works as a simple calculator. Use a grid layout to arrange
// buttons for the digits and for the +, -, % operation. Add a text field to 
//display the result. Handle any possible exceptions like divided by zero.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    // Text field for display
    private JTextField display;

    // Variables to store operands and operator
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // --- Display field ---
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // --- Buttons layout ---
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Button labels
        String[] buttons = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "%",
                "C", "0", "=", "/"
        };

        // Create and add buttons
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if ((command.charAt(0) >= '0' && command.charAt(0) <= '9')) {
                display.setText(display.getText() + command);
            } else if (command.charAt(0) == 'C') {
                display.setText("");
                num1 = num2 = result = 0;
            } else if (command.equals("+") || command.equals("-") ||
                       command.equals("/") || command.equals("%")) {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText("");
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            display.setText("Error: Divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    case '%':
                        if (num2 == 0) {
                            display.setText("Error: Mod by 0");
                            return;
                        }
                        result = num1 % num2;
                        break;
                }
                display.setText(String.valueOf(result));
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
}
