package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Theater;

public class TheaterManage {

	Scanner scan = new Scanner(System.in);
	private String theaterId;
	private String theaterAddr;
	private String theaterTel;
	private int screenNum;
	private String theaterIdToFix;
	private String deleteThisTheater;

	DAO dao = DAO.sharedInstance();
	Theater theater = new Theater();

	public void run() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.��ȭ�� ���  2.��ȭ�� ���� ����  3.��ȭ�� ����  9.�ٸ� ���� ����  ");

		switch (chooseWork) {
		case 1: // ��ȭ�� ���
			System.out.println("��ȭ���� ����մϴ�.");
			this.addTheater();
			System.out.println();
			this.run();
			break;

		case 2: // ��ȭ�� ���� ����
			theaterIdToFix = this.inputString("������ ��ȭ�� ���̵� : ");
			theater.setTheaterId(theaterIdToFix);

			boolean r1 = dao.checkTheaterId(theater); // DAO ��ȭ�� : ���̵� �ߺ� �˻�

			if (r1) {
				this.fixTheaterInfo();
				System.out.println();
			} else {
				System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
			}

			this.run();
			break;

		case 3: // ��ȭ�� ����
			deleteThisTheater = this.inputString("������ ��ȭ�� ���̵� : ");
			theater.setTheaterId(deleteThisTheater);

			boolean r2 = dao.checkTheaterId(theater); // DAO ��ȭ�� : ���̵� �ߺ� �˻�

			if (r2) {
				this.deleteTheater();
				System.out.println();
			} else {
				System.out.println("��ġ�ϴ� ��ȭ���� �����ϴ�.");
			}

			this.run();
			break;

		case 9: // ����
			System.out.println("��ȭ�� ������ ��Ĩ�ϴ�.");
			break;

		default:
			this.run();
		}
	}

	private void addTheater() {
		theaterId = this.inputString("��ȭ�� ���̵� : ");
		theater.setTheaterId(theaterId);

		boolean b1 = dao.checkTheaterId(theater);  // DAO ��ȭ�� : ���̵� �ߺ� �˻�

		if (!b1) {
			theaterAddr = this.inputString("��ȭ�� �ּ� : ");
			theaterTel = this.inputString("��ȭ�� ��ȭ��ȣ : ");
			screenNum = this.inputInt("��ȭ���� (�ִ�)�󿵰� �� : ");
			theater.setTheaterAddr(theaterAddr);
			theater.setTheaterTel(theaterTel);
			theater.setScreenNum(screenNum);

			boolean b2 = dao.insertTheater(theater); // DAO ��ȭ�� : ����

			if (b2) {
				System.out.println("��ȭ���� ��ϵǾ����ϴ�.");
			} else {
				System.out.println("��ȭ�� ����� �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("�̹� �����ϴ� ��ȭ���Դϴ�.");
		}

	}

	private void fixTheaterInfo() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.��ȭ�� �ּ�  2.��ȭ�� ��ȭ��ȣ  3.(�ִ�)�󿵰� ��  9.���� ����  ");

		switch (chooseWork) {
		case 1: // ��ȭ�� �ּ� ����
			String newTheaterAddress = this.inputString("���ο� ��ȭ�� �ּ� : ");
			theater.setTheaterAddr(newTheaterAddress);

			boolean b1 = dao.updateTheater(theater); // DAO ��ȭ�� : ���� ������Ʈ

			if (b1) {
				System.out.println("�ּҰ� ����Ǿ����ϴ�.");
			} else {
				System.out.println("�ּ� ������ �����Ͽ����ϴ�.");
			}

			this.fixTheaterInfo();
			break;

		case 2: // ��ȭ�� ��ȭ��ȣ ����
			String newTheaterTel = this.inputString("���ο� ��ȭ�� ��ȭ��ȣ : ");
			theater.setTheaterTel(newTheaterTel);

			boolean b2 = dao.updateTheater(theater); // DAO ��ȭ�� : ���� ������Ʈ

			if (b2) {
				System.out.println("��ȭ��ȣ�� ����Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ��ȣ ������ �����Ͽ����ϴ�.");
			}

			this.fixTheaterInfo();
			break;

		case 3: // ��ȭ�� �ִ� �󿵰� �� ����
			int newScreenNum = this.inputInt("���� ������ (�ִ�)�󿵰� �� : ");
			theater.setScreenNum(newScreenNum);

			boolean b3 = dao.updateTheater(theater); // DAO ��ȭ�� : ���� ������Ʈ

			if (b3) {
				System.out.println("�󿵰� ���� ����Ǿ����ϴ�.");
			} else {
				System.out.println("�󿵰� �� ������ �����Ͽ����ϴ�.");
			}

			this.fixTheaterInfo();
			break;

		case 9: // ���� ����
			System.out.println("��ȭ�� ���� ������ ��Ĩ�ϴ�.");
			break;

		default:
			this.fixTheaterInfo();
		}

	}

	private void deleteTheater() {
		if (this.inputString("�����Ͻðڽ��ϱ�? (Y/N) ").equals("Y")) {

			boolean b = dao.deleteTheater(theater); // DAO ��ȭ�� : ����

			if (b) {
				System.out.println("��ȭ���� �����Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ�� ������ �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("��ȭ�� ���� ������ ����մϴ�.");
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
