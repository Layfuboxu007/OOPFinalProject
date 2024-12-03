import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BookingScreen extends JFrame {
    private JLabel selectedMovieLabel;
    private JTextField userNameField;
    private JSpinner ticketCountSpinner;
    private JLabel totalPriceLabel;

    private double ticketPrice;

    public BookingScreen(String selectedMovie, double ticketPrice) {
        this.ticketPrice = ticketPrice;

        setTitle("Booking Screen");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        selectedMovieLabel = new JLabel("Selected Movie: " + selectedMovie, JLabel.CENTER);
        selectedMovieLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(selectedMovieLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(new JLabel("Your Name:"));
        userNameField = new JTextField();
        mainPanel.add(userNameField);

        mainPanel.add(new JLabel("Amount of Tickets:"));
        ticketCountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        mainPanel.add(ticketCountSpinner);

        mainPanel.add(new JLabel("Price per Ticket:"));
        JLabel ticketPriceLabel = new JLabel("PhP " + ticketPrice);
        ticketPriceLabel.setForeground(new Color(0, 128, 0)); 
        mainPanel.add(ticketPriceLabel);

        mainPanel.add(new JLabel("Total Price:"));
        totalPriceLabel = new JLabel("PhP " + ticketPrice);
        totalPriceLabel.setForeground(new Color(0, 128, 0)); 
        mainPanel.add(totalPriceLabel);

        add(mainPanel, BorderLayout.CENTER);

        JButton proceedToSeatsButton = new JButton("Proceed to Seat Booking");
        proceedToSeatsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        add(proceedToSeatsButton, BorderLayout.SOUTH);

        ticketCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int ticketCount = (int) ticketCountSpinner.getValue();
                double totalPrice = ticketCount * ticketPrice;
                totalPriceLabel.setText("PhP " + totalPrice);
            }
        });

        proceedToSeatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameField.getText().trim();
                if (userName.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        BookingScreen.this,
                        "Please enter your name.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                // info -> seatbooking
                int ticketCount = (int) ticketCountSpinner.getValue();
                double totalPrice = ticketCount * ticketPrice;

                new SeatBookingScreen(userName, selectedMovie, ticketCount, totalPrice).setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }
}
