package Manager;

import java.util.Scanner;

public class ScreenManage {
	Scanner scan = new Scanner(System.in);
	private String screenId; // ��ȭ��  + �󿵰� ��ȣ
	private String theaterId;
	private String screeId;
	private int availSeat;
	private String theaterIdToFix; // ������ ��ȭ���� ���̵�
	private String fixThisScreen;
	
	public void run() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.�󿵰� ���  2.�󿵰� ���� ����   9.�ٸ� ���� ����");
		
		switch (chooseWork) {
		case 1: // �󿵰� ���
			System.out.println("�󿵰��� ����մϴ�.");
			this.addScreen();
			this.run();
			break;
			
		case 2: // �󿵰� ���� ����
			// ��ȭ�� ����Ʈ�� ������ -> ��ȭ�� id�� �Է��� ���翩�� Ȯ��
			//   -> �󿵰� ����Ʈ�� ������ -> �󿵰� ��ȣ�� �Է��� ���翩�� Ȯ�� ����� �ϳ��� ��� ���� ������ /
			
			// <��ȭ�� ����Ʈ ��� >
			theaterIdToFix = this.inputString("������ ��ȭ�� ���̵� : ");
			if(!theaterIdToFix.equals("�Է��� ���̵� ���� ���ϸ�")) 
				System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
			else { 
				// < �󿵰� ����Ʈ ���
				fixThisScreen = this.inputString("������ �󿵰� ��ȣ : ");
				if(!fixThisScreen.equals("�󿵰��� ���� ���ϸ�")) 
					System.out.println("��ġ�ϴ� �󿵰��� �����ϴ�.");
				else
					this.fixScreenInfo();
			}
			this.run();
			break;
			
		case 9:
			System.out.println("��ȭ�� ������ ��Ĩ�ϴ�.");
			break;
			default :
				this.run();
		}
	}

	private void addScreen() {
		// < ��ȭ�� ID�� ���� �����ش�. >
		theaterId = this.inputString("�󿵰��� ����� ��ȭ�� ���̵� : ");
		// if < ��ȭ�� ID�� ��ġ��> {
		screeId = this.inputString("����� �󿵰� ��ȣ : ");
		availSeat = this.inputInt("�󿵰��� �ִ� �¼� �� : ");
//		}
//		else {
//			System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
//		}
		
		// �󿵰� �߰�
		System.out.println("�󿵰��� ��ϵǾ����ϴ�.");
	}
	
	private void fixScreenInfo() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.�󿵰� ��ȣ  2.�󿵰��� �ִ� �¼� ��  9.���� ����");
		switch(chooseWork) {
		case 1 : // �󿵰� ��ȣ
			String newScreenId = this.inputString("���ο� ��ȭ�� ��ȣ : ");
			// < �󿵰� ��ȣ ���� >
			this.fixScreenInfo();
			break;
		
		case 2 : // �󿵰� �ִ� �¼� ��
			int newAvailSeat = this.inputInt("���ο� �󿵰� �¼� �� : ");
			// < �¼��� ���� > - ���� ó�� : ���� �� ���� ���� �Է��� ������ Ŭ ��??
			this.fixScreenInfo();
			break;
			
		case 9: // ���� ����
			System.out.println("�󿵰� ���� ������ ��Ĩ�ϴ�.");
			break;
			default :
				this.fixScreenInfo();
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
