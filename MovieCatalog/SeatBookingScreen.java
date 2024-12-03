import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatBookingScreen extends JFrame {
    private static final int ROWS = 2;
    private static final int COLS = 10;
    private double ticketPrice;
    private String customerName;
    private String movieTitle;

    private JButton[][] seats;
    private boolean[][] seatStatus;
    private int ticketsToBook;
    private int ticketsBooked = 0;
    private StringBuilder bookedSeats;

    public SeatBookingScreen(String customerName, String movieTitle, int ticketsToBook, double ticketPrice) {
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.ticketsToBook = ticketsToBook;
        this.ticketPrice = ticketPrice;
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
                            // deselect chair function kay ambut.
                            seatStatus[finalRow][finalCol] = false;
                            seats[finalRow][finalCol].setBackground(Color.GREEN);
                            seats[finalRow][finalCol].setEnabled(true);
                            ticketsBooked--;
                            bookedSeats.setLength(bookedSeats.length() - seats[finalRow][finalCol].getText().length() - 1);
                        } else if (ticketsBooked < ticketsToBook) {
                            // blue = selected ang seat
                            seatStatus[finalRow][finalCol] = true;
                            seats[finalRow][finalCol].setBackground(Color.BLUE);
                            seats[finalRow][finalCol].setEnabled(false);
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
                    double totalPrice = ticketsBooked * ticketPrice;
                    new PaymentConfirmationScreen(
                        customerName,
                        movieTitle,
                        ticketsBooked,
                        totalPrice
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
