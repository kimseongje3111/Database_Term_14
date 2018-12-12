package Manager;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Ticket;
import Database.User;

public class PublishTicket {

	Scanner scan = new Scanner(System.in);
	String userId;

	DAO dao = DAO.sharedInstance();
	User user = new User();

	public void run() {
		userId = this.inputString("> �߱��� ���� ���̵� (ex.root) : ");
		user.setUserId(userId);

		List<Ticket> ticket_list = dao.getTicketList(user); // DAO Ƽ�� �߱� : �������� Ƽ�� (������ Ȯ��)
		
		if (ticket_list == null) {
			System.out.println("����� Ƽ���� �������� �ʽ��ϴ�.");
			this.run();
		}

		System.out.println("------------------------------------- ������ Ƽ�� ��� -------------------------------------");
		System.out.println("[Ƽ�Ϲ�ȣ] ��ȭ / ��ȭ�� / �󿵰� ��ȣ / �� ��¥ / �� �ð� / �¼� ��ȣ / ������ / ���� ���� / ��� ����Ʈ");

		int count = 1;
		for (Ticket ticket : ticket_list) {
			System.out.println(count + ". " + ticket);
			count++;
		}
		System.out.println();

		System.out.println("> ���� ������ Ȯ���մϴ�.");
		for (Ticket ticket : ticket_list) {
			if (ticket.isPaymentBool()) {
				System.out.println("> [" + ticket.getTicketId() + "] ���� �Ϸ�� Ƽ���Դϴ�. Ƽ���� �����մϴ�.");
			} else {
				System.out.println("> [" + ticket.getTicketId() + "] ������ �Ϸ���� �ʾҽ��ϴ�. ���� ������ �����մϴ�.");
				this.fieldPay(ticket);
				System.out.println();
			}
		}

		System.out.println();
		if (this.inputString("> Ƽ���� �߰��� �����Ͻðڽ��ϱ�? (Y/N) ").equals("Y")) {
			this.run();
		} else {
			System.out.println("> Ƽ�� ������ �����մϴ�.");
		}

	}

	private void fieldPay(Ticket ticket) {
		System.out.println("> ����Ʈ�� 1000P �̻���� ��� �����մϴ�.");
		int user_point = dao.getUsedPoint(user); // DAO Ƽ�� �߱� : �������� ���� ����Ʈ ��������
		System.out.println("> ���� ������� ���� ����Ʈ : " + user_point + " P");

		int point_use = this.inputInt("> ����� ����Ʈ : ");

		if (user_point < 1000) {
			System.out.println("> ����� ����Ʈ��  1000P ���� �Դϴ�. ����Ʈ ����� �Ұ����մϴ�.");

			dao.ticketing(user, false, 0); // DAO Ƽ�� �߱� : ����Ʈ ��� x, ������� ���� ����Ʈ 100�� ����, Ƽ�� ���� Ƚ�� ����
			System.out.println("> ���� �������Դϴ�. ����Ʈ 100P �� �����մϴ�.");
			System.out.println("> ������ �Ϸ�Ǿ����ϴ�. Ƽ���� �����մϴ�.");

		} else if (point_use != 0 && point_use < 1000) {
			System.out.println("> ����Ʈ�� 1000P �̻���� ��� �����մϴ�. ����Ʈ ����� �Ұ����մϴ�.");

			dao.ticketing(user, false, 0); // DAO Ƽ�� �߱� : ����Ʈ ��� x, ������� ���� ����Ʈ 100�� ����, Ƽ�� ���� Ƚ�� ����
			System.out.println("> ���� �������Դϴ�. ����Ʈ 100P �� �����մϴ�.");
			System.out.println("> ������ �Ϸ�Ǿ����ϴ�. Ƽ���� �����մϴ�.");
			
		} else {
			if (point_use == 0) {
				dao.ticketing(user, false, 0); // DAO Ƽ�� �߱� : ����Ʈ ��� x, ������� ���� ����Ʈ 100�� ����, Ƽ�� ���� Ƚ�� ����

				System.out.println("> ����Ʈ�� ������� �ʾҽ��ϴ�.");
				System.out.println("> ���� �������Դϴ�. ����Ʈ 100P �� �����մϴ�.");

			} else if (user_point < point_use) {
				System.out.println("> ����� �� �ִ� ����Ʈ�� �ʰ��Ͽ����ϴ�. �ٽ� ������ �����մϴ�.");
				this.fieldPay(ticket);

			} else {
				dao.ticketing(user, true, point_use); // DAO Ƽ�� �߱� : ����Ʈ ���, ������� ���� ����Ʈ ����, Ƽ�� ���� Ƚ�� ����
				ticket.setUsedPoint(point_use);

				System.out.println("> ����Ʈ " + point_use + "P �� ����Ͽ����ϴ�.");
				System.out.println("> ���� �������Դϴ�.");
				System.out.println("> ������ �Ϸ�Ǿ����ϴ�. Ƽ���� �����մϴ�.");
			}
		}

		dao.OnSitePayment(ticket); // DAO Ƽ�� �߱� : �ش� Ƽ���� ���� ���� True, ��� ����Ʈ ����

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
