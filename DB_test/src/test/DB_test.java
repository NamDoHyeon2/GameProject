package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DB_test {
	
	Scanner in = new Scanner(System.in);
	public void login_menu() {
		Scanner in = new Scanner(System.in);
		System.out.println("------------");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("------------");
		int select1 = in.nextInt();
			
		if(select1 == 1) {
			login();
		}else if(select1 == 2) {
			join_membership();
		}else {
			System.out.println("올바르지 않은 선택사항입니다.");
		}
	}
	
	
	public void join_membership() {
		String user = "JUNG";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공!");
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 접속");		
			System.out.println("설정할 아이디를 입력해주세요 : ");
			String user_id = in.next();
			
			// 아이디 중복 체크를 위한 SQL 쿼리 작성
	        String checkSql = "SELECT COUNT(*) FROM USER_INFO WHERE LOGIN_ID = ?";
	        PreparedStatement checkStmt = con.prepareStatement(checkSql);
	        checkStmt.setString(1, user_id);
	        ResultSet resultSet = checkStmt.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        
	        if (count > 0) {
	            System.out.println("아이디가 중복됩니다!");
	            // 중복된 경우 회원가입 메뉴로 돌아갑니다.
	            login_menu();
	            return; // 중복되었으므로 더 이상 진행하지 않음
	        }
	        
			System.out.println("설정할 비밀번호를 입력해주세요 : ");
			String user_password = in.next();
			System.out.println("유저이름을 작성해주세요 : ");
			String user_name = in.next();
			String sql = "INSERT INTO USER_INFO VALUES(SEQ_USER_INFO.NEXTVAL,?,?,?,SYSDATE)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_password);
			pstmt.setString(3, user_name);
			int result = pstmt.executeUpdate();
			System.out.println("회원가입이 완료되었습니다!");
			login_menu();
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void login() {
		String user = "JUNG";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
	        System.out.println("아이디를 입력해주세요 : ");
	        String login_id = in.next();
	        System.out.println("비밀번호를 입력해 주세요 : ");
	        String login_password = in.next();
	        
	        // 로그인을 위한 SQL 쿼리 작성
	        String sql = "SELECT * FROM USER_INFO WHERE LOGIN_ID = ? AND LOGIN_PASSWORD = ?";
	        Connection con = DriverManager.getConnection(url, user, password);
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, login_id);
	        pstmt.setString(2, login_password);
	        ResultSet resultSet = pstmt.executeQuery();

	        if (resultSet.next()) {
	            System.out.println("로그인 성공!");
	            game_select();
	        } else {
	            System.out.println("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
	            login_menu();
	        }

	        resultSet.close();
	        pstmt.close();
	        con.close();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void game_select() {
		System.out.println("------------");
		System.out.println("1. 시작");
		System.out.println("2. 덱 확인");
		System.out.println("3. 기록");
		System.out.println("4. 설정");
		System.out.println("------------");
		
		int user_select = in.nextInt();
		
	if(user_select == 1) {
		
	}else if(user_select == 2) {
		
	}else if(user_select == 3) {
		
	}else if(user_select == 4) {
		
	}
		
	}
	
	public static void main(String[] args) {
		DB_test user = new DB_test();
		user.login_menu();	
		
	}

}
