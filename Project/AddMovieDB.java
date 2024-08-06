import javax.swing.*;
//import java.awt.*;
import java.sql.*;
public class AddMovieDB {

    public static void addInfoToDatabase(String title, String genre, String director, String lang, int release,String synopsis, double rating, String[] cast)
    {
        try
        {
            String username = "root";
            String password = "Upanisha10$";
            String url = "jdbc:mysql://localhost:3306/ movieinformation";
            Connection con = DriverManager.getConnection(url, username, password);
            if(con != null)
            {
                String insertQuery = "INSERT INTO Movie(title,director, genre, language, release_year,synopsis,rating) VALUES  (?,?,?,?,?,?,?)";
                PreparedStatement insePreparedStatement = con.prepareStatement(insertQuery);
                insePreparedStatement.setString(1, title);
                insePreparedStatement.setString(2, director);
                insePreparedStatement.setString(3,genre);
                insePreparedStatement.setString(4,lang);
                insePreparedStatement.setInt(5,release);
                insePreparedStatement.setDouble(7, rating);
                insePreparedStatement.setString(6,synopsis);

                insePreparedStatement.executeUpdate();

                String movieIdQuery = "SELECT movie_id FROM Movie WHERE title = ?";
                PreparedStatement moviPreparedStatement = con.prepareStatement(movieIdQuery);
                moviPreparedStatement.setString(1, title);
                ResultSet movieIdResultSet = moviPreparedStatement.executeQuery();
                movieIdResultSet.next();
                String movieId = movieIdResultSet.getString(1);
                
                for(int i = 0; i < cast.length; i++)
                {
                    String retrieveQuery = "SELECT actor_id FROM Actor WHERE name = ?";
                    PreparedStatement rPreparedStatement = con.prepareStatement(retrieveQuery);
                    rPreparedStatement.setString(1,cast[i]);
                    ResultSet actorInfo = rPreparedStatement.executeQuery();
                    if(!actorInfo.next()) // No such actor is present in the Actor table
                    {
                        //if actor is not present in actor database,  then insert him/her into database
                        String actorInsertQuery = "INSERT INTO Actor(name) VALUES (?)";
                        PreparedStatement acStatement = con.prepareStatement(actorInsertQuery);
                        acStatement.setString(1, cast[i]);
                        acStatement.executeUpdate();

                        //now retreive his id
                        String actorIdQuery = "SELECT  actor_id FROM Actor WHERE name = ?";
                        PreparedStatement acPreparedStatement = con.prepareStatement(actorIdQuery);
                        acPreparedStatement.setString(1,cast[i]);
                        ResultSet rs = acPreparedStatement.executeQuery();
                        rs.next();
                        String actorId = rs.getString(1);

                        //now create a connection btw the actor and the movie through moviecast database
                        String movieCastQuery = "INSERT INTO MovieCast VALUES(?,?)";
                        PreparedStatement movieCStatement = con.prepareStatement(movieCastQuery);
                        movieCStatement.setInt(1, Integer.parseInt(movieId));
                        movieCStatement.setInt(2,Integer.parseInt(actorId));
                        movieCStatement.executeUpdate();
                    }
                    else // Actor is already present in the Actor table
                    {
                        String actorId = actorInfo.getString(1);
                        String movieCastQuery = "INSERT INTO MovieCast VALUES(?,?)";
                        PreparedStatement movieCStatement = con.prepareStatement(movieCastQuery);
                        movieCStatement.setInt(1, Integer.parseInt(movieId));
                        movieCStatement.setInt(2,Integer.parseInt(actorId));
                        movieCStatement.executeUpdate();
                    }
                }


            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Oops! Something went wrong!", "Warning", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        }
    }
    
}
