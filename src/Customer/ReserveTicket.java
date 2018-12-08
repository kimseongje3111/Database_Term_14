package Customer;

import java.util.Scanner;

import Database.User;

public class ReserveTicket {
	
	Scanner scan = new Scanner(System.in);
	private String movieName;
	private String theaterName;
	private String screenDate;
	private String screenTime;
	
	public void run(User user) {
		// 영화 예매 진행
		// 영화 제목 리스트를 보여줌
		movieName = this.inputString("영화 제목을 입력하시오. : ");
		// 이름이 존재 안함
		if (movieName.equals("이름이 없네")) {
			System.out.println("잘 못된 입력입니다.");
			run(user);
		}
		// 영화관 리스트도 보여줌
		theaterName = this.inputString("영화관을 고르세요. : ");
		if (theaterName.equals("영화관이 없네")) {
			System.out.println("잘 못된 입력입니다.");
			run(user);
		}
		// 해당 영화의 영화관의 상영날짜 리스트를 모두 보여줌
		screenDate = this.inputString("상영날짜를 택하세요.");
		if (screenDate.equals("상영날짜가 없네")) {
			System.out.println("잘 못된 입력입니다.");
			run(user);
		}
		// 해당 영화의 영화관의 상영날짜의 상영시간 리스트를 모두 보여줌
		screenTime = this.inputString("상영시간을 입력하세요. : ");
		if (screenTime.equals("상영시간이 없네")) {
			System.out.println("잘 못된 입력입니다.");
			run(user);
		}
		// 앞에 입력받은 정보를 가지고 예약 좌석에서 해당 상여관의 예매좌석과 예약유무를 (좌석수만큼) 모두 출력
		// <예약이 가능한 좌석 수를 출력>
		
		int howManyReservation = this.inputInt("몇개의 좌석을 예매하겠습니까. : ");
		// 가능한 좌석 수보다 크다면 ~ howManyReservation을 다시 입력
		
		// 예약이 안되어 있는 좌석만 예약이가능하도록
		for(int i=0; i<howManyReservation; i++) {
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
		case 1 : // 인터넷 결제
			this.internetPay();
			break;
		case 2 : // 현장 결제
			this.fieldPay();
			break;
			default :
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
