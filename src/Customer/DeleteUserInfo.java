package Customer;

import java.util.Scanner;

import Database.DAO;
import Database.User;

public class DeleteUserInfo {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run(User user) {
		String sure = this.inputString("���� Ż���Ͻðڽ��ϱ�? (Y/N) ");

		if (sure.equals("Y")) {

			boolean r = true; // DAO ȸ�� Ż�� : ���� ����

			if (r) {
				System.out.println("ȸ�� Ż�� �Ϸ�Ǿ����ϴ�.");
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			} else {
				System.out.println("ȸ�� Ż�� �����Ͽ����ϴ�.");
			}

		} else {
			System.out.println("ȸ�� Ż�� ����մϴ�.");
		}
		// �׳� �ٷ� ����
		// ��� Ƽ�� ����� ���� cascade

		// < Ż���ϸ� ���α׷� ���� >
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
