package Customer;

import java.util.Scanner;

import Database.User;

public class UserData {
	
	Scanner scan = new Scanner(System.in);
	
	public void run(User user) {
		int chooseWork = this.inputInt("1.ȸ�� ���� ����  2.ȸ�� Ż��  9.�ڷΰ���  ");
		
		switch (chooseWork) {
		case 1: // ȸ�� ���� ����
			new FixUserData().run(user);
			break;

		case 2: // ȸ�� Ż��
			new DeleteUserInfo().run(user);
			break;

		case 9: // �ڷ� ����
			System.out.println("ȸ�� ������ �����մϴ�.");
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
