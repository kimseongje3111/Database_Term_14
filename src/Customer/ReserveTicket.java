package Customer;

import java.util.Scanner;

import Database.User;

public class ReserveTicket {
	
	Scanner scan = new Scanner(System.in);
	private String movieName;
	private String theaterName;
	private String screenDate;
	private String screenTime;
	
	public void run(User user) {
		// ��ȭ ���� ����
		// ��ȭ ���� ����Ʈ�� ������
		movieName = this.inputString("��ȭ ������ �Է��Ͻÿ�. : ");
		// �̸��� ���� ����
		if (movieName.equals("�̸��� ����")) {
			System.out.println("�� ���� �Է��Դϴ�.");
			run(user);
		}
		// ��ȭ�� ����Ʈ�� ������
		theaterName = this.inputString("��ȭ���� ������. : ");
		if (theaterName.equals("��ȭ���� ����")) {
			System.out.println("�� ���� �Է��Դϴ�.");
			run(user);
		}
		// �ش� ��ȭ�� ��ȭ���� �󿵳�¥ ����Ʈ�� ��� ������
		screenDate = this.inputString("�󿵳�¥�� ���ϼ���.");
		if (screenDate.equals("�󿵳�¥�� ����")) {
			System.out.println("�� ���� �Է��Դϴ�.");
			run(user);
		}
		// �ش� ��ȭ�� ��ȭ���� �󿵳�¥�� �󿵽ð� ����Ʈ�� ��� ������
		screenTime = this.inputString("�󿵽ð��� �Է��ϼ���. : ");
		if (screenTime.equals("�󿵽ð��� ����")) {
			System.out.println("�� ���� �Է��Դϴ�.");
			run(user);
		}
		// �տ� �Է¹��� ������ ������ ���� �¼����� �ش� �󿩰��� �����¼��� ���������� (�¼�����ŭ) ��� ���
		// <������ ������ �¼� ���� ���>
		
		int howManyReservation = this.inputInt("��� �¼��� �����ϰڽ��ϱ�. : ");
		// ������ �¼� ������ ũ�ٸ� ~ howManyReservation�� �ٽ� �Է�
		
		// ������ �ȵǾ� �ִ� �¼��� �����̰����ϵ���
		for(int i=0; i<howManyReservation; i++) {
			String seatNumber = this.inputString("�¼��� �Է��ϼ��� : ");
			// <if ���� ���� ���� �¼� �߿��� �Է°� ������ ���ٸ� seatNumber�ٽ� �Է� �޴´�
			// <else ���� ��� ����> - > �� ���� 1ȸ �Ϸ�
			this.finishReservationWithchoossingPay();
		}
		System.out.println("���Ű� �Ϸ�Ǿ����ϴ�.");
	}

	private void finishReservationWithchoossingPay() {
		int howToPay = this.inputInt("���������� �����ϼ���. ( 1. ���ͳ� ���� 2. ���� ���� )");
		switch (howToPay) {
		case 1 : // ���ͳ� ����
			this.internetPay();
			break;
		case 2 : // ���� ����
			this.fieldPay();
			break;
			default :
				System.out.println("�ٽ� �Է��Ͻÿ�.");
				this.finishReservationWithchoossingPay();
		}
	}

	private void internetPay() {
		// p.6 ���� - ���ͳ� ����(����Ʈ�� ���� ����, ���� ���Խ� 100����Ʈ �ο�), ���� ����(���� ����ȵ�)
		// <����Ʈ ������>
		// <����� ����Ʈ ���> - ����ó�� : ����Ʈ �ʰ�, 1000���̻� ��밡��
		// < ��, ����Ʈ�� �����ϸ� 100 �Ⱥο� ���� ���� >
		// ���� ���� �� < ����Ʈ 100 �ο� >
		// ���� ��
	}

	private void fieldPay() {
		System.out.println("���忡�� ������ �Ϸ��� �ּ���.");
		// Ƽ�� ���̺� ����, ���� ������ �ȵǾ��ٷ� ����
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
