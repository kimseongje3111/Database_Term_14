package main;

import java.util.Scanner;

public class LogIn {
	Scanner scan = new Scanner(System.in);
	
	public void run() {
		String id = this.inputString("���̵� : ");
		String pw = this.inputString("��й�ȣ : ");
		
//		id�� �������̸� �����ڷ� ����
		System.out.println("�����ڷ� �α��� �մϴ�.");
		new Manager().run();
		
//		id�� ���̸� ������ ����
		System.out.println("ȯ���մϴ� ����.");
		new Customer().run();
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
