package background; 

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class sign_screen {
	
	public int PK;
	public JFrame signupFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sign_screen window = new sign_screen();
					window.signupFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sign_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		signupFrame = new JFrame("ȸ������");
		
    	ImagePanel signupPanel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\signimg2.png").getImage());
		ImageIcon confirmImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\confirm.png");
		ImageIcon cancleImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\cancle.png");
		ImageIcon registerImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\register.png");
    	
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setBounds(510, 350, 900, 700);
        signupFrame.setUndecorated(true);
        
        signupFrame.getContentPane().add(signupPanel);
        signupPanel.setLayout(null);

        JLabel idLabel = new JLabel("���̵�");
        idLabel.setFont(new Font("�������", Font.BOLD, 32));
        idLabel.setBounds(80, 50, 117, 46);
        signupPanel.add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setFont(new Font("���� ���", Font.BOLD, 40));
        idTextField.setBounds(293, 50, 376, 46);
        signupPanel.add(idTextField);

        JButton checkIdButton = new JButton(confirmImg);
        checkIdButton.setBounds(697, 50, 167, 46);
        signupPanel.add(checkIdButton);
        checkIdButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				if (id.isEmpty()) {
		            System.out.println("���̵� �Է����ּ���.");
		            return;
		        }
				
				boolean duplicate = false;
				try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@172.16.117.226:1521:xe";
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
				    System.out.println("�ߺ��� ���̵� ����.");
				} else {
				    System.out.println("�ߺ��� ���̵� ����.");
				}
				
			}catch (ClassNotFoundException f) {
					f.printStackTrace();
			}catch (SQLException f) {
					f.printStackTrace();
			}
				
		}
        	
       });

        JLabel passwordLabel = new JLabel("��й�ȣ");
        passwordLabel.setFont(new Font("�������", Font.BOLD, 32));
        passwordLabel.setBounds(80, 129, 140, 46);
        signupPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("���� ���", Font.BOLD, 32));
        passwordField.setBounds(293, 129, 376, 46);
        signupPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("��й�ȣ Ȯ��");
        confirmPasswordLabel.setFont(new Font("�������", Font.BOLD, 32));
        confirmPasswordLabel.setBounds(80, 209, 213, 46);
        signupPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("���� ���", Font.BOLD, 32));
        confirmPasswordField.setBounds(293, 209, 376, 46);
        signupPanel.add(confirmPasswordField);

        JLabel nicknameLabel = new JLabel("�г���");
        nicknameLabel.setFont(new Font("�������", Font.BOLD, 32));
        nicknameLabel.setBounds(80, 450, 100, 46);
        signupPanel.add(nicknameLabel);

        JTextField nicknameTextField = new JTextField();
        nicknameTextField.setFont(new Font("���� ���", Font.BOLD, 32));
        nicknameTextField.setBounds(293, 450, 376, 46);
        signupPanel.add(nicknameTextField);
        
        JButton checkNicknameButton = new JButton(confirmImg);
        checkNicknameButton.setBounds(697, 450, 167, 46);
        signupPanel.add(checkNicknameButton);
        checkNicknameButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String nickname = nicknameTextField.getText();
				if (nickname.isEmpty()) {
		            System.out.println("���̵� �Է����ּ���.");
		            return; 
		        }
				
				boolean duplicate = false;
				try {
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@172.16.117.226:1521:xe";
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
				    System.out.println("�ߺ��� �г����� ����.");
				} else {
				    System.out.println("�ߺ��� �г��� ����.");
				}
			}catch (ClassNotFoundException f) {
					f.printStackTrace();
			}catch (SQLException f) {
					f.printStackTrace();
			}
				
		}
        	
       });

        JButton signupButton = new JButton(registerImg);
        signupButton.setBounds(80, 566, 260, 70);
        signupPanel.add(signupButton);
        signupButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			    String id = idTextField.getText();
			    String nickname = nicknameTextField.getText();
			    String pass = new String(passwordField.getPassword());
			    String passconfirm = new String(confirmPasswordField.getPassword());
			    boolean isRegistered = false;

			    try {
			        Class.forName("oracle.jdbc.OracleDriver");
			        String url = "jdbc:oracle:thin:@172.16.117.226:1521:xe";
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
			                System.out.println("���̵� �̹� �����մϴ�.");
			                break;
			            }else if (nickname.equals(login_nickname)) {
			                isRegistered = true;
			                System.out.println("�г����� �̹� �����մϴ�.");
			                break;
			            }else if (id.isEmpty()) {
			            System.out.println("���̵� �Է����ּ���.");
				            return;
				        }else if (nickname.isEmpty()) {
				            System.out.println("�г����� �Է����ּ���.");
				            return; 
				        }else if (pass.isEmpty()) {
				        	System.out.println("��й�ȣ�� �Է����ּ���.");
				        	return;
				        }else if (passconfirm.isEmpty()) {
				        	System.out.println("��й�ȣ Ȯ���� ���� ��й�ȣ�� �����ּ���");
				        	return;
				        }else if (!pass.equals(passconfirm)) {
				            isRegistered = true;
				            System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				            break;
				        }
			        }
			        

			        

			        if (!isRegistered) {
			        	System.out.println("ȸ�����Կ� �����ϼ̽��ϴ�.");
			        	Account singlogin = new Account();
			        	singlogin.setLogin_id(id);
			        	singlogin.setLogin_password(pass);
			        	singlogin.setLogin_name(nickname);
						singlogin.join_membership();
						signupFrame.setVisible(false);
			        }

			    } catch (ClassNotFoundException f) {
			        f.printStackTrace();
			    } catch (SQLException f) {
			        f.printStackTrace();
			    }
		
			}
       });
        
        
        JButton cancleButton = new JButton(cancleImg);
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
}
