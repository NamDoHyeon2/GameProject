package Pokemon_Record_Area;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_select_test {
	String DB_user = "JUNG";
	String DB_password = "1234";
	String DB_url = "jdbc:oracle:thin:@localhost:1521:xe";

	public static void main(String[] args) {
		DB_select_test user_db = new DB_select_test();
		String user = user_db.DB_user;
		String pass = user_db.DB_password;
		String url = user_db.DB_url;
		String sql = "";
		
		/*
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("클래스 로딩 성공!");
			sql = "SELECT * FROM GAME_RECORD";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
	        while (resultSet.next()) {
	        	 	int recordId = resultSet.getInt("RECORD_ID");
	                int gameCount = resultSet.getInt("GAME_COUNT");
	                String gameResult = resultSet.getString("GAME_RESULT");
	                int gameEndHeart = resultSet.getInt("GAME_END_HEART");
	                int gameEndTurn = resultSet.getInt("GAME_END_TURN");
	                java.sql.Date gameEndDate = resultSet.getDate("GAME_END_DATE");
	                String date = gameEndDate.toString();
	                
	                System.out.println("Record ID: " + recordId);
	                System.out.println("Game Count: " + gameCount);
	                System.out.println("Game Result: " + gameResult);
	                System.out.println("Game End Heart: " + gameEndHeart);
	                System.out.println("Game End Turn: " + gameEndTurn);
	                System.out.println("Game End Date: " + date);
	                System.out.println("--------------------------------");
            }
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		try {
			int userid = 1;
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("클래스 로딩 성공!");
			  sql =  "SELECT UR.USER_ID, UR.RECORD_ID, RL.LOCATION1_OBJ, RL.LOCATION2_OBJ, RL.LOCATION3_OBJ, RL.LOCATION4_OBJ, RL.LOCATION5_OBJ, " +
		                 "GR.GAME_COUNT, GR.GAME_RESULT, GR.GAME_END_HEART, GR.GAME_END_TURN, GR.GAME_END_DATE " +
		                 "FROM USER_RECORD UR " +
		                 "INNER JOIN RECORD_LOCATION RL ON UR.RECORD_ID = RL.RECORD_ID " +
		                 "INNER JOIN GAME_RECORD GR ON UR.RECORD_ID = GR.RECORD_ID " +
		                 "WHERE UR.USER_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userid);
			ResultSet resultSet = pstmt.executeQuery();
			
			 while (resultSet.next()) {
			        int user_id = resultSet.getInt("USER_ID");
			        int record_id = resultSet.getInt("RECORD_ID");
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

			        System.out.println("USER_ID: " + user_id);
			        System.out.println("RECORD_ID : " + record_id);
			        System.out.println("Game Count: " + gameCount);
			        System.out.println("Game Result: " + gameResult);
			        System.out.println("Game End Heart: " + gameEndHeart);
			        System.out.println("Game End Turn: " + gameEndTurn);
			        System.out.println("Game End Date: " + date);
			        System.out.println("");
			        System.out.println("Location 1: " + location1);
			        System.out.println("Location 2: " + location2);
			        System.out.println("Location 3: " + location3);
			        System.out.println("Location 4: " + location4);
			        System.out.println("Location 5: " + location5);
			        System.out.println("--------------------------------");
			    } 
			    con.close(); // 연결 닫기
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
