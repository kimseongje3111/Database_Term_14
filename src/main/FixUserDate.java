package main;

import java.util.Scanner;

public class FixUserDate {
	Scanner scan = new Scanner(System.in);
	public void run() {
		// ������� ���̵� �� ������ �ҷ���
		// ó���� while 1. �н����� 2. ���� 3. ������� 4. �ּ� 5. ��ȭ��ȣ �߿� ������ �ٲ��� �Է� �޴´�
		// �� 9. ����
		// �ϳ��ϳ� �����Ͽ� ��� ������Ʈ ���ش�.
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
