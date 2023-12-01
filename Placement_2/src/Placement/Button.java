package Placement;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Button extends JPanel {
	private ImagePanel next_btn;

	/**
	 * Create the panel.
	 */
	public Button() {
		setLayout(null);
		setOpaque(false);
		this.next_btn = new ImagePanel (new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\Next_Battle.png").getImage());
		this.add(next_btn);
	}
	public ImagePanel getButtonPanel() {
		return this.next_btn;
	}
}
