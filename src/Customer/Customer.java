package Customer;

import java.util.Scanner;

import Database.User;

public class Customer {
	Scanner scan = new Scanner(System.in);

	public void run(User user) {
		boolean run = true;
		while (run) {
			System.out.println();
			System.out.println();
			System.out.println("������ ������ �����ϼ���.");
			int chooseWork = this.inputInt("1.��ȭ ��Ʈ �˻�  2.��ȭ ����  3.��ȭ ���� ����  4.ȸ�� ����  5.�α׾ƿ�  ");

			switch (chooseWork) {
			case 1: // ��ȭ ��Ʈ �˻�
				System.out.println("���� ������ ��ȭ�Դϴ�. (��������)");
				new ShowScreeningMovie().run();
				System.out.println();
				break;

			case 2: // ��ȭ ����
				System.out.println("��ȭ ���Ÿ� �����մϴ�.");
				new ReserveTicket().run(user);
				System.out.println();
				break;

			case 3: // ��ȭ ���� ����
				System.out.println("���� ��Ȳ�Դϴ�.");
				new ShowReservation().run(user);
				System.out.println();
				break;

			case 4: // ȸ�� ����
				System.out.println("ȸ�� �����Դϴ�.");
				new UserData().run(user);
				System.out.println();
				break;

			case 5: // �α׾ƿ�
				System.out.println("���α׷��� �����մϴ�.");
				run = false;
				break;

			default:
			}
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
