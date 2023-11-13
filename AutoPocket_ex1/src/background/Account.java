package background;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

 
public class Account {
	private String DB_user = "SCOTT";
	private String DB_password = "scott";
	private String DB_url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String login_id;
	private String login_password;
	private String login_name;
	private String sql = "";
	
	public String getLogin_id() {
        return login_id;
    }



    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }



    public String getLogin_password() {
        return login_password;
    }



    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }



    public String getLogin_name() {
        return login_name;
    }



    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    
	
public void join_membership() {
    String sql = "";
    try {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);

        sql = "INSERT INTO USER_INFO(USER_ID,LOGIN_ID,LOGIN_PASSWORD,USER_NAME)"
                + "VALUES(SEQ_USER_INFO.NEXTVAL,?,?,?)";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, this.login_id);
        pstmt.setString(2, this.login_password);
        pstmt.setString(3, this.login_name);
        pstmt.executeUpdate();


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
	
public boolean login() { 
    try {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);

        sql = "SELECT LOGIN_ID, LOGIN_PASSWORD FROM USER_INFO";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();

        // 사용자의 아이디 및 비밀번호 확인
        while (resultSet.next()) {
            String login_id = resultSet.getString("LOGIN_ID");
            String login_password = resultSet.getString("LOGIN_PASSWORD");

            if (this.login_id.equals(login_id) && this.login_password.equals(login_password)) {
                System.out.println("로그인에 성공하셨습니다.");
                return true;
            }
        }

        // 일치하는 값이 없을 때
        System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
        return false;
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // 오류 발생 시도
    return false;
	}
}

