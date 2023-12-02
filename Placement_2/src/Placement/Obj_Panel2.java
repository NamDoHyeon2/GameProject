package Placement;

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



public class Obj_Panel2 extends JPanel {
	private JLabel heart_label;
	private JLabel damage_label;
	private JLabel Lv_label;
	private JLabel exp_label;
	private JLabel check_grade_label;
	private JLabel no_check_grade_label;
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
	private ImagePanel check_panel;
	private ImagePanel default_check_panel;
	private ImagePanel pokemon_panel;
	private ImagePanel frozen_panel;
	private boolean isfrozen;
	
	
	public Obj_Panel2() {
		this.ischecked = false;
		this.isfrozen = false;
		
		setLayout(null);
		setBounds(161, 566, 486, 340);
		setOpaque(false);
		ImagePanel heart_panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\shop_heart.png").getImage());
		ImagePanel damage_panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\shop_damage.png").getImage());
		this.default_check_panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\no_check.png").getImage());
		this.check_panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\check.png").getImage());
		this.heart_label = new JLabel(this.heart);
		this.damage_label = new JLabel(this.damage);
		this.frozen_panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\prozen_obj.png").getImage());
		this.pokemon_panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\"+ pokemonNum +".png").getImage());
		
		frozen_panel.setBounds(28, 93, 120, 131);
		this.add(frozen_panel);
		frozen_panel.setVisible(false);
		
		pokemon_panel.setBounds(12, 126, 166, 166);
		this.add(pokemon_panel);
		
		this.exp_label = new JLabel("null/2");
		exp_label.setBounds(81, 20, 81, 28);
		this.add(exp_label);
		exp_label.setFont(new Font("굴림", Font.BOLD, 24));
		exp_label.setHorizontalAlignment(JLabel.CENTER); 
		exp_label.setVerticalAlignment(JLabel.CENTER);
		
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
		
		default_check_panel.setBounds(62, 59, 56, 56);
		this.add(default_check_panel);
		default_check_panel.setLayout(null);
		
		check_panel.setBounds(62, 60, 56, 56);
		this.add(check_panel);
		check_panel.setLayout(null);
		check_panel.setVisible(false);
		
		this.check_grade_label = new JLabel(this.grade);
		check_grade_label.setFont(new Font("굴림", Font.BOLD, 29));
		check_grade_label.setBounds(0, 5, 57, 51);
		check_panel.add(check_grade_label);
		check_grade_label.setHorizontalAlignment(JLabel.CENTER); 
		check_grade_label.setVerticalAlignment(JLabel.CENTER);
		
		this.no_check_grade_label = new JLabel(this.grade);
		no_check_grade_label.setFont(new Font("굴림", Font.BOLD, 29));
		no_check_grade_label.setBounds(0, 5, 57, 51);
		default_check_panel.add(no_check_grade_label);
		no_check_grade_label.setHorizontalAlignment(JLabel.CENTER); 
		no_check_grade_label.setVerticalAlignment(JLabel.CENTER);
		
		this.Lv_label = new JLabel("Lvnull");
		Lv_label.setFont(new Font("굴림", Font.BOLD, 24));
		Lv_label.setBounds(39, 21, 53, 27);
		add(Lv_label);
		
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
	
	public void set_grade(int grade) {
		this.grade = Integer.toString(grade);
		update_grade_label();
	}
	
	public int get_grade() {
		return Integer.parseInt(this.grade);
	}
	
	public void update_grade_label() { //이미지 패널이 2개여서 2개의 label을 업데이트 하기위한 함수
		check_grade_label.setText(this.grade);
		no_check_grade_label.setText(this.grade);
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
	
    private void update_pokemon_Image() { //포켓몬 값을 설정하고 그 패널을 업데이트 해주는 함수.
        String imagePath = "C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\" + this.pokemonNum + ".png";
        this.pokemon_panel.setImage(new ImageIcon(imagePath).getImage());
        repaint(); 
    }
    
    public boolean ischecked() { //체크 여부를 반환하는 함수.
    	return ischecked;
    }
    
    public void check() { //체크 했을때
    	ischecked = true;
    	default_check_panel.setVisible(false);
		check_panel.setVisible(true);
    }
    public void no_check() { //체크 해제 했을때
    	ischecked = false;
    	default_check_panel.setVisible(true);
		check_panel.setVisible(false);
    }
    public ImagePanel get_pokemon_panel() { //이미지 패널을 받아서 다른 클래스에서도 사용할 수 있게 함
    	return pokemon_panel;
    }
    
    public ImagePanel get_frozen_panel() { //이미지 패널을 받아서 다른 클래스에서 사용할 수 있게 하기위함
    	return frozen_panel;
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
