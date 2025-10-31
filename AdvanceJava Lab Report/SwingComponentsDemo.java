//  LAB 2 :WAP to demonstrate the use of JSwing Components and layout managers to it.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingComponentsDemo extends JFrame implements ActionListener {

    // Declare components
    private JTextField nameField;
    private JPasswordField passField;
    private JComboBox<String> genderBox;
    private JCheckBox termsCheck;
    private JButton submitBtn, clearBtn;
    private JTextArea outputArea;

    public SwingComponentsDemo() {
        // Frame title and layout
        setTitle("Swing Components & Layout Managers Demo");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        // --- Top Panel (BorderLayout.NORTH) ---
        JLabel title = new JLabel("User Registration Form", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // --- Center Panel (GridLayout) ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Add form components
        formPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        formPanel.add(passField);

        formPanel.add(new JLabel("Gender:"));
        String[] gender = {"Select", "Male", "Female", "Other"};
        genderBox = new JComboBox<>(gender);
        formPanel.add(genderBox);

        formPanel.add(new JLabel("Accept Terms:"));
        termsCheck = new JCheckBox("I agree");
        formPanel.add(termsCheck);

        add(formPanel, BorderLayout.CENTER);

        // --- Bottom Panel (FlowLayout) ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        submitBtn = new JButton("Submit");
        clearBtn = new JButton("Clear");
        buttonPanel.add(submitBtn);
        buttonPanel.add(clearBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Output Area (BorderLayout.EAST) ---
        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        add(outputArea, BorderLayout.EAST);

        // --- Add Action Listeners ---
        submitBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            if (!termsCheck.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please accept the terms first!");
                return;
            }

            String name = nameField.getText();
            String gender = (String) genderBox.getSelectedItem();
            String pass = new String(passField.getPassword());

            outputArea.setText("✅ Registration Details:\n");
            outputArea.append("Name: " + name + "\n");
            outputArea.append("Gender: " + gender + "\n");
            outputArea.append("Password: " + pass + "\n");
        } else if (e.getSource() == clearBtn) {
            nameField.setText("");
            passField.setText("");
            genderBox.setSelectedIndex(0);
            termsCheck.setSelected(false);
            outputArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingComponentsDemo::new);
    }
}
