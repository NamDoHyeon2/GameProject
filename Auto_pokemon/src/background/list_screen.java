package background;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;

public class list_screen {

	private JLabel numpage;
	private JFrame frame;
	private JPanel textpanel_1;
	private JPanel textpanel_2;
	private JPanel textpanel_3;
	private JPanel textpanel_4;
	private JPanel textpanel_5;
	private JPanel textpanel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					list_screen window = new list_screen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public list_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    frame = new JFrame();
	    frame.setTitle("Auto Pockmon");
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    frame.setUndecorated(true);
	    
	    ImagePanel listImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
	    ImagePanel caterpie = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\01_Ä³ÅÍÇÇ.png").getImage());
	    ImagePanel Weedle = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\02_»ÔÃæÀÌ.png").getImage());
	    ImagePanel gugu = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\03_±¸±¸.png").getImage());
	    ImagePanel Rattata = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\04_²¿·¿.png").getImage());
	    ImagePanel Spearow = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\05_±úºñÂü.png").getImage());
	    ImagePanel Avo = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\06_¾Æº¸.png").getImage());
	    ImageIcon nextImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\nextBtn2.jpg");
	    ImageIcon previousImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\previous.jpg");
	    
	    listImg.setLayout(null);
	    listImg.setPreferredSize(listImg.getPreferredSize());
	    
	    frame.getContentPane().add(listImg, BorderLayout.SOUTH);
	    
	    JPanel listpanel = new JPanel();
	    listpanel.setBackground(new Color(255, 255, 255));
	    listpanel.setBounds(100, 50, 1720, 980);
	    
	    listImg.add(listpanel);
	    listpanel.setLayout(null);
	    
	    JPanel pocketpanel_1 = new JPanel();
	    pocketpanel_1.setBounds(42, 32, 751, 266);
	    pocketpanel_1.setLayout(null);
	    listpanel.add(pocketpanel_1);
	    
	    textpanel_1 = new JPanel();
	    textpanel_1.setLayout(null);
	    textpanel_1.setBackground(new Color(207, 189, 175));
	    textpanel_1.setBounds(292, 5, 453, 256);
	    pocketpanel_1.add(textpanel_1);
	    pocketpanel_1.add(caterpie);
	    caterpie.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	    caterpie.setLayout(null);
	    caterpie.setBounds(12, 5, 271, 256);
	    
	    JPanel pocketpanel_2 = new JPanel();
	    pocketpanel_2.setLayout(null);
	    pocketpanel_2.setBounds(42, 321, 751, 266);
	    listpanel.add(pocketpanel_2);
	    
	    textpanel_2 = new JPanel();
	    textpanel_2.setLayout(null);
	    textpanel_2.setBackground(new Color(207, 189, 175));
	    textpanel_2.setBounds(292, 5, 453, 256);
	    pocketpanel_2.add(textpanel_2);
	    pocketpanel_2.add(Weedle);
	    Weedle.setBounds(12, 5, 271, 256);
	    Weedle.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	    Weedle.setLayout(null);
	    
	    JPanel pocketpanel_3 = new JPanel();
	    pocketpanel_3.setLayout(null);
	    pocketpanel_3.setBounds(42, 615, 751, 266);
	    listpanel.add(pocketpanel_3);
	    
	    textpanel_3 = new JPanel();
	    textpanel_3.setLayout(null);
	    textpanel_3.setBackground(new Color(207, 189, 175));
	    textpanel_3.setBounds(292, 5, 453, 256);
	    pocketpanel_3.add(textpanel_3);
	    pocketpanel_3.add(gugu);
	    gugu.setBounds(9, 5, 271, 256);
	    gugu.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	    gugu.setLayout(null);
	    
	    JPanel pocketpanel_4 = new JPanel();
	    pocketpanel_4.setLayout(null);
	    pocketpanel_4.setBounds(922, 32, 751, 266);
	    listpanel.add(pocketpanel_4);
	    
	    textpanel_4 = new JPanel();
	    textpanel_4.setLayout(null);
	    textpanel_4.setBackground(new Color(207, 189, 175));
	    textpanel_4.setBounds(292, 5, 453, 256);
	    pocketpanel_4.add(textpanel_4);
	    pocketpanel_4.add(Rattata);
	    Rattata.setBounds(9, 5, 271, 256);
	    Rattata.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	    Rattata.setLayout(null);
	    
	    JPanel pocketpanel_5 = new JPanel();
	    pocketpanel_5.setLayout(null);
	    pocketpanel_5.setBounds(922, 321, 751, 266);
	    listpanel.add(pocketpanel_5);
	    
	    textpanel_5 = new JPanel();
	    textpanel_5.setLayout(null);
	    textpanel_5.setBackground(new Color(207, 189, 175));
	    textpanel_5.setBounds(292, 5, 453, 256);
	    pocketpanel_5.add(textpanel_5);
	    pocketpanel_5.add(Spearow);
	    Spearow.setBounds(9, 5, 271, 256);
	    Spearow.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	    Spearow.setLayout(null);
	    
	    JPanel pocketpanel_6 = new JPanel();
	    pocketpanel_6.setLayout(null);
	    pocketpanel_6.setBounds(922, 615, 751, 266);
	    listpanel.add(pocketpanel_6);
	    
	    textpanel_6 = new JPanel();
	    textpanel_6.setLayout(null);
	    textpanel_6.setBackground(new Color(207, 189, 175));
	    textpanel_6.setBounds(292, 5, 453, 256);
	    pocketpanel_6.add(textpanel_6);
	    pocketpanel_6.add(Avo);
	    Avo.setBounds(9, 5, 271, 256);
	    Avo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	    Avo.setLayout(null);
	    
	    JButton nextBtn_1 = new JButton(nextImg);
	    nextBtn_1.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_1.setBounds(922, 899, 60, 60);
	    listpanel.add(nextBtn_1);
	    
	    JButton previousBtn_1 = new JButton(previousImg);
	    previousBtn_1.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_1.setBounds(733, 899, 60, 60);
	    listpanel.add(previousBtn_1);
	    previousBtn_1.setVisible(false);
	  
	    numpage = new JLabel("1/10");
	    numpage.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
	    numpage.setBounds(810, 899, 92, 55);
	    listpanel.add(numpage);
	    nextBtn_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textpanel_1.setVisible(false);
				textpanel_2.setVisible(false);
				textpanel_3.setVisible(false);
				textpanel_4.setVisible(false);
				textpanel_5.setVisible(false);
				textpanel_6.setVisible(false);
				caterpie.setVisible(false);
				Weedle.setVisible(false);
				gugu.setVisible(false);
				Rattata.setVisible(false);
				Spearow.setVisible(false);
				Avo.setVisible(false);
				
				numpage.setText("2/10");
				
				previousBtn_1.setVisible(true);
			    
			}
	    	
	    });
	    
	    input_data();
	    
	    frame.pack();
	}
	
	public void input_data() {
	    try {
	        int i = 1;
	        Class.forName("oracle.jdbc.OracleDriver");
	        String url = "jdbc:oracle:thin:@119.195.135.42:1521:xe";
	        String user = "JUNG";
	        String password = "1234";

	        Connection con = DriverManager.getConnection(url, user, password);
	        String sql = "SELECT OBJECT_ID, OBJECT_NAME, OBJECT_TYPE, GRADE, DAMAGE, STAMINA FROM OBJECT_LEVEL1";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        ResultSet resultSet = pstmt.executeQuery();

	        while (resultSet.next()) {
	            JTextArea text_i = new JTextArea();
	            String name = resultSet.getString("OBJECT_NAME");
	            String type = resultSet.getString("OBJECT_TYPE");
	            int pk = resultSet.getInt("OBJECT_ID");
	            int grade = resultSet.getInt("GRADE");
	            int damage = resultSet.getInt("DAMAGE");
	            int stamina = resultSet.getInt("STAMINA");

	            text_i.setText("ÀÌ¸§ : " + name + "\r\n\r\nÅ¸ÀÔ : " + type + "\r\n\r\nµî±Þ : " + grade + "\r\n\r\n°ø°Ý·Â : " + damage + "\r\n\r\nÃ¼·Â : "  + stamina);
	            text_i.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	            text_i.setEditable(false);
	            text_i.setBackground(new Color(207, 189, 175));
	            text_i.setBounds(0, 0, 447, 256);

	            if (pk == 1) {
	                textpanel_1.add(text_i);
	            } else if (pk == 2) {
	                textpanel_2.add(text_i);
	            } else if (pk == 3 ) {
	            	textpanel_3.add(text_i);
	            } else if (pk == 4) {
	            	textpanel_4.add(text_i);
	            } else if (pk == 5) {
	            	textpanel_5.add(text_i);
	            } else if (pk == 6) {
	            	textpanel_6.add(text_i);
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

