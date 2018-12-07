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
			System.out.println("클래스 로드 실패 : "+e.getMessage());
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
	// 데이터베이스 연동에 필요한 변수들을 선언
	Connection conn;
	
	// SQL 실행에 필요한 변수
	Statement stmt; 
	
	// select 구문을 수행했을 때 결과를 저장할 변수
	private ResultSet rs;
	
	private boolean connect() {
		boolean result = false;
		
		try{
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("\n- MySQL Connection");
			result = true;
		} catch (Exception e) {
			System.out.println("연결 실패 : " +e.getMessage());
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
			System.out.println("해제 실패 : " + e.getMessage());
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
			// 연결에 실패했을 때 작업
			System.out.println("데이터베이스 연결에 실패했습니다.");
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
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	

	// 영화관 테이블 아이디 중복 검사
	public boolean checkTheaterId() {
		return true;
	}

	// 영화관 정보 삽입
	public boolean insertTheater() {
		return true;
	}
	
	// 영화관 정보 업데이트
	public boolean updateTheater() {
		return true;
	}

	// 영화관 정보 삭제
	public boolean deleteTheater() {
		return true;
	}
	
	// 상영관 아이디 중복 검사
	public boolean checkScreenId() {
		return true;
	}


	// 상영관 정보 삽입
	public boolean insertScreen() {
		return true;
	}
	
	// 상영관 정보 업데이트
	public boolean updateScreen() {
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
