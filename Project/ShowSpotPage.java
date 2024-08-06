import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowSpotPage {
    public static void main(String[] args) {
        JFrame showSpot = new JFrame("Welcome to ShowSpot");
        ImageIcon icon = new ImageIcon("D:/Movie_Management/Pictures/GlassesIcon.png");
        showSpot.setIconImage(icon.getImage());
        showSpot.setSize(800, 490);
        showSpot.setResizable(false);
        showSpot.setLayout(null);
        showSpot.setLocationRelativeTo(null);
        showSpot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ImageIcon imageIcon = new ImageIcon("D:/Movie_Management/Pictures/ShowSpotbg1.png");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(800,450);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(new ImageIcon("D:/Movie_Management/Pictures/ShowSpotbg1.png").getImage().getScaledInstance(800, 450, Image.SCALE_SMOOTH)));
        imageLabel.setBounds(0, 0, 800, 450);

        JButton proceedButton = new JButton("PROCEED");
        proceedButton.setBounds(180,350,100,50);
        proceedButton.setBackground(Color.darkGray);
        proceedButton.setForeground(Color.WHITE);
        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                showSpot.setVisible(false);
                MainPage.showDatabase();
            }
        });

       panel.add(imageLabel);
       showSpot.add(proceedButton);

       showSpot.add(panel);
       showSpot.setVisible(true);
    }
    
}
