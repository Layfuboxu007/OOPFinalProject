import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        setTitle("Login");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        JTextField userField = new JTextField(15);
        gbc.gridx = 1;
        add(userField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(15);
        gbc.gridx = 1;
        add(passField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals("admin") && password.equals("password")) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login Successful!");
                    dispose();
                    MovieCatalogScreen movieCatalog = new MovieCatalogScreen();
                    movieCatalog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid Credentials!");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Ensure the first screen opens at the right size
        new LoginScreen().setVisible(true);
    }
}
