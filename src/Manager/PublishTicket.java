package Manager;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Ticket;

public class PublishTicket {

	Scanner scan = new Scanner(System.in);
	String userId;

	DAO dao = DAO.sharedInstance();

	public void run() {
		userId = this.inputString("발권할 고객의 아이디 : ");

		List<Ticket> ticket_list = null; // DAO 티켓 발권 : 예매자의 티켓 (예매자 확인)

		for (Ticket ticket : ticket_list) {
			if (!ticket.isPaymentBool()) {
				System.out.println("[" + ticket.getTicketId() + "] 결제 완료된 티켓입니다. 티켓을 발행합니다.");
			} else {
				System.out.println("[" + ticket.getTicketId() + "] 결제가 완료되지 않았습니다. 현장 결제를 진행합니다.");
				this.fieldPay(ticket);
			}
		}

		if (this.inputString("티켓을 추가로 발행하시겠습니까? (Y/N) ").equals("Y")) {
			this.run();
		} else {
			System.out.println("티켓 발행을 종료합니다.");
		}
		System.out.println();

	}

	private void fieldPay(Ticket ticket) {
		int user_point = 2000; // DAO 티켓 발권 : 예매자의 가용 포인트 가져오기
		System.out.println("현재 사용자의 가용 포인트 : " + user_point + " P");

		int point_use = this.inputInt("사용할 포인트 : ");

		if (user_point < 1000) {
			System.out.println("포인트는 1000점 이상부터 사용 가능합니다.");
			System.out.println("결제가 완료되었습니다. 티켓을 발행합니다.");
		} else {
			if (point_use == 0) {

				// DAO 티켓 발권 : 포인트 사용 x, 사용자의 가용 포인트 100점 증가, 티켓 구매 횟수 증가

				System.out.println("포인트를 사용하지 않았습니다. 포인트 100점을 적립합니다.");
				System.out.println("결제가 완료되었습니다. 티켓을 발행합니다.");
			} else if (user_point < point_use) {
				System.out.println("사용할 수 있는 포인트를 초과하였습니다.");
				this.fieldPay(ticket);
			} else {

				// DAO 티켓 발권 : 포인트 사용, 사용자의 가용 포인트 감소, 티켓 구매 횟수 증가

				System.out.println("포인트 " + point_use + "점을 사용하였습니다.");
				System.out.println("결제가 완료되었습니다. 티켓을 발행합니다.");
			}
		}

		// DAO 티켓 발권 : 해당 티켓의 결제 유무 True

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
