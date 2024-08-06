import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.JTableHeader;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
public class SearchResultsPage {
    public static void searchForMovie(String enteredString)
    {
        //SQL QUERY
        try
        {
            String username = "root";
            String password = "Upanisha10$";
            String url = "jdbc:mysql://localhost:3306/ movieinformation";
            Connection con = DriverManager.getConnection(url, username, password);
            if(con != null)
            {
                String selectQuery = "SELECT title, genre, language, release_year FROM Movie WHERE title LIKE ?";
                PreparedStatement ps = con.prepareStatement(selectQuery);
                ps.setString(1,"%" + enteredString + "%");
                ResultSet rs = ps.executeQuery();
                if(!rs.isBeforeFirst())
                {
                    int result = JOptionPane.showConfirmDialog(null,"We don't have that! Would you like to add it?");
                    if(result == JOptionPane.YES_OPTION)
                    {
                        AddNewMovie.addMovie();
                    }
                }
                else
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

                    while(rs.next())
                        {
                            String name = rs.getString(1);
                            String genre = rs.getString(2);
                            String lang = rs.getString(3);
                            String year = rs.getString(4);
                            model.addRow(new Object[]{name,genre,lang,year});
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
                    searchFrame.setResizable(false);
                    searchFrame.setVisible(true);
                    searchFrame.setLocationRelativeTo(null);

                    searchFrame.add(scrollPane, BorderLayout.CENTER);

            
                }
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Oops! Something went wrong.","Warning", JOptionPane.ERROR_MESSAGE);
        }



        
    
    }
}
