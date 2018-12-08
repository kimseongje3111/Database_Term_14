package Customer;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Movie;
import Database.ScreeningMovie;
import Database.Theater;
import Database.User;

public class ReserveTicket {

	Scanner scan = new Scanner(System.in);
	private String movieName;
	private String theaterName;
	private String screenDate;
	private String screenTime;

	DAO dao = DAO.sharedInstance();

	public void run(User user) {

		System.out.println("<<<<<<<<<<<<<<<<<<<< 상영 영화 목록 >>>>>>>>>>>>>>>>>>>>");
		List<ScreeningMovie> screening_list = dao.getScreeningMovieList(); // DAO 상영 영화 : 상영 영화 목록 가져오기
		for (ScreeningMovie sm : screening_list) {
			System.out.println(sm);
		}
		System.out.println();

		// 영화 제목
		movieName = this.inputString("영화 제목을 입력하시오. : ");
		Movie movie = new Movie();
		movie.setMovieName(movieName);
		
		boolean r1 = dao.checkMovieName(movie); // DAO 영화 : 제목 확인
		
		if (!r1) {
			System.out.println("존재하지 않는 영화입니다.");
			this.run(user);
		}
		
		// 영화관
		theaterName = this.inputString("영화관을 고르세요. : ");
		Theater theater = new Theater(theaterName);
		
		boolean r2 = dao.checkTheaterId(theater);
		
		if (!r2) {
			System.out.println("존재하지 않는 영화관입니다.");
			this.run(user);
		}
		
		// 상영날짜
		boolean r3 = false;
		screenDate = this.inputString("상영날짜를 택하세요.");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenDate() == screenDate) {
				r3 = true;
			}
		}
		
		if (!r3) {
			System.out.println("존재하지 않는 상영날짜입니다.");
			this.run(user);
		}
		
		// 상영시간
		boolean r4 = false;
		screenTime = this.inputString("상영시간을 입력하세요. : ");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenTime() == screenTime) {
				r4 = true;
			}
		}
		
		if (!r4) {
			System.out.println("존재하지 않는 상영시간입니다.");
			this.run(user);
		}
		
		//List<ReserveTicket>

		int howManyReservation = this.inputInt("몇개의 좌석을 예매하겠습니까. : ");
		// 가능한 좌석 수보다 크다면 ~ howManyReservation을 다시 입력

		// 예약이 안되어 있는 좌석만 예약이가능하도록
		for (int i = 0; i < howManyReservation; i++) {
			String seatNumber = this.inputString("좌석을 입력하세요 : ");
			// <if 예약 되지 않은 좌석 중에서 입력과 같은게 없다면 seatNumber다시 입력 받는다
			// <else 결제 방법 선택> - > 및 예약 1회 완료
			this.finishReservationWithchoossingPay();
		}
		System.out.println("에매가 완료되었습니다.");
	}

	private void finishReservationWithchoossingPay() {
		int howToPay = this.inputInt("결제수단을 선택하세요. ( 1. 인터넷 결제 2. 현장 결제 )");
		switch (howToPay) {
		case 1: // 인터넷 결제
			this.internetPay();
			break;
		case 2: // 현장 결제
			this.fieldPay();
			break;
		default:
			System.out.println("다시 입력하시오.");
			this.finishReservationWithchoossingPay();
		}
	}

	private void internetPay() {
		// p.6 결제 - 인터넷 결제(포인트와 현금 결제, 한장 구입시 100포인트 부여), 현장 결제(결제 진행안됨)
		// <포인트 보여줌>
		// <사용할 포인트 물어봄> - 예외처리 : 포인트 초과, 1000점이상만 사용가능
		// < 단, 포인트로 결제하면 100 안부여 결제 끝남 >
		// 현금 결제 시 < 포인트 100 부여 >
		// 결제 끝
	}

	private void fieldPay() {
		System.out.println("현장에서 결제를 완료해 주세요.");
		// 티켓 테이블에 저장, 결제 유무는 안되었다로 저장
	}

	private int inputInt(String string) {
		System.out.print(string);
		return Integer.parseInt(scan.nextLine());
	}

	private String inputString(String string) {
		System.out.print(string);
		return scan.nextLine();
	}
}
