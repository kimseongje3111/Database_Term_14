package Manager;

import java.util.Scanner;

public class TheaterManage {
	Scanner scan = new Scanner(System.in);
	private String theaterId;
	private String theaterAddr;
	private String theaterTel;
	private int screenNum;
	private String theaterIdToFix; // ������ ��ȭ���� ���̵�
	private String deleteThisTheater;
	
	public void run() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.��ȭ�� ���  2.��ȭ�� ���� ����  3.��ȭ�� ����  9.�ٸ� ���� ����");
		
		switch (chooseWork) {
		case 1: // ��ȭ�� ���
			System.out.println("��ȭ���� ����մϴ�.");
			this.addTheater();
			this.run();
			break;
			
		case 2: // ��ȭ�� ���� ����
			// ��ȭ�� id�� �Է��� ���翩�� Ȯ�� -> ����� �ϳ��� ��� ���� ������ /
			theaterIdToFix = this.inputString("������ ��ȭ�� ���̵� : ");
			if(theaterIdToFix.equals("�Է��� ���̵� ���� �ϸ�")) this.fixTheaterInfo();
			else System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
			this.run();
			break;
			
		case 3 : // ��ȭ�� ����
			deleteThisTheater = this.inputString("������ ��ȭ�� ���̵� : ");
			if(deleteThisTheater.equals("�Է��� ���̵� ���� �ϸ�")) this.deleteTheater();
			else System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
			this.run();
			
		case 9:
			System.out.println("��ȭ�� ������ ��Ĩ�ϴ�.");
			break;
			default :
				this.run();
		}
	}

	private void addTheater() {
		theaterId = this.inputString("��ȭ�� ���̵� : ");
		// if < ��ȭ�� ID�� ��ġ��> {
		theaterAddr = this.inputString("��ȭ�� �ּ� : ");
		theaterTel = this.inputString("��ȭ�� ��ȭ��ȣ : ");
		screenNum = this.inputInt("��ȭ���� (�ִ�)�󿵰� �� : ");
//		}
//		else {
//			System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
//		}
		
		// <��ȭ�� �߰�>
		System.out.println("��ȭ���� ��ϵǾ����ϴ�.");
	}
	
	private void fixTheaterInfo() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.��ȭ�� �ּ�  2.��ȭ�� ��ȭ��ȣ  3.(�ִ�)�󿵰� ��  9.���� ����");
		switch(chooseWork) {
		case 1 : // ��ȭ�� �ּ� ����
			String newTheaterAddress = this.inputString("���ο� ��ȭ�� �ּ� : ");
			// < �ּ� ���� ���� >
			this.fixTheaterInfo();
			break;
		
		case 2 : // ��ȭ�� ��ȭ��ȣ ����
			String newTheaterTel = this.inputString("���ο� ��ȭ�� ��ȭ��ȣ : ");
			// < ��ȭ��ȣ ���� ���� >
			this.fixTheaterInfo();
			break;
			
		case 3 : // ��ȭ�� �ִ� �󿵰� �� ����
			int newScreenNum = this.inputInt("���� ������ (�ִ�)�󿵰� �� : ");
			// < �󿵰� �� ���� ���� >
			this.fixTheaterInfo();
			break;
			
		case 9: // ���� ����
			System.out.println("��ȭ�� ���� ������ ��Ĩ�ϴ�.");
			break;
			default :
				this.fixTheaterInfo();
		}
	}
	
	private void deleteTheater() {
		if(this.inputString("�����Ͻðڽ��ϱ�? (Y / N) ").equals("Y")) {
			// < �Է��� ��ȭ�� ���̵� ����
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
