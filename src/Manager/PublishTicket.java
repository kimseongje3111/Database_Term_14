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
		userId = this.inputString("�߱��� ���� ���̵� : ");

		List<Ticket> ticket_list = null; // DAO Ƽ�� �߱� : �������� Ƽ�� (������ Ȯ��)

		for (Ticket ticket : ticket_list) {
			if (!ticket.isPaymentBool()) {
				System.out.println("[" + ticket.getTicketId() + "] ���� �Ϸ�� Ƽ���Դϴ�. Ƽ���� �����մϴ�.");
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
		System.out.println();

	}

	private void fieldPay(Ticket ticket) {
		int user_point = 2000; // DAO Ƽ�� �߱� : �������� ���� ����Ʈ ��������
		System.out.println("���� ������� ���� ����Ʈ : " + user_point + " P");

		int point_use = this.inputInt("����� ����Ʈ : ");

		if (user_point < 1000) {
			System.out.println("����Ʈ�� 1000�� �̻���� ��� �����մϴ�.");
			System.out.println("������ �Ϸ�Ǿ����ϴ�. Ƽ���� �����մϴ�.");
		} else {
			if (point_use == 0) {

				// DAO Ƽ�� �߱� : ����Ʈ ��� x, ������� ���� ����Ʈ 100�� ����, Ƽ�� ���� Ƚ�� ����

				System.out.println("����Ʈ�� ������� �ʾҽ��ϴ�. ����Ʈ 100���� �����մϴ�.");
				System.out.println("������ �Ϸ�Ǿ����ϴ�. Ƽ���� �����մϴ�.");
			} else if (user_point < point_use) {
				System.out.println("����� �� �ִ� ����Ʈ�� �ʰ��Ͽ����ϴ�.");
				this.fieldPay(ticket);
			} else {

				// DAO Ƽ�� �߱� : ����Ʈ ���, ������� ���� ����Ʈ ����, Ƽ�� ���� Ƚ�� ����

				System.out.println("����Ʈ " + point_use + "���� ����Ͽ����ϴ�.");
				System.out.println("������ �Ϸ�Ǿ����ϴ�. Ƽ���� �����մϴ�.");
			}
		}

		// DAO Ƽ�� �߱� : �ش� Ƽ���� ���� ���� True

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
