package background;  

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class login_screen {
 
    private JFrame frmAutoPockmon;
    private JTextField textField;
    private JPasswordField passwordField;
    private JPanel loginPanel;
    private JPanel mainloginPanel;
    private JPanel PocketImg;
    private JPanel titleImg;
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login_screen window = new login_screen();
                    window.frmAutoPockmon.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public login_screen() {
        initialize();
    }

    private void initialize() {
        frmAutoPockmon = new JFrame();
        frmAutoPockmon.setTitle("Auto Pockmon\r\n");
        frmAutoPockmon.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmAutoPockmon.setUndecorated(true);

        loginPanel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
        mainloginPanel= new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\main2.png").getImage());
        PocketImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\Pocketmon2.png").getImage());
        titleImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\Title2.png").getImage());
        ImageIcon startImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\start.png");
        ImageIcon signupImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\signup.png");
        ImageIcon exitImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit3.png");
       
        titleImg.setLocation(428, 10);
        PocketImg.setLocation(438, 170);
        mainloginPanel.setLocation(610, 540);
        
        loginPanel.setLayout(null);
        mainloginPanel.setLayout(null);

        loginPanel.setPreferredSize(loginPanel.getPreferredSize());
        
        loginPanel.add(mainloginPanel);
        loginPanel.add(titleImg);
        loginPanel.add(PocketImg);
        
        JButton signupbutton = new JButton(signupImg);
        signupbutton.setSize(293, 102);
        signupbutton.setLocation(372, 310);
        signupbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sign_screen sc = new sign_screen();
			}
        	
        });
        
        mainloginPanel.add(signupbutton);
        JButton exitbutton = new JButton(exitImg);
        exitbutton.setForeground(new Color(30, 144, 255));
        exitbutton.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
        	
        });
        JButton startbutton = new JButton(startImg);
        startbutton.setSize(293, 102);
        startbutton.setLocation(43, 310);
        startbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				loginstart();
				
			}
        	
        });
        mainloginPanel.add(startbutton);
        exitbutton.setSize(50, 50);
        exitbutton.setLocation(1860, 10);
        exitbutton.setBorderPainted(false);
        loginPanel.add(exitbutton);
        
        JLabel lblNewLabel = new JLabel("¾ÆÀÌµð");
        lblNewLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 32));
        lblNewLabel.setBounds(88, 90, 149, 69);
        mainloginPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ºñ¹Ð¹øÈ£");
        lblNewLabel_1.setFont(new Font("³ª´®°íµñ", Font.BOLD, 32));
        lblNewLabel_1.setBounds(88, 187, 149, 84);
        mainloginPanel.add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 30));
        textField.setBounds(249, 95, 385, 55);
        mainloginPanel.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 30));
        passwordField.setBounds(249, 204, 385, 55);
        mainloginPanel.add(passwordField);
        
        frmAutoPockmon.getContentPane().add(loginPanel);
        frmAutoPockmon.pack();
           
    }
    
    private void loginstart() {
        String id = textField.getText();
        String pass = new String(passwordField.getPassword());
       
        Account login_check = new Account();
        login_check.setLogin_id(id);
        login_check.setLogin_password(pass);
        if(login_check.login() == true) {
        	main_screen ms = new main_screen();
        	frmAutoPockmon.dispose();
        }
    }
}