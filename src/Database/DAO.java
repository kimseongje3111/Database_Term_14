package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/db_term_1?autoReconnect=true&useSSL=false";
	static final String USERNAME = "root";
	static final String PASSWORD = "201402408";

	static {
		try {
			Class.forName(JDBC_DRIVER);
		} catch(ClassNotFoundException e) {
			System.out.println("Ŭ���� �ε� ���� : "+e.getMessage());
		}
	}
	
	private DAO() {}
	private static DAO obj;
	
	public static DAO sharedInstance() {
		if(obj == null) {
			obj = new DAO();
		}
		return obj;
	}
	// �����ͺ��̽� ������ �ʿ��� �������� ����
	Connection conn;
	
	// SQL ���࿡ �ʿ��� ����
	Statement stmt; 
	
	// select ������ �������� �� ����� ������ ����
	private ResultSet rs;
	
	private boolean connect() {
		boolean result = false;
		
		try{
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("\n- MySQL Connection");
			result = true;
		} catch (Exception e) {
			System.out.println("���� ���� : " +e.getMessage());
		}
		return result;
	}
	
	private void close() {
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch( Exception e) {
			System.out.println("���� ���� : " + e.getMessage());
		}
	}
	
	public List<User> getUserList() { // select
		List<User> list = null;
		String sql = "SELECT * FROM user";
		if(connect()) {
			try {
				stmt = conn.createStatement();
				if(stmt != null) {
					rs = stmt.executeQuery(sql);
					
					list = new ArrayList<User>();
					
					while(rs.next()) {
						User user = new User();
						user.setUserId((rs.getString("userId")));
						user.setPwd((rs.getString("pwd")));
						user.setName((rs.getString("name")));
						user.setBirth((rs.getString("birth")));
						user.setAddr((rs.getString("addr")));
						user.setPhoneNum((rs.getString("phoneNum")));
						user.setPoint((rs.getInt("point")));
						user.setTicketPurchaseNum(rs.getInt(("ticketPurchaseNum")));
						
						list.add(user);
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			// ���ῡ �������� �� �۾�
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return list;
	}
	
	public boolean InsertUser(User user) {
		boolean result = false;
		
		if(this.connect()) {
			try {
				String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPwd());
				pstmt.setNull(3, java.sql.Types.NULL);
				pstmt.setNull(4, java.sql.Types.NULL);
				pstmt.setNull(5, java.sql.Types.NULL);
				pstmt.setNull(6, java.sql.Types.NULL);
//				pstmt.setString(3, user.getName());
//				pstmt.setString(4, user.getBirth());
//				pstmt.setString(5, user.getAddr());
//				pstmt.setString(6, user.getPhoneNum());
				pstmt.setNull(7, java.sql.Types.NULL);
				pstmt.setNull(8, java.sql.Types.NULL);
				
				int r = pstmt.executeUpdate();
				
				if(r>0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ ����");
			System.exit(0);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	

	// ��ȭ�� ���̺� ���̵� �ߺ� �˻�
	public boolean checkTheaterId() {
		return true;
	}

	// ��ȭ�� ���� ����
	public boolean insertTheater() {
		return true;
	}
	
	// ��ȭ�� ���� ������Ʈ
	public boolean updateTheater() {
		return true;
	}

	// ��ȭ�� ���� ����
	public boolean deleteTheater() {
		return true;
	}
	
	// �󿵰� ���̵� �ߺ� �˻�
	public boolean checkScreenId() {
		return true;
	}


	// �󿵰� ���� ����
	public boolean insertScreen() {
		return true;
	}
	
	// �󿵰� ���� ������Ʈ
	public boolean updateScreen() {
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
