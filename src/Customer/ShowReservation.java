package Customer;

import java.util.Scanner;

import Database.User;

public class ShowReservation {

	Scanner scan = new Scanner(System.in);

	public void run(User user) {
		// ���� ���� ��Ȳ�� ������
		
		int chooseWork = this.inputInt("1.���� ���   9.�ڷΰ���  ");
		
		switch (chooseWork) {
		case 1: // �ָ� ���
			new CancelReservation().run(user);
			break;
			
		case 9: // �ڷ� ����
			System.out.println("��ȭ ���� ������ �����մϴ�.");
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
