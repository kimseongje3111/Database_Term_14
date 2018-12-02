package Manager;

import java.util.Scanner;

public class PublishTicket {
	Scanner scan = new Scanner(System.in);
	String userId;
	String ticketId;
	
	public void run() { // 발권은 여러번해도 상관 없다.
		// < user 의 ID를 입력받는다. >
		userId = this.inputString("발권할 고객의 아이디 : ");
		// < user 의 예매한 티켓을 출력한다. >
		
		// < 발행할 티켓을 입력 받는다 >
		ticketId = this.inputString("발행할 티켓 아이디 : ");
		
		// < 결제 완료이면 티켓 발행 >
		if(ticketId.equals("티켓이 결제 완료라면"))
			System.out.println("결제 완료된 티켓입니다. 티켓을 발행하였습니다.");
		// < 결제가 안되어 있다면 customer에서 결제 하듯이 인터넷 결제 진행 >
		else {
			System.out.println("결제가 완료되지 않았습니다. 결제를 진행합니다.");
			// < customer의 발행을 사용 >
			this.fieldPay();
			System.out.println("결제가 완료되었습니다. 티켓을 발행합니다.");
		}
		
		if(this.inputString("티켓을 추가로 발행하시겠습니까? (y / n) ").equals("Y"))
			this.run();
		else
			System.out.println("티켓 발행을 종료합니다.");
	}
	
	private void fieldPay() {
		// customer꺼 사용
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
