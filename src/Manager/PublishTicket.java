package Manager;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Ticket;

public class PublishTicket {

	Scanner scan = new Scanner(System.in);
	String userId;

	DAO dao = DAO.sharedInstance();

	public void run() { // 발권은 여러번해도 상관 없다.
		// < user 의 ID를 입력받는다. >
		userId = this.inputString("발권할 고객의 아이디 : ");
		
		List<Ticket> ticket_list = null; // DAO 티켓 발권 : 예매자의 티켓 (예매자 확인)
		// < user 의 예매한 티켓을 출력한다. >
		
		for(Ticket ticket : ticket_list) {
			if (!ticket.isPaymentBool()) {
				System.out.println("[" + ticket.getTicketId() + "] 결제 완료된 티켓입니다. 티켓을 발행하였습니다.");
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

	}

	private void fieldPay(Ticket ticket) {
		
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
