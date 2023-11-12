package Auto_pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectInformation {
	private String DB_user = "JUNG";
	private String DB_password = "1234";
	private String DB_url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String sql = "";
	private int user_turn_id_pk;
	private int user_id_pk;
	
	//
	public ObjectInformation(int user_id_pk) {
		this.user_id_pk = user_id_pk;
		try {
			//오브젝트들의 정보를 넣기 위해서 필요한 한 user_count의 최댓값 과 turn_count의 최댓값을 이용해 찾은 turn_info의 pk값
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			//TURN의 최고 구하기
			String sql = "SELECT TURN_ID " +
                    "FROM TURN_INFORMATION " +
                    "LEFT JOIN USER_RECORD ON TURN_INFORMATION.USER_RECORD_ID = USER_RECORD.USER_RECORD_ID " +
                    "LEFT JOIN USER_INFO ON USER_RECORD.USER_ID = USER_INFO.USER_ID " +
                    "WHERE USER_RECORD.USER_COUNT = (SELECT MAX(USER_COUNT) FROM USER_RECORD WHERE USER_RECORD.USER_ID = ?) " +
                    "AND TURN_INFORMATION.TURN_COUNT = (SELECT MAX(TURN_COUNT) "
                    + "FROM TURN_INFORMATION "
                    + "WHERE TURN_INFORMATION.USER_RECORD_ID = USER_RECORD.USER_RECORD_ID AND USER_RECORD.USER_ID = ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id_pk);
			pstmt.setInt(2, user_id_pk);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				user_turn_id_pk = resultSet.getInt(1);
			}
			System.out.println(user_turn_id_pk);
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void lnput_location(String location1_obj, String location2_obj,String location3_obj,String location4_obj,String location5_obj) {
		String object_id = null;
		int location_num = 0;
		int obj_pk_num = 0;
		try {
			String[] ObjectLocaiton = {location1_obj, location2_obj, location3_obj, location4_obj, location5_obj};
			int[] ObjectNum = new int[5];
			
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			
			for(String obj_location : ObjectLocaiton) {
				sql = "SELECT OBJECT_ID FROM OBJECT_LEVEL1 WHERE OBJECT_NAME = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, obj_location);
				ResultSet resultSet = pstmt.executeQuery();
				if (!resultSet.isBeforeFirst()) {
		            // 결과가 없는 경우
					sql = "SELECT OBJECT_ID FROM OBJECT_LEVEL2 WHERE OBJECT_NAME = ?";
		            pstmt = con.prepareStatement(sql);
		            pstmt.setString(1, obj_location);
		            resultSet = pstmt.executeQuery();
		            if(!resultSet.isBeforeFirst()) {
		            	sql = "SELECT OBJECT_ID FROM OBJECT_LEVEL3 WHERE OBJECT_NAME = ?";
			            pstmt = con.prepareStatement(sql);
			            pstmt.setString(1, obj_location);
			            resultSet = pstmt.executeQuery();
			            if(!resultSet.isBeforeFirst()) {
			            }else {
			            	while (resultSet.next()) {
			            		++location_num;
			            		object_id = resultSet.getString(1);
			            		int object_pk_num = Integer.parseInt(object_id);
			            		System.out.println("Location" + location_num + " LEVEL3_OBJECT_ID: " + object_id);
			            		ObjectNum[location_num-1] = object_pk_num;
			            	}
			            }
		            }else {
		            	while (resultSet.next()) {
		            		++location_num;
		            		object_id = resultSet.getString(1);
		            		System.out.println("Location" + location_num + " LEVEL2_OBJECT_ID: " + object_id);
		            		int object_pk_num = Integer.parseInt(object_id);
		            		ObjectNum[location_num-1] = object_pk_num;
		            	}
		            }
		        } else {
		            while (resultSet.next()) {
		            	++location_num;
		                object_id = resultSet.getString(1);
		                System.out.println("Location" + location_num + " LEVEL1_OBJECT_ID: " + object_id);
		                int object_pk_num = Integer.parseInt(object_id);
	            		ObjectNum[location_num-1] = object_pk_num;
		            }
		        }
			}	
			System.out.println("ObjectNum 배열의 값:");
	        for (int i = 0; i < ObjectNum.length; i++) {
	            System.out.println("Location" + (i + 1) + " Object ID: " + ObjectNum[i]);
	        }
	        sql = "INSERT INTO LOCATION_INFO(TURN_ID,LOCATION1_OBJ,LOCATION2_OBJ,LOCATION3_OBJ,LOCATION4_OBJ,LOCATION5_OBJ)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setInt(1, user_turn_id_pk);
	        pstmt.setInt(2, ObjectNum[0]);
	        pstmt.setInt(3, ObjectNum[1]);
	        pstmt.setInt(4, ObjectNum[2]);
	        pstmt.setInt(5, ObjectNum[3]);
	        pstmt.setInt(6, ObjectNum[4]);
	        pstmt.executeUpdate();
			System.out.println("행이 추가되었습니다!");
	        
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void Show_Loacation_Info(int pic_count) {
		String turn_info = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql ="SELECT USER_NAME, USER_COUNT, TURN_COUNT, LOCATION1_OBJ,LOCATION2_OBJ,LOCATION3_OBJ,LOCATION4_OBJ,LOCATION5_OBJ "
					+ "FROM LOCATION_INFO "
					+ "LEFT JOIN TURN_INFORMATION ON LOCATION_INFO.TURN_ID = TURN_INFORMATION.TURN_ID "
					+ "LEFT JOIN USER_RECORD ON TURN_INFORMATION.USER_RECORD_ID = USER_RECORD.USER_RECORD_ID "
					+ "LEFT JOIN USER_INFO ON USER_RECORD.USER_ID = USER_INFO.USER_ID "
					+ "WHERE USER_INFO.USER_ID = ? "
					+ "AND USER_RECORD.USER_COUNT = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user_id_pk);
			pstmt.setInt(2, pic_count);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				String user_name = resultSet.getString("USER_NAME");
				int user_count = resultSet.getInt("USER_COUNT");
				int turn_count = resultSet.getInt("TURN_COUNT");
				int location1 = resultSet.getInt("LOCATION1_OBJ");
				int location2 = resultSet.getInt("LOCATION2_OBJ");
				int location3 = resultSet.getInt("LOCATION3_OBJ");
				int location4 = resultSet.getInt("LOCATION4_OBJ");
				int location5 = resultSet.getInt("LOCATION5_OBJ");
					
				System.out.println("유저이름 : " + user_name);
				System.out.println("게임 수 : " + user_count);
				System.out.println("턴 수: " + turn_count);
				System.out.println("위치 1 기물 : " + location1);
				System.out.println("위치 2 기물 : " + location2);
				System.out.println("위치 3 기물 : " + location3);
				System.out.println("위치 4 기물 : " + location4);
				System.out.println("위치 5 기물 : " + location5);
				System.out.println("--------------------------");
				
        	}
	
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
				e.printStackTrace();
		}
	}
	
	public void input_obj_data() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection(DB_url, DB_user, DB_password);
			sql = "SELECT OBJECT_ID, OBJECT_NAME, OBJECT_TYPE, GRADE, DAMAGE, STAMINA " 
				  +	"FROM OBJECT_LEVEL1 "
				  +	"WHERE OBJECT_ID <= 6 ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				String name = resultSet.getString("OBJECT_NAME");
				String type = resultSet.getString("OBJECT_TYPE");
				int grade = resultSet.getInt("GRADE");
				int damage = resultSet.getInt("DAMAGE");
				int stamina = resultSet.getInt("STAMINA");
				
				System.out.println(name);
				System.out.println(type);
				System.out.println(grade);
				System.out.println(damage);
				System.out.println(stamina);
        	}

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
				e.printStackTrace();
		}
	}
}
