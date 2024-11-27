import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentConfirmationScreen extends JFrame {
    private Movie movie;
    private String buyerName;
    private int ticketCount;
    private double totalAmount;
    private java.util.List<String> selectedSeats;

    public PaymentConfirmationScreen(Movie movie, String buyerName, int ticketCount, double totalAmount, java.util.List<String> selectedSeats) {
        this.movie = movie;
        this.buyerName = buyerName;
        this.ticketCount = ticketCount;
        this.totalAmount = totalAmount;
        this.selectedSeats = selectedSeats;

        setTitle("Payment Confirmation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Buyer and movie details
        JLabel buyerLabel = new JLabel("Buyer Name: " + buyerName);
        JLabel movieLabel = new JLabel("Movie: " + movie.title);
        JLabel ticketCountLabel = new JLabel("Tickets: " + ticketCount);
        JLabel totalAmountLabel = new JLabel("Total Amount: PHP " + totalAmount);
        JLabel seatsLabel = new JLabel("Seats: " + String.join(", ", selectedSeats));

        // Payment and final confirmation
        JLabel paymentLabel = new JLabel("Payment Method:");
        JComboBox<String> paymentMethodCombo = new JComboBox<>(new String[]{"Cash", "Credit Card", "Gcash"});
        JButton confirmButton = new JButton("Confirm Payment");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPaymentMethod = (String) paymentMethodCombo.getSelectedItem();
                JOptionPane.showMessageDialog(PaymentConfirmationScreen.this,
                        "Payment Confirmed!\n" +
                                "Buyer: " + buyerName + "\n" +
                                "Movie: " + movie.title + "\n" +
                                "Tickets: " + ticketCount + "\n" +
                                "Total: PHP " + totalAmount + "\n" +
                                "Seats: " + String.join(", ", selectedSeats) + "\n" +
                                "Payment Method: " + selectedPaymentMethod,
                        "Payment Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        // Add components to the frame
        add(buyerLabel);
        add(movieLabel);
        add(ticketCountLabel);
        add(totalAmountLabel);
        add(seatsLabel);
        add(paymentLabel);
        add(paymentMethodCombo);
        add(new JLabel()); // Spacer
        add(confirmButton);

        setVisible(true);
    }
}
