package background;
  
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private Image img;
	
	public ImagePanel(Image img) {
		this.img = img;
		this.setSize(img.getWidth(null), img.getHeight(null));
	}
	
	 public void setImage(Image img) {
	        this.img = img;
	        this.setSize(img.getWidth(null), img.getHeight(null));
	        repaint(); // 패널 다시 그리기
	    }
	
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
				
	}
}
