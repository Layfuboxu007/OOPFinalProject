import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Movie {
    String title;
    double pricePerTicket;

    public Movie(String title, double pricePerTicket) {
        this.title = title;
        this.pricePerTicket = pricePerTicket;
    }

    @Override
    public String toString() {
        return title + " (PHP " + pricePerTicket + ")";
    }
}

public class MovieCatalogScreen extends JFrame {
    private JList<Movie> movieList;
    private DefaultListModel<Movie> movieListModel;

    public MovieCatalogScreen() {
        setTitle("Movie Ticketing System - Movie Catalog");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sample movie data
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avengers", 200));
        movies.add(new Movie("Inception", 250));
        movies.add(new Movie("Frozen", 150));

        movieListModel = new DefaultListModel<>();
        movies.forEach(movieListModel::addElement);

        movieList = new JList<>(movieListModel);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(movieList);

        JButton bookButton = new JButton("Book Selected Movie");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movie selectedMovie = movieList.getSelectedValue();
                if (selectedMovie != null) {
                    new BookingScreen(selectedMovie).setVisible(true); // Navigate to booking screen
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(MovieCatalogScreen.this,
                            "Please select a movie to book!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(new JLabel("Select a Movie:", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bookButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
