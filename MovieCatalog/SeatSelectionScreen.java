import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SeatSelectionScreen extends JFrame {
    private Movie movie;
    private String buyerName;
    private int ticketCount;
    private List<String> selectedSeats = new ArrayList<>();
    private List<JButton> seatButtons = new ArrayList<>();

    public SeatSelectionScreen(Movie movie, String buyerName, int ticketCount) {
        this.movie = movie;
        this.buyerName = buyerName;
        this.ticketCount = ticketCount;

        setTitle("Seat Selection");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 5));


        for (int i = 1; i <= 25; i++) {
            String seatLabel = "S" + i;
            JButton seatButton = new JButton(seatLabel);
            seatButton.setBackground(Color.GREEN);

            seatButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (seatButton.getBackground() == Color.GREEN) {
                        if (selectedSeats.size() < ticketCount) {
                            seatButton.setBackground(Color.RED);
                            selectedSeats.add(seatLabel);
                        } else {
                            JOptionPane.showMessageDialog(SeatSelectionScreen.this,
                                    "You cannot select more than " + ticketCount + " seats!",
                                    "Selection Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        seatButton.setBackground(Color.GREEN);
                        selectedSeats.remove(seatLabel);
                    }
                }
            });

            seatButtons.add(seatButton);
            add(seatButton);
        }


        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedSeats.size() == ticketCount) {
                    double totalAmount = ticketCount * movie.getPrice();
                    new PaymentConfirmationScreen(movie, buyerName, ticketCount, totalAmount, selectedSeats).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(SeatSelectionScreen.this,
                            "Please select " + ticketCount + " seats before proceeding.",
                            "Incomplete Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

       
        add(proceedButton);

        setVisible(true);
    }
}
