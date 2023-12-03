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
	private JPanel explanation;
	private JTextArea ex_label;
	
	
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
		this.heart = "0";
		setLayout(null);
		setBounds(161, 566, 486, 340);
		setOpaque(false);
		ImagePanel heart_panel = new ImagePanel(new ImageIcon("C:\\Placement_2\\src\\Image\\shop_heart.png").getImage());
		ImagePanel damage_panel = new ImagePanel(new ImageIcon("C:\\Placement_2\\src\\Image\\shop_damage.png").getImage());
		this.heart_label = new JLabel(this.heart);
		this.damage_label = new JLabel(this.damage);
		this.pokemon_panel = new ImagePanel(new ImageIcon("C:\\Placement_2\\src\\Image\\"+ pokemonNum +".png").getImage());
		
		pokemon_panel.setBounds(12, 126, 166, 166);
		this.add(pokemon_panel);
		
		this.exp_label = new JLabel("null/2");
		exp_label.setBounds(69, 68, 81, 28);
		this.add(exp_label);
		exp_label.setFont(new Font("굴림", Font.BOLD, 24));
		exp_label.setHorizontalAlignment(JLabel.CENTER); 
		exp_label.setVerticalAlignment(JLabel.CENTER);
		exp_label.setForeground(Color.WHITE);
		
		heart_panel.setBounds(111, 272, 67, 65);
		heart_panel.setLayout(null);
		this.add(heart_panel);
		
		heart_label.setFont(new Font("굴림", Font.BOLD, 30));
		heart_label.setBounds(0, 15, 67, 33);
		heart_label.setHorizontalAlignment(JLabel.CENTER); // 수평 정렬 설정
		heart_label.setVerticalAlignment(JLabel.CENTER);
		heart_panel.add(heart_label);

		damage_panel.setBounds(22, 261, 70, 76);
		damage_panel.setLayout(null);
		this.add(damage_panel);
		
		damage_label.setFont(new Font("굴림", Font.BOLD, 30));
		damage_label.setBounds(0, 24, 70, 33);
		damage_label.setHorizontalAlignment(JLabel.CENTER); 
		damage_label.setVerticalAlignment(JLabel.CENTER);
		damage_panel.add(damage_label);
		
		this.Lv_label = new JLabel("Lvnull");
		Lv_label.setFont(new Font("굴림", Font.BOLD, 24));
		Lv_label.setBounds(28, 69, 53, 27);
		add(Lv_label);
		Lv_label.setForeground(Color.WHITE);
		
		this.explanation = new JPanel();
		explanation.setBackground(new Color(255, 251, 202));
		explanation.setBounds(201, 72, 250, 119);
		Border border = BorderFactory.createTitledBorder("Panel Border");
		explanation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(explanation);
		explanation.setLayout(null);
		explanation.setVisible(false);
		
		this.ex_label = new JTextArea("", 5, 20);
		ex_label.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 17));
		ex_label.setLineWrap(true);
		ex_label.setWrapStyleWord(true);
		ex_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		ex_label.setAlignmentY(Component.CENTER_ALIGNMENT);
		ex_label.setBounds(12, 10, 226, 94);
		explanation.add(ex_label);
	}
	
	//---------------------------------------------
	public void show_ex() {
		explanation.setVisible(true);
	}
	public void no_show_ex() {
		explanation.setVisible(false);
	}
	public JPanel get_expanel() {
		return explanation;
	}
	public void set_pokemon_num(int pokemon_num, int Lv) {
	    if (Lv == 1) {
	        this.pokemonNum = Integer.toString(pokemon_num);
	    } else if (Lv == 2) {
	        this.pokemonNum = "2_" + Integer.toString(pokemon_num);
	    }else if (Lv == 3) {
	    	 this.pokemonNum = "3_" + Integer.toString(pokemon_num);
	    }
	    update_pokemon_Image(); // 포켓몬 패널 값 set 후 업데이트
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
    
    public ImagePanel get_pokemon_panel() { //이미지 패널을 받아서 다른 클래스에서도 사용할 수 있게 함
    	return pokemon_panel;
    }
	
    private void update_pokemon_Image() { //포켓몬 값을 설정하고 그 패널을 업데이트 해주는 함수.
        String imagePath = "C:\\Placement_2\\src\\Image\\" + this.pokemonNum + ".png";
        this.pokemon_panel.setImage(new ImageIcon(imagePath).getImage());
        repaint(); 
    }
    
    public boolean ischecked() { //체크 여부를 반환하는 함수.
    	return ischecked;
    }
  
    
    public void set_ex(String ex) {
    	this.ex = ex;
    	this.ex_label.setText(ex);
    }
    
    public String get_ex() {
    	return this.ex;
    }
    
    public void no_frozen() {
    	this.isfrozen = false;
    }
}
