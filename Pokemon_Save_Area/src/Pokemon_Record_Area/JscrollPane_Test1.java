package Pokemon_Record_Area;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

public class JscrollPane_Test1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JscrollPane_Test1 frame = new JscrollPane_Test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JscrollPane_Test1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setBounds(172, 67, 1600, 900);
		
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);		
		
		JPanel mainpanel = new JPanel();
		mainpanel.setBackground(new Color(192, 192, 192));
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		mainpanel.setLayout(gridBagLayout);
		
		int row = 0;
		for (int i = 1; i < 100; i ++) {
			constraints.insets = new Insets(10, 0, 10, 0);
			constraints.fill = GridBagConstraints.VERTICAL;
	        constraints.weighty = 1.0;
	        constraints.gridx = 0;
	        constraints.gridy = row;
			
			ImagePanel ImagePanel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\record_test3.png").getImage());
			ImagePanel.setPreferredSize(new Dimension(1300, 260));
			
			gridBagLayout.setConstraints(ImagePanel, constraints);
			mainpanel.add(ImagePanel);
			row++;
		}
		scrollpane.setViewportView(mainpanel);
		contentPane.add(scrollpane);
	}
}
