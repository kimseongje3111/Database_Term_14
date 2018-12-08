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
	private String deleteThisScreen;

	DAO dao = DAO.sharedInstance();
	Screen screen = new Screen();
	Theater theater = new Theater();

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
			theaterIdToFix = this.inputString("������ ��ȭ�� ���̵� (ex.daejeon_cgv) : ");
			screen.setTheaterId(theaterIdToFix);
			theater.setTheaterId(theaterId);

			boolean r1 = dao.checkTheaterId(theater); // DAO �󿵰� : ��ȭ�� �ߺ� �˻�

			if (r1) {
				fixThisScreen = this.inputString("������ �󿵰� ��ȣ (ex.1) : ");
				screenId = theaterIdToFix + fixThisScreen;
				screen.setScreenId(screenId);

				boolean r2 = dao.checkScreenId(screen); // DAO �󿵰� : ���̵� �ߺ� �˻�

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
		theaterId = this.inputString("�󿵰��� ����� ��ȭ�� ���̵� (ex.daejeon_cgv) : ");
		screen.setTheaterId(theaterId);
		theater.setTheaterId(theaterId);

		boolean b1 = dao.checkTheaterId(theater); // DAO �󿵰� : ��ȭ�� ���̵� �ߺ� �˻�

		if (b1) {
			screenNum = this.inputString("����� �󿵰� ��ȣ (ex.1) : ");
			availSeat = this.inputInt("�󿵰��� �ִ� �¼� �� (ex.30) : ");
			screenId = theaterId + screenNum;
			screen.setScreenId(screenId);
			screen.setTheaterId(theaterId);
			screen.setScreenNum(screenNum);
			screen.setAvailSeat(availSeat);

			boolean b2 = dao.checkScreenId(screen); // DAO �󿵰� : ���̵� �ߺ� �˻�

			if (!b2) {

				boolean b3 = dao.insertScreen(screen); // DAO �󿵰� : ��� (�ش� ��ȭ���� �ִ� �󿵰� �� Ȯ��)

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
		System.out.println("������ ������ �Է��ϼ���.");
		int newAvailSeat = this.inputInt("���ο� �󿵰� �¼� �� : ");
		screen.setAvailSeat(newAvailSeat);

		boolean b = dao.updateScreen(screen); // DAO �󿵰� : ���� ������Ʈ

		if (b) {
			System.out.println("�󿵰� �¼� ���� ����Ǿ����ϴ�.");
		} else {
			System.out.println("�󿵰� �¼� �� ������ �����Ͽ����ϴ�.");
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
