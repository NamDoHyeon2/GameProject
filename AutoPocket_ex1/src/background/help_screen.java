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
	    numpage.setFont(new Font("맑은 고딕", Font.BOLD, 35));
	    numpage.setBounds(807, 850, 120, 55);
	    helppanel.add(numpage);
	    
	    JLabel gameIntroduce = new JLabel("<html>포켓몬을 구매하고 배치하며 대결을 통해 뱃지를 모으세요!" + 
	    		"<br>뱃지 10개를 모으면 승리합니다!<br>단, 목숨이 0이 되면 패배합니다!<br><br> " +
	    		"매 턴 코인은 10개로 시작하며 포켓몬이나 아이템을 사기 위해서는 코인 3개를 사용해야 합니다!<br><br>" + 
	    		"상점 리롤을 통해 팀에게 맞는 포켓몬을 찾아보세요!<br>얼리기를 하면 리롤이 되지 않습니다!<br><br>" + 
	    		"턴 수가 늘어날 수록 더욱 강력한 포켓몬과 아이템이 상점에 나옵니다!<br>"+ 
	    		"턴 1 : 상점 기물 수 3, 최대 기물 등급 1<br>" + 
	    		"턴 2 : 상점 기물 수 4, 최대 기물 등급 2<br>" + 
	    		"턴 3 : 상점 기물 수 4, 최대 기물 등급 3<br>" + 
	    		"턴 4 : 상점 기물 수 5, 최대 기물 등급 4<br>" + 
	    		"턴 5 : 상점 기물 수 5, 최대 기물 등급 5<br>" + 
	    		"턴 6 : 상점 기물 수 5, 최대 기물 등급 6</html>");
	    gameIntroduce.setFont(new Font("맑은 고딕", Font.BOLD, 30));
	    gameIntroduce.setBounds(36, 65, 1608, 803);
	    helppanel.add(gameIntroduce);
	    
	    JLabel Title = new JLabel("배치 단계");
	    Title.setFont(new Font("맑은 고딕", Font.BOLD, 50));
	    Title.setBounds(36, 10, 270, 129);
	    helppanel.add(Title);
	    
	    nextBtn_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("2/3");
				gameIntroduce.setText("<html>포켓몬과 아이템은 각각 능력이 있습니다! <br>이 점을 활용하여 나만의 포켓몬 팀을 꾸리세요!<br><br>" + 
				"같은 포켓몬이 있다면 합쳐보세요!<br>" + 
				"경험치가 다 차면 진화를 합니다!<br>" + 
				"진화한 포켓몬은 더욱 강력해 집니다!</html>");
				previous_1.setVisible(true);
				nextBtn_1.setVisible(false);
				nextBtn_2.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("3/3");
				Title.setText("<html>전투 단계</html>");
				gameIntroduce.setText("<html>전투는 전부 자동으로 진행 됩니다!<br>" + 
				"전투를 보며 팀의 부족한 부분을 확인하세요!<br>" + 
				"턴 수가 늘어날 수록 적은 더욱 강력해 집니다!</html>");
				previous_1.setVisible(false);
				nextBtn_2.setVisible(false);
				previous_2.setVisible(true);
			}
	    	
	    });
	    
	    previous_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("1/3");
				gameIntroduce.setText("<html>포켓몬을 구매하고 배치하며 대결을 통해 뱃지를 모으세요!" + 
	    		"<br>뱃지 10개를 모으면 승리합니다!<br>단, 목숨이 0이 되면 패배합니다!<br><br> " +
	    		"매 턴 코인은 10개로 시작하며 포켓몬이나 아이템을 사기 위해서는 코인 3개를 사용해야 합니다!<br><br>" + 
	    		"상점 리롤을 통해 팀에게 맞는 포켓몬을 찾아보세요!<br>얼리기를 하면 리롤이 되지 않습니다!<br><br>" + 
	    		"턴 수가 늘어날 수록 더욱 강력한 포켓몬과 아이템이 상점에 나옵니다!<br>"+ 
	    		"턴 1 : 상점 기물 수 3, 최대 기물 등급 1<br>" + 
	    		"턴 2 : 상점 기물 수 4, 최대 기물 등급 2<br>" + 
	    		"턴 3 : 상점 기물 수 4, 최대 기물 등급 3<br>" + 
	    		"턴 4 : 상점 기물 수 5, 최대 기물 등급 4<br>" + 
	    		"턴 5 : 상점 기물 수 5, 최대 기물 등급 5<br>" + 
	    		"턴 6 : 상점 기물 수 5, 최대 기물 등급 6</html>");
				previous_1.setVisible(false);
				nextBtn_1.setVisible(true);
				
			}
	    	
	    });
	    
	    previous_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numpage.setText("2/3");
				gameIntroduce.setText("<html>포켓몬과 아이템은 각각 능력이 있습니다! <br>이 점을 활용하여 나만의 포켓몬 팀을 꾸리세요!<br><br>" + 
						"같은 포켓몬이 있다면 합쳐보세요!<br>" + 
						"경험치가 다 차면 진화를 합니다!<br>" + 
						"진화한 포켓몬은 더욱 강력해 집니다!</html>");
				
				nextBtn_2.setVisible(true);
				previous_1.setVisible(true);
				previous_2.setVisible(false);
			}
	    	
	    });
	
	}

}
