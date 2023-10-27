package Auto_pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

//계정에 대한 클래스
//인자로 유저의 아이디, 비밀번호, 이름을 입력받음

public class Account {
	private String DB_user = "JUNG";
	private String DB_password = "1234";
	private String DB_url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String login_id;
	private String login_password;
	private String login_name;
	private String sql = "";
	
	public Account(String login_id, String login_password, String login_name) {
		this.login_id = login_id;
		this.login_password = login_password;
		this.login_name = login_name;
	}
	
	//회원가입
	public void join_membership() {
		String sql = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			System.out.println("클래스 로딩 성공!");
			
			sql = "INSERT INTO USER_INFO(USER_ID,LOGIN_ID,LOGIN_PASSWORD,USER_NAME)"
					+ "VALUES(SEQ_USER_INFO.NEXTVAL,?,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, this.login_id);
			pstmt.setString(2, this.login_password);
			pstmt.setString(3, this.login_name);			
			pstmt.executeUpdate();
			
			System.out.println("행을 추가했습니다!");
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			//아이디 또는 이름이 중복됐을때의 예외처리.
			if (e instanceof SQLIntegrityConstraintViolationException) {
                System.out.println("아이디 또는 이름이 중복됩니다!");
            } else {
                e.printStackTrace();
            }
		}
	}
	public boolean duplicate() {
		String sql = "";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			System.out.println("클래스 로딩 성공!");
			
			sql = "SELECT * FROM USER_INFO WHERE LOGIN_ID = ?";
			PreparedStatement pstmt;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login_id);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			if (resultSet.next()) {
                // 결과가 있을 때 처리
                System.out.println("중복됩니다.");
                return false;
                
            } else {
                System.out.println("중복되지 않습니다.");
                return true;
            }				
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
	        return false;
		}catch (SQLException e) {
			e.printStackTrace();
	        return false;
		}
	}
	
	
	//로그인
	public void login() { 
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			System.out.println("클래스 로딩 성공!");
			
			sql = "SELECT LOGIN_ID, LOGIN_PASSWORD FROM USER_INFO";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			//사용자의 아이디 및 비밀번호 확인
			while(resultSet.next()) {
				String login_id = resultSet.getString("LOGIN_ID");
				String login_password = resultSet.getString("LOGIN_PASSWORD");
				
				if(this.login_id.equals(login_id) && this.login_password.equals(login_password)) {
					System.out.println("로그인에 성공하셨습니다.");
				}else {
					System.out.println("일치하는 값이 없는디요?");
				}
			}	
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
