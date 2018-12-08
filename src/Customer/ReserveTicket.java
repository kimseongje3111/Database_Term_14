package Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Movie;
import Database.ReservedSeat;
import Database.Screen;
import Database.ScreeningMovie;
import Database.Theater;
import Database.Ticket;
import Database.User;

public class ReserveTicket {

	Scanner scan = new Scanner(System.in);
	private String movieName;
	private String theaterName;
	private String screenDate;
	private String screenTime;
	private String screenNum;

	DAO dao = DAO.sharedInstance();

	public void run(User user) {

		System.out.println("<<<<<<<<<<<<<<<<<<<< 상영 영화 목록 >>>>>>>>>>>>>>>>>>>>");
		List<ScreeningMovie> screening_list = dao.getScreeningMovieList(); // DAO 상영 영화 : 상영 영화 목록 가져오기
		for (ScreeningMovie sm : screening_list) {
			System.out.println(sm);
		}
		System.out.println();

		// 영화 제목
		movieName = this.inputString("영화 제목을 입력하시오. (ex.ironman) : ");
		Movie movie = new Movie();
		movie.setMovieName(movieName);

		boolean r1 = dao.checkMovieName(movie); // DAO 영화 : 제목 확인

		if (!r1) {
			System.out.println("존재하지 않는 영화입니다.");
			this.run(user);
		}

		// 영화관
		theaterName = this.inputString("영화관을 고르세요. (ex.daejeon_cgv) : ");
		Theater theater = new Theater(theaterName);

		boolean r2 = dao.checkTheaterId(theater);

		if (!r2) {
			System.out.println("존재하지 않는 영화관입니다.");
			this.run(user);
		}

		// 상영날짜
		boolean r3 = false;
		screenDate = this.inputString("상영날짜를 택하세요. (ex.20181212) : ");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenDate().equals(screenDate)) {
				r3 = true;
			}
		}

		if (!r3) {
			System.out.println("존재하지 않는 상영날짜입니다.");
			this.run(user);
		}

		// 상영시간
		boolean r4 = false;
		screenTime = this.inputString("상영시간을 입력하세요. (ex.1315) : ");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenTime().equals(screenTime)) {
				r4 = true;
			}
		}

		if (!r4) {
			System.out.println("존재하지 않는 상영시간입니다.");
			this.run(user);
		}

		screenNum = this.inputString("상영관 번호를 입력하세요. (ex.1) : ");
		String screenId = theaterName + screenNum;
		Screen screen = new Screen(screenId);

		boolean r5 = dao.checkScreenId(screen);

		if (!r5) {
			System.out.println("존재하지 않는 상영관입니다.");
			this.run(user);
		}

		ScreeningMovie result = null;
		for (ScreeningMovie sm : screening_list) {
			if (sm.getMovieName().equals(movieName) && sm.getScreenDate().equals(screenDate)
					&& sm.getScreenTime().equals(screenTime) && sm.getScreenId().equals(screenId)) {
				result = sm;
			}
		}

		// DAO 예약 좌석 : 예약 좌석 리스트 가져오기
		List<ReservedSeat> rs_list = dao.getSeatList(result.getScreenMovieId(), result.getScreenDate());

		System.out.println("<<<<<<<<<<<<<<<<<<<< 예약 좌석 목록 >>>>>>>>>>>>>>>>>>>>");
		int availcount = 0;
		int count = 0;
		for (ReservedSeat rs : rs_list) {
			if (count != 0 && count % 5 == 0) {
				System.out.println();
			}
			if (rs.isReserveBool()) {
				System.out.print(String.format("%3s[%s] : X", " ", rs.getSeat()));
			} else {
				availcount++;
				System.out.print(String.format("%3s[%s] : O", " ", rs.getSeat()));
			}
			count++;
		}
		System.out.println();
		System.out.println();

		int howManyReservation = 0;
		do {
			howManyReservation = this.inputInt("몇 개의 좌석을 예매하겠습니까? : ");

		} while (availcount < howManyReservation);

		List<Ticket> ticket_list = new ArrayList<>();
		for (int i = 0; i < howManyReservation; i++) {
			Ticket ticket = new Ticket();
			String seatNumber = this.inputString("좌석을 입력하세요. (ex.1) : ");
			for (ReservedSeat rs : rs_list) {
				if (rs.getSeat().equals(seatNumber)) {
					if (!rs.isReserveBool()) {
						ticket.setMovieName(movieName);
						ticket.setTheaterName(theaterName);
						ticket.setScreenNum(screenNum);
						ticket.setScreenDate(screenDate);
						ticket.setScreenTime(screenTime);
						ticket.setSeatNum(seatNumber);
						ticket.setUserId(user.getUserId());
						ticket.setTicketId(movieName + theaterName + screenNum + screenDate + screenTime + seatNumber);

						ticket_list.add(ticket);
					} else {
						System.out.println("이미 예약되어있는 좌석입니다.");
						i--;
					}
				}
			}
		}

		List<Ticket> result_ticket = null;
		result_ticket = this.finishReservationWithchoossingPay(ticket_list, user);
		boolean isFinished = true;
		for (Ticket rt : result_ticket) {
			int screenMovieNum = 0;
			
			for (ScreeningMovie sm : screening_list) {
				if (sm.getScreenDate().equals(rt.getScreenDate()) &&
						sm.getMovieName().equals(rt.getMovieName()) &&
						sm.getScreenId().equals(rt.getTheaterName() + rt.getScreenNum()) &&
						sm.getScreenTime().equals(rt.getScreenTime())) {
					screenMovieNum = sm.getScreenMovieId();
				}
			}
			
			boolean r6 = dao.insertTicket(rt, screenMovieNum); // DAO 영화 예매 : 티켓 삽입

			if (!r6) {
				isFinished = false;
			}
		}
		
		if (isFinished) {
			System.out.println("에매가 완료되었습니다. 예약 현황에서 확인할 수 있습니다.");
		} else {
			System.out.println("예매를 실패하였습니다.");
		}
		
	}

	private List<Ticket> finishReservationWithchoossingPay(List<Ticket> list, User user) {
		List<Ticket> result = null;
		int howToPay = this.inputInt("결제수단을 선택하세요. (1.인터넷 결제  2.현장 결제) : ");

		switch (howToPay) {
		case 1: // 인터넷 결제
			result = this.internetPay(list, user);
			break;

		case 2: // 현장 결제
			result = this.fieldPay(list);
			break;

		default:
			System.out.println("다시 입력하세요.");
			this.finishReservationWithchoossingPay(list, user);
		}

		return result;
	}

	private List<Ticket> internetPay(List<Ticket> list, User user) {
		List<Ticket> result = new ArrayList<>();
		System.out.println();
		System.out.println("<<<<<< 총 " + list.size() + "개의 표에 대해서 결제를 진행합니다. >>>>>>");

		for (int i = 0; i < list.size(); i++) {
			int user_point = dao.getUsedPoint(user); // DAO 티켓 발권 : 예매자의 가용 포인트 가져오기
			System.out.println("현재 사용자의 가용 포인트 : " + user_point + " P");

			int point_use = this.inputInt("사용할 포인트 : ");

			if (user_point < 1000) {
				System.out.println("포인트는 1000P 이상부터 사용 가능합니다.");
				dao.ticketing(user, false, 0); // DAO 티켓 발권 : 포인트 사용 x, 사용자의 가용 포인트 100점 증가, 티켓 구매 횟수 증가
				
				System.out.println("포인트를 사용하지 않았습니다. 포인트 100P 를 적립합니다.");
				list.get(i).setPaymentBool(true);
				list.get(i).setUsedPoint(0);
			} else {
				if (point_use == 0) {

					dao.ticketing(user, false, 0); // DAO 티켓 발권 : 포인트 사용 x, 사용자의 가용 포인트 100점 증가, 티켓 구매 횟수 증가

					System.out.println("포인트를 사용하지 않았습니다. 포인트 100P 를 적립합니다.");
				} else if (user_point < point_use) {
					System.out.println("사용할 수 있는 포인트를 초과하였습니다. 다시 결제를 진행합니다.");
					this.internetPay(list, user);
				} else {
					dao.ticketing(user, true, point_use); // DAO 티켓 발권 : 포인트 사용, 사용자의 가용 포인트 감소, 티켓 구매 횟수 증가

					System.out.println("포인트 " + point_use + "P 를 사용하였습니다.");
				}
				list.get(i).setPaymentBool(true);
				list.get(i).setUsedPoint(point_use);
			}
			result.add(list.get(i));
		}
		System.out.println("결제가 완료되었습니다.");

		return result;
	}

	private List<Ticket> fieldPay(List<Ticket> list) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket t : list) {
			t.setPaymentBool(false);
			t.setUsedPoint(0);
			result.add(t);
		}

		System.out.println("현장에서 결제를 완료해 주세요.");
		return result;
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
