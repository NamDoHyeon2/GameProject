package Placement;

import java.sql.*;
import java.util.LinkedList;

public class setItem {
	private LinkedList<item> item;
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "JUNG";
	String password = "1234";

	public setItem() {
		this.item = new LinkedList<>();
		item_info();
	}

	void item_info() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			String sql = "SELECT OBJECT_ID, OBJECT_NAME, GRADE, ABILITY FROM OBJECT_ITEM";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int OBJECT_ID = rs.getInt("OBJECT_ID");
				String OBJECT_NAME = rs.getString("OBJECT_NAME");
				int GRADE = rs.getInt("GRADE");
				String ABILITY = rs.getString("ABILITY");
				item.add(new item(OBJECT_ID, GRADE, OBJECT_NAME, ABILITY));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}
	}

	item getItem(int itemNum) {
		for (item i : item) {
			if (i.getItemNum() == itemNum) {
				return i;
			}
		}
		return null;
	}
}
