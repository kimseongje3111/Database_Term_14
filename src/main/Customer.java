package main;

import java.util.Scanner;

public class Customer {
	Scanner scan = new Scanner(System.in);
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("������ ������ �����ϼ���.");
			int chooseWork = this.inputInt("1.��ȭ ��Ʈ �˻�  2.��ȭ ����  3.���� Ȯ��  4.���� ���  5.ȸ����������  6.ȸ��Ż��  7.�α׾ƿ�  ");
			
			switch (chooseWork) {
			case 1: // ��ȭ �˻�
				// p.4 ��ȭ �˻� (��Ʈ�� ������ ������)
				System.out.println("���� ������ ��ȭ�Դϴ�. (��������)");
				new ShowScreeningMovie().run();
				break;
			
			case 2: // ��ȭ ����
				// p.5 ��ȭ ���� - �����嵵 ����
				System.out.println("��ȭ ���Ÿ� �����մϴ�.");
				new ReserveTicket().run();
				break;
			case 3: // ���� Ȯ��
				System.out.println("���� ��Ȳ�Դϴ�.");
				new ShowReservation().run();
				break;
			case 4: // ���� ���
				new ShowReservation().run();
				System.out.println("����� ��ȭ�� ������ �ּ���.");
				new CancelReservation().run();
				break;
			case 5: // ȸ����������
				System.out.println("ȸ������ ������ �����մϴ�.");
				new FixUserDate().run();
				break;
			case 6: // ȸ��Ż��
				new UserInforDelete().run();
				break;
			case 7: // �α׾ƿ�
				System.out.println("�����մϴ�.");
				run = false;
				break;
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
