package background;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JSlider;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class option_screen {
	
	int i = 100;
	private int initialX = 0;
	private int initialY = 0;
	public RoundPanel Roundpanel;
	public ImagePanel optionImg;
	public JButton exitBtn;
	public JButton accountDeleteBtn;
	public JButton logoutBtn;
	public JLabel pknumber;
	 
	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
        optionImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
        ImagePanel Soundbar = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\Soundbar.png").getImage());
        ImagePanel Effectbar = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\Effectbar.png").getImage());
        ImageIcon listpanelexitImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit.png");
        Effectbar.setLocation(231, 282);
        Effectbar.setSize(1296, 130);
        Soundbar.setLocation(231, 110);
        Soundbar.setSize(1296, 125);
        Soundbar.setLayout(null);
        ImageIcon SoundImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\Sound.png");
        ImageIcon accountDelete = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\delete.png");
        ImageIcon logout = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\logout.png");
        
        optionImg.setLayout(null);
	    optionImg.setPreferredSize(optionImg.getPreferredSize());
	    
	    Roundpanel = new RoundPanel(40, Color.lightGray);
	    Roundpanel.setBounds(120, 80, 1680, 920);
	    optionImg.add(Roundpanel);
	    Roundpanel.setLayout(null);
	    
	    RoundPanel Roundpanel2 = new RoundPanel(40, Color.WHITE);
	    Roundpanel2.setBounds(50, 616, 514, 269);
	    Roundpanel.add(Roundpanel2);
	    Roundpanel2.setLayout(null);
	    
	    JLabel version = new JLabel("¹öÀü : 1.0.0");
	    version.setFont(new Font("³ª´®°íµñ", Font.BOLD, 30));
	    version.setBounds(12, 88, 160, 35);
	    Roundpanel2.add(version);
	    
	    pknumber = new JLabel();
	    pknumber.setFont(new Font("³ª´®°íµñ", Font.BOLD, 30));
	    pknumber.setBounds(12, 134, 490, 35);
	    Roundpanel2.add(pknumber);
	    
	    exitBtn = new JButton(listpanelexitImg);
	    exitBtn.setLocation(1579, 10);
	    exitBtn.setSize(89, 89);
	    Roundpanel.add(exitBtn);
	    
	    
	    JButton soundImg = new JButton(SoundImg);
	    soundImg.setLocation(50, 110);
	    soundImg.setSize(125, 125);
	    soundImg.setBorderPainted(false); 
	    soundImg.setContentAreaFilled(false);
	    Roundpanel.add(soundImg);
	    Roundpanel.add(Soundbar);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(274, 0, 1022, 125);
	    Soundbar.add(panel_1);
	    panel_1.setLayout(null);
	    
	    JButton soundBarBtn = new JButton();
	    soundBarBtn.setBounds(996, 0, 26, 125);
	    panel_1.add(soundBarBtn);
	    soundBarBtn.setBackground(new Color(0, 0, 0));
	    soundBarBtn.setBorderPainted(false);
	    
	    JPanel soundBarPanel = new JPanel();
	    soundBarPanel.setBounds(0, 2, 996, 125);
	    panel_1.add(soundBarPanel);
	    soundBarPanel.setBackground(new Color(128, 255, 255));
	    soundBarPanel.setLayout(null);


	    Roundpanel.add(Effectbar);
	    Effectbar.setLayout(null);
	    
	    JPanel panel_2 = new JPanel();
	    panel_2.setBounds(274, 1, 1022, 130);
	    Effectbar.add(panel_2);
	    panel_2.setLayout(null);
	    
	    JPanel effectBarPanel = new JPanel();
	    effectBarPanel.setBounds(0, 2, 996, 130);
	    panel_2.add(effectBarPanel);
	    effectBarPanel.setLayout(null);
	    effectBarPanel.setBackground(new Color(128, 255, 255));
	    
	    JButton effectBarBtn = new JButton();
	    effectBarBtn.setBounds(995, 0, 27, 130);
	    panel_2.add(effectBarBtn);
	    effectBarBtn.setBorderPainted(false);
	    effectBarBtn.setBackground(Color.BLACK);
	    
	    
	    JLabel soundBarLabel = new JLabel(i + "%");
	    soundBarLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 40));
	    soundBarLabel.setBounds(1539, 110, 138, 73);
	    Roundpanel.add(soundBarLabel);
	    
	    JLabel effectBarLabel = new JLabel("100%");
	    effectBarLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 40));
	    effectBarLabel.setBounds(1539, 279, 138, 73);
	    Roundpanel.add(effectBarLabel);
	    
	    accountDeleteBtn = new JButton(accountDelete);
	    accountDeleteBtn.setLocation(1386, 745);
	    accountDeleteBtn.setSize(250, 140);
	    accountDeleteBtn.setBorderPainted(false); 
	    accountDeleteBtn.setContentAreaFilled(false);
	    Roundpanel.add(accountDeleteBtn);
	    
	  
	    logoutBtn = new JButton(logout);
	    logoutBtn.setLocation(1050, 745);
	    logoutBtn.setSize(250, 140);
	    logoutBtn.setBorderPainted(false); 
	    logoutBtn.setContentAreaFilled(false);
	    Roundpanel.add(logoutBtn);
	    
	    
	    
	    soundBarBtn.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            initialX = e.getX();
	            initialY = e.getY();
	            
	        }
	    });
        
	    soundBarBtn.addMouseMotionListener(new MouseMotionAdapter() {
	    	
	    	
	        @Override
	        public void mouseDragged(MouseEvent e) {
	        	login_screen ls = new login_screen();
	        	
	            int newX = e.getXOnScreen() - initialX - 628;
	            int newY = e.getYOnScreen() - initialY;

	            int frameWidth = panel_1.getWidth();
	            int frameHeight = panel_1.getHeight();
	            int panelWidth = soundBarBtn.getWidth();
	            int panelHeight = soundBarBtn.getHeight();

	            newX = Math.max(0, Math.min(newX, frameWidth - panelWidth));
	            newY = Math.max(0, Math.min(newY, frameHeight - panelHeight));

	            int deltaX = newX - soundBarBtn.getX();
	            int deltaY = newY - soundBarBtn.getY();

	            int newPanel2Width = soundBarPanel.getWidth() + deltaX;
	            int newPanel2Height = soundBarPanel.getHeight() + deltaY;
	            
	            double percentage = (double) newX / (frameWidth - panelWidth) * 100;

	            i = (int) Math.max(0, Math.min(percentage, 100));
	            soundBarLabel.setText(i + "%");

	            soundBarPanel.setSize(newPanel2Width, newPanel2Height);
	            soundBarBtn.setLocation(newX, newY);
	            
	            float volume = (float) (i / 100.0); 
	            ls.backgroundMusic.setVolume(volume);
	            ls = null;
	        }
	    });
	    
	    effectBarBtn.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            initialX = e.getX();
	            initialY = e.getY();
	            
	        }
	    });
	    
	    effectBarBtn.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {

	            int newX = e.getXOnScreen() - initialX - 628;
	            int newY = e.getYOnScreen() - initialY;

	            int frameWidth = panel_2.getWidth();
	            int frameHeight = panel_2.getHeight();
	            int panelWidth = effectBarBtn.getWidth();
	            int panelHeight = effectBarBtn.getHeight();

	            newX = Math.max(0, Math.min(newX, frameWidth - panelWidth));
	            newY = Math.max(0, Math.min(newY, frameHeight - panelHeight));

	            int deltaX = newX - effectBarBtn.getX();
	            int deltaY = newY - effectBarBtn.getY();

	            int newPanel2Width = effectBarPanel.getWidth() + deltaX;
	            int newPanel2Height = effectBarPanel.getHeight() + deltaY;
	            
	            double percentage = (double) newX / (frameWidth - panelWidth) * 100;

	            i = (int) Math.max(0, Math.min(percentage, 100));
	            effectBarLabel.setText(i + "%");

	            effectBarPanel.setSize(newPanel2Width, newPanel2Height);
	            effectBarBtn.setLocation(newX, newY);
	        }
	    });
	   
	}

	
}

