package Manager;

import java.util.Scanner;

public class PublishTicket {
	Scanner scan = new Scanner(System.in);
	String userId;
	String ticketId;
	
	public void run() { // �߱��� �������ص� ��� ����.
		// < user �� ID�� �Է¹޴´�. >
		userId = this.inputString("�߱��� ���� ���̵� : ");
		// < user �� ������ Ƽ���� ����Ѵ�. >
		
		// < ������ Ƽ���� �Է� �޴´� >
		ticketId = this.inputString("������ Ƽ�� ���̵� : ");
		
		// < ���� �Ϸ��̸� Ƽ�� ���� >
		if(ticketId.equals("Ƽ���� ���� �Ϸ���"))
			System.out.println("���� �Ϸ�� Ƽ���Դϴ�. Ƽ���� �����Ͽ����ϴ�.");
		// < ������ �ȵǾ� �ִٸ� customer���� ���� �ϵ��� ���ͳ� ���� ���� >
		else {
			System.out.println("������ �Ϸ���� �ʾҽ��ϴ�. ������ �����մϴ�.");
			// < customer�� ������ ��� >
			this.fieldPay();
			System.out.println("������ �Ϸ�Ǿ����ϴ�. Ƽ���� �����մϴ�.");
		}
		
		if(this.inputString("Ƽ���� �߰��� �����Ͻðڽ��ϱ�? (y / n) ").equals("Y"))
			this.run();
		else
			System.out.println("Ƽ�� ������ �����մϴ�.");
	}
	
	private void fieldPay() {
		// customer�� ���
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
