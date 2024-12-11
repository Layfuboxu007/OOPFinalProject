import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatBookingScreen extends JFrame {
    private static final int ROWS = 2;
    private static final int COLS = 10;
    private double totalPrice;
    private String customerName;
    private String movieTitle;

    private JButton[][] seats;
    private boolean[][] seatStatus;
    private int ticketsToBook;
    private int ticketsBooked = 0;
    private StringBuilder bookedSeats;

    public SeatBookingScreen(String customerName, String movieTitle, int ticketsToBook, double totalPricee) {
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.ticketsToBook = ticketsToBook;
        this.totalPricee = totalPricee;
        this.bookedSeats = new StringBuilder();

        setTitle("Movie Seat Booking");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        seats = new JButton[ROWS][COLS];
        seatStatus = new boolean[ROWS][COLS];

        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(ROWS, COLS, 5, 5));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String seatLabel = (char) ('A' + row) + String.valueOf(col + 1);
                seats[row][col] = new JButton(seatLabel);
                seats[row][col].setBackground(Color.GREEN);
                seats[row][col].setFocusPainted(false);

                final int finalRow = row;
                final int finalCol = col;

                seats[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (seatStatus[finalRow][finalCol]) {
                            // Deselect seat
                            seatStatus[finalRow][finalCol] = false;
                            seats[finalRow][finalCol].setBackground(Color.GREEN);
                            ticketsBooked--;

                            // Remove deselected seat from bookedSeats
                            String seatText = seats[finalRow][finalCol].getText() + " ";
                            int index = bookedSeats.indexOf(seatText);
                            if (index != -1) {
                                bookedSeats.delete(index, index + seatText.length());
                            }
                        } else if (ticketsBooked < ticketsToBook) {
                            // Select seat
                            seatStatus[finalRow][finalCol] = true;
                            seats[finalRow][finalCol].setBackground(Color.BLUE);
                            ticketsBooked++;
                            bookedSeats.append(seats[finalRow][finalCol].getText()).append(" ");

                            if (ticketsBooked == ticketsToBook) {
                                JOptionPane.showMessageDialog(
                                    SeatBookingScreen.this,
                                    "Maximum seats reached, cannot book more seats.",
                                    "Booking Complete",
                                    JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        } else {
                            // Notify user if max tickets reached
                            JOptionPane.showMessageDialog(
                                SeatBookingScreen.this,
                                "You cannot select more than " + ticketsToBook + " seats.",
                                "Limit Reached",
                                JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }
                });

                seatPanel.add(seats[row][col]);
            }
        }

        add(seatPanel, BorderLayout.CENTER);

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new FlowLayout());

        JLabel paymentLabel = new JLabel("Choose Payment Method:");
        paymentPanel.add(paymentLabel);

        JButton cardButton = new JButton("Card");
        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    SeatBookingScreen.this,
                    "Card payment option is under construction due to naglisod.",
                    "Under Construction",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });
        paymentPanel.add(cardButton);

        JButton cashButton = new JButton("Cash");
        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ticketsBooked > 0) {
                    new PaymentConfirmationScreen(
                        customerName,
                        movieTitle,
                        ticketsBooked,
                        totalPricee
                    ).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(
                        SeatBookingScreen.this,
                        "No seats booked yet.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        paymentPanel.add(cashButton);

        add(paymentPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
