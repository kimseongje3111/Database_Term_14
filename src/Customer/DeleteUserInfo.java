package Customer;

import java.util.Scanner;

public class DeleteUserInfo {
	Scanner scan = new Scanner(System.in);
	public void run() {
		String sure = this.inputString("���� Ż���Ͻðڽ��ϱ�? (y / n) ");
		// �׳� �ٷ� ����
		// ��� Ƽ�� ����� ���� cascade
		
		// < Ż���ϸ� ���α׷� ���� >
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