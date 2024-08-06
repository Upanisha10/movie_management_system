import java.sql.*;
import java.awt.*;
import javax.swing.*;
public class ShowInformation
{
    public static void showInfo(String movieSelected) {
        try
        {
            String username = "root";
            String password = "Upanisha10$";
            String url = "jdbc:mysql://localhost:3306/ movieinformation";
            Connection con = DriverManager.getConnection(url, username, password);
            if(con != null)
            {
                String movieQuery = "SELECT Movie.movie_id, Movie.genre, Movie.language, Movie.director, Movie.synopsis, Movie.rating,Movie.title,Movie.release_year FROM Movie WHERE title = ?";
                PreparedStatement ps = con.prepareStatement(movieQuery);
                ps.setString(1,movieSelected);

                ResultSet rs = ps.executeQuery();
                if(!rs.next())
                {
                    System.out.println("Oops! We don't have that");
                }
                else
                {
                    //insert all the values of result set into table
                    //
                    JFrame showFrame = new JFrame(rs.getString(7));
                    ImageIcon icon = new ImageIcon("D:/Movie_Management/Pictures/GlassesIcon.png");
                    showFrame.setIconImage(icon.getImage());


                    JLabel movieLabel = new JLabel(rs.getString(7) + " (" + rs.getString(8) + ")");
                    movieLabel.setFont(new Font("Cambria",Font.BOLD, 40));
                    Color headingColor = new Color(17,100,102);
                    movieLabel.setForeground(headingColor);
                    movieLabel.setBounds(50,40,900,100);
   
                    Font labelFont = new Font("Cambria",Font.BOLD, 20);

                    Font valueFont = new Font("Cambria",Font.PLAIN,18);
                    JLabel directorLabel = new JLabel("Director : ");
                    directorLabel.setForeground(headingColor);
                    directorLabel.setFont(labelFont);
                    directorLabel.setBounds(50,100,130,100);
    
                    JLabel directorNamLabel = new JLabel(rs.getString(4));
                    directorNamLabel.setFont(valueFont);
                    directorNamLabel.setBounds(180,100,400,100);
    
                    JLabel genreLabel = new JLabel("Genre : ");
                    genreLabel.setFont(labelFont);
                    genreLabel.setForeground(headingColor);
                    genreLabel.setBounds(50,170,130,100);
    
                    JLabel genreNamLabel = new JLabel(rs.getString(2));
                    genreNamLabel.setFont(valueFont);
                    genreNamLabel.setBounds(180,170,400,100);
    
                    JLabel languageLabel = new JLabel("Language : ");
                    languageLabel.setFont(labelFont);
                    languageLabel.setForeground(headingColor);
                    languageLabel.setBounds(50,230,130,100);
    
                    JLabel languageNamLabel = new JLabel(rs.getString(3));
                    languageNamLabel.setFont(valueFont);
                    languageNamLabel.setBounds(180,230,400,100);
    
                    JLabel synopsisLabel = new JLabel("Synopsis : ");
                    synopsisLabel.setFont(labelFont);
                    synopsisLabel.setForeground(headingColor);
                    synopsisLabel.setBounds(50,300,130,100);
    
                    JTextArea synopsisArea = new JTextArea(rs.getString(5));
                    synopsisArea.setLineWrap(true);
                    synopsisArea.setWrapStyleWord(true);
                    synopsisArea.setFont(valueFont);
                    synopsisArea.setBounds(180,340,600,150);
                    synopsisArea.setEditable(false);
    
                    JLabel castLabel = new JLabel("Cast : ");
                    castLabel.setFont(labelFont);
                    castLabel.setForeground(headingColor);
                    castLabel.setBounds(500,100,130,100);
    
                    JTextArea castArea = new JTextArea();
                    castArea.setFont(valueFont);
                    castArea.setBounds(630,140,300,120);
    
                    String castQuery = "SELECT Actor.name FROM Movie JOIN MovieCast on Movie.movie_id = MovieCast.movie_id JOIN Actor on MovieCast.actor_id = Actor.actor_id where Movie.movie_id = ?";
                    PreparedStatement pStatement = con.prepareStatement(castQuery);
                    pStatement.setString(1,rs.getString(1));
                    ResultSet rSet = pStatement.executeQuery();
                    while(rSet.next())
                    {
                        String actor = rSet.getString(1);
                        castArea.append(actor);
                        castArea.append("\n");
    
                    }
                    castArea.setEditable(false);
    
                    JLabel ratingLabel = new JLabel("Rating : ");
                    ratingLabel.setFont(labelFont);
                    ratingLabel.setForeground(headingColor);
                    ratingLabel.setBounds(500,230,130,100);
    
                    JLabel ratingValue = new JLabel(rs.getString(6));
                    ratingValue.setFont(valueFont);
                    ratingValue.setBounds(630,230,100,100);
    
    
    
                    showFrame.setVisible(true);
                    showFrame.setSize(1000,600);
                    showFrame.setLayout(null);
                    showFrame.setResizable(false);
                    showFrame.setLocationRelativeTo(null);
                
                    //showFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    showFrame.add(movieLabel);
                    showFrame.add(directorLabel);
                    showFrame.add(directorNamLabel);
                    showFrame.add(genreLabel);
                    showFrame.add(genreNamLabel);
                    showFrame.add(languageLabel);
                    showFrame.add(languageNamLabel);
                    showFrame.add(synopsisLabel);
                    showFrame.add(synopsisArea);
                    showFrame.add(castLabel);
                    showFrame.add(castArea);
                    showFrame.add(ratingLabel);
                    showFrame.add(ratingValue);
    

                }

               
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Oops! Something went wrong.", "Warning", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
} 