package background;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Place_Area2 extends JPanel {
	
	private boolean ischecked;
	private int location_num;
	private ImagePanel select_place_panel;
	private ImagePanel no_select_place_panel;
	
	public Place_Area2(int location_num) {
		this.location_num = location_num;
		this.ischecked = false;
		
		setLayout(null);
		setOpaque(false);
		setBounds(495, 418, 202, 117);
		
		this.select_place_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\location3.png").getImage());
		select_place_panel.setLayout(null);
		select_place_panel.setBounds(12, 10, 189, 98);
		this.add(select_place_panel);
		select_place_panel.setVisible(false);
		
		this.no_select_place_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\location2.png").getImage());
		no_select_place_panel.setLayout(null);
		no_select_place_panel.setBounds(13, 10, 178, 98);
		this.add(no_select_place_panel);

		/*
		select_place_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				no_check();
				
			}
		});
		no_select_place_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				check();
			}
		});
		*/
		
	}
	public int get_location_num() {
		return location_num;
	}
	
	public void check() {
		this.select_place_panel.setVisible(true);
		this.no_select_place_panel.setVisible(false);
		this.ischecked = true;
		
	}
	public void no_check() {
		this.select_place_panel.setVisible(false);
		this.no_select_place_panel.setVisible(true);
		this.ischecked = false;
	
	}
	public ImagePanel get_select_place_panel() {
		return this.select_place_panel;
	}
	public ImagePanel get_no_select_place_panel() {
		return this.no_select_place_panel;
	}
	
	public boolean ischecked() {
		return ischecked;
	}
	
}
