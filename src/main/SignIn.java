package main;

import java.util.Scanner;

public class SignIn {
	Scanner scan = new Scanner(System.in);
	public void run() {
		String userID = this.inputString("���̵� : ");
		String pwd = this.inputString("��й�ȣ : ");
		String name = this.inputString("���� (ex. �̼���) : ");
		String birth = this.inputString("������� (ex. 19940216) : ");
		String addr = this.inputString("�ּ� : ");
		String phoneNum = this.inputString("��ȭ��ȣ : ");
		
		// ����� ���̺� ȸ������ ������ ����ȴ� // ȸ�����Ե� ������ �ѹ� ������?
		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		// 2 �� ���� Ÿ�̸�?
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
