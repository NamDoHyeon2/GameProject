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
import javax.swing.border.LineBorder;

public class help_screen {

	private JFrame frmAutoPockmon;
	public ImagePanel helpImg;
	public RoundPanel helppanel;
	public JButton exitBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					help_screen window = new help_screen();
					window.frmAutoPockmon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public help_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	    helpImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
	    ImageIcon nextImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\nextBtn.png");
	    ImageIcon previousImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\previous.png");
	    ImageIcon ExitImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit.png");
	    helpImg.setLayout(null);
	    helpImg.setPreferredSize(helpImg.getPreferredSize());
	    
	    helppanel = new RoundPanel(45, Color.lightGray);
	    helpImg.add(helppanel);
	    helppanel.setLayout(null);
	    
	    JButton nextBtn_1 = new JButton(nextImg);
	    nextBtn_1.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_1.setBounds(885, 848, 60, 60);
	    nextBtn_1.setBorderPainted(false); 
	    nextBtn_1.setContentAreaFilled(false);
	    helppanel.add(nextBtn_1);
	    
	    JButton nextBtn_2 = new JButton(nextImg);
	    nextBtn_2.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_2.setBounds(885, 848, 60, 60);
	    nextBtn_2.setBorderPainted(false); 
	    nextBtn_2.setContentAreaFilled(false);
	    helppanel.add(nextBtn_2);
	    nextBtn_2.setVisible(false);
	    
	    JButton previous_1 = new JButton(previousImg);
	    previous_1.setBorder(new LineBorder(Color.WHITE));
	    previous_1.setBounds(725, 848, 60, 60);
	    previous_1.setBorderPainted(false); 
	    previous_1.setContentAreaFilled(false);
	    helppanel.add(previous_1);
	    previous_1.setVisible(false);
	    
	    JButton previous_2 = new JButton(previousImg);
	    previous_2.setBorder(new LineBorder(Color.WHITE));
	    previous_2.setBounds(725, 848, 60, 60);
	    previous_2.setBorderPainted(false); 
	    previous_2.setContentAreaFilled(false);
	    helppanel.add(previous_2);
	    previous_2.setVisible(false);
	    
	    exitBtn = new JButton(ExitImg);
	    exitBtn.setLocation(1578, 10);
	    exitBtn.setSize(90, 90);
	    exitBtn.setBorderPainted(false); 
	    exitBtn.setContentAreaFilled(false);
	    helppanel.add(exitBtn);
	    
	    JLabel numpage = new JLabel("1/3");
	    numpage.setFont(new Font("���� ���", Font.BOLD, 35));
	    numpage.setBounds(807, 850, 120, 55);
	    helppanel.add(numpage);
	    
	    JLabel gameIntroduce = new JLabel("<html>���ϸ��� �����ϰ� ��ġ�ϸ� ����� ���� ������ ��������!" + 
	    		"<br>���� 10���� ������ �¸��մϴ�!<br>��, ����� 0�� �Ǹ� �й��մϴ�!<br><br> " +
	    		"�� �� ������ 10���� �����ϸ� ���ϸ��̳� �������� ��� ���ؼ��� ���� 3���� ����ؾ� �մϴ�!<br><br>" + 
	    		"���� ������ ���� ������ �´� ���ϸ��� ã�ƺ�����!<br>�󸮱⸦ �ϸ� ������ ���� �ʽ��ϴ�!<br><br>" + 
	    		"�� ���� �þ ���� ���� ������ ���ϸ�� �������� ������ ���ɴϴ�!<br>"+ 
	    		"�� 1 : ���� �⹰ �� 3, �ִ� �⹰ ��� 1<br>" + 
	    		"�� 2 : ���� �⹰ �� 4, �ִ� �⹰ ��� 2<br>" + 
	    		"�� 3 : ���� �⹰ �� 4, �ִ� �⹰ ��� 3<br>" + 
	    		"�� 4 : ���� �⹰ �� 5, �ִ� �⹰ ��� 4<br>" + 
	    		"�� 5 : ���� �⹰ �� 5, �ִ� �⹰ ��� 5<br>" + 
	    		"�� 6 : ���� �⹰ �� 5, �ִ� �⹰ ��� 6</html>");
	    gameIntroduce.setFont(new Font("���� ���", Font.BOLD, 30));
	    gameIntroduce.setBounds(36, 65, 1608, 803);
	    helppanel.add(gameIntroduce);
	    
	    JLabel Title = new JLabel("��ġ �ܰ�");
	    Title.setFont(new Font("���� ���", Font.BOLD, 50));
	    Title.setBounds(36, 10, 270, 129);
	    helppanel.add(Title);
	    
	    nextBtn_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("2/3");
				gameIntroduce.setText("<html>���ϸ�� �������� ���� �ɷ��� �ֽ��ϴ�! <br>�� ���� Ȱ���Ͽ� ������ ���ϸ� ���� �ٸ�����!<br><br>" + 
				"���� ���ϸ��� �ִٸ� ���ĺ�����!<br>" + 
				"����ġ�� �� ���� ��ȭ�� �մϴ�!<br>" + 
				"��ȭ�� ���ϸ��� ���� ������ ���ϴ�!</html>");
				previous_1.setVisible(true);
				nextBtn_1.setVisible(false);
				nextBtn_2.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("3/3");
				Title.setText("<html>���� �ܰ�</html>");
				gameIntroduce.setText("<html>������ ���� �ڵ����� ���� �˴ϴ�!<br>" + 
				"������ ���� ���� ������ �κ��� Ȯ���ϼ���!<br>" + 
				"�� ���� �þ ���� ���� ���� ������ ���ϴ�!</html>");
				previous_1.setVisible(false);
				nextBtn_2.setVisible(false);
				previous_2.setVisible(true);
			}
	    	
	    });
	    
	    previous_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("1/3");
				gameIntroduce.setText("<html>���ϸ��� �����ϰ� ��ġ�ϸ� ����� ���� ������ ��������!" + 
	    		"<br>���� 10���� ������ �¸��մϴ�!<br>��, ����� 0�� �Ǹ� �й��մϴ�!<br><br> " +
	    		"�� �� ������ 10���� �����ϸ� ���ϸ��̳� �������� ��� ���ؼ��� ���� 3���� ����ؾ� �մϴ�!<br><br>" + 
	    		"���� ������ ���� ������ �´� ���ϸ��� ã�ƺ�����!<br>�󸮱⸦ �ϸ� ������ ���� �ʽ��ϴ�!<br><br>" + 
	    		"�� ���� �þ ���� ���� ������ ���ϸ�� �������� ������ ���ɴϴ�!<br>"+ 
	    		"�� 1 : ���� �⹰ �� 3, �ִ� �⹰ ��� 1<br>" + 
	    		"�� 2 : ���� �⹰ �� 4, �ִ� �⹰ ��� 2<br>" + 
	    		"�� 3 : ���� �⹰ �� 4, �ִ� �⹰ ��� 3<br>" + 
	    		"�� 4 : ���� �⹰ �� 5, �ִ� �⹰ ��� 4<br>" + 
	    		"�� 5 : ���� �⹰ �� 5, �ִ� �⹰ ��� 5<br>" + 
	    		"�� 6 : ���� �⹰ �� 5, �ִ� �⹰ ��� 6</html>");
				previous_1.setVisible(false);
				nextBtn_1.setVisible(true);
				
			}
	    	
	    });
	    
	    previous_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("2/3");
				gameIntroduce.setText("<html>���ϸ�� �������� ���� �ɷ��� �ֽ��ϴ�! <br>�� ���� Ȱ���Ͽ� ������ ���ϸ� ���� �ٸ�����!<br><br>" + 
						"���� ���ϸ��� �ִٸ� ���ĺ�����!<br>" + 
						"����ġ�� �� ���� ��ȭ�� �մϴ�!<br>" + 
						"��ȭ�� ���ϸ��� ���� ������ ���ϴ�!</html>");
				
				nextBtn_2.setVisible(true);
				previous_1.setVisible(true);
				previous_2.setVisible(false);
			}
	    	
	    });
	
	}

}
