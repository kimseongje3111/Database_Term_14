package main;

import java.util.Scanner;

public class UserInforDelete {
	Scanner scan = new Scanner(System.in);
	public void run() {
		String sure = this.inputString("���� Ż���Ͻðڽ��ϱ�? ");
		// �׳� �ٷ� ����
		// ��� Ƽ�� ����� ���� cascade
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
