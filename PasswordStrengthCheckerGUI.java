import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordStrengthCheckerGUI extends JFrame {
    private JTextField passwordField;
    private JLabel strengthLabel;

    public PasswordStrengthCheckerGUI() {
        setTitle("Password Strength Checker");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK); // Set background color to black

        // Create UI components
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setForeground(Color.WHITE); // Set text color to white
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font style

        passwordField = new JTextField(25); // Increase size of password field

        JButton checkButton = new JButton("Check Strength");
        checkButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font style
        checkButton.setBackground(Color.WHITE); // Set background color to white
        checkButton.setForeground(Color.BLACK); // Set text color to black

        // Rounded edges for the button
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border roundedBorder = new RoundedBorder(10); // Create rounded border with radius 10
        checkButton.setBorder(BorderFactory.createCompoundBorder(border, roundedBorder));
        checkButton.setFocusPainted(false); // Remove focus border
        checkButton.setOpaque(true); // Ensure background color is shown

        strengthLabel = new JLabel("Password Strength: ");
        strengthLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font style
        strengthLabel.setForeground(Color.WHITE); // Set text color to white

        // Add action listener to the button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String strength = checkPasswordStrength(password);
                strengthLabel.setText("Password Strength: " + strength);
                updateLabelColor(strength);
            }
        });

        // Set up the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(checkButton, gbc);

        gbc.gridy = 2;
        add(strengthLabel, gbc);
    }

    private void updateLabelColor(String strength) {
        switch (strength) {
            case "Very Weak":
                strengthLabel.setForeground(Color.RED);
                break;
            case "Weak":
                strengthLabel.setForeground(Color.ORANGE);
                break;
            case "Medium":
                strengthLabel.setForeground(Color.YELLOW);
                break;
            case "Strong":
                strengthLabel.setForeground(Color.GREEN);
                break;
            case "Very Strong":
                strengthLabel.setForeground(Color.BLUE);
                break;
            default:
                strengthLabel.setForeground(Color.WHITE);
                break;
        }
    }

    public static String checkPasswordStrength(String password) {
        int score = 0;

        // Check length
        if (password.length() >= 8) {
            score++;
        }
        if (password.length() >= 12) {
            score++;
        }

        // Check for uppercase and lowercase letters
        if (password.matches(".*[A-Z].*")) {
            score++;
        }
        if (password.matches(".*[a-z].*")) {
            score++;
        }

        // Check for digits
        if (password.matches(".*[0-9].*")) {
            score++;
        }

        // Check for special characters
        if (password.matches(".*[!@#$%^&*()-+=].*")) {
            score++;
        }

        // Determine strength
        switch (score) {
            case 0:
            case 1:
            case 2:
                return "Very Weak";
            case 3:
                return "Weak";
            case 4:
                return "Medium";
            case 5:
                return "Strong";
            case 6:
                return "Very Strong";
            default:
                return "Unknown";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordStrengthCheckerGUI().setVisible(true);
            }
        });
    }

    // Custom class for rounded button border
    static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
