package Customer;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Movie;
import Database.ScreeningMovie;
import Database.Theater;
import Database.User;

public class ReserveTicket {

	Scanner scan = new Scanner(System.in);
	private String movieName;
	private String theaterName;
	private String screenDate;
	private String screenTime;

	DAO dao = DAO.sharedInstance();

	public void run(User user) {

		System.out.println("<<<<<<<<<<<<<<<<<<<< �� ��ȭ ��� >>>>>>>>>>>>>>>>>>>>");
		List<ScreeningMovie> screening_list = dao.getScreeningMovieList(); // DAO �� ��ȭ : �� ��ȭ ��� ��������
		for (ScreeningMovie sm : screening_list) {
			System.out.println(sm);
		}
		System.out.println();

		// ��ȭ ����
		movieName = this.inputString("��ȭ ������ �Է��Ͻÿ�. : ");
		Movie movie = new Movie();
		movie.setMovieName(movieName);
		
		boolean r1 = dao.checkMovieName(movie); // DAO ��ȭ : ���� Ȯ��
		
		if (!r1) {
			System.out.println("�������� �ʴ� ��ȭ�Դϴ�.");
			this.run(user);
		}
		
		// ��ȭ��
		theaterName = this.inputString("��ȭ���� ������. : ");
		Theater theater = new Theater(theaterName);
		
		boolean r2 = dao.checkTheaterId(theater);
		
		if (!r2) {
			System.out.println("�������� �ʴ� ��ȭ���Դϴ�.");
			this.run(user);
		}
		
		// �󿵳�¥
		boolean r3 = false;
		screenDate = this.inputString("�󿵳�¥�� ���ϼ���.");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenDate() == screenDate) {
				r3 = true;
			}
		}
		
		if (!r3) {
			System.out.println("�������� �ʴ� �󿵳�¥�Դϴ�.");
			this.run(user);
		}
		
		// �󿵽ð�
		boolean r4 = false;
		screenTime = this.inputString("�󿵽ð��� �Է��ϼ���. : ");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenTime() == screenTime) {
				r4 = true;
			}
		}
		
		if (!r4) {
			System.out.println("�������� �ʴ� �󿵽ð��Դϴ�.");
			this.run(user);
		}
		
		//List<ReserveTicket>

		int howManyReservation = this.inputInt("��� �¼��� �����ϰڽ��ϱ�. : ");
		// ������ �¼� ������ ũ�ٸ� ~ howManyReservation�� �ٽ� �Է�

		// ������ �ȵǾ� �ִ� �¼��� �����̰����ϵ���
		for (int i = 0; i < howManyReservation; i++) {
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
		case 1: // ���ͳ� ����
			this.internetPay();
			break;
		case 2: // ���� ����
			this.fieldPay();
			break;
		default:
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
