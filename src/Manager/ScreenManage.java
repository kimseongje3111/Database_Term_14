package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Screen;
import Database.Theater;

public class ScreenManage {

	Scanner scan = new Scanner(System.in);
	private String screenId;
	private String theaterId;
	private String screenNum;
	private int availSeat;
	private String theaterIdToFix;
	private String fixThisScreen;

	DAO dao = DAO.sharedInstance();
	Screen screen = new Screen();

	public void run() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.�󿵰� ���  2.�󿵰� ���� ����  9.�ٸ� ���� ����  ");

		switch (chooseWork) {
		case 1: // �󿵰� ���
			System.out.println("�󿵰��� ����մϴ�.");
			this.addScreen();
			System.out.println();
			this.run();
			break;

		case 2: // �󿵰� ���� ����
			theaterIdToFix = this.inputString("������ ��ȭ�� ���̵� : ");
			screen.setTheaterId(theaterIdToFix);

			boolean r1 = true; // DAO �󿵰� : ��ȭ�� �ߺ� �˻�

			if (r1) {
				fixThisScreen = this.inputString("������ �󿵰� ��ȣ : ");
				screenId = theaterIdToFix + fixThisScreen;
				screen.setScreenId(screenId);

				boolean r2 = true; // DAO �󿵰� : ���̵� �ߺ� �˻�

				if (r2) {
					this.fixScreenInfo();
					System.out.println();
				} else {
					System.out.println("��ġ�ϴ� �󿵰��� �����ϴ�.");
				}
			} else {
				System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
			}

			this.run();
			break;

		case 9:
			System.out.println("��ȭ�� ������ ��Ĩ�ϴ�.");
			break;

		default:
			this.run();
		}
	}

	private void addScreen() {
		theaterId = this.inputString("�󿵰��� ����� ��ȭ�� ���̵� : ");
		screen.setTheaterId(theaterId);

		boolean b1 = true; // DAO �󿵰� : ��ȭ�� ���̵� �ߺ� �˻�

		if (b1) {
			screenNum = this.inputString("����� �󿵰� ��ȣ : ");
			availSeat = this.inputInt("�󿵰��� �ִ� �¼� �� : ");
			screenId = theaterId + screenNum;
			screen.setScreenId(screenId);
			screen.setTheaterId(theaterId);
			screen.setScreenNum(screenNum);
			screen.setAvailSeat(availSeat);

			boolean b2 = false; // DAO �󿵰� : ���̵� �ߺ� �˻�

			if (!b2) {

				boolean b3 = true; // DAO �󿵰� : ��� (�ش� ��ȭ���� �ִ� �󿵰� �� Ȯ��)

				if (b3) {
					System.out.println("�󿵰��� ��ϵǾ����ϴ�.");
				} else {
					System.out.println("�󿵰� ����� �����Ͽ����ϴ�.");
				}
			} else {
				System.out.println("�̹� �����ϴ� �󿵰��Դϴ�.");
			}
		} else {
			System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
		}

	}

	private void fixScreenInfo() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.�󿵰��� �ִ� �¼� ��  9.���� ����  ");

		switch (chooseWork) {
		case 1: // �󿵰� �ִ� �¼� ��
			int newAvailSeat = this.inputInt("���ο� �󿵰� �¼� �� : ");
			screen.setAvailSeat(newAvailSeat);

			boolean b = true; // DAO �󿵰� : ���� ������Ʈ

			if (b) {
				System.out.println("�󿵰� �¼� ���� ����Ǿ����ϴ�.");
			} else {
				System.out.println("�󿵰� �¼� �� ������ �����Ͽ����ϴ�.");
			}

			this.fixScreenInfo();
			break;

		case 9: // ���� ����
			System.out.println("�󿵰� ���� ������ ��Ĩ�ϴ�.");
			break;

		default:
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
