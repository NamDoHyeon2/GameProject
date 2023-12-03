package background;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Record_Screen2 extends JPanel {
	String DB_user = "JUNG";
	String DB_password = "1234";
	String DB_url = "jdbc:oracle:thin:@localhost:1521:xe";
	public int user_pk;
	public JButton exit_btn;
	public JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public Record_Screen2(int user_pk) {
		DB_select_test user_db = new DB_select_test();
		String user = user_db.DB_user;
		String pass = user_db.DB_password;
		String url = user_db.DB_url;
		String sql = "";
		this.user_pk = user_pk;
		
		this.setLayout(null);
		this.setBounds(0, 0, 1920, 1080);
		
		//배경 설정
		ImagePanel background_panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\login_background.png").getImage());
		this.add(background_panel);
		background_panel.setPreferredSize(new Dimension(1920, 1080));
		background_panel.setLayout(null);
		
		//스크롤 생성
		scrollPane = new JScrollPane();
		scrollPane.setBounds(219, 49, 1551, 951);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		background_panel.add(scrollPane);
		
		//레아웃 설정 패널
		JPanel main_panel = new JPanel();
		main_panel.setBackground(new Color(255,250,240));
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		main_panel.setLayout(gridBagLayout);
		scrollPane.setViewportView(main_panel);
		
		//나가기버튼
		JPanel exit_Panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Image\\exit.png").getImage());;
		exit_Panel.setPreferredSize(new Dimension(90, 90));
		constraints.insets = new Insets(20, 0, 10, 0);
		constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weighty = 1.0;
        constraints.gridx = 1;
        constraints.gridy = 0;
        gridBagLayout.setConstraints(exit_Panel, constraints);
		main_panel.add(exit_Panel);	
		exit_Panel.setLayout(null);
		exit_btn = new JButton("");
		exit_btn.setBounds(0, 0, 90, 90);
		exit_btn.setOpaque(false);
		exit_btn.setContentAreaFilled(false);
		exit_btn.setBorderPainted(false);
		exit_Panel.add(exit_btn);
		
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("클래스 로딩 성공!");
			sql = "SELECT UR.USER_ID, UR.RECORD_ID, RL.LOCATION1_OBJ, RL.LOCATION2_OBJ, RL.LOCATION3_OBJ, RL.LOCATION4_OBJ, RL.LOCATION5_OBJ, " +
	                 "GR.GAME_COUNT, GR.GAME_RESULT, GR.GAME_END_HEART, GR.GAME_END_TURN, GR.GAME_END_DATE " +
	                 "FROM USER_RECORD UR " +
	                 "INNER JOIN RECORD_LOCATION RL ON UR.RECORD_ID = RL.RECORD_ID " +
	                 "INNER JOIN GAME_RECORD GR ON UR.RECORD_ID = GR.RECORD_ID " +
	                 "WHERE UR.USER_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_pk);
			ResultSet resultSet = pstmt.executeQuery();
			
			int first_row_height = 10;
			int row = 1;
	        while (resultSet.next()) {
	        	 	int recordId = resultSet.getInt("RECORD_ID");
	                int gameCount = resultSet.getInt("GAME_COUNT");
	                String gameResult = resultSet.getString("GAME_RESULT");
	                int gameEndHeart = resultSet.getInt("GAME_END_HEART");
	                int gameEndTurn = resultSet.getInt("GAME_END_TURN");
	                java.sql.Date gameEndDate = resultSet.getDate("GAME_END_DATE");
	                String date = gameEndDate.toString();
	                int location1 = resultSet.getInt("LOCATION1_OBJ");
			        int location2 = resultSet.getInt("LOCATION2_OBJ");
			        int location3 = resultSet.getInt("LOCATION3_OBJ");
			        int location4 = resultSet.getInt("LOCATION4_OBJ");
			        int location5 = resultSet.getInt("LOCATION5_OBJ");
	                
	                constraints.insets = new Insets(first_row_height, 0, 10, 0);
	    			constraints.fill = GridBagConstraints.VERTICAL;
	    	        constraints.weighty = 1.0;
	    	        constraints.gridx = 0;
	    	        constraints.gridy = row;
	    	        
	    			ImagePanel Record_Panel = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\record_test3.png").getImage());
	    			Record_Panel.setPreferredSize(new Dimension(1300, 260));	
	    			Record_Panel.setLayout(null);
	    			
	    			ImagePanel location_5 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\" + location1 +".png").getImage());
	    			location_5.setBounds(347, 93, 155, 155);
	    			Record_Panel.add(location_5);

	    			ImagePanel location_4 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\" + location2 +".png").getImage());
	    			location_4.setBounds(524, 93, 155, 155);
	    			Record_Panel.add(location_4);

	    			ImagePanel location_3 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\" + location3 +".png").getImage());
	    			location_3.setBounds(691, 93, 155, 155);
	    			Record_Panel.add(location_3);

	    			ImagePanel location_2 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\" + location4 +".png").getImage());
	    			location_2.setBounds(858, 93, 155, 155);
	    			Record_Panel.add(location_2);

	    			ImagePanel location_1 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\" + location5 +".png").getImage());
	    			location_1.setBounds(180, 93, 155, 155);
	    			Record_Panel.add(location_1);

	    			JButton Detail_btm = new JButton("");
	    			Detail_btm.setOpaque(false);
	    			Detail_btm.setContentAreaFilled(false);
	    			Detail_btm.setBorderPainted(false);
	    			Detail_btm.setBounds(75, 122, 97, 95);
	    			Detail_btm.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
	    					System.out.println("버튼을 누르셨네요!");
	    				}
	    			});
	    			
	    			Record_Panel.add(Detail_btm);

	    			JLabel lblNewLabel = new JLabel(date);
	    			lblNewLabel.setFont(new Font("굴림", Font.BOLD, 33));
	    			lblNewLabel.setBounds(33, 10, 595, 62);
	    			Record_Panel.add(lblNewLabel);

	    			JLabel lblNewLabel_1 = new JLabel(gameResult);
	    			lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 56));
	    			lblNewLabel_1.setBounds(1208, 9, 70, 63);
	    			Record_Panel.add(lblNewLabel_1);

	    			JLabel lblNewLabel_2 = new JLabel(": " + gameEndHeart);
	    			lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 60));
	    			lblNewLabel_2.setBounds(1146, 96, 132, 62);
	    			Record_Panel.add(lblNewLabel_2);

	    			JLabel lblNewLabel_2_1 = new JLabel(": " + gameEndTurn);
	    			lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 60));
	    			lblNewLabel_2_1.setBounds(1146, 168, 132, 87);
	    			Record_Panel.add(lblNewLabel_2_1);
	    					
	    			gridBagLayout.setConstraints(Record_Panel, constraints);
	    			main_panel.add(Record_Panel);
	    			row++;
	    			first_row_height = 10;
            }
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
