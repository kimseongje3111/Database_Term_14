package Manager;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Ticket;

public class PublishTicket {

	Scanner scan = new Scanner(System.in);
	String userId;

	DAO dao = DAO.sharedInstance();

	public void run() { // �߱��� �������ص� ��� ����.
		// < user �� ID�� �Է¹޴´�. >
		userId = this.inputString("�߱��� ���� ���̵� : ");
		
		List<Ticket> ticket_list = null; // DAO Ƽ�� �߱� : �������� Ƽ�� (������ Ȯ��)
		// < user �� ������ Ƽ���� ����Ѵ�. >
		
		for(Ticket ticket : ticket_list) {
			if (!ticket.isPaymentBool()) {
				System.out.println("[" + ticket.getTicketId() + "] ���� �Ϸ�� Ƽ���Դϴ�. Ƽ���� �����Ͽ����ϴ�.");
			} else {
				System.out.println("[" + ticket.getTicketId() + "] ������ �Ϸ���� �ʾҽ��ϴ�. ���� ������ �����մϴ�.");
				this.fieldPay(ticket);
			}
		}

		if (this.inputString("Ƽ���� �߰��� �����Ͻðڽ��ϱ�? (Y/N) ").equals("Y")) {
			this.run();
		} else {
			System.out.println("Ƽ�� ������ �����մϴ�.");
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
