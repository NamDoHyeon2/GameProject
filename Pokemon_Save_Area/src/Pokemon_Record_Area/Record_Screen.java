package Pokemon_Record_Area;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Pokemon_Record_Area.ImagePanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Insets;

public class Record_Screen extends JFrame {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Record_Screen frame = new Record_Screen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Record_Screen() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("RECORD_SCREEN");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// frame.setUndecorated(true);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);

		ImagePanel background_panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
		background_panel.setBounds(0, 0, 1920, 1080);
		background_panel.setPreferredSize(new Dimension(1920, 1080));
		frame.getContentPane().add(background_panel);
		background_panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(157, 49, 1600, 900);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JPanel main_panel = new JPanel();
		main_panel.setBackground(new Color(255, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		main_panel.setLayout(gridBagLayout);
		
		int row = 0;
		for(int i = 0; i < 100; i ++) {
			constraints.insets = new Insets(10, 0, 10, 0);
			constraints.fill = GridBagConstraints.VERTICAL;
	        constraints.weighty = 1.0;
	        constraints.gridx = 0;
	        constraints.gridy = row;
	        
			ImagePanel Record_Panel = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\record_test3.png").getImage());
			Record_Panel.setPreferredSize(new Dimension(1300, 260));
			
			Record_Panel.setLayout(null);

			ImagePanel location_5 = new ImagePanel(
					new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\01_캐터피_record.png")
							.getImage());
			location_5.setBounds(347, 93, 155, 155);
			Record_Panel.add(location_5);

			ImagePanel location_4 = new ImagePanel(
					new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\01_캐터피_record.png")
							.getImage());
			location_4.setBounds(524, 93, 155, 155);
			Record_Panel.add(location_4);

			ImagePanel location_3 = new ImagePanel(
					new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\01_캐터피_record.png")
							.getImage());
			location_3.setBounds(691, 93, 155, 155);
			Record_Panel.add(location_3);

			ImagePanel location_2 = new ImagePanel(
					new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\01_캐터피_record.png")
							.getImage());
			location_2.setBounds(858, 93, 155, 155);
			Record_Panel.add(location_2);

			ImagePanel location_1 = new ImagePanel(
					new ImageIcon("C:\\Project\\GameProject-DB_feature\\AutoPocket_ex1\\src\\Image\\01_캐터피_record.png")
							.getImage());
			location_1.setBounds(180, 93, 155, 155);
			Record_Panel.add(location_1);

			JButton btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("버튼을 누르셨네요!");
				}
			});

			btnNewButton.setOpaque(false);
			btnNewButton.setContentAreaFilled(false);
			btnNewButton.setBorderPainted(false);
			btnNewButton.setBounds(75, 122, 97, 95);
			Record_Panel.add(btnNewButton);

			JLabel lblNewLabel = new JLabel("2023년 11월 5일");
			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 33));
			lblNewLabel.setBounds(33, 10, 595, 62);
			Record_Panel.add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel("승");
			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 56));
			lblNewLabel_1.setBounds(1208, 9, 70, 63);
			Record_Panel.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel(": 7");
			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 60));
			lblNewLabel_2.setBounds(1146, 96, 132, 62);
			Record_Panel.add(lblNewLabel_2);

			JLabel lblNewLabel_2_1 = new JLabel(": 16");
			lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 60));
			lblNewLabel_2_1.setBounds(1146, 168, 132, 87);
			Record_Panel.add(lblNewLabel_2_1);
					
			gridBagLayout.setConstraints(Record_Panel, constraints);
			main_panel.add(Record_Panel);
			row++;
		}
		background_panel.add(scrollPane);
		scrollPane.setViewportView(main_panel);
	}
}
