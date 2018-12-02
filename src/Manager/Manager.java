package Manager;

import java.util.Scanner;

public class Manager {
	Scanner scan = new Scanner(System.in);
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("������ ������ �����ϼ���.");
			int chooseWork = this.inputInt("1.��ȭ�� ���� ����  2.�󿵰� ���� ����  3.��ȭ ���� ����"
					+ "4.�� ��ȭ ���� ����  5.VIP �� ����  6.��ȭ Ƽ�� ����  7.�α׾ƿ� ");

			switch (chooseWork) {
			case 1: // ��ȭ�� ���� ���� ( ��� ���� ���� )
				System.out.println("��ȭ���� �����մϴ�.");
				new TheaterManage().run();
				break;

			case 2: // �󿵰� ���� ���� ( ��� ���� ���� )
				System.out.println("�󿵰��� �����մϴ�.");
				new ScreenManage().run();
				break;
				
			case 3: // ��ȭ ���� ���� ( ��� ���� ���� )
				System.out.println("��ȭ�� �����մϴ�.");
				new MovieManage().run();
				break;
				
			case 4: // �� ��ȭ ���� ���� ( ��� ���� ���� )
				System.out.println("�� ��ȭ�� �����մϴ�.");
				new ScreeningMovieManage().run();
				break;
				
			case 5: // VIP �� ����
				System.out.println("VIP ���� �����մϴ�.");
				new VIPCustomerManage().run();
				break;
				
			case 6: // ��ȭ Ƽ�� ����
				System.out.println("������ Ƽ���� �����մϴ�.");
				new PublishTicket().run();
				break;
				
			case 7: // �α׾ƿ�
				System.out.println("�����մϴ�.");
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

