import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingScreen extends JFrame {
    private JTextField buyerNameField;
    private JSpinner ticketCountSpinner;
    private JLabel totalAmountLabel;

    private Movie movie;
    private double totalAmount;

    public BookingScreen(Movie movie) {
        this.movie = movie;

        setTitle("Movie Ticketing System - Booking");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel movieLabel = new JLabel("Movie: " + movie.title);
        JLabel priceLabel = new JLabel("Price per Ticket: PHP " + movie.pricePerTicket);

        buyerNameField = new JTextField();

        JLabel ticketCountLabel = new JLabel("Number of Tickets:");
        ticketCountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        ticketCountSpinner.addChangeListener(e -> updateTotalAmount());

        totalAmountLabel = new JLabel("Total Amount: PHP " + movie.pricePerTicket);

        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buyerName = buyerNameField.getText();
                int ticketCount = (int) ticketCountSpinner.getValue();

                if (buyerName.isEmpty()) {
                    JOptionPane.showMessageDialog(BookingScreen.this,
                            "Please enter buyer's name!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    new SeatSelectionScreen(movie, buyerName, ticketCount).setVisible(true);
                    dispose();
                }
            }
        });

        add(movieLabel);
        add(priceLabel);
        add(new JLabel("Buyer's Name:"));
        add(buyerNameField);
        add(ticketCountLabel);
        add(ticketCountSpinner);
        add(new JLabel(""));
        add(totalAmountLabel);
        add(new JLabel(""));
        add(proceedButton);

        setVisible(true);
    }

    private void updateTotalAmount() {
        int ticketCount = (int) ticketCountSpinner.getValue();
        totalAmount = ticketCount * movie.pricePerTicket;
        totalAmountLabel.setText("Total Amount: PHP " + totalAmount);
    }
}
