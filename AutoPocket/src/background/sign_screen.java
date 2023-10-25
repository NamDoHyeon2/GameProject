package background;

import java.awt.Color;
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

	private JFrame signupFrame;

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
	private void initialize() {
		signupFrame = new JFrame("회원가입");
		
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
        
    }
}
