import javax.swing.*;
import java.awt.Font;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.JTableHeader;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class FilterResultsPage {
    public static void filterByGenre(String selectedGenre)
    {
        String query = "SELECT title, genre, language, release_year FROM Movie WHERE genre = ?";
        showResults(query,selectedGenre);
    }
    public static void filterByLanguage(String selectedLanguage)
    {
        String query = "SELECT title, genre, language, release_year from Movie WHERE language = ?";
        showResults(query,selectedLanguage);
    }
    public static void showResults(String query, String selected)
    {
        JFrame searchFrame = new JFrame("Search Results");
        ImageIcon img = new ImageIcon("D:/Movie_Management/Pictures/GlassesIcon.png");
        searchFrame.setIconImage(img.getImage());
        
        Font tableFont = new Font("Cambria",Font.PLAIN,18);
        Font headingFont = new Font("Cambria",Font.BOLD,14);

        DefaultTableModel model = new DefaultTableModel(new String[]{"MOVIE NAME", "GENRE", "LANGUAGE", "RELEASE YEAR"}, 0);
        JTable table = new JTable(model);
        table.setFont(tableFont);
        int rowHeight = 50;
        table.setRowHeight(rowHeight);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(headingFont);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(1000, 800));

        table.getColumnModel().getColumn(0).setPreferredWidth(2000);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(300);

        table.setDefaultEditor(Object.class, null);
        
        try 
        {
            String username = "root";
            String password = "Upanisha10$";
            String url = "jdbc:mysql://localhost:3306/ movieinformation";
            Connection con = DriverManager.getConnection(url, username, password);
            if(con != null)
            {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1,selected);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    String movie = rs.getString(1);
                    String genre = rs.getString(2);
                    String lang = rs.getString(3);
                    String year = rs.getString(4);
                    model.addRow(new Object[]{movie,genre,lang,year});

                }
            }
        } 
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Oops. Something went wrong","Warning", JOptionPane.ERROR_MESSAGE);
        }

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evnt)
            {
                if(!evnt.getValueIsAdjusting())
                {
                    int selectedRow = table.getSelectedRow();
                    if(selectedRow != -1)
                    {
                        Object movie = table.getValueAt(selectedRow, 0);
                        String movieSelected = movie.toString();
                        ShowInformation.showInfo(movieSelected);
                    }
                }
            }
        });

        searchFrame.setSize(1200,600);
        searchFrame.setLayout(new BorderLayout());
        searchFrame.setVisible(true);
        searchFrame.setResizable(false);
        searchFrame.setLocationRelativeTo(null);

        searchFrame.add(scrollPane, BorderLayout.CENTER);
    }
    
}
