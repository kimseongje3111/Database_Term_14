package Customer;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Ticket;
import Database.User;

public class CancelReservation {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run(User user, List<Ticket> list) {
		int choose = this.inputInt("���Ÿ� ����� Ƽ���� �����ϼ���. : ");
		Ticket remove_ticket = list.remove(choose - 1);
		dao.deleteTicket(remove_ticket); // DAO ���� ��� : Ƽ�� ����
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
