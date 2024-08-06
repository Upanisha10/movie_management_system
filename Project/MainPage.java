import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.table.JTableHeader;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class MainPage {

    public static void showDatabase()
    {
        JFrame frame = new JFrame("SHOWSPOT");
        frame.setSize(1700, 800);
        frame.setResizable(false);
        frame.setLayout(null);
        ImageIcon img = new ImageIcon("D:/Movie_Management/Pictures/GlassesIcon.png");
        frame.setIconImage(img.getImage());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        JLabel label1 = new JLabel("FILTER");
        JLabel label2 = new JLabel("Genre");
        JLabel label3 = new JLabel("Language");
        JLabel label4 = new JLabel("ORDER BY");

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
                String queString = "SELECT title, genre, language, release_year FROM Movie";
                PreparedStatement pStatement = con.prepareStatement(queString);
                ResultSet rs = pStatement.executeQuery();
                while(rs.next())
                {
                    String title = rs.getString(1);
                    String genre = rs.getString(2);
                    String lang = rs.getString(3);
                    String release = rs.getString(4);
                    model.addRow(new Object[]{title, genre, lang, release});
                }
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Oops! Something went wrong.","Warning",JOptionPane.ERROR_MESSAGE);
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

        JTextField searchField = new JTextField("Search Here");
        JButton searchButton = new JButton("SEARCH");
        searchButton.setBackground(Color.gray);
        JButton button = new JButton("ADD NEW MOVIE");
        button.setBackground(Color.gray);


        JRadioButton radio1 = new JRadioButton("Romance");
        JRadioButton radio2 = new JRadioButton("Comedy");
        JRadioButton radio3 = new JRadioButton("Drama");
        JRadioButton radio4 = new JRadioButton("Horror");
        JRadioButton radio5 = new JRadioButton("Action");
        JRadioButton radio6 = new JRadioButton("Scifi");
        JRadioButton radio7 = new JRadioButton("English");
        JRadioButton radio8 = new JRadioButton("Telugu");
        JRadioButton radio9 = new JRadioButton("Hindi");
        JRadioButton radio10 = new JRadioButton("Release year");

        panel1.setBounds(0, 0, 300, 800);
        panel2.setBounds(450,150, 1000, 530);

        label1.setBounds(20, 70, 100, 100);
        label2.setBounds(30, 120, 100, 80);
        radio1.setBounds(45, 180, 150, 30);
        radio2.setBounds(45, 210, 150, 30);
        radio3.setBounds(45, 240, 150, 30);
        radio4.setBounds(45, 270, 150, 30);
        radio5.setBounds(45, 300, 150, 30);
        radio6.setBounds(45, 330, 150, 30);
        label3.setBounds(30, 380, 100, 80);
        radio7.setBounds(45, 440, 150, 30);
        radio8.setBounds(45, 470, 150, 30);
        radio9.setBounds(45, 500, 150, 30);
        label4.setBounds(20, 540, 140, 100);
        radio10.setBounds(45, 620, 150, 30);

        button.setBounds(1210,700,240,40);
        searchField.setBounds(450, 80,500 , 40);
        searchButton.setBounds(970, 80, 100, 40);

        panel1.setBackground(Color.gray);
        panel2.setBackground(Color.gray);
        ButtonGroup bg1 = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();
        ButtonGroup bg3 = new ButtonGroup();

        Font myFont1 = new Font("Arial", Font.BOLD, 24);
        Font myFont2 = new Font("Cambria", Font.BOLD, 16);

        label1.setFont(myFont1);
        label4.setFont(myFont1);
        label2.setFont(myFont2);
        label3.setFont(myFont2);

        bg1.add(radio1);
        bg1.add(radio2);
        bg1.add(radio3);
        bg1.add(radio4);
        bg1.add(radio5);
        bg1.add(radio6);
        bg2.add(radio7);
        bg2.add(radio8);
        bg2.add(radio9);
        bg3.add(radio10);

        panel1.setLayout(null);
        panel2.setLayout(new BorderLayout());

        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(radio1);
        panel1.add(radio2);
        panel1.add(radio3);
        panel1.add(radio4);
        panel1.add(radio5);
        panel1.add(radio6);
        panel1.add(radio7);
        panel1.add(radio8);
        panel1.add(radio9);
        panel1.add(label4);
        panel1.add(radio10);
        frame.add(searchField);
        frame.add(searchButton);
        frame.add(button);
        panel2.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel1);
        frame.add(panel2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Search functionality
        searchField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search Here")) {
                    searchField.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search Here");
                }
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                AddNewMovie.addMovie();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(searchField.getText() != "Search Here" && searchField.getText() != null)
                {
                    SearchResultsPage.searchForMovie(searchField.getText());
                    searchField.setText("Search Here");
                }
            }
        });

        ActionListener orderByActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // AbstractButton aButton = (AbstractButton) e.getSource();
                if(radio10.isSelected()) OrderByResultsPage.orderByResults();
            }
        };
        radio10.addActionListener(orderByActionListener);

        ActionListener filterByLang = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String selectedLang;
                if(radio7.isSelected()) selectedLang = "English";
                else if(radio8.isSelected()) selectedLang  = "Telugu";
                else if(radio9.isSelected()) selectedLang = "Hindi";
                else selectedLang  = null;
                FilterResultsPage.filterByLanguage(selectedLang);
            }
        };
        radio7.addActionListener(filterByLang);
        radio8.addActionListener(filterByLang);
        radio9.addActionListener(filterByLang);

        ActionListener filterByGenre = new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String selectedGenre;
                if(radio1.isSelected()) selectedGenre = "Romance";
                else if(radio2.isSelected()) selectedGenre = "Comedy";
                else if(radio3.isSelected()) selectedGenre = "Drama";
                else if(radio4.isSelected()) selectedGenre = "Horror";
                else if(radio5.isSelected()) selectedGenre = "Action";
                else if(radio6.isSelected()) selectedGenre = "Scifi";
                else selectedGenre = null;
                FilterResultsPage.filterByGenre(selectedGenre);
            }
        };
        radio1.addActionListener(filterByGenre);
        radio2.addActionListener(filterByGenre);
        radio3.addActionListener(filterByGenre);
        radio4.addActionListener(filterByGenre);
        radio5.addActionListener(filterByGenre);
        radio6.addActionListener(filterByGenre);
    }
    
}
    
