package Auto_pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GameCount {
	private String DB_user = "JUNG";
	private String DB_password = "1234";
	private String DB_url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String sql = "";
	private int user_id_pk;
	private int max_count = 0;
	
	public GameCount(int user_id_pk) {
		this.user_id_pk = user_id_pk;
		//사용자의 마지막 게임횟수를 저장
		//생성자에선 USER_INFO 의 PK값을 매개변수로 가짐
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql = "SELECT MAX(USER_COUNT) FROM USER_RECORD WHERE USER_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id_pk);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				max_count = resultSet.getInt(1);
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//전체 게임이 시작할 때 기록
	public void Input_Record() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql = "INSERT INTO USER_RECORD(USER_RECORD_ID,USER_COUNT,USER_ID)"
					+ "VALUES(SEQ_USER_RECORD.NEXTVAL,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, max_count + 1);
			pstmt.setInt(2, user_id_pk);
			pstmt.executeUpdate();
			System.out.println("행이 추가되었습니다!");
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//전체 게임이 끝나고 승, 패를 업데이트
	//매개변수로 승리 또는 패배를 가짐
	public void Result_Update(String game_result) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql = "UPDATE USER_RECORD "
				    + "SET GAME_RESULT = ? " /*또는 '패' 등 원하는 값을 설정*/
					+ "WHERE USER_COUNT = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, game_result);
			pstmt.setInt(2, max_count);
			pstmt.executeUpdate();
			System.out.println("행이 수정되었습니다!");
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//유저의 게임수 조회
	public void Show_Record() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			//JOIN으로 묶어줌
			sql = "SELECT USER_RECORD.USER_COUNT, USER_RECORD.GAME_RESULT, USER_INFO.USER_NAME " 
				    + "FROM USER_RECORD LEFT JOIN USER_INFO ON "
				    + "USER_RECORD.USER_ID = USER_INFO.USER_ID "
				    + "WHERE USER_INFO.USER_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id_pk);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				String user_name = resultSet.getString("USER_NAME");
				int user_count = resultSet.getInt("USER_COUNT");
				String game_result = resultSet.getString("GAME_RESULT");
				
				System.out.println("유저이름 : " + user_name);
				System.out.println("게임 수 : " + user_count);
				System.out.println("결과 : " + game_result);
				System.out.println("---------------------");
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
