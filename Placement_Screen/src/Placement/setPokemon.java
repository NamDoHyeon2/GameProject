package Placement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class setPokemon {
	int pokemonNum;
	private LinkedList<Pokemon> Lv1pokemon;
	private LinkedList<Pokemon> Lv2pokemon;
	private LinkedList<Pokemon> Lv3pokemon;
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@119.195.135.42:1521:xe";
	String username = "JUNG";
	String password = "1234";

	public setPokemon() {
		this.Lv1pokemon = new LinkedList<>();
		this.Lv2pokemon = new LinkedList<>();
		this.Lv3pokemon = new LinkedList<>();
		setLV1Pokemon_info();
		setLV2Pokemon_info();
		setLV3Pokemon_info();
	}

	void setLV1Pokemon_info() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			String sql = "SELECT OBJECT_ID, OBJECT_NAME, OBJECT_TYPE, GRADE, DAMAGE, STAMINA, ABILITY FROM OBJECT_LEVEL1";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int OBJECT_ID = rs.getInt("OBJECT_ID");
				String OBJECT_NAME = rs.getString("OBJECT_NAME");
				String OBJECT_TYPE = rs.getString("OBJECT_TYPE");
				int GRADE = rs.getInt("GRADE");
				int DAMAGE = rs.getInt("DAMAGE");
				int STAMINA = rs.getInt("STAMINA");
				String ABILITY = rs.getString("ABILITY");
				Lv1pokemon.add(new Pokemon(OBJECT_ID, 1, 1, OBJECT_NAME, OBJECT_TYPE, GRADE, STAMINA, DAMAGE, ABILITY));
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

	void setLV2Pokemon_info() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			String sql = "SELECT OBJECT_ID, OBJECT_NAME, OBJECT_TYPE, GRADE, DAMAGE, STAMINA, ABILITY FROM OBJECT_LEVEL2";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int OBJECT_ID = rs.getInt("OBJECT_ID");
				String OBJECT_NAME = rs.getString("OBJECT_NAME");
				String OBJECT_TYPE = rs.getString("OBJECT_TYPE");
				int GRADE = rs.getInt("GRADE");
				int DAMAGE = rs.getInt("DAMAGE");
				int STAMINA = rs.getInt("STAMINA");
				String ABILITY = rs.getString("ABILITY");
				Lv2pokemon.add(new Pokemon(OBJECT_ID, 2, 1, OBJECT_NAME, OBJECT_TYPE, GRADE, STAMINA, DAMAGE, ABILITY));
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

	void setLV3Pokemon_info() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);

			String sql = "SELECT OBJECT_ID, OBJECT_NAME, OBJECT_TYPE, GRADE, DAMAGE, STAMINA, ABILITY FROM OBJECT_LEVEL3";
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int OBJECT_ID = rs.getInt("OBJECT_ID");
				String OBJECT_NAME = rs.getString("OBJECT_NAME");
				String OBJECT_TYPE = rs.getString("OBJECT_TYPE");
				int GRADE = rs.getInt("GRADE");
				int DAMAGE = rs.getInt("DAMAGE");
				int STAMINA = rs.getInt("STAMINA");
				String ABILITY = rs.getString("ABILITY");
				Lv3pokemon.add(new Pokemon(OBJECT_ID, 3, 1, OBJECT_NAME, OBJECT_TYPE, GRADE, STAMINA, DAMAGE, ABILITY));
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

	Pokemon getLV1Pokemon(int pokemonNum) {
		for (Pokemon item : Lv1pokemon) {
			if (item.getPokemonNum() == pokemonNum) {
				int pokemonNum1 = item.getPokemonNum();
				int Lv = item.getLv();
				int exp = item.getExp();
				String name = item.getName();
				String type = item.getType();
				int grade = item.getGrade();
				int health = item.getHealth();
				int power = item.getPower();
				String ability = item.getAbility();
				Pokemon p = new Pokemon(pokemonNum1, Lv, exp, name, type, grade, health, power, ability);
				return p;
			}

		}
		return null;
	}

	Pokemon getLV2Pokemon(int pokemonNum) {
		for (Pokemon item : Lv2pokemon) {
			if (item.getPokemonNum() == pokemonNum)
				return item;
		}
		return null;
	}

	Pokemon getLV3Pokemon(int pokemonNum) {
		for (Pokemon item : Lv3pokemon) {
			if (item.getPokemonNum() == pokemonNum)
				return item;
		}
		return null;
	}
}
