package Customer;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Ticket;
import Database.User;

public class ShowReservation {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run(User user) {
		// 나의 예약 현황을 보여줌
		System.out.println("------------------------------------- 예매 현황 -------------------------------------");
		System.out.println("[티켓번호] 영화 / 영화관 / 상영관 번호 / 상영 날짜 / 상영 시간 / 좌석 번호 / 예매자 / 결제 유무 / 사용 포인트");

		List<Ticket> my_ticket = dao.getTicketList(user);
		int count = 1;
		for (Ticket t : my_ticket) {
			System.out.println(count + ". " + t);
			count++;
		}
		System.out.println();

		System.out.println("실행할 업무를 선택하세요.");
		int chooseWork = this.inputInt("1.예매 취소   9.뒤로가기  ");

		switch (chooseWork) {
		case 1: // 애매 취소
			new CancelReservation().run(user, my_ticket);
			System.out.println();
			this.run(user);
			break;

		case 9: // 뒤로 가기
			System.out.println("> 영화 예약 관리를 종료합니다.");
			break;

		default:
		}

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
