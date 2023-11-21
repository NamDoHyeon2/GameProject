package Pokemon_Record_Area; 

import Auto_pokemon.*;

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
 
	public JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login_screen window = new login_screen();
                    window.frame.setVisible(true);
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
        frame = new JFrame();
        frame.setTitle("Auto Pocket");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\login_background.png").getImage());
        ImagePanel mainloginPanel= new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\main2.png").getImage());
        ImagePanel PocketImg = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\Pocketmon2.png").getImage());
        ImagePanel titleImg = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\Title2.png").getImage());
        ImageIcon startImg = new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\start.png");
        ImageIcon signupImg = new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\signup.png");
        ImageIcon exitImg = new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\exit3.png");
       
        titleImg.setLocation(428, 10);
        PocketImg.setLocation(438, 170);
        mainloginPanel.setLocation(610, 540);
        
        loginPanel.setLayout(null);
        mainloginPanel.setLayout(null);

        loginPanel.setPreferredSize(loginPanel.getPreferredSize());
        
        loginPanel.add(mainloginPanel);
        loginPanel.add(titleImg);
        loginPanel.add(PocketImg);
        
        //회원가입 창열기
        JButton signupbutton = new JButton(signupImg);
        signupbutton.setSize(293, 102);
        signupbutton.setLocation(372, 310);
        signupbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openSignupWindow();
				
			}
        	
        });
        
        //나가기
        mainloginPanel.add(signupbutton);
        JButton exitbutton = new JButton(exitImg);
        exitbutton.setForeground(new Color(30, 144, 255));
        exitbutton.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
        	
        });
        //로그인 하기
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
        
        JLabel lblNewLabel = new JLabel("아이디");
        lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        lblNewLabel.setBounds(88, 90, 149, 69);
        mainloginPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("비밀번호");
        lblNewLabel_1.setFont(new Font("나눔고딕", Font.BOLD, 32));
        lblNewLabel_1.setBounds(88, 187, 149, 84);
        mainloginPanel.add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        textField.setBounds(249, 95, 385, 55);
        mainloginPanel.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        passwordField.setBounds(249, 204, 385, 55);
        mainloginPanel.add(passwordField);
        
        frame.getContentPane().add(loginPanel);
        frame.pack();
           
    }
    private void openSignupWindow() {
    	
    	JFrame signupFrame = new JFrame("회원가입");
		
		ImagePanel signupPanel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\signimg2.png").getImage());
		ImageIcon confirmImg = new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\confirm.png");
    	
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setBounds(510, 350, 900, 700);
        signupFrame.setUndecorated(true);
        
        signupFrame.getContentPane().add(signupPanel);
        signupPanel.setLayout(null);

        JLabel idLabel = new JLabel("아이디");
        idLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        idLabel.setBounds(80, 50, 117, 46);
        signupPanel.add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        idTextField.setBounds(293, 50, 376, 46);
        signupPanel.add(idTextField);
        
        //아이디 중복 확인 버튼
        JButton checkIdButton = new JButton(confirmImg);
        checkIdButton.setBounds(697, 50, 167, 46);
        signupPanel.add(checkIdButton);
        checkIdButton.addActionListener(new ActionListener(){
			@Override
			//아이디 중복 확인 버튼 클릭 메소드
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				boolean duplicate = false;
				try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "JUNG";
				String password = "1234";

				Connection connection = DriverManager.getConnection(url, user, password);
				String sql = "SELECT * FROM USER_INFO WHERE LOGIN_ID = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, id);
				
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
				    String value = resultSet.getString("LOGIN_ID");
				    if (id.equals(value)) {
				        duplicate = true;
				        break; 
				    }
				}

				if (duplicate) {
				    System.out.println("중복된 아이디가 있음.");
				} else {
				    System.out.println("중복된 아이디 없음.");
				}
			}catch (ClassNotFoundException f) {
					f.printStackTrace();
			}catch (SQLException f) {
					f.printStackTrace();
			}
				
		}
        	
       });

        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        passwordLabel.setBounds(80, 129, 140, 46);
        signupPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        passwordField.setBounds(293, 129, 376, 46);
        signupPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인");
        confirmPasswordLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        confirmPasswordLabel.setBounds(80, 209, 213, 46);
        signupPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        confirmPasswordField.setBounds(293, 209, 376, 46);
        signupPanel.add(confirmPasswordField);

        JLabel nicknameLabel = new JLabel("닉네임");
        nicknameLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        nicknameLabel.setBounds(80, 450, 100, 46);
        signupPanel.add(nicknameLabel);

        JTextField nicknameTextField = new JTextField();
        nicknameTextField.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        nicknameTextField.setBounds(293, 450, 376, 46);
        signupPanel.add(nicknameTextField);
        
        //닉네임 중복확인 버튼
        JButton checkNicknameButton = new JButton(confirmImg);
        checkNicknameButton.setBounds(697, 450, 167, 46);
        signupPanel.add(checkNicknameButton);
        checkNicknameButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String nickname = nicknameTextField.getText();
				boolean duplicate = false;
				try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "JUNG";
				String password = "1234";

				Connection connection = DriverManager.getConnection(url, user, password);
				String sql = "SELECT * FROM USER_INFO WHERE USER_NAME = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, nickname);
				
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
				    String value = resultSet.getString("USER_NAME");
				    if (nickname.equals(value)) {
				        duplicate = true;
				        break; 
				    }
				}

				if (duplicate) {
				    System.out.println("중복된 닉네임이 있음.");
				} else {
				    System.out.println("중복된 닉네임 없음.");
				}
			}catch (ClassNotFoundException f) {
					f.printStackTrace();
			}catch (SQLException f) {
					f.printStackTrace();
			}
				
		}
        	
       });
        
        //가입
        JButton signupButton = new JButton("가입");
        signupButton.setBounds(80, 566, 260, 70);
        signupPanel.add(signupButton);
        signupButton.addActionListener(new ActionListener(){
			@Override
			//가입 버튼 이벤트
			public void actionPerformed(ActionEvent e) {
			    String id = idTextField.getText();
			    String nickname = nicknameTextField.getText();
			    String pass = new String(passwordField.getPassword());
			    String passconfirm = new String(confirmPasswordField.getPassword());
			    boolean isRegistered = false;

			    try {
			        Class.forName("oracle.jdbc.OracleDriver");
			        String url = "jdbc:oracle:thin:@localhost:1521:xe";
			        String user = "JUNG";
			        String password = "1234";

			        Connection connection = DriverManager.getConnection(url, user, password);
			        String sql = "SELECT LOGIN_ID, USER_NAME FROM USER_INFO";
			        PreparedStatement statement = connection.prepareStatement(sql);
			        ResultSet resultSet = statement.executeQuery();

			        while (resultSet.next()) {
			            String login_id = resultSet.getString("LOGIN_ID");
			            String login_nickname = resultSet.getString("USER_NAME");

			            if (id.equals(login_id)) {
			                isRegistered = true;
			                System.out.println("아이디가 이미 존재합니다.");
			                break;
			            }
			            
			            if (nickname.equals(login_nickname)) {
			                isRegistered = true;
			                System.out.println("닉네임이 이미 존재합니다.");
			                break;
			            }
			        }

			        if (!pass.equals(passconfirm)) {
			            isRegistered = true;
			            System.out.println("비밀번호가 일치하지 않습니다.");
			        }

			        if (!isRegistered) {
			        	System.out.println("회원가입에 성공하셨습니다.");
			        	Account ac = new Account();
			        	ac.setLogin_id(id);
			        	ac.setLogin_password(pass);
			        	ac.setLogin_name(nickname);
						ac.join_membership();
						signupFrame.setVisible(false);
			        }

			    } catch (ClassNotFoundException f) {
			        f.printStackTrace();
			    } catch (SQLException f) {
			        f.printStackTrace();
			    }
		
			}
       });
        
        
        JButton cancleButton = new JButton("취소");
        cancleButton.setBounds(554, 566, 260, 70);
        signupPanel.add(cancleButton);
        cancleButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				signupFrame.setVisible(false);
				
			}
       
        });
        signupFrame.setVisible(true);
	}
    
    private void loginstart() {
        String id = textField.getText();
        String pass = new String(passwordField.getPassword());
        boolean isstart = false;

        Account login_check = new Account();
        login_check.setLogin_id(id);
        login_check.setLogin_password(pass);
        login_check.login();
        
        frame.setVisible(false);
		main_screen Main_Screen = new main_screen();
		Main_Screen.frmAutoPockmon.setVisible(true);

    }
}