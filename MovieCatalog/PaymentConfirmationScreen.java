import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.*;

public class PaymentConfirmationScreen extends JFrame {
    private String userName;
    private String selectedMovie;
    private int ticketCount;
    private double totalAmount;

    private JTextField paymentField;
    private JLabel changeLabel;

    public PaymentConfirmationScreen(String userName, String selectedMovie, int ticketCount, double totalAmount) {
        this.userName = userName;
        this.selectedMovie = selectedMovie;
        this.ticketCount = ticketCount;
        this.totalAmount = totalAmount;

        setTitle("Payment Confirmation");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(new JLabel(userName), gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Movie:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(new JLabel(selectedMovie), gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Tickets:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(new JLabel(String.valueOf(ticketCount)), gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Total Amount:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(new JLabel("PhP " + totalAmount), gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Amount Paid:"), gbc);

        gbc.gridx = 1;
        paymentField = new JTextField(15);
        ((AbstractDocument) paymentField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*\\.?[0-9]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
                if (string.matches("[0-9]*\\.?[0-9]*")) {
                    super.replace(fb, offset, length, string, attrs);
                }
            }
        });
        mainPanel.add(paymentField, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Change:"), gbc);

        gbc.gridx = 1;
        changeLabel = new JLabel("PhP 0.00");
        mainPanel.add(changeLabel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm Payment");
        add(confirmButton, BorderLayout.SOUTH);

        
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amountPaid = Double.parseDouble(paymentField.getText().trim());

                    if (amountPaid < totalAmount) {
                        JOptionPane.showMessageDialog(
                            PaymentConfirmationScreen.this,
                            "Insufficient amount! Please enter a valid amount.",
                            "Payment Error",
                            JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    
                    double change = amountPaid - totalAmount;
                    changeLabel.setText("PhP " + change);

                    
                    JOptionPane.showMessageDialog(
                        PaymentConfirmationScreen.this,
                        "Payment successful!",
                        "Payment Successful",
                        JOptionPane.INFORMATION_MESSAGE
                    );

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        PaymentConfirmationScreen.this,
                        "Invalid amount entered! Please enter a valid amount.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        
        paymentField.requestFocusInWindow();

        setVisible(true);
    }

    public static void main(String[] args) {
        new PaymentConfirmationScreen("John Doe", "Avengers: Endgame", 3, 2250.0);
    }
}
