package main;

import java.util.Scanner;

public class CancelReservation {
	Scanner scan = new Scanner(System.in);
	public void run() {
		// ���� ��ȣ�� �Է��ϰ� ���� �Ǵ� ���� ��� ����
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
