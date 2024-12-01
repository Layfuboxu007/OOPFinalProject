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
       private Movie selectedMovie; // Track the selected movie
   
       public MovieCatalogScreen() {
           setTitle("Movie Ticketing System - Movie Catalog");
           setSize(500, 400);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setLayout(new BorderLayout());
   
           // Movie one
           ImageIcon picOne = new ImageIcon("Avengers.jpg");
           Image originalImageOne = picOne.getImage();
           Image scaledImageOne = originalImageOne.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
           picOne = new ImageIcon(scaledImageOne);
           JButton pictureOneButton = new JButton(picOne);
           pictureOneButton.setBorderPainted(false);
           pictureOneButton.setContentAreaFilled(false);
           pictureOneButton.setFocusPainted(false);
           
           // Movie two
           ImageIcon picTwo = new ImageIcon("Inception.jpg");
           Image originalImageTwo = picTwo.getImage();
           Image scaledImageTwo = originalImageTwo.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
           picTwo = new ImageIcon(scaledImageTwo);
           JButton pictureTwoButton = new JButton(picTwo);
           pictureTwoButton.setBorderPainted(false);
           pictureTwoButton.setContentAreaFilled(false);
           pictureTwoButton.setFocusPainted(false);
           
           // Movie three
           ImageIcon picThree = new ImageIcon("Frozen.jpg");
           Image originalImageThree = picThree.getImage();
           Image scaledImageThree = originalImageThree.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
           picThree = new ImageIcon(scaledImageThree);
           JButton pictureThreeButton = new JButton(picThree);
           pictureThreeButton.setBorderPainted(false);
           pictureThreeButton.setContentAreaFilled(false);
           pictureThreeButton.setFocusPainted(false);
           
           // Add buttons to action listeners to select the movie
           pictureOneButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   selectedMovie = new Movie("Avengers", 200);
               }
           });
   
           pictureTwoButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   selectedMovie = new Movie("Inception", 250);
               }
           });
   
           pictureThreeButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   selectedMovie = new Movie("Frozen", 150);
               }
           });
   
           // Add the buttons to a JPanel
           JPanel imagePanel = new JPanel();
           imagePanel.add(pictureOneButton);
           imagePanel.add(pictureTwoButton);
           imagePanel.add(pictureThreeButton);
           add(imagePanel, BorderLayout.CENTER);
   
           // "Book Selected Movie" button
           JButton bookButton = new JButton("Book Selected Movie");
   bookButton.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           // Check if a movie has been selected
           if (selectedMovie != null) {
               new BookingScreen(selectedMovie).setVisible(true); // Navigate to booking screen
               dispose();
           } else {
               // Show an error if no movie is selected
               JOptionPane.showMessageDialog(MovieCatalogScreen.this,
                       "Please select a movie to book!", "Error", JOptionPane.ERROR_MESSAGE);
           }
       }
   });
           add(bookButton, BorderLayout.SOUTH);
           setVisible(true);
       }
   }
   