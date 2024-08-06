import javax.swing.*;
import java.awt.*;
//import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AddNewMovie{

    public static void addMovie(){
        JFrame window = new JFrame("Add Movie");
        ImageIcon icon = new ImageIcon("D:/Movie_Management/Pictures/GlassesIcon.png");
        window.setIconImage(icon.getImage());
        
        JLabel label1 = new JLabel();
        label1.setText("ADD A MOVIE");
        label1.setBounds(440,20,500,70);
        Color headingColor = new Color(17,100,102);
        label1.setForeground(headingColor);
        Font myFont = new Font("Cambria",Font.BOLD,50);
        Font bt = new Font("Cambria",Font.BOLD,20);
        label1.setFont(myFont);
        //label1.setForeground(Color.MAGENTA);

        JLabel moviename = new JLabel();
        moviename.setText("Movie Name: ");
        moviename.setBounds(50,120,150,40);
        Font seriffont = new Font("Cambria",Font.PLAIN,20);
        moviename.setFont(seriffont);


        JLabel cast = new JLabel();
        cast.setText("Cast: ");
        cast.setBounds(640,120,100,40);
        cast.setFont(seriffont);

        JLabel genre = new JLabel();
        genre.setText("Genre: ");
        genre.setBounds(50,240,150,40);
        genre.setFont(seriffont);

        JLabel director = new JLabel();
        director.setText("Director: ");
        director.setBounds(50,320,150,40);
        director.setFont(seriffont);

        JLabel lang = new JLabel();
        lang.setText("Language: ");
        lang.setBounds(50,400,150,40);
        lang.setFont(seriffont);

        JLabel date = new JLabel();
        date.setText("Release Year: ");
        date.setBounds(640,320,150,40);
        date.setFont(seriffont);

        JLabel rating = new JLabel();
        rating.setText("Rating: ");
        rating.setBounds(640,400,150,40);
        rating.setFont(seriffont);


        JLabel synopsis = new JLabel();
        synopsis.setText("Synopsis: ");
        synopsis.setBounds(50,480,200,40);
        synopsis.setFont(seriffont);
        JTextField t,t2,t3,t4,t6,t7;
        JTextArea t5,t1;

        t = new JTextField(50);
        t.setBounds(200,120,330,40);
        t.setFont(seriffont);

        t1 = new JTextArea();
        t1.setBounds(760,120,340,170);
        t1.setFont(seriffont);

        t2 = new JTextField(50);
        t2.setBounds(200,240,330,40);
        t2.setFont(seriffont);

        t3 = new JTextField(50);
        t3.setBounds(200,320,330,40); 
        t3.setFont(seriffont);

        t4 = new JTextField(50);
        t4.setBounds(200,400,330,40);
        t4.setFont(seriffont);

        t6 = new JTextField(50);
        t6.setBounds(760,320,330,40);
        t6.setFont(seriffont);

        t7 = new JTextField(50);
        t7.setBounds(760,400,330,40);
        t7.setFont(seriffont);

        t5 = new JTextArea();
        t5.setBounds(200,480,900,140);
        t5.setLineWrap(true);
        t5.setWrapStyleWord(true);
        t5.setFont(seriffont);


        JButton clear = new JButton();
        clear.setText("CLEAR");
        clear.setBounds(320,650,200,50);
        clear.setFont(bt);
        //Color clearColor = new Color(50,80,86);
        clear.setBackground(Color.CYAN);

        JButton submit = new JButton();
        submit.setText("SUBMIT");
        submit.setBounds(780,650,200,50);
        submit.setFont(bt);
        submit.setBackground(Color.GREEN);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String movieTitle = t.getText();
                String movieGenre = t2.getText();
                String movieDirector = t3.getText();
                String movieLang = t4.getText();
                int movieRelease = Integer.parseInt(t6.getText());
                double movieRating = Double.parseDouble(t7.getText());
                String movieSynopsis = t5.getText();
                String textAreaContent = t1.getText();
                String[] movieCast = textAreaContent.split("\n");
                AddMovieDB.addInfoToDatabase(movieTitle,movieGenre,movieDirector,movieLang,movieRelease,movieSynopsis,movieRating,movieCast);
                JOptionPane.showMessageDialog(null, "Successfully Added!", "Add Movie", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the text fields?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                t.setText(null);
                t1.setText(null);
                t2.setText(null);
                t3.setText(null);
                t4.setText(null);
                t5.setText(null);
                t6.setText(null);
                t7.setText(null);
                //coField.setText(null);
                }
            }
        });
        

        window.add(t);
        window.add(t1);
        window.add(t2);
        window.add(t3);
        window.add(t4);
        window.add(t6);
        window.add(t5);
        window.add(t7);
        //window.add(tf);
        window.add(label1);
        window.add(moviename);
        window.add(cast);
        window.add(genre);
        window.add(director);
        window.add(lang);
        window.add(date);
        window.add(synopsis);
        window.add(rating);

        window.add(clear);
        window.add(submit);

        window.setResizable(false);
        window.setSize(1200,770);
        window.setLocationRelativeTo(null);
        window.setLayout(null);//No Layouts
        window.setVisible(true);
       // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
