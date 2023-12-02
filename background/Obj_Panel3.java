package background;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;



public class Obj_Panel3 extends JPanel {
	private JLabel heart_label;
	private JLabel damage_label;
	private JLabel Lv_label;
	private JLabel exp_label;
	
	
	private boolean ischecked;
	private int location_num;
	
	private String ex;
	private String pokemonNum;
	private String Lv;
	private String exp;
	private String grade;
	private String heart;
	private String damage;
	private ImagePanel pokemon_panel;
	private boolean isfrozen;
	
	
	public Obj_Panel3() {
		this.ischecked = false;
		this.isfrozen = false;
		
		setLayout(null);
		setBounds(161, 566, 220, 340);
		setOpaque(false);
		ImagePanel heart_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\shop_heart.png").getImage());
		ImagePanel damage_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\shop_damage.png").getImage());
		this.heart_label = new JLabel(this.heart);
		this.damage_label = new JLabel(this.damage);
		this.pokemon_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\"+ pokemonNum +".png").getImage());
		
		pokemon_panel.setBounds(12, 126, 166, 166);
		this.add(pokemon_panel);
		
		this.exp_label = new JLabel("null/2");
		exp_label.setBounds(69, 68, 81, 28);
		this.add(exp_label);
		exp_label.setFont(new Font("援대┝", Font.BOLD, 24));
		exp_label.setHorizontalAlignment(JLabel.CENTER); 
		exp_label.setVerticalAlignment(JLabel.CENTER);
		exp_label.setForeground(Color.WHITE);
		
		heart_panel.setBounds(111, 272, 67, 65);
		heart_panel.setLayout(null);
		this.add(heart_panel);
		
		heart_label.setFont(new Font("援대┝", Font.BOLD, 30));
		heart_label.setBounds(0, 15, 67, 33);
		heart_label.setHorizontalAlignment(JLabel.CENTER); // �닔�룊 �젙�젹 �꽕�젙
		heart_label.setVerticalAlignment(JLabel.CENTER);
		heart_panel.add(heart_label);

		damage_panel.setBounds(22, 261, 70, 76);
		damage_panel.setLayout(null);
		this.add(damage_panel);
		
		damage_label.setFont(new Font("援대┝", Font.BOLD, 30));
		damage_label.setBounds(0, 24, 70, 33);
		damage_label.setHorizontalAlignment(JLabel.CENTER); 
		damage_label.setVerticalAlignment(JLabel.CENTER);
		damage_panel.add(damage_label);
		
		this.Lv_label = new JLabel("Lvnull");
		Lv_label.setFont(new Font("援대┝", Font.BOLD, 24));
		Lv_label.setBounds(28, 69, 53, 27);
		add(Lv_label);
		Lv_label.setForeground(Color.WHITE);
		Border border = BorderFactory.createTitledBorder("Panel Border");
	}
	
	//---------------------------------------------
	
	public void set_pokemon_num(int pokemon_num, int Lv) {
	    if (Lv == 1) {
	        this.pokemonNum = Integer.toString(pokemon_num);
	    } else if (Lv == 2) {
	        this.pokemonNum = "2_" + Integer.toString(pokemon_num);
	    }else if (Lv == 3) {
	    	 this.pokemonNum = "3_" + Integer.toString(pokemon_num);
	    }
	    update_pokemon_Image(); // �룷耳볥が �뙣�꼸 媛� set �썑 �뾽�뜲�씠�듃
	}
	
	public int get_pokemon_num() {
	    // Check if the string starts with "2_" or "3_"
	    if (this.pokemonNum.startsWith("2_")) {
	        // Remove "2_" from the beginning of the string
	        return Integer.parseInt(this.pokemonNum.substring(2));
	    } else if (this.pokemonNum.startsWith("3_")) {
	        // Remove "3_" from the beginning of the string
	        return Integer.parseInt(this.pokemonNum.substring(2));
	    } else {
	        // If it doesn't start with "2_" or "3_", parse the entire string as an integer
	        return Integer.parseInt(this.pokemonNum);
	    }
	}
	
	public void set_Lv(int Lv) {
		this.Lv = Integer.toString(Lv);
		Lv_label.setText("Lv" + this.Lv);
	}
	public int get_LV() {
		return Integer.parseInt(this.Lv);
	}
	
	public void set_exp(int exp) {
		this.exp = Integer.toString(exp);
		exp_label.setText(this.exp + "/3");
	}
	public int get_exp() {
		return Integer.parseInt(this.exp);
	}
	
	public int get_grade() {
		return Integer.parseInt(this.grade);
	}
	
	public void set_heart(int heart) {
		this.heart = Integer.toString(heart);
		heart_label.setText(this.heart);
	}
	public int get_heart() {
		return Integer.parseInt(this.heart);
	}
	
	
	public void set_damage(int damage) {
		this.damage = Integer.toString(damage);
		damage_label.setText(this.damage);
	}
	public int get_damage() {
		return Integer.parseInt(this.damage);
	}
	
    public void set_location_num(int location_num) {
    	this.location_num = location_num;
    }
    
    public int get_location_num() {
    	return this.location_num;
    }
	
    private void update_pokemon_Image() { //�룷耳볥が 媛믪쓣 �꽕�젙�븯怨� 洹� �뙣�꼸�쓣 �뾽�뜲�씠�듃 �빐二쇰뒗 �븿�닔.
        String imagePath = "C:\\ex1\\AutoPocket_ex1\\src\\Images\\" + this.pokemonNum + ".png";
        this.pokemon_panel.setImage(new ImageIcon(imagePath).getImage());
        repaint(); 
    }
    
    public boolean ischecked() { //泥댄겕 �뿬遺�瑜� 諛섑솚�븯�뒗 �븿�닔.
    	return ischecked;
    }
  
    
    public void set_ex(String ex) {
    	this.ex = ex;
    	
    }
    
    public String get_ex() {
    	return this.ex;
    }
    
    public void no_frozen() {
    	this.isfrozen = false;
    }
}
