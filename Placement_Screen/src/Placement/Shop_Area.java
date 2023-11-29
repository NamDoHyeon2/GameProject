package Placement;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Shop_Area extends JPanel {

	public Shop_Area() {
		
		setLayout(null);
		setOpaque(false);
		
		ImagePanel shop_location_1 = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\location2.png").getImage());
		shop_location_1.setBounds(12, 10, 178, 98);
		this.add(shop_location_1);
		shop_location_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ImagePanel shop_location_2 = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\location2.png").getImage());
		shop_location_2.setBounds(224, 10, 178, 98);
		this.add(shop_location_2);
		
		ImagePanel shop_location_3 = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\location2.png").getImage());
		shop_location_3.setBounds(436, 10, 178, 98);
		this.add(shop_location_3);
		
		ImagePanel shop_location_4 = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\location2.png").getImage());
		shop_location_4.setBounds(648, 10, 178, 98);
		this.add(shop_location_4);
		
		ImagePanel shop_location_5 = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\location2.png").getImage());
		shop_location_5.setBounds(860, 10, 178, 98);
		this.add(shop_location_5);
	}

}
