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
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패 : " + e.getMessage());
		}
	}

	private DAO() {
	}

	private static DAO obj;

	public static DAO sharedInstance() {
		if (obj == null) {
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

		try {
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("\n- MySQL Connection");
			result = true;
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
		return result;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("해제 실패 : " + e.getMessage());
		}
	}

	public List<User> getUserList() { // select
		List<User> list = null;
		String sql = "SELECT * FROM user";
		if (connect()) {
			try {
				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);

					list = new ArrayList<User>();

					while (rs.next()) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// 연결에 실패했을 때 작업
			System.out.println("데이터베이스 연결에 실패했습니다.");
			System.exit(0);
		}
		return list;
	}

	public boolean InsertUser(User user) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPwd());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getBirth());
				pstmt.setString(5, user.getAddr());
				pstmt.setString(6, user.getPhoneNum());
				pstmt.setInt(7, 0);
				pstmt.setInt(8, 0);

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화관 테이블 아이디 중복 검사
	public boolean checkTheaterId(Theater theater) {
		boolean result = false;

		String tid = theater.getTheaterId();

		String sql = "SELECT count(*) FROM theater WHERE theaterId = '" + tid + "';";
		int count = 0;
		if (this.connect()) {
			try {
				stmt = conn.createStatement();
				// System.out.println(stmt);
				if (stmt != null) {
					rs = stmt.executeQuery(sql);

					rs.next();
					count = rs.getInt(1);
					System.out.println(count);
				}

				if (count >= 1) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화관 정보 삽입
	public boolean insertTheater(Theater theater) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO theater VALUES (?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, theater.getTheaterId());
				pstmt.setString(2, theater.getTheaterAddr());
				pstmt.setString(3, theater.getTheaterTel());
				pstmt.setInt(4, theater.getScreenNum());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화관 정보 업데이트
	public boolean updateTheater(Theater theater) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "UPDATE theater SET theaterAddr = ?, theaterTel = ?, screenNum = ?  WHERE theaterId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, theater.getTheaterAddr());
				pstmt.setString(2, theater.getTheaterTel());
				pstmt.setInt(3, theater.getScreenNum());
				pstmt.setString(4, theater.getTheaterId());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화관 정보 삭제
	public boolean deleteTheater(Theater theater) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "DELETE FROM theater WHERE theaterId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, theater.getTheaterId());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 상영관 아이디 중복 검사
	public boolean checkScreenId(Screen screen) {
		boolean result = false;

		String sid = screen.getScreenId();
		String sql = "SELECT count(*) FROM screen WHERE screenId = '" + sid + "';";
		int count = 0;
		if (this.connect()) {
			try {
				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					count = rs.getInt(1);
				}

				if (count >= 1) {
					result = true;
				}

				// 데이터베이스 생성 객체 해제
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 상영관 정보 삽입
	public boolean insertScreen(Screen screen) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO screen VALUES (?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, screen.getScreenId());
				pstmt.setString(2, screen.getTheaterId());
				pstmt.setString(3, screen.getScreenNum());
				pstmt.setInt(4, screen.getAvailSeat());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 상영관 정보 업데이트
	public boolean updateScreen(Screen screen) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "UPDATE screen SET availSeat = ?  WHERE screenId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, screen.getAvailSeat());
				pstmt.setString(2, screen.getScreenId());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화 제목 중복 검사
	public boolean checkMovieName(Movie movie) {
		boolean result = false;

		String mName = movie.getMovieName();
		int count = 0;
		if (this.connect()) {
			try {
				String sql = "SELECT count(*) FROM movie WHERE movieName = '" + mName + "';";

				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					count = rs.getInt(1);
				}
				if (count >= 1) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화 아이디 중복 검사
	public boolean checkMovieId(Movie movie) {
		boolean result = false;

		String mid = movie.getMovieId();
		int count = 0;
		if (this.connect()) {
			try {
				String sql = "SELECT count(*) FROM movie WHERE movieId = '" + mid + "';";

				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					count = rs.getInt(1);
				}
				if (count >= 1) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화 정보 삽입(객체)
	public boolean insertMovie(Movie movie) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO movie VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, movie.getMovieId());
				pstmt.setString(2, movie.getMovieName());
				pstmt.setString(3, movie.getDirector());
				pstmt.setString(4, movie.getCast());
				pstmt.setString(5, movie.getRating());
				pstmt.setString(6, movie.getKeyInfo());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화 정보 업데이트
	public boolean updateMovieInfo(Movie movie) {
		boolean result = false;

		System.out.println(movie);
		if (this.connect()) {
			try {
				String sql = "UPDATE movie SET movieName = ?, director = ?, cast = ? rating = ?, keyInfo = ?  WHERE movieId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, movie.getMovieName());
				pstmt.setString(2, movie.getDirector());
				pstmt.setString(3, movie.getCast());
				pstmt.setString(4, movie.getRating());
				pstmt.setString(5, movie.getKeyInfo());
				pstmt.setString(6, movie.getMovieId());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 영화 정보 삭제
	public boolean deleteMovie(Movie movie) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "DELETE FROM movie WHERE movieName = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, movie.getMovieName());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 상영 영화 리스트 가져오기
	public List<ScreeningMovie> getScreeningMovieList(ScreeningMovie screeningMovie) {
		List<ScreeningMovie> list = null;
		String sql = "SELECT * FROM screeningMovie";
		if (connect()) {
			try {
				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);

					list = new ArrayList<ScreeningMovie>();

					while (rs.next()) {
						ScreeningMovie sm = new ScreeningMovie();
						sm.setScreenMovieId(rs.getInt("screenMovieId"));
						sm.setScreenDate(rs.getString("screenDate"));
						sm.setMovieName(rs.getString("movieName"));
						sm.setScreenId(rs.getString("screenId"));
						sm.setScreenTime(rs.getString("screenTime"));

						list.add(sm);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// 연결에 실패했을 때 작업
			System.out.println("데이터베이스 연결에 실패했습니다.");
			System.exit(0);
		}
		return list;

	}

	// 상영 영화 번호 중복 검사
	public boolean checkScreeningMovieId(ScreeningMovie screeningMovie) {
		boolean result = false;

		int sid = screeningMovie.getScreenMovieId();
		int count = 0;
		if (this.connect()) {
			try {
				String sql = "SELECT count(*) FROM screeningMovie WHERE screenMovieId = '" + sid + "';";

				stmt = conn.createStatement();
				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					count = rs.getInt(1);
				}
				if (count >= 1) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 상영 영화 삽입
	public boolean insertScreeningMovie(ScreeningMovie sMovie) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO screeningMovie VALUES (?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, sMovie.getScreenMovieId());
				pstmt.setString(2, sMovie.getScreenDate());
				pstmt.setString(3, sMovie.getMovieName());
				pstmt.setString(4, sMovie.getScreenId());
				pstmt.setString(4, sMovie.getScreenTime());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 상영 영화 삭제
	public boolean deleteScreeningMovie(ScreeningMovie screeningMovie) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "DELETE FROM screeningMovie WHERE screenMovieId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, screeningMovie.getScreenMovieId());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	// 17. 예매자 티켓 리스트 가져오기
	public List<Ticket> getTicketList(User user) {
		List<Ticket> list = null;
		String id = "'" + user.getUserId() + "'";

		String sql = "SELECT * FROM ticket WHERE userId = " + id;
		if (connect()) {
			try {
				stmt = conn.createStatement();

				if (stmt != null) {

					rs = stmt.executeQuery(sql);

					list = new ArrayList<Ticket>();

					while (rs.next()) {
						Ticket t = new Ticket();
						t.setTicketId(rs.getString("ticketId"));
						t.setMovieName(rs.getString("movieName"));
						t.setTheaterName(rs.getString("theaterName"));
						t.setScreenNum(rs.getString("screenNum"));
						t.setScreenDate(rs.getString("screenDate"));
						t.setSeatNum(rs.getString("seatNum"));
						t.setUserId(rs.getString("userId"));
						t.setPaymentBool(rs.getBoolean("paymentBool"));
						t.setUsedPoint(rs.getString("usedPoint"));
						list.add(t);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// 연결에 실패했을 때 작업
			System.out.println("데이터베이스 연결에 실패했습니다.");
			System.exit(0);
		}
		return list;

	}

	// 18. 예매자 가용 포인트 가져오기
	public int getUsedPoint(User user) {
		int point = 0;
		String uid = user.getUserId();
		if(this.connect()) {
			String sql = "SELECT usedPoint FROM user WHERE userId = '" + uid + "';";

			try {
				stmt = conn.createStatement();

				if(stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					point = rs.getInt(1);
				}
				this.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}

		}
		return point;
	}


	// 19. 티켓 현장 결제 - 포인트 사용 o   +   20. 티켓 현장 결제 - 포인트 사용 x
	public boolean ticketing(User user, Boolean T, int point) {

		String uid = user.getUserId();
		
		boolean result = false;
		if (this.connect()) {
			try {
				String sql = "UPDATE user SET point = point - "+ String.valueOf(point) +", ticketPurchaseNum = ticketPurchaseNum + 1 WHERE userId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, uid);

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}


	// 21. 티켓 현장 결제 - 결제 완료
	public boolean OnSitePayment(Ticket ticket) {

		String uid = ticket.getUserId();
		
		boolean result = false;
		
		if (this.connect()) {
			try {
				String sql = "UPDATE ticket SET paymentBool = 1 WHERE userId = '"+ uid +"';";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}

	
	
	// 22. VIP 관리
	public List<String> getVipList(String period){
		List<String> list = null;
		String temp[] = period.split("~");
		int start = Integer.parseInt(temp[0]);			// ex) 20181208
		int end = Integer.parseInt(temp[1]);
		
		//String sql = "SELECT userId from ticket WHERE "
		
		
		if(this.connect()) {
			String sql = "SELECT userId FROM ticket WHERE ";

			// SELECT userId FROM (SELECT * FROM ticket WHERE screenDate BETWEEN start AND end) as a GROUP BY userId ORDER BY 2 DESC LIMIT 10;
			
			try {
				stmt = conn.createStatement();

				if(stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					//point = rs.getInt(1);
				}
				this.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}

		}
		
		
		
		
		return list;
	}
	
	
	

}
