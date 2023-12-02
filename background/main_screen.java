package background;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;

public class main_screen {

	public JFrame frmAutoPockmon;
	public ImagePanel loginImg;
	public ImagePanel IDImg;
	public JButton playButton;
	public JButton listButton;
	public JButton recordButton;
	public JButton optionButton;
	public JButton helpButton;
	public JButton exitbutton;
	public JLabel nicknameLabel;
	
	private static int pk;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_screen window = new main_screen(pk);
					window.frmAutoPockmon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main_screen(int pk) {
		initialize();
		this.pk = pk;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		
        loginImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
        IDImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\ID.png").getImage());
        ImageIcon playImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\play.png");
        ImageIcon optionImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\option.png");
        ImageIcon recordImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\record.png");
        ImageIcon pocketlistImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\pocketmonlist.png");
        ImageIcon helpImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\Help.png");
        ImageIcon exitImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit.png");
        
        loginImg.setLayout(null);
        
        loginImg.setPreferredSize(loginImg.getPreferredSize());
        
        IDImg.setBounds(new Rectangle(70, 77, 378, 127));
        loginImg.add(IDImg);
        IDImg.setLayout(null);
        
        nicknameLabel = new JLabel();
        nicknameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 45));
        nicknameLabel.setBounds(120, 35, 260, 41);
        IDImg.add(nicknameLabel);
        
        
        playButton = new JButton(playImg);
        playButton.setBounds(696, 77, 635, 259);
        loginImg.add(playButton);
        
        listButton = new JButton(pocketlistImg);
        listButton.setBounds(696, 455, 635, 123);
        loginImg.add(listButton);
        
        recordButton = new JButton(recordImg);
        recordButton.setBounds(696, 685, 635, 123);
        loginImg.add(recordButton);
        
        optionButton = new JButton(optionImg);
        optionButton.setBounds(696, 918, 635, 123);
        loginImg.add(optionButton);
        
        helpButton = new JButton(helpImg);
        helpButton.setLocation(77, 455);
        helpButton.setSize(371, 120);
        loginImg.add(helpButton);
        
        exitbutton = new JButton(exitImg);
        exitbutton.setForeground(new Color(30, 144, 255));
        exitbutton.setSize(84, 84);
        exitbutton.setLocation(1836, 0);
        exitbutton.setBorderPainted(false); 
        exitbutton.setContentAreaFilled(false);
        loginImg.add(exitbutton);
        
        
        
        playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Placement_Screen ps= new Placement_Screen();
				loginImg.setVisible(false);
				ps.placementbackground.setVisible(true);
			}
        	
        });
        
        listButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IDImg.setVisible(false);
				playButton.setVisible(false);
				listButton.setVisible(false);
				recordButton.setVisible(false);
				optionButton.setVisible(false);
				helpButton.setVisible(false);
				
				list_screen2 ls = new list_screen2();
				ls.initialize();
				loginImg.add(ls.listpanel);
				ls.listpanel.setBounds(106, 51, 1720, 980);
				
				ls.exitBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ls.listpanel.setVisible(false);
						IDImg.setVisible(true);
						playButton.setVisible(true);
						listButton.setVisible(true);
						recordButton.setVisible(true);
						optionButton.setVisible(true);
						helpButton.setVisible(true);
					}
					
				});
			};
        
        });
       
        recordButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IDImg.setVisible(false);
				playButton.setVisible(false);
				listButton.setVisible(false);
				recordButton.setVisible(false);
				optionButton.setVisible(false);
				helpButton.setVisible(false);
				
			};
        
        });
       
        
        
        helpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IDImg.setVisible(false);
				playButton.setVisible(false);
				listButton.setVisible(false);
				recordButton.setVisible(false);
				optionButton.setVisible(false);
				helpButton.setVisible(false);
				
				help_screen hs = new help_screen();
				loginImg.add(hs.helppanel);
				hs.helppanel.setBounds(120, 80, 1680, 920);
				
				hs.exitBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						hs.helppanel.setVisible(false);
						IDImg.setVisible(true);
						playButton.setVisible(true);
						listButton.setVisible(true);
						recordButton.setVisible(true);
						optionButton.setVisible(true);
						helpButton.setVisible(true);
						
					}
				
				});
			}
        
        });
        
        exitbutton.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
        	
        });
        

	}
}
