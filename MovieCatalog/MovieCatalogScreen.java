import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieCatalogScreen extends JFrame {

    private String[] movieTitles = {
        "Transformers One",
        "Godzilla X Kong",
        "Borderlands",
        "Deadpool and Wolverine",
        "Alien: Romulus"
    };

    private String[] movieDescriptions = {
        "Transformers battle for supremacy in this epic adventure.",
        "Godzilla and Kong face off for the ultimate showdown.",
        "A post-apocalyptic world filled with crazy adventures.",
        "Deadpool teams up with Wolverine in this action-packed film.",
        "A chilling story set in space with deadly aliens."
    };

    private double[] ticketPrices = {300.0, 600.0, 600.0, 600.0, 400.0};

    private JLabel imageLabel;

    public MovieCatalogScreen() {
        setTitle("Movie Catalog");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // center kay ambut nganu mu tiko ang window, nayabag.
        setLocationRelativeTo(null);

        JList<String> movieList = new JList<>(movieTitles);
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movieList.setVisibleRowCount(5);

        JScrollPane scrollPane = new JScrollPane(movieList);
        add(scrollPane, BorderLayout.WEST);

        JPanel movieDetailsPanel = new JPanel();
        movieDetailsPanel.setLayout(new BoxLayout(movieDetailsPanel, BoxLayout.Y_AXIS));

        // ImagePanel para ma center, AYAW HILABTI, please.
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageLabel = new JLabel();
        imagePanel.add(imageLabel);
        movieDetailsPanel.add(imagePanel);

        JLabel titleLabel = new JLabel("Select a movie to see details.");
        JLabel descriptionLabel = new JLabel("");
        JLabel priceLabel = new JLabel("");
        movieDetailsPanel.add(titleLabel);
        movieDetailsPanel.add(descriptionLabel);
        movieDetailsPanel.add(priceLabel);

        add(movieDetailsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton bookButton = new JButton("Book Movie");
        JButton logoutButton = new JButton("Log Out");

        bottomPanel.add(bookButton);
        bottomPanel.add(logoutButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Book button action listener
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = movieList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    // sending selected movie index to booking screen, ayaw ilisdi please thanks.
                    new BookingScreen(movieTitles[selectedIndex], ticketPrices[selectedIndex]).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                        MovieCatalogScreen.this,
                        "Please select a movie first!",
                        "No Movie Selected",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });

        
        logoutButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        dispose();
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setSize(400, 200);
        loginScreen.setLocationRelativeTo(null);
        loginScreen.setVisible(true);
             }
         });


        movieList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = movieList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    titleLabel.setText("Title: " + movieTitles[selectedIndex]);
                    descriptionLabel.setText("Description: " + movieDescriptions[selectedIndex]);
                    priceLabel.setText("Price: PhP " + ticketPrices[selectedIndex]);

                    String imagePath = "Posters//" 
                        + movieTitles[selectedIndex].replace(" ", "").replace(":", "_") + ".jpg";
                    loadImage(imagePath);
                }
            }
        });

        setVisible(true);
    }

    private void loadImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        if (imageIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("Error loading image at: " + imagePath);
        } else {
            Image image = imageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(image));
        }
    }

    public static void main(String[] args) {
        new MovieCatalogScreen();
    }
}
