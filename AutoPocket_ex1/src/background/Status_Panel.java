package background;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Status_Panel extends JPanel {
	private int coin;
	private int heart;
	private int win;
	private JLabel coin_num;
	private JLabel heart_num;
	private JLabel win_num;
	

	public Status_Panel(int coin, int heart, int win) {	
		this.coin = coin;
		this.heart = heart;
		this.win = win;
		
		setLayout(null);
		setOpaque(false);
		
		Shop_Area shop_area = new Shop_Area();
		shop_area.setBounds(150, 797, 1045, 113);
		this.add(shop_area);
		//save_static_panel.add(shop_area);
		
		ImagePanel user_heart = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\user_heart.png").getImage());
		user_heart.setBounds(12, 10, 238, 151);
		this.add(user_heart);
		user_heart.setLayout(null);
		//save_static_panel.add(user_heart);
		
		this.heart_num = new JLabel(Integer.toString(this.heart));
		heart_num.setFont(new Font("±¼¸²", Font.BOLD, 65));
		heart_num.setBounds(136, 49, 79, 56);
		user_heart.add(heart_num);
		
		ImagePanel user_coin = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\user_coin.png").getImage());
		user_coin.setBounds(313, 10, 238, 151);
		this.add(user_coin);
		user_coin.setLayout(null);
		//save_static_panel.add(user_coin);
		
		this.coin_num = new JLabel(Integer.toString(this.coin));
		coin_num.setFont(new Font("±¼¸²", Font.BOLD, 65));
		coin_num.setBounds(135, 50, 79, 56);
		user_coin.add(coin_num);
		
		ImagePanel user_win = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\user_win.png").getImage());
		user_win.setBounds(615, 10, 238, 151);
		this.add(user_win);
		user_win.setLayout(null);
		//save_static_panel.add(user_win);
		
		this. win_num = new JLabel(Integer.toString(this.win));
		win_num.setFont(new Font("±¼¸²", Font.BOLD, 65));
		win_num.setBounds(136, 50, 79, 56);
		user_win.add(win_num);
		
	}
	public JLabel get_coin_num() {
		return this.coin_num;
	}
	public JLabel get_heart_num() {
		return this.heart_num;
	}
	public JLabel get_win_num() {
		return this.win_num;
	}
	public void set_coin_num(int coin){
		this.coin = coin;
		coin_num.setText(Integer.toString(this.coin));
	}
	
	public void set_heart_num (int heart){
		this.heart = heart;
		heart_num.setText(Integer.toString(this.heart));
	}
	
	public void set_win_num (int win){
		this.win = win;
		win_num.setText(Integer.toString(this.win));
	}
	public void up_win() {
		this.win++;
		win_num.setText(Integer.toString(this.win));
	}
	public void down_heart() {
		this.heart--;
		heart_num.setText(Integer.toString(this.heart));
	}
	public int get_life_num() {
		return this.heart;
	}
	public int get_bedge_num() {
		return this.win;
	}
}