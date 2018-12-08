package Customer;

import java.util.Scanner;

import Database.DAO;
import Database.User;

public class FixUserData {

	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run(User user) {
		User c_user = null; // DAO ȸ�� ���� ���� : ȸ�� ���� ��������
		System.out.println(c_user);
		System.out.println();

		System.out.println("������ ������ �Է��ϼ���.");
		String newPassword = this.inputString("���ο� �н����� : ");
		String newName = this.inputString("���ο� �̸� : ");
		String newBirth = this.inputString("���ο� ������� : ");
		String newAddr = this.inputString("���ο� �ּ� : ");
		String newPhone = this.inputString("���ο� ��ȭ��ȣ : ");

		c_user.setPwd(newPassword);
		c_user.setName(newName);
		c_user.setBirth(newBirth);
		c_user.setAddr(newAddr);
		c_user.setPhoneNum(newPhone);

		boolean r = true; // DAO ȸ�� ���� ���� : ���� ������Ʈ

		if (r) {
			System.out.println("ȸ�� ������ ����Ǿ����ϴ�.");
		} else {
			System.out.println("ȸ�� ���� ������ �����Ͽ����ϴ�.");
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
