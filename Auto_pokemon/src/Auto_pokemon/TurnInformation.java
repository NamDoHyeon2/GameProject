package Auto_pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Turn 정보 저장 및 조회
public class TurnInformation {
	private String DB_user = "JUNG";
	private String DB_password = "1234";
	private String DB_url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String sql = "";
	private int user_record_id_pk;
	private int user_id_pk;
	
	public TurnInformation(int user_id_pk) {
		this.user_id_pk = user_id_pk;
		try {
			//user의 마지막 게임 pk값을 저장 -> insert문에 사용하기 위해서
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql = "SELECT USER_RECORD_ID " +
		              "FROM USER_RECORD " +
		              "WHERE USER_ID = ? " +
		              "AND USER_COUNT = (SELECT MAX(USER_COUNT) " +
		                                "FROM USER_RECORD WHERE USER_ID = ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id_pk);
			pstmt.setInt(2, user_id_pk);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				user_record_id_pk = resultSet.getInt(1);
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//한 게임의 한 턴이 끝날때마다 턴 정보를 저장
	public void Input_Turn(int turn_count, int heart_number, int win_number, String result ) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql = "INSERT INTO TURN_INFORMATION(TURN_ID, TURN_RESULT, TURN_COUNT, HEART_NUMBER, WIN_NUMBER, USER_RECORD_ID)"
					+ "VALUES(SEQ_TURN_INFORMATION.NEXTVAL,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, result);
			pstmt.setInt(2, turn_count);
			pstmt.setInt(3, heart_number);
			pstmt.setInt(4, win_number);
			pstmt.setInt(5, user_record_id_pk);
			pstmt.executeUpdate();
			System.out.println("행이 추가되었습니다!");
						
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Show_Turn_Record(int pick_count) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql = "SELECT USER_NAME, USER_COUNT, GAME_RESULT, TURN_COUNT, HEART_NUMBER, WIN_NUMBER, TURN_RESULT "
                    + "FROM TURN_INFORMATION "
                    + "LEFT JOIN USER_RECORD ON TURN_INFORMATION.USER_RECORD_ID = USER_RECORD.USER_RECORD_ID "
                    + "LEFT JOIN USER_INFO ON USER_RECORD.USER_ID = USER_INFO.USER_ID "
                    + "WHERE USER_RECORD.USER_COUNT = ? AND USER_INFO.USER_ID = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pick_count);
			pstmt.setInt(2, user_id_pk);
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				String user_name = resultSet.getString("USER_NAME");
				int user_count = resultSet.getInt("USER_COUNT");
				String game_result = resultSet.getString("GAME_RESULT");
				int turn_count = resultSet.getInt("TURN_COUNT");
				int heart_number = resultSet.getInt("HEART_NUMBER");
				int win_number = resultSet.getInt("WIN_NUMBER");
				String turn_result = resultSet.getString("TURN_RESULT");
				
				System.out.println("유저이름 : " + user_name);
				System.out.println("게임 수 : " + user_count);
				System.out.println("게임 결과 : " + game_result);
				System.out.println("턴 수 : " + turn_count);
				System.out.println("심장 수 : " + heart_number);
				System.out.println("이긴 수 : " + win_number);
				System.out.println("턴 결과 : " + turn_result);
				System.out.println("--------------------------");
			}	
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
