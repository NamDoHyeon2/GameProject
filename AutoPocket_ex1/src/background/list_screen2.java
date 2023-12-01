package background;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class list_screen2 {
	public JPanel listpanel;
	private JPanel[] pocketPanels = new JPanel[60];
    private JPanel[] textPanels = new JPanel[60];
    public JButton exitBtn;
    
	public void initialize() {
	    ImageIcon nextImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\nextBtn.png");
	    ImageIcon previousImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\previous.png");
	    ImageIcon listpanelexitImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit.png");
	    ImageIcon pocketpanelexitImg = new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit.png");
	    
	    listpanel = new JPanel();
	    listpanel.setBackground(new Color(255, 255, 255));
	    
	    listpanel.setLayout(null);
	    
	    exitBtn = new JButton(listpanelexitImg);
	    exitBtn.setLocation(1670, 0);
	    exitBtn.setSize(50, 50);
	    listpanel.add(exitBtn);
	    
	    JButton exitBtn2 = new JButton(pocketpanelexitImg);
	    exitBtn2.setBounds(1408, 0, 92, 92);
	    
	    exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listpanel.setVisible(false);
				
			}
	    	
	    });
	   
	    
	    for (int i=1; i<=60; i++) {
	    	final int num = i;
	    	ImagePanel PocketImg = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\" + i + ".png").getImage());
	    	ImagePanel PocketImg2 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImg\\" + i + ".png").getImage());
	    	ImagePanel PocketImglv2 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImgLv2\\" + i + ".png").getImage());
	    	ImagePanel PocketImglv3 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\pocketImgLv3\\" + i + ".png").getImage());
	    	JPanel pocketpanel_i = new JPanel();
	    	JPanel textpanel_i = new JPanel();
	    	pocketpanel_i.setLayout(null);
	    	switch(i) {
	    	case 1,7,13,19,25,31,37,43,49,55:
	    		pocketpanel_i.setBounds(42, 31, 751, 266);
	    		break;
	    	case 2,8,14,20,26,32,38,44,50,56:
	    		pocketpanel_i.setBounds(42, 321, 751, 266);
	    		break;
	    	case 3,9,15,21,27,33,39,45,51,57:
	    		pocketpanel_i.setBounds(42, 611, 751, 266);
	    		break;
	    	case 4,10,16,22,28,34,40,46,52,58:
	    		pocketpanel_i.setBounds(900, 31, 751, 266);
	    		break;
	    	case 5,11,17,23,29,35,41,47,53,59:
	    		pocketpanel_i.setBounds(900, 321, 751, 266);
	    		break;
	    	case 6,12,18,24,30,36,42,48,54,60:
	    		pocketpanel_i.setBounds(900, 611, 751, 266);
	    		break;
	    	}
	    	listpanel.add(pocketpanel_i);
	    		
		    textpanel_i.setBounds(292, 5, 453, 256);
		    textpanel_i.setLayout(null);
		    textpanel_i.setBackground(new Color(207, 189, 175));
		    pocketpanel_i.add(textpanel_i);
		    pocketpanel_i.add(PocketImg);
		    PocketImg.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		    PocketImg.setLayout(null);
		    PocketImg.setBounds(12, 5, 271, 256);
		    
		    pocketpanel_i.addMouseListener(new MouseAdapter() {
		    	public void mouseClicked(MouseEvent e) {
		    			
		    		JPanel explanation = new JPanel();
		    	    explanation.setBounds(122, 100, 1500, 750);
		    	    explanation.setBackground(new Color(192, 192, 192));
		    	    listpanel.add(explanation);
		    	    explanation.setLayout(null);
		    	    explanation.add(PocketImg2);
		    	    
		    	    PocketImg2.setLayout(null);
		    	    PocketImg2.setBounds(30, 30, 270, 256);
		    	    PocketImg2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		    
		    	    explanation.add(PocketImglv2);
		    	    PocketImglv2.setLayout(null);
		    	    PocketImglv2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		    	    PocketImglv2.setBounds(350, 30, 270, 256);
		    	    
		    	    explanation.add(PocketImglv3);
		    	    PocketImglv3.setLayout(null);
		    	    PocketImglv3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		    	    PocketImglv3.setBounds(670, 30, 270, 256);
		    	    listpanel.setComponentZOrder(explanation, 0);
		    	    
		    	    explanation.add(exitBtn2);
		    	    
		    	    exitBtn2.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				explanation.setVisible(false);
		    			}
		    	    });
		    	    
		    	    RoundPanel rp = new RoundPanel(40,Color.WHITE);
		            rp.setBounds(30, 320, 1440, 400); 
		            rp.setVisible(true);
		            rp.setLayout(null);
		            explanation.add(rp);
		            explanation.setComponentZOrder(rp, 0);
		            
		            RoundPanel rp2 = new RoundPanel(40, Color.WHITE);
		            rp2.setBounds(975, 30, 400, 111); 
		            rp2.setVisible(true);
		            rp2.setLayout(null);
		            explanation.add(rp2);
		            explanation.setComponentZOrder(rp2, 0);
		            
		            RoundPanel rp3 = new RoundPanel(40, Color.WHITE);
		            rp3.setBounds(975, 175, 400, 111); 
		            rp3.setVisible(true);
		            rp3.setLayout(null);
		            explanation.add(rp3);
		            explanation.setComponentZOrder(rp3 ,0);
		    	    
		            new Thread(new Runnable() {
		                public void run() {
		                    try {
		                    	Class.forName("oracle.jdbc.OracleDriver");
		            	        String url = "jdbc:oracle:thin:@172.16.117.226:1521:xe";
		            	        String user = "JUNG";
		            	        String password = "1234";

		            	        Connection conn = DriverManager.getConnection(url, user, password);
		            	        String sql = "SELECT L1.OBJECT_ID, L1.OBJECT_TYPE, L1.ABILITY AS ABILITY_LEVEL1, " +
		            	                	"L2.ABILITY AS ABILITY_LEVEL2, L3.ABILITY AS ABILITY_LEVEL3 " +
		            	                	"FROM OBJECT_LEVEL1 L1 " +
		            	                	"LEFT JOIN OBJECT_LEVEL2 L2 ON L1.OBJECT_ID = L2.OBJECT_ID " +
		            	                	"LEFT JOIN OBJECT_LEVEL3 L3 ON L1.OBJECT_ID = L3.OBJECT_ID";;
		            	        PreparedStatement pstmt = conn.prepareStatement(sql);
		            	        ResultSet resultSet = pstmt.executeQuery();

		            	        while (resultSet.next()) {
		                            String ID = resultSet.getString("OBJECT_ID");
		                            String Type = resultSet.getString("OBJECT_TYPE");
		                            String Ability1 = resultSet.getString("ABILITY_LEVEL1");
		                            String Ability2 = resultSet.getString("ABILITY_LEVEL2");
		                            String Ability3 = resultSet.getString("ABILITY_LEVEL3");

		                            if (ID.equals(String.valueOf(num))) {
		                                JLabel pocketId = new JLabel("Æ÷ÄÏ¸ó ¹øÈ£ : " + ID);
		                                JLabel pocketType = new JLabel("Æ÷ÄÏ¸ó Å¸ÀÔ : " + Type);
		                                JLabel pocketAbility = new JLabel("<html>LV 1 : " + Ability1 + "<br><br>LV 2 : " + Ability2 + "<br><br>LV 3 : " + Ability3 + "</html>");

		                                pocketId.setBounds(10, 10, 500, 85);
		                                pocketId.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		                                pocketType.setBounds(10, 7, 500, 100);
		                                pocketType.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		                                pocketAbility.setLayout(null);
		                                pocketAbility.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		                                pocketAbility.setBounds(10, 42, 1300, 300);
		                                
		                                rp.add(pocketAbility);
		                                rp2.add(pocketType);
		                                rp3.add(pocketId);

		                                pocketId.setVisible(true);
		                                pocketType.setVisible(true);
		                                pocketAbility.setVisible(true);

		                                SwingUtilities.invokeLater(new Runnable() {
		                                    public void run() {
		                                        listpanel.revalidate();
		                                        listpanel.repaint();
		                                    }
		                                });
		                            }
		                        }
		            	        conn.close();
		                    } catch (ClassNotFoundException e) {
		                        e.printStackTrace();
		                    } catch (SQLException e) {
		                        e.printStackTrace();
		                    }
		                }
		            }).start();
		        }
		    });
		    
		    pocketPanels[i - 1] = pocketpanel_i;
            textPanels[i - 1] = textpanel_i;
	    }
	    
	    input_data();
	    
	    JButton nextBtn_1 = new JButton(nextImg);
	    nextBtn_1.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_1.setBounds(900, 899, 60, 60);
	    nextBtn_1.setBorderPainted(false); 
	    nextBtn_1.setContentAreaFilled(false);
	    listpanel.add(nextBtn_1);
	    
	    JButton nextBtn_2 = new JButton(nextImg);
	    nextBtn_2.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_2.setBounds(900, 899, 60, 60);
	    nextBtn_2.setBorderPainted(false); 
	    nextBtn_2.setContentAreaFilled(false);
	    listpanel.add(nextBtn_2);
	    
	    JButton nextBtn_3 = new JButton(nextImg);
	    nextBtn_3.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_3.setBounds(900, 899, 60, 60);
	    nextBtn_3.setBorderPainted(false); 
	    nextBtn_3.setContentAreaFilled(false);
	    listpanel.add(nextBtn_3);
	    
	    JButton nextBtn_4 = new JButton(nextImg);
	    nextBtn_4.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_4.setBounds(900, 899, 60, 60);
	    nextBtn_4.setBorderPainted(false); 
	    nextBtn_4.setContentAreaFilled(false);
	    listpanel.add(nextBtn_4);
	    
	    JButton nextBtn_5 = new JButton(nextImg);
	    nextBtn_5.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_5.setBounds(900, 899, 60, 60);
	    nextBtn_5.setBorderPainted(false); 
	    nextBtn_5.setContentAreaFilled(false);
	    listpanel.add(nextBtn_5);
	    
	    JButton nextBtn_6 = new JButton(nextImg);
	    nextBtn_6.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_6.setBounds(900, 899, 60, 60);
	    nextBtn_6.setBorderPainted(false); 
	    nextBtn_6.setContentAreaFilled(false);
	    listpanel.add(nextBtn_6);
	    
	    JButton nextBtn_7 = new JButton(nextImg);
	    nextBtn_7.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_7.setBounds(900, 899, 60, 60);
	    nextBtn_7.setBorderPainted(false); 
	    nextBtn_7.setContentAreaFilled(false);
	    listpanel.add(nextBtn_7);
	    
	    JButton nextBtn_8 = new JButton(nextImg);
	    nextBtn_8.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_8.setBounds(900, 899, 60, 60);
	    nextBtn_8.setBorderPainted(false); 
	    nextBtn_8.setContentAreaFilled(false);
	    listpanel.add(nextBtn_8);
	    
	    JButton nextBtn_9 = new JButton(nextImg);
	    nextBtn_9.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_9.setBounds(900, 899, 60, 60);
	    nextBtn_9.setBorderPainted(false); 
	    nextBtn_9.setContentAreaFilled(false);
	    listpanel.add(nextBtn_9);
	    
	    JButton nextBtn_10 = new JButton(nextImg);
	    nextBtn_10.setBorder(new LineBorder(Color.WHITE));
	    nextBtn_10.setBounds(900, 899, 60, 60);
	    nextBtn_10.setBorderPainted(false); 
	    nextBtn_10.setContentAreaFilled(false);
	    listpanel.add(nextBtn_10);
	    
	    JButton previousBtn_1 = new JButton(previousImg);
	    previousBtn_1.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_1.setBounds(733, 899, 60, 60);
	    previousBtn_1.setBorderPainted(false); 
	    previousBtn_1.setContentAreaFilled(false);
	    listpanel.add(previousBtn_1);
	    
	    JButton previousBtn_2 = new JButton(previousImg);
	    previousBtn_2.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_2.setBounds(733, 899, 60, 60);
	    previousBtn_2.setBorderPainted(false); 
	    previousBtn_2.setContentAreaFilled(false);
	    listpanel.add(previousBtn_2);
	    
	    JButton previousBtn_3 = new JButton(previousImg);
	    previousBtn_3.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_3.setBounds(733, 899, 60, 60);
	    previousBtn_3.setBorderPainted(false); 
	    previousBtn_3.setContentAreaFilled(false);
	    listpanel.add(previousBtn_3);
	    
	    JButton previousBtn_4 = new JButton(previousImg);
	    previousBtn_4.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_4.setBounds(733, 899, 60, 60);
	    previousBtn_4.setBorderPainted(false); 
	    previousBtn_4.setContentAreaFilled(false);
	    listpanel.add(previousBtn_4);
	    
	    JButton previousBtn_5 = new JButton(previousImg);
	    previousBtn_5.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_5.setBounds(733, 899, 60, 60);
	    previousBtn_5.setBorderPainted(false); 
	    previousBtn_5.setContentAreaFilled(false);
	    listpanel.add(previousBtn_5);
	    
	    JButton previousBtn_6 = new JButton(previousImg);
	    previousBtn_6.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_6.setBounds(733, 899, 60, 60);
	    previousBtn_6.setBorderPainted(false); 
	    previousBtn_6.setContentAreaFilled(false);
	    listpanel.add(previousBtn_6);
	    
	    JButton previousBtn_7 = new JButton(previousImg);
	    previousBtn_7.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_7.setBounds(733, 899, 60, 60);
	    previousBtn_7.setBorderPainted(false); 
	    previousBtn_7.setContentAreaFilled(false);
	    listpanel.add(previousBtn_7);
	    
	    JButton previousBtn_8 = new JButton(previousImg);
	    previousBtn_8.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_8.setBounds(733, 899, 60, 60);
	    previousBtn_8.setBorderPainted(false); 
	    previousBtn_8.setContentAreaFilled(false);
	    listpanel.add(previousBtn_8);
	    
	    JButton previousBtn_9 = new JButton(previousImg);
	    previousBtn_9.setBorder(new LineBorder(Color.WHITE));
	    previousBtn_9.setBounds(733, 899, 60, 60);
	    previousBtn_9.setBorderPainted(false); 
	    previousBtn_9.setContentAreaFilled(false);
	    listpanel.add(previousBtn_9);
	  
	    JLabel numpage = new JLabel("1/10");
	    numpage.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
	    numpage.setBounds(807, 900, 120, 55);
	    listpanel.add(numpage);
	    
	    nextBtn_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[0].setVisible(false);
			    pocketPanels[1].setVisible(false);
			    pocketPanels[2].setVisible(false);
			    pocketPanels[3].setVisible(false);
			    pocketPanels[4].setVisible(false);
			    pocketPanels[5].setVisible(false);

			    numpage.setText("2/10");
			    previousBtn_1.setVisible(true);

			    pocketPanels[6].setVisible(true);
			    pocketPanels[7].setVisible(true);
			    pocketPanels[8].setVisible(true);
			    pocketPanels[9].setVisible(true);
			    pocketPanels[10].setVisible(true);
			    pocketPanels[11].setVisible(true);
			    nextBtn_1.setVisible(false);
			    nextBtn_2.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[6].setVisible(false);
			    pocketPanels[7].setVisible(false);
			    pocketPanels[8].setVisible(false);
			    pocketPanels[9].setVisible(false);
			    pocketPanels[10].setVisible(false);
			    pocketPanels[11].setVisible(false);
				
				numpage.setText("3/10");
				previousBtn_1.setVisible(false);
				previousBtn_2.setVisible(true);
				
				pocketPanels[12].setVisible(true);
			    pocketPanels[13].setVisible(true);
			    pocketPanels[14].setVisible(true);
			    pocketPanels[15].setVisible(true);
			    pocketPanels[16].setVisible(true);
			    pocketPanels[17].setVisible(true);
			    nextBtn_2.setVisible(false);
			    nextBtn_3.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[12].setVisible(false);
			    pocketPanels[13].setVisible(false);
			    pocketPanels[14].setVisible(false);
			    pocketPanels[15].setVisible(false);
			    pocketPanels[16].setVisible(false);
			    pocketPanels[17].setVisible(false);
			    
			    numpage.setText("4/10");
			    previousBtn_2.setVisible(false);
				previousBtn_3.setVisible(true);
				
				pocketPanels[18].setVisible(true);
			    pocketPanels[19].setVisible(true);
			    pocketPanels[20].setVisible(true);
			    pocketPanels[21].setVisible(true);
			    pocketPanels[22].setVisible(true);
			    pocketPanels[23].setVisible(true);
			    nextBtn_3.setVisible(false);
			    nextBtn_4.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[18].setVisible(false);
			    pocketPanels[19].setVisible(false);
			    pocketPanels[20].setVisible(false);
			    pocketPanels[21].setVisible(false);
			    pocketPanels[22].setVisible(false);
			    pocketPanels[23].setVisible(false);
			    
			    numpage.setText("5/10");
			    previousBtn_3.setVisible(false);
				previousBtn_4.setVisible(true);
				
				pocketPanels[24].setVisible(true);
			    pocketPanels[25].setVisible(true);
			    pocketPanels[26].setVisible(true);
			    pocketPanels[27].setVisible(true);
			    pocketPanels[28].setVisible(true);
			    pocketPanels[29].setVisible(true);
			    nextBtn_4.setVisible(false);
			    nextBtn_5.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[24].setVisible(false);
			    pocketPanels[25].setVisible(false);
			    pocketPanels[26].setVisible(false);
			    pocketPanels[27].setVisible(false);
			    pocketPanels[28].setVisible(false);
			    pocketPanels[29].setVisible(false);
			    
			    numpage.setText("6/10");
			    previousBtn_4.setVisible(false);
				previousBtn_5.setVisible(true);
				
				pocketPanels[30].setVisible(true);
			    pocketPanels[31].setVisible(true);
			    pocketPanels[32].setVisible(true);
			    pocketPanels[33].setVisible(true);
			    pocketPanels[34].setVisible(true);
			    pocketPanels[35].setVisible(true);
			    nextBtn_5.setVisible(false);
			    nextBtn_6.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[30].setVisible(false);
			    pocketPanels[31].setVisible(false);
			    pocketPanels[32].setVisible(false);
			    pocketPanels[33].setVisible(false);
			    pocketPanels[34].setVisible(false);
			    pocketPanels[35].setVisible(false);
			    
			    numpage.setText("7/10");
			    previousBtn_5.setVisible(false);
				previousBtn_6.setVisible(true);
				
				pocketPanels[36].setVisible(true);
			    pocketPanels[37].setVisible(true);
			    pocketPanels[38].setVisible(true);
			    pocketPanels[39].setVisible(true);
			    pocketPanels[40].setVisible(true);
			    pocketPanels[41].setVisible(true);
			    nextBtn_6.setVisible(false);
			    nextBtn_7.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[36].setVisible(false);
			    pocketPanels[37].setVisible(false);
			    pocketPanels[38].setVisible(false);
			    pocketPanels[39].setVisible(false);
			    pocketPanels[40].setVisible(false);
			    pocketPanels[41].setVisible(false);
			    
			    numpage.setText("8/10");
			    previousBtn_6.setVisible(false);
				previousBtn_7.setVisible(true);
				
				pocketPanels[42].setVisible(true);
			    pocketPanels[43].setVisible(true);
			    pocketPanels[44].setVisible(true);
			    pocketPanels[45].setVisible(true);
			    pocketPanels[46].setVisible(true);
			    pocketPanels[47].setVisible(true);
			    nextBtn_7.setVisible(false);
			    nextBtn_8.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[42].setVisible(false);
			    pocketPanels[43].setVisible(false);
			    pocketPanels[44].setVisible(false);
			    pocketPanels[45].setVisible(false);
			    pocketPanels[46].setVisible(false);
			    pocketPanels[47].setVisible(false);
			    
			    numpage.setText("9/10");
			    previousBtn_7.setVisible(false);
				previousBtn_8.setVisible(true);
				
				pocketPanels[48].setVisible(true);
			    pocketPanels[49].setVisible(true);
			    pocketPanels[50].setVisible(true);
			    pocketPanels[51].setVisible(true);
			    pocketPanels[52].setVisible(true);
			    pocketPanels[53].setVisible(true);
			    nextBtn_8.setVisible(false);
			    nextBtn_9.setVisible(true);
			}
	    	
	    });
	    
	    nextBtn_9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[48].setVisible(false);
			    pocketPanels[49].setVisible(false);
			    pocketPanels[50].setVisible(false);
			    pocketPanels[51].setVisible(false);
			    pocketPanels[52].setVisible(false);
			    pocketPanels[53].setVisible(false);
			    
			    numpage.setText("10/10");
			    previousBtn_8.setVisible(false);
				previousBtn_9.setVisible(true);
				
				pocketPanels[54].setVisible(true);
			    pocketPanels[55].setVisible(true);
			    pocketPanels[56].setVisible(true);
			    pocketPanels[57].setVisible(true);
			    pocketPanels[58].setVisible(true);
			    pocketPanels[59].setVisible(true);
			    nextBtn_9.setVisible(false);
			    nextBtn_10.setVisible(false);
			}
	    	
	    });
	    
	    
	    previousBtn_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[0].setVisible(true);
			    pocketPanels[1].setVisible(true);
			    pocketPanels[2].setVisible(true);
			    pocketPanels[3].setVisible(true);
			    pocketPanels[4].setVisible(true);
			    pocketPanels[5].setVisible(true);
				
				numpage.setText("1/10");
				previousBtn_1.setVisible(false);
				
				pocketPanels[6].setVisible(false);
			    pocketPanels[7].setVisible(false);
			    pocketPanels[8].setVisible(false);
			    pocketPanels[9].setVisible(false);
			    pocketPanels[10].setVisible(false);
			    pocketPanels[11].setVisible(false);
				nextBtn_1.setVisible(true);
				
			}
	    
	    });
	    
	    previousBtn_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[6].setVisible(true);
			    pocketPanels[7].setVisible(true);
			    pocketPanels[8].setVisible(true);
			    pocketPanels[9].setVisible(true);
			    pocketPanels[10].setVisible(true);
			    pocketPanels[11].setVisible(true);
				
				numpage.setText("2/10");
				previousBtn_1.setVisible(true);
				previousBtn_2.setVisible(false);
				
				pocketPanels[12].setVisible(false);
			    pocketPanels[13].setVisible(false);
			    pocketPanels[14].setVisible(false);
			    pocketPanels[15].setVisible(false);
			    pocketPanels[16].setVisible(false);
			    pocketPanels[17].setVisible(false);
			    nextBtn_2.setVisible(true);
			    nextBtn_3.setVisible(false);
				
			}
	    
	    });
	    
	    previousBtn_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[12].setVisible(true);
			    pocketPanels[13].setVisible(true);
			    pocketPanels[14].setVisible(true);
			    pocketPanels[15].setVisible(true);
			    pocketPanels[16].setVisible(true);
			    pocketPanels[17].setVisible(true);
				
				numpage.setText("3/10");
				previousBtn_2.setVisible(true);
				previousBtn_3.setVisible(false);
				
				pocketPanels[18].setVisible(false);
			    pocketPanels[19].setVisible(false);
			    pocketPanels[20].setVisible(false);
			    pocketPanels[21].setVisible(false);
			    pocketPanels[22].setVisible(false);
			    pocketPanels[23].setVisible(false);
			    nextBtn_3.setVisible(false);
			    nextBtn_4.setVisible(true);
				
			}
	    
	    });
	    
	    previousBtn_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[18].setVisible(true);
			    pocketPanels[19].setVisible(true);
			    pocketPanels[20].setVisible(true);
			    pocketPanels[21].setVisible(true);
			    pocketPanels[22].setVisible(true);
			    pocketPanels[23].setVisible(true);
				
				numpage.setText("4/10");
				previousBtn_3.setVisible(true);
				previousBtn_4.setVisible(false);
				
				pocketPanels[24].setVisible(false);
			    pocketPanels[25].setVisible(false);
			    pocketPanels[26].setVisible(false);
			    pocketPanels[27].setVisible(false);
			    pocketPanels[28].setVisible(false);
			    pocketPanels[29].setVisible(false);
			    nextBtn_4.setVisible(true);
			    nextBtn_5.setVisible(false);
				
			}
	    
	    });
	    
	    previousBtn_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[24].setVisible(true);
			    pocketPanels[25].setVisible(true);
			    pocketPanels[26].setVisible(true);
			    pocketPanels[27].setVisible(true);
			    pocketPanels[28].setVisible(true);
			    pocketPanels[29].setVisible(true);
				
				numpage.setText("5/10");
				previousBtn_4.setVisible(true);
				previousBtn_5.setVisible(false);
				
				pocketPanels[30].setVisible(false);
			    pocketPanels[31].setVisible(false);
			    pocketPanels[32].setVisible(false);
			    pocketPanels[33].setVisible(false);
			    pocketPanels[34].setVisible(false);
			    pocketPanels[35].setVisible(false);
			    nextBtn_5.setVisible(true);
			    nextBtn_6.setVisible(false);
				
			}
	    
	    });
	    
	    previousBtn_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[30].setVisible(true);
			    pocketPanels[31].setVisible(true);
			    pocketPanels[32].setVisible(true);
			    pocketPanels[33].setVisible(true);
			    pocketPanels[34].setVisible(true);
			    pocketPanels[35].setVisible(true);
				
				numpage.setText("6/10");
				previousBtn_5.setVisible(true);
				previousBtn_6.setVisible(false);
				
				pocketPanels[36].setVisible(false);
			    pocketPanels[37].setVisible(false);
			    pocketPanels[38].setVisible(false);
			    pocketPanels[39].setVisible(false);
			    pocketPanels[40].setVisible(false);
			    pocketPanels[41].setVisible(false);
			    nextBtn_6.setVisible(true);
			    nextBtn_7.setVisible(false);
				
			}
	    
	    });
	    
	    previousBtn_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[36].setVisible(true);
			    pocketPanels[37].setVisible(true);
			    pocketPanels[38].setVisible(true);
			    pocketPanels[39].setVisible(true);
			    pocketPanels[40].setVisible(true);
			    pocketPanels[41].setVisible(true);
				
				numpage.setText("7/10");
				previousBtn_6.setVisible(true);
				previousBtn_7.setVisible(false);
				
				pocketPanels[42].setVisible(false);
			    pocketPanels[43].setVisible(false);
			    pocketPanels[44].setVisible(false);
			    pocketPanels[45].setVisible(false);
			    pocketPanels[46].setVisible(false);
			    pocketPanels[47].setVisible(false);
			    nextBtn_7.setVisible(true);
			    nextBtn_8.setVisible(false);
				
			}
	    
	    });
	    
	    previousBtn_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[42].setVisible(true);
			    pocketPanels[43].setVisible(true);
			    pocketPanels[44].setVisible(true);
			    pocketPanels[45].setVisible(true);
			    pocketPanels[46].setVisible(true);
			    pocketPanels[47].setVisible(true);
				
				numpage.setText("8/10");
				previousBtn_7.setVisible(true);
				previousBtn_8.setVisible(false);
				
				pocketPanels[48].setVisible(false);
			    pocketPanels[49].setVisible(false);
			    pocketPanels[50].setVisible(false);
			    pocketPanels[51].setVisible(false);
			    pocketPanels[52].setVisible(false);
			    pocketPanels[53].setVisible(false);
			    nextBtn_8.setVisible(true);
			    nextBtn_9.setVisible(false);
				
			}
	    
	    });
	    
	    previousBtn_9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pocketPanels[48].setVisible(true);
			    pocketPanels[49].setVisible(true);
			    pocketPanels[50].setVisible(true);
			    pocketPanels[51].setVisible(true);
			    pocketPanels[52].setVisible(true);
			    pocketPanels[53].setVisible(true);
				
				numpage.setText("9/10");
				previousBtn_8.setVisible(true);
				previousBtn_9.setVisible(false);
				
				pocketPanels[54].setVisible(false);
			    pocketPanels[55].setVisible(false);
			    pocketPanels[56].setVisible(false);
			    pocketPanels[57].setVisible(false);
			    pocketPanels[58].setVisible(false);
			    pocketPanels[59].setVisible(false);
			    nextBtn_9.setVisible(true);
			    nextBtn_10.setVisible(false);
				
			}
	    
	    });
	    
	    
	}
	
	public void input_data() {
	    try {
	        int i = 1;
	        Class.forName("oracle.jdbc.OracleDriver");
	        String url = "jdbc:oracle:thin:@172.16.117.226:1521:xe";
	        String user = "JUNG";
	        String password = "1234";

	        Connection con = DriverManager.getConnection(url, user, password);
	        String sql = "SELECT OBJECT_ID, OBJECT_NAME, OBJECT_TYPE, GRADE, DAMAGE, STAMINA FROM OBJECT_LEVEL1";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        ResultSet resultSet = pstmt.executeQuery();

	        while (resultSet.next()) {
	            JTextArea text_i = new JTextArea();
	            String name = resultSet.getString("OBJECT_NAME");
	            String type = resultSet.getString("OBJECT_TYPE");
	            int pk = resultSet.getInt("OBJECT_ID");
	            int grade = resultSet.getInt("GRADE");
	            int damage = resultSet.getInt("DAMAGE");
	            int stamina = resultSet.getInt("STAMINA");

	            text_i.setText("ÀÌ¸§ : " + name + "\r\n\r\nÅ¸ÀÔ : " + type + "\r\n\r\nµî±Þ : " + grade + "\r\n\r\n°ø°Ý·Â : " + damage + "\r\n\r\nÃ¼·Â : "  + stamina);
	            text_i.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
	            text_i.setEditable(false);
	            text_i.setBackground(new Color(207, 189, 175));
	            text_i.setBounds(0, 0, 447, 256);

	            if (pk >= 1 && pk <= 60) {
                    textPanels[pk - 1].add(text_i);
                }
	        }
	        con.close();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
