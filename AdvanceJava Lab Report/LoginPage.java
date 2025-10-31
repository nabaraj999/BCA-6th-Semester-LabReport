// LAB 3:Create a login page in Swing and add events to it on click of login button

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

    // Components
    private JLabel userLabel, passLabel, messageLabel;
    private JTextField userText;
    private JPasswordField passText;
    private JButton loginButton, clearButton;

    public LoginPage() {
        // Frame setup
        setTitle("Login Page");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Username Label and TextField
        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        userText = new JTextField();
        userText.setBounds(150, 50, 180, 30);
        add(userText);

        // Password Label and Field
        passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        passText = new JPasswordField();
        passText.setBounds(150, 100, 180, 30);
        add(passText);

        // Message Label
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 140, 300, 30);
        messageLabel.setForeground(Color.RED);
        add(messageLabel);

        // Buttons
        loginButton = new JButton("Login");
        loginButton.setBounds(80, 180, 100, 30);
        loginButton.addActionListener(this);
        add(loginButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(200, 180, 100, 30);
        clearButton.addActionListener(this);
        add(clearButton);

        setVisible(true);
    }

    // Action event handling
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = new String(passText.getPassword());

            // Simple check
            if (username.equals("admin") && password.equals("12345")) {
                messageLabel.setForeground(new Color(0, 128, 0));
                messageLabel.setText("Login Successful!");
                
                // Optional: open a new window
                JOptionPane.showMessageDialog(this, "Welcome " + username + "!");
                // dispose(); // Uncomment if you want to close the login window after success
            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Invalid username or password!");
            }
        } else if (e.getSource() == clearButton) {
            userText.setText("");
            passText.setText("");
            messageLabel.setText("");
        }
    }

    public static void main(String[] args) {
        // Run GUI safely
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
}
