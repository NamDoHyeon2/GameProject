package background;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

 
public class Account {
	private String DB_user = "JUNG";
	private String DB_password = "1234";
	private String DB_url = "jdbc:oracle:thin:@119.195.135.42:1521:xe";
	private String login_id;
	private String login_password;
	private String login_name;
	private String sql = "";
	private int pk;
	
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
    
    public int getPk() {
		return pk;
	}



	public void setPk(int pk) {
		this.pk = pk;
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
	        //?•„?´?”” ?˜?Š” ?´ë¦„ì´ ì¤‘ë³µ??„?•Œ?˜ ?˜ˆ?™¸ì²˜ë¦¬.
	        if (e instanceof SQLIntegrityConstraintViolationException) {
	            System.out.println("?•„?´?”” ?˜?Š” ?´ë¦„ì´ ì¤‘ë³µ?©?‹ˆ?‹¤!");
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
	
	        // ?‚¬?š©??˜ ?•„?´?”” ë°? ë¹„ë?ë²ˆí˜¸ ?™•?¸
	        while (resultSet.next()) {
	            String login_id = resultSet.getString("LOGIN_ID");
	            String login_password = resultSet.getString("LOGIN_PASSWORD");
	
	            if (this.login_id.equals(login_id) && this.login_password.equals(login_password)) {
	                System.out.println("ë¡œê·¸?¸?— ?„±ê³µí•˜?…¨?Šµ?‹ˆ?‹¤.");
	                return true;
	            }
	        }
	
	        // ?¼ì¹˜í•˜?Š” ê°’ì´ ?—†?„ ?•Œ
	        System.out.println("?•„?´?”” ?˜?Š” ë¹„ë?ë²ˆí˜¸ê°? ?¼ì¹˜í•˜ì§? ?•Š?Šµ?‹ˆ?‹¤.");
	        return false;
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	
	    // ?˜¤ë¥? ë°œìƒ ?‹œ?„
	    return false;
		}
	
	public int pknum(String login_id, String login_password) {
		
	    try {
	        Class.forName("oracle.jdbc.OracleDriver");
	
	        Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
	        String sql = "SELECT USER_ID FROM USER_INFO WHERE LOGIN_ID = ? AND LOGIN_PASSWORD = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, this.login_id);
	        pstmt.setString(2, this.login_password);
	
	        ResultSet resultSet = pstmt.executeQuery();
	
	        if (resultSet.next()) {
	        	pk = resultSet.getInt("USER_ID");
	        }
	    } catch (ClassNotFoundException e) { 
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return pk;
	}
	
	public String getNickname(int pk) {
	    String nickname = null;

	    try {
	        Class.forName("oracle.jdbc.OracleDriver");

	        Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
	        String sql = "SELECT USER_NAME FROM USER_INFO WHERE USER_ID = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, pk);

	        ResultSet resultSet = pstmt.executeQuery();

	        if (resultSet.next()) {
	            nickname = resultSet.getString("USER_NAME");
	        }
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return nickname;
	}

	
	public void accountDelete(int pk) {
	    try {
	        Class.forName("oracle.jdbc.OracleDriver");

	        Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
	        String sql = "DELETE FROM USER_INFO WHERE USER_ID = ?";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, pk);

	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("ê³„ì • ?‚­? œê°? ?™„ë£Œë˜?—ˆ?Šµ?‹ˆ?‹¤.");
	        } else {
	            System.out.println("?¼ì¹˜í•˜?Š” pkê°’ì´ ?—†?Šµ?‹ˆ?‹¤.");
	        }

	        con.close();

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}

