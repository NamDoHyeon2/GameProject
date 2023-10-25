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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class login_screen {

    private JFrame frame;
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
        frame.setVisible(true);

        ImagePanel loginPanel = new ImagePanel(new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\login_background.png").getImage());
        ImagePanel mainloginPanel= new ImagePanel(new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\main2.png").getImage());
        ImagePanel PocketImg = new ImagePanel(new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\Pocketmon2.png").getImage());
        ImagePanel titleImg = new ImagePanel(new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\Title2.png").getImage());
        ImageIcon startImg = new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\start.png");
        ImageIcon signupImg = new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\signup.png");
        ImageIcon exitImg = new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\exit3.png");
       
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
				openSignupWindow();
				
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
		
		ImagePanel signupPanel = new ImagePanel(new ImageIcon("C:\\Project\\AutoPocket\\src\\Image\\signimg2.png").getImage());
    	
        signupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signupFrame.setBounds(510, 350, 900, 700);
        signupFrame.setUndecorated(true);
        
        signupFrame.getContentPane().add(signupPanel);
        signupPanel.setLayout(null);

        JLabel idLabel = new JLabel("아이디:");
        idLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        idLabel.setBounds(80, 50, 117, 46);
        signupPanel.add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        idTextField.setBounds(293, 50, 376, 46);
        signupPanel.add(idTextField);

        JButton checkIdButton = new JButton("중복 확인");
        checkIdButton.setBounds(697, 50, 117, 46);
        signupPanel.add(checkIdButton);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        passwordLabel.setBounds(80, 129, 140, 46);
        signupPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        passwordField.setBounds(293, 129, 376, 46);
        signupPanel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
        confirmPasswordLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        confirmPasswordLabel.setBounds(80, 209, 213, 46);
        signupPanel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("맑은 고딕", Font.BOLD, 32));
        confirmPasswordField.setBounds(293, 209, 376, 46);
        signupPanel.add(confirmPasswordField);

        JLabel nicknameLabel = new JLabel("닉네임:");
        nicknameLabel.setFont(new Font("나눔고딕", Font.BOLD, 32));
        nicknameLabel.setBounds(80, 450, 100, 46);
        signupPanel.add(nicknameLabel);
        
        JButton checkNicknameButton = new JButton("중복 확인");
        checkNicknameButton.setBounds(697, 450, 117, 45);
        signupPanel.add(checkNicknameButton);

        JTextField nicknameTextField = new JTextField();
        nicknameTextField.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        nicknameTextField.setBounds(293, 450, 376, 46);
        signupPanel.add(nicknameTextField);

        JButton signupButton = new JButton("가입");
        signupButton.setBounds(80, 566, 260, 70);
        signupPanel.add(signupButton);
        
        
        JButton cancleButton = new JButton("취소");
        cancleButton.setBounds(554, 566, 260, 70);
        signupPanel.add(cancleButton);
        cancleButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				signupFrame.setVisible(false);
				
			}
       
        });
        
        JLabel idLabel_1 = new JLabel("비일치");
        idLabel_1.setForeground(new Color(0, 0, 255));
        idLabel_1.setFont(new Font("나눔고딕", Font.BOLD, 32));
        idLabel_1.setBounds(697, 209, 117, 46);
        signupPanel.add(idLabel_1);

        String id = idTextField.getText();
        String pass = new String(passwordField.getPassword());
        String passconfirm = new String(confirmPasswordField.getPassword());

        if (pass.equals(passconfirm)) {
            JLabel idLabel_2 = new JLabel("일치");
            idLabel_2.setForeground(new Color(0, 255, 0));
            idLabel_2.setFont(new Font("나눔고딕", Font.BOLD, 32));
            idLabel_2.setBounds(697, 209, 117, 46);
            signupPanel.add(idLabel_2);
            idLabel_1.setVisible(false);
        } else {
            idLabel_1.setVisible(true); // If passwords do not match, show "비일치" label
        }
        
        signupFrame.setVisible(true);
    }
}