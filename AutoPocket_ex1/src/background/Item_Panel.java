package background;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Item_Panel extends JPanel {
	private ImagePanel check_panel;
	private ImagePanel default_check_panel;
	private ImagePanel frozen_panel;
	private ImagePanel item_panel;
	private JPanel explain;
	private JTextArea ex_label;
	private boolean ischecked;
	private boolean isselected;
	private boolean isfrozen;
	private int itemNum;
	private int location;
	private String ex;
		/**
	 * Create the panel.
	 */
	public Item_Panel() {
		setLayout(null);
		setOpaque(false);
		//this.itemNum = 2;
		this.item_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Item_Image\\" + itemNum + ".png").getImage());
		this.default_check_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\no_check.png").getImage());
		this.check_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\check.png").getImage());
		this.frozen_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\prozen_obj.png").getImage());
		
		
		this.explain = new JPanel();
		explain.setBackground(new Color(255, 251, 202));
		explain.setBounds(94, 51, 250, 119);
		explain.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(explain);
		explain.setLayout(null);
		explain.setVisible(false);
		
		this.ex_label = new JTextArea("", 5, 20);
		ex_label.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 17));
		ex_label.setLineWrap(true);
		ex_label.setWrapStyleWord(true);
		ex_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		ex_label.setAlignmentY(Component.CENTER_ALIGNMENT);
		ex_label.setBounds(12, 10, 226, 94);
		explain.add(ex_label);
		this.add(frozen_panel);
		this.add(item_panel);
		this.add(default_check_panel);
		this.add(check_panel);
		default_check_panel.setLocation(63, 0);
		check_panel.setLocation(62, 0);
		frozen_panel.setLocation(45, 61);
		ischecked = false;
		isfrozen = false;
		isselected = false;
		this.check_panel.setVisible(false);
		this.frozen_panel.setVisible(false);
		Border border = BorderFactory.createTitledBorder("Panel Border");

	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
		updateImage();
	}
	
	public int getItemNum() {
		return this.itemNum;
	}

	public JPanel get_item_panel() { //??´? ?¨? ëŚŹí´, ??´? ?´ëŚ? ?´ë˛¤í¸ ??´?.
		return this.item_panel;
	}
	
	public boolean ischecked() { //ě˛´íŹ ??ëĽ? ëł´ęł  ě˛´íŹëĽ? ??ë§? ?  ? ?ę˛? ??¤.
		return this.ischecked;
	}
	
	public void setItemLocation(int location) {
		this.location = location;
	}
	
	public int getItemLocation() {
		return this.location;
	}
	
	public void check() { 
		this.default_check_panel.setVisible(false);
		this.check_panel.setVisible(true);
		this.ischecked = true;
		this.isselected = true;
	}
	
	public void no_check() {
		this.default_check_panel.setVisible(true);
		this.check_panel.setVisible(false);
		this.ischecked = false;
		this.isselected = false;
	}
	
	public void frozen() {
		this.frozen_panel.setVisible(true);
		this.isfrozen = true;
	}
	
	public ImagePanel get_frozen_panel() {
		return this.frozen_panel;
	}
	
	private void updateImage() {
	    Image newImage = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Item_Image\\" + itemNum + ".png").getImage();
	    this.item_panel.setImage(newImage);
	}
	
    public void set_ex(String ex) {
    	this.ex = ex;
    	this.ex_label.setText(ex);
    }
    
	public void show_ex() {
		explain.setVisible(true);
	}
	
	public void no_show_ex() {
		explain.setVisible(false);
	}
}
