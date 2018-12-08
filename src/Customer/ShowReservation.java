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
		// ���� ���� ��Ȳ�� ������
		System.out.println("------------------------------------- ���� ��Ȳ -------------------------------------");
		System.out.println("[Ƽ�Ϲ�ȣ] ��ȭ / ��ȭ�� / �󿵰� ��ȣ / �� ��¥ / �� �ð� / �¼� ��ȣ / ������ / ���� ���� / ��� ����Ʈ");

		List<Ticket> my_ticket = dao.getTicketList(user);
		int count = 1;
		for (Ticket t : my_ticket) {
			System.out.println(count + ". " + t);
			count++;
		}
		System.out.println();

		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.���� ���   9.�ڷΰ���  ");

		switch (chooseWork) {
		case 1: // �ָ� ���
			new CancelReservation().run(user, my_ticket);
			System.out.println();
			this.run(user);
			break;

		case 9: // �ڷ� ����
			System.out.println("> ��ȭ ���� ������ �����մϴ�.");
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
