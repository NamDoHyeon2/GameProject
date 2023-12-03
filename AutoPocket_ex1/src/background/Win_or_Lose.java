package background;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Win_or_Lose extends JPanel {
	private int life;
	private int badge;
	private ImagePanel placement_screen;
	private Status_Panel status_panel;
	private JLabel WinLabel;
	private JLabel LoseLabel;
	private JLabel titleLabel;
	public ImagePanel placementbackground;

	public Win_or_Lose(ImagePanel placement_screen, Status_Panel status) {
		this.placement_screen = placement_screen;
		this.status_panel = status;
		this.life = status_panel.get_life_num();
		this.badge = status_panel.get_bedge_num();
		this.setBounds(0, 0, 1920, 1080);
		setLayout(null);
		setOpaque(false);
		placementbackground = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\combat_background.png").getImage());
		
		placementbackground.setLayout(null);
		this.add(placementbackground);
        ImagePanel winImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\user_win.png").getImage());
        winImg.setLocation(434, 388);
        ImagePanel heartImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\user_heart.png").getImage());
        heartImg.setLocation(1020, 388);
        
        
        
		RoundPanel WinPanel = new RoundPanel(45, Color.LIGHT_GRAY);
		WinPanel.setBounds(160,80,1600,920);
		WinPanel.setLayout(null);
		WinPanel.add(winImg);
		WinPanel.add(heartImg);
		winImg.setLayout(null);
		placementbackground.add(WinPanel);
		
		this.WinLabel = new JLabel();
		//WinLabel.setText(Integer.toString(badge));
		WinLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 70));
		WinLabel.setBounds(134, 10, 107, 118);
		winImg.add(WinLabel);
		winImg.setLayout(null);

		
		this.LoseLabel = new JLabel();
		//LoseLabel.setText(Integer.toString(life));
		LoseLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 70));
		LoseLabel.setBounds(132, 20, 86, 94);
		heartImg.add(LoseLabel);
		heartImg.setLayout(null);
		
		
		this.titleLabel = new JLabel("½Â¸® !");
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 98));
		titleLabel.setBounds(722, 168, 450, 150);
		WinPanel.add(titleLabel);
		
		JButton confirmBtn = new JButton("È®ÀÎ");
		WinPanel.add(confirmBtn);
		confirmBtn.setBounds(543,700, 600, 126);
		
		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				placementbackground.setVisible(false);
				placement_screen.setVisible(true);
			}
			
		});
		
	}
	public JLabel get_win_label (){
		return this.WinLabel;
	}
	
	public JLabel get_lose_label (){
		return this.LoseLabel;
	}
	
	public JLabel get_title_label () {
		return this.titleLabel;
	}
}