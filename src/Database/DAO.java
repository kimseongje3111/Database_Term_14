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
			System.out.println("Ŭ���� �ε� ���� : " + e.getMessage());
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

	// �����ͺ��̽� ������ �ʿ��� �������� ����
	Connection conn;

	// SQL ���࿡ �ʿ��� ����
	Statement stmt;

	// select ������ �������� �� ����� ������ ����
	private ResultSet rs;

	private boolean connect() {
		boolean result = false;

		try {
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			// System.out.println("\n- MySQL Connection");
			result = true;
		} catch (Exception e) {
			System.out.println("���� ���� : " + e.getMessage());
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
			System.out.println("���� ���� : " + e.getMessage());
		}
	}

	// login check
	public boolean checkLogin(User user) {
		boolean result = false;
		String uid = user.getUserId();
		String upwd = user.getPwd();
		int count = 0;

		if (this.connect()) {
			try {
				String sql = "SELECT count(*) FROM user WHERE userId = '" + uid + "' AND pwd = '" + upwd + "';";
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);

					rs.next();
					count = rs.getInt(1);
				}

				if (count >= 1) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;

	}

	// 0. ���� ����Ʈ ��ȯ
	public List<User> getUserList() { // select
		List<User> list = null;

		if (connect()) {
			try {
				String sql = "SELECT * FROM user";
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
			// ���ῡ �������� �� �۾�
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return list;
	}

	// 0. ����� ȸ�� ����
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
				pstmt.setInt(7, 1000);
				pstmt.setInt(8, 0);

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 1. ��ȭ�� ���̺� ���̵� �ߺ� �˻�
	public boolean checkTheaterId(Theater theater) {
		boolean result = false;
		String tid = theater.getTheaterId();
		int count = 0;

		if (this.connect()) {
			try {
				String sql = "SELECT count(*) FROM theater WHERE theaterId = '" + tid + "';";
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);

					rs.next();
					count = rs.getInt(1);
				}

				if (count >= 1) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 2. ��ȭ�� ���� ����
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
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 3. ��ȭ�� ���� ������Ʈ
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
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 4. ��ȭ�� ���� ����
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
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 5. �󿵰� ���̵� �ߺ� �˻�
	public boolean checkScreenId(Screen screen) {
		boolean result = false;
		String sid = screen.getScreenId();
		int count = 0;

		if (this.connect()) {
			try {
				String sql = "SELECT count(*) FROM screen WHERE screenId = '" + sid + "';";
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					count = rs.getInt(1);
				}

				if (count >= 1) {
					result = true;
				}

				// �����ͺ��̽� ���� ��ü ����
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 6. �󿵰� ���� ����
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
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 7. �󿵰� ���� ������Ʈ
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
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 8. ��ȭ ���� �ߺ� �˻�
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
				// �����ͺ��̽� ���� ��ü ����
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 9. ��ȭ ���̵� �ߺ� �˻�
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
				// �����ͺ��̽� ���� ��ü ����
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 10. ��ȭ ���� ����(��ü)
	public boolean insertMovie(Movie movie) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO movie VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, movie.getMovieId());
				pstmt.setString(2, movie.getMovieName());
				pstmt.setString(3, movie.getDirector());
				pstmt.setString(4, movie.getCasting());
				pstmt.setString(5, movie.getRating());
				pstmt.setString(6, movie.getKeyInfo());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 11. ��ȭ ���� ������Ʈ
	public boolean updateMovieInfo(Movie movie) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "UPDATE movie SET director = ?, casting = ?, rating = ?, keyInfo = ?  WHERE movieName = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, movie.getDirector());
				pstmt.setString(2, movie.getCasting());
				pstmt.setString(3, movie.getRating());
				pstmt.setString(4, movie.getKeyInfo());
				pstmt.setString(5, movie.getMovieName());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 12. ��ȭ ���� ����
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
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 13. �� ��ȭ ����Ʈ ��������
	public List<ScreeningMovie> getScreeningMovieList() {
		List<ScreeningMovie> list = null;
		
		if (connect()) {
			try {
				String sql = "SELECT * FROM screeningMovie";
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
			// ���ῡ �������� �� �۾�
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return list;

	}

	// 14. �� ��ȭ ��ȣ �ߺ� �˻�
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
				// �����ͺ��̽� ���� ��ü ����
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 15. �� ��ȭ ����
	public boolean insertScreeningMovie(ScreeningMovie sMovie) { // sMovie�� �����¼� ����
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO screeningMovie VALUES (?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, sMovie.getScreenMovieId());
				pstmt.setString(2, sMovie.getScreenDate());
				pstmt.setString(3, sMovie.getMovieName());
				pstmt.setString(4, sMovie.getScreenId());
				pstmt.setString(5, sMovie.getScreenTime());

				int r = pstmt.executeUpdate();

				if (r > 0 && seatMaking(sMovie)) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 15 + �� ��ȭ ���Խ� �ش� �� ��ȭ�� �ش��ϴ� �󿵰��� �¼����� ���� �ش� �¼��� ��ŭ ���� �¼� Ʃ���� �߰��Ѵ�.
	public boolean seatMaking(ScreeningMovie sMovie) {
		int seatNum = seatNumFromScreen(sMovie.getScreenId()); // �ش� �󿵰��� �¼� ��
		int count = 1;

		if (this.connect()) {
			try {
				for (count = 1; count < seatNum + 1; count++) {
					String sql = "INSERT INTO reservedSeat VALUES (?, ?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(sql);

					pstmt.setInt(1, sMovie.getScreenMovieId());
					pstmt.setString(2, String.valueOf(count)); // �¼� ��ȣ = count ��ȣ
					pstmt.setBoolean(3, false); // default = 0
					pstmt.setString(4, sMovie.getScreenDate());

					pstmt.executeUpdate();

					pstmt.close();
				}
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return true;
	}

	// 15 + + �󿵰� ���� �¼��� ��ȯ �Լ�
	public int seatNumFromScreen(String screenid) { // �󿵰� id�� �Է¹���, return : �¼���
		int result = 0;

		if (this.connect()) {
			try {
				String sql = "SELECT availSeat FROM screen WHERE screenid = '" + screenid + "';";
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					result = rs.getInt(1);
				}
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 16. �� ��ȭ ����
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
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 17. ������ Ƽ�� ����Ʈ ��������
	public List<Ticket> getTicketList(User user) {
		List<Ticket> list = null;
		String id = "'" + user.getUserId() + "'";

		if (connect()) {
			try {
				String sql = "SELECT * FROM ticket WHERE userId = " + id;
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
						t.setScreenTime(rs.getString("screenTime"));
						t.setSeatNum(rs.getString("seatNum"));
						t.setUserId(rs.getString("userId"));
						t.setPaymentBool(rs.getBoolean("paymentBool"));
						t.setUsedPoint(rs.getInt("usedPoint"));
						list.add(t);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// ���ῡ �������� �� �۾�
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return list;

	}

	// 18. ������ ���� ����Ʈ ��������
	public int getUsedPoint(User user) {
		int point = 0;
		String uid = user.getUserId();

		if (this.connect()) {
			String sql = "SELECT point FROM user WHERE userId = '" + uid + "';";

			try {
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					point = rs.getInt(1);
				}
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}
		return point;
	}

	// 19. Ƽ�� ���� ���� - ����Ʈ ��� o + 20. Ƽ�� ���� ���� - ����Ʈ ��� x
	public boolean ticketing(User user, Boolean T, int point) {
		boolean result = false;
		String uid = user.getUserId();
		String sql = "";

		if (this.connect()) {
			try {
				if (T) { // T�� ����Ʈ ��� + 100 ����
					sql = "UPDATE user SET point = point - " + String.valueOf(point)
							+ ", ticketPurchaseNum = ticketPurchaseNum + 1 WHERE userId = ?";
				} else { // T�� FALSE�� ����Ʈ ��� X + 100
					sql = "UPDATE user SET point = point + 100, ticketPurchaseNum = ticketPurchaseNum + 1 WHERE userId = ?";
				}

				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, uid);

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 21. Ƽ�� ���� ���� - ���� �Ϸ�
	public boolean OnSitePayment(Ticket ticket) {
		boolean result = false;
		String uid = ticket.getUserId();
		int point = ticket.getUsedPoint();

		if (this.connect()) {
			try {
				String sql = "UPDATE ticket SET paymentBool = 1, usedPoint = " + String.valueOf(point)
						+ " WHERE userId = '" + uid + "';";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 22. VIP ����
	public List<String> getVipList(String period) { ///////////////////////////////////////////////////////////////////
		List<String> list = null;
		String temp[] = period.split("~");
		int start = Integer.parseInt(temp[0]); // ex) 20181208
		int end = Integer.parseInt(temp[1]);

		// String sql = "SELECT userId from ticket WHERE "

		if (this.connect()) {
			String sql = "SELECT userId FROM ticket WHERE ";

			// SELECT userId FROM (SELECT * FROM ticket WHERE screenDate BETWEEN start AND
			// end) as a GROUP BY userId ORDER BY 2 DESC LIMIT 10;

			try {
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
				}

				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return list;
	}

	/** �����ڿ� �Լ� �� **/

	/** ������ �Լ� ���� **/

	// 23. ȸ�� ���� ���� - �ش� ȸ�� ���� ���
	public User printUserInfo(User user) {
		User u = new User();
		String userid = user.getUserId();

		if (this.connect()) {
			try {
				String sql = "SELECT * FROM user WHERE userId = '" + userid + "';";
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);
					rs.next();
					u.setUserId(rs.getString("userId"));
					u.setPwd(rs.getString("pwd"));
					u.setName(rs.getString("name"));
					u.setBirth(rs.getString("birth"));
					u.setAddr(rs.getString("addr"));
					u.setPhoneNum(rs.getString("phoneNum"));
					u.setPoint(rs.getInt("point"));
					u.setTicketPurchaseNum(rs.getInt("ticketPurchaseNum"));
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return u;
	}

	// 24. ȸ�� ���� ���� - ������ ���� ������Ʈ (User ��ü / id, ����Ʈ, ����Ƚ�� x)
	public boolean editUserInfo(User user) {
		boolean result = false;
		String userid = user.getUserId();

		if (this.connect()) {
			try {
				String sql = "UPDATE user SET pwd = ? name = ? birth = ? addr = ? phoneNum = ? WHERE userId = '"
						+ userid + "';";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, user.getPwd());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getBirth());
				pstmt.setString(4, user.getAddr());
				pstmt.setString(5, user.getPhoneNum());

				int r = pstmt.executeUpdate();
				if (r > 0) {
					result = true;
				}

				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 25. ȸ�� Ż�� - ���� ���� (User ��ü)
	public boolean deleteUser(User user) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "DELETE FROM user WHERE userId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, user.getUserId());

				int r = pstmt.executeUpdate();

				if (r > 0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 26. ��ȭ ��Ʈ ���
	public List<String> printMovieRating() { // ������ : ������ ���ִ� ��/ �� �¼�
												// ��ȣ//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
		List<String> list = new ArrayList<String>();

		return list;
	}

	// 27. ��ȭ ���� - ���� �¼� ����Ʈ ��������
	public List<ReservedSeat> getSeatList(int id, String date) { // �� ��ȭ ��ȣ, �󿵿�ȭ �󿵳�¥
		List<ReservedSeat> list = null;

		if (this.connect()) {
			try {
				String sql = "SELECT * FROM reservedSeat WHERE screenMovieId = " + String.valueOf(id)
						+ " AND onScreenDate = '" + date + "'";
				stmt = conn.createStatement();

				if (stmt != null) {
					rs = stmt.executeQuery(sql);

					list = new ArrayList<ReservedSeat>();

					while (rs.next()) {
						ReservedSeat seat = new ReservedSeat();
						seat.setScreenMovieId(rs.getInt("screenMovieId"));
						seat.setSeat(rs.getString("seat"));
						seat.setReserveBool(rs.getBoolean("reserveBool"));
						seat.setOnScreenDate(rs.getString("onScreenDate"));

						list.add(seat);
					}
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return list;
	}

	// 28. ��ȭ ���� - Ƽ�� ���� (Ticket ��ü)
	public boolean insertTicket(Ticket ticket, int screenMovieNum) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "INSERT INTO ticket VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, ticket.getTicketId());
				pstmt.setString(2, ticket.getMovieName());
				pstmt.setString(3, ticket.getTheaterName());
				pstmt.setString(4, ticket.getScreenNum());
				pstmt.setString(5, ticket.getScreenDate());
				pstmt.setString(6, ticket.getScreenTime());
				pstmt.setString(7, ticket.getSeatNum());
				pstmt.setString(8, ticket.getUserId());
				pstmt.setBoolean(9, ticket.isPaymentBool());
				pstmt.setInt(10, ticket.getUsedPoint());

				int r = pstmt.executeUpdate();
				if (r > 0 && setReservedSeat(ticket, screenMovieNum)) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 30 +. Ƽ�� ������ �ش� ���� �¼� ���� ���� �ʱ�ȭ
	public boolean setReservedSeat(Ticket ticket, int screenMovieNum) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "UPDATE reservedSeat SET reserveBool = 1 WHERE screenMovieId = ? AND seat = ? AND onScreenDate = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, screenMovieNum);
				pstmt.setString(2, ticket.getSeatNum());
				pstmt.setString(3, ticket.getScreenDate());

				int r = pstmt.executeUpdate();
				if (r > 0) {
					result = true;
				}

				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 29. ��ȭ ���� Ȯ�� - ������ Ƽ�� ���
	public List<Ticket> printAllTicket(User user) {
		List<Ticket> list = null;

		if (connect()) {
			try {
				String sql = "SELECT * FROM ticket WHERE userId = '" + user.getUserId() + "';";
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
						t.setScreenTime(rs.getString("screenTime"));
						t.setSeatNum(rs.getString("seatNum"));
						t.setUserId(rs.getString("userId"));
						t.setPaymentBool(rs.getBoolean("paymentBool"));
						t.setUsedPoint(rs.getInt("availPoint"));
						list.add(t);
					}
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return list;

	}

	// 30. ���� ��� - ������ Ƽ�� ����

	public boolean deleteTicket(Ticket ticket, int screenMovieNum) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "DELETE FROM ticket WHERE ticketId = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, ticket.getTicketId());

				int r = pstmt.executeUpdate();

				boolean x = resetReservedSeat(ticket, screenMovieNum);

				if (r > 0 && x) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����Ͽ����ϴ�.");
			System.exit(0);
		}
		return result;
	}

	// 30 +. Ƽ�� ������ �ش� ���� �¼� ���� ���� �ʱ�ȭ
	public boolean resetReservedSeat(Ticket ticket, int screenMovieNum) {
		boolean result = false;

		if (this.connect()) {
			try {
				String sql = "UPDATE reservedSeat SET reserveBool = 0 WHERE screenMovieId = ? AND seat = ? AND onScreenDate = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, screenMovieNum);
				pstmt.setString(2, ticket.getSeatNum());
				pstmt.setString(3, ticket.getScreenDate());

				int r = pstmt.executeUpdate();
				if (r > 0) {
					result = true;
				}

				pstmt.close();
				this.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return result;
	}

}
