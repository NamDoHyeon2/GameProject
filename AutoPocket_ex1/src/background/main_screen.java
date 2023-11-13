package background;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;

public class main_screen {

	private JFrame frmAutoPockmon;
	public ImagePanel loginImg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_screen window = new main_screen();
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
	public main_screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmAutoPockmon = new JFrame();
		frmAutoPockmon.setTitle("Auto Pockmon");
		frmAutoPockmon.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frmAutoPockmon.setUndecorated(true);
        
        loginImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
        ImagePanel IDImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\ID.png").getImage());
        ImageIcon playImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\play.png");
        ImageIcon optionImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\option.png");
        ImageIcon recordImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\record.png");
        ImageIcon pocketlistImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\pocketmonlist.png");
        ImageIcon helpImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\Help.png");
        ImageIcon exitImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit3.png");

        
        loginImg.setLayout(null);
        
        loginImg.setPreferredSize(loginImg.getPreferredSize());
        
        frmAutoPockmon.getContentPane().add(loginImg);
        loginImg.add(IDImg);
        IDImg.setLocation(71, 77);
        
        JButton playButton = new JButton(playImg);
        playButton.setBounds(696, 77, 635, 259);
        loginImg.add(playButton);
        
        JButton listButton = new JButton(pocketlistImg);
        listButton.setBounds(696, 455, 635, 123);
        loginImg.add(listButton);
        
        JButton recordButton = new JButton(recordImg);
        recordButton.setBounds(696, 685, 635, 123);
        loginImg.add(recordButton);
        
        JButton optionButton = new JButton(optionImg);
        optionButton.setBounds(696, 918, 635, 123);
        loginImg.add(optionButton);
        
        JButton helpButton = new JButton(helpImg);
        helpButton.setBounds(71, 918, 374, 123);
        loginImg.add(helpButton);
        
        JButton exitbutton = new JButton(exitImg);
        exitbutton.setForeground(new Color(30, 144, 255));
        exitbutton.setSize(50, 50);
        exitbutton.setLocation(1860, 10);
        loginImg.add(exitbutton);
        
        playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IDImg.setVisible(false);
				playButton.setVisible(false);
				listButton.setVisible(false);
				recordButton.setVisible(false);
				optionButton.setVisible(false);
				helpButton.setVisible(false);
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
       
        optionButton.addActionListener(new ActionListener() {

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
				
			};
        
        });
        
        exitbutton.addActionListener(new ActionListener(){
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
        	
        });
        
        frmAutoPockmon.pack();
        frmAutoPockmon.setVisible(true);
	}

}
