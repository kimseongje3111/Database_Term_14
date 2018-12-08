package Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.Movie;
import Database.ReservedSeat;
import Database.Screen;
import Database.ScreeningMovie;
import Database.Theater;
import Database.Ticket;
import Database.User;

public class ReserveTicket {

	Scanner scan = new Scanner(System.in);
	private String movieName;
	private String theaterName;
	private String screenDate;
	private String screenTime;
	private String screenNum;

	DAO dao = DAO.sharedInstance();

	public void run(User user) {

		System.out.println("<<<<<<<<<<<<<<<<<<<< �� ��ȭ ��� >>>>>>>>>>>>>>>>>>>>");
		List<ScreeningMovie> screening_list = dao.getScreeningMovieList(); // DAO �� ��ȭ : �� ��ȭ ��� ��������
		for (ScreeningMovie sm : screening_list) {
			System.out.println(sm);
		}
		System.out.println();

		// ��ȭ ����
		movieName = this.inputString("��ȭ ������ �Է��Ͻÿ�. (ex.ironman) : ");
		Movie movie = new Movie();
		movie.setMovieName(movieName);

		boolean r1 = dao.checkMovieName(movie); // DAO ��ȭ : ���� Ȯ��

		if (!r1) {
			System.out.println("�������� �ʴ� ��ȭ�Դϴ�.");
			this.run(user);
		}

		// ��ȭ��
		theaterName = this.inputString("��ȭ���� ������. (ex.daejeon_cgv) : ");
		Theater theater = new Theater(theaterName);

		boolean r2 = dao.checkTheaterId(theater);

		if (!r2) {
			System.out.println("�������� �ʴ� ��ȭ���Դϴ�.");
			this.run(user);
		}

		// �󿵳�¥
		boolean r3 = false;
		screenDate = this.inputString("�󿵳�¥�� ���ϼ���. (ex.20181212) : ");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenDate().equals(screenDate)) {
				r3 = true;
			}
		}

		if (!r3) {
			System.out.println("�������� �ʴ� �󿵳�¥�Դϴ�.");
			this.run(user);
		}

		// �󿵽ð�
		boolean r4 = false;
		screenTime = this.inputString("�󿵽ð��� �Է��ϼ���. (ex.1315) : ");
		for (ScreeningMovie sm : screening_list) {
			if (sm.getScreenTime().equals(screenTime)) {
				r4 = true;
			}
		}

		if (!r4) {
			System.out.println("�������� �ʴ� �󿵽ð��Դϴ�.");
			this.run(user);
		}

		screenNum = this.inputString("�󿵰� ��ȣ�� �Է��ϼ���. (ex.1) : ");
		String screenId = theaterName + screenNum;
		Screen screen = new Screen(screenId);

		boolean r5 = dao.checkScreenId(screen);

		if (!r5) {
			System.out.println("�������� �ʴ� �󿵰��Դϴ�.");
			this.run(user);
		}

		ScreeningMovie result = null;
		for (ScreeningMovie sm : screening_list) {
			if (sm.getMovieName().equals(movieName) && sm.getScreenDate().equals(screenDate)
					&& sm.getScreenTime().equals(screenTime) && sm.getScreenId().equals(screenId)) {
				result = sm;
			}
		}

		// DAO ���� �¼� : ���� �¼� ����Ʈ ��������
		List<ReservedSeat> rs_list = dao.getSeatList(result.getScreenMovieId(), result.getScreenDate());

		System.out.println("<<<<<<<<<<<<<<<<<<<< ���� �¼� ��� >>>>>>>>>>>>>>>>>>>>");
		int availcount = 0;
		int count = 0;
		for (ReservedSeat rs : rs_list) {
			if (count != 0 && count % 5 == 0) {
				System.out.println();
			}
			if (rs.isReserveBool()) {
				System.out.print(String.format("%3s[%s] : X", " ", rs.getSeat()));
			} else {
				availcount++;
				System.out.print(String.format("%3s[%s] : O", " ", rs.getSeat()));
			}
			count++;
		}
		System.out.println();
		System.out.println();

		int howManyReservation = 0;
		do {
			howManyReservation = this.inputInt("�� ���� �¼��� �����ϰڽ��ϱ�? : ");

		} while (availcount < howManyReservation);

		List<Ticket> ticket_list = new ArrayList<>();
		for (int i = 0; i < howManyReservation; i++) {
			Ticket ticket = new Ticket();
			String seatNumber = this.inputString("�¼��� �Է��ϼ���. (ex.1) : ");
			for (ReservedSeat rs : rs_list) {
				if (rs.getSeat().equals(seatNumber)) {
					if (!rs.isReserveBool()) {
						ticket.setMovieName(movieName);
						ticket.setTheaterName(theaterName);
						ticket.setScreenNum(screenNum);
						ticket.setScreenDate(screenDate);
						ticket.setScreenTime(screenTime);
						ticket.setSeatNum(seatNumber);
						ticket.setUserId(user.getUserId());
						ticket.setTicketId(movieName + theaterName + screenNum + screenDate + screenTime + seatNumber);

						ticket_list.add(ticket);
					} else {
						System.out.println("�̹� ����Ǿ��ִ� �¼��Դϴ�.");
						i--;
					}
				}
			}
		}

		List<Ticket> result_ticket = null;
		result_ticket = this.finishReservationWithchoossingPay(ticket_list, user);
		boolean isFinished = true;
		for (Ticket rt : result_ticket) {
			int screenMovieNum = 0;
			
			for (ScreeningMovie sm : screening_list) {
				if (sm.getScreenDate().equals(rt.getScreenDate()) &&
						sm.getMovieName().equals(rt.getMovieName()) &&
						sm.getScreenId().equals(rt.getTheaterName() + rt.getScreenNum()) &&
						sm.getScreenTime().equals(rt.getScreenTime())) {
					screenMovieNum = sm.getScreenMovieId();
				}
			}
			
			boolean r6 = dao.insertTicket(rt, screenMovieNum); // DAO ��ȭ ���� : Ƽ�� ����

			if (!r6) {
				isFinished = false;
			}
		}
		
		if (isFinished) {
			System.out.println("���Ű� �Ϸ�Ǿ����ϴ�. ���� ��Ȳ���� Ȯ���� �� �ֽ��ϴ�.");
		} else {
			System.out.println("���Ÿ� �����Ͽ����ϴ�.");
		}
		
	}

	private List<Ticket> finishReservationWithchoossingPay(List<Ticket> list, User user) {
		List<Ticket> result = null;
		int howToPay = this.inputInt("���������� �����ϼ���. (1.���ͳ� ����  2.���� ����) : ");

		switch (howToPay) {
		case 1: // ���ͳ� ����
			result = this.internetPay(list, user);
			break;

		case 2: // ���� ����
			result = this.fieldPay(list);
			break;

		default:
			System.out.println("�ٽ� �Է��ϼ���.");
			this.finishReservationWithchoossingPay(list, user);
		}

		return result;
	}

	private List<Ticket> internetPay(List<Ticket> list, User user) {
		List<Ticket> result = new ArrayList<>();
		System.out.println();
		System.out.println("<<<<<< �� " + list.size() + "���� ǥ�� ���ؼ� ������ �����մϴ�. >>>>>>");

		for (int i = 0; i < list.size(); i++) {
			int user_point = dao.getUsedPoint(user); // DAO Ƽ�� �߱� : �������� ���� ����Ʈ ��������
			System.out.println("���� ������� ���� ����Ʈ : " + user_point + " P");

			int point_use = this.inputInt("����� ����Ʈ : ");

			if (user_point < 1000) {
				System.out.println("����Ʈ�� 1000P �̻���� ��� �����մϴ�.");
				dao.ticketing(user, false, 0); // DAO Ƽ�� �߱� : ����Ʈ ��� x, ������� ���� ����Ʈ 100�� ����, Ƽ�� ���� Ƚ�� ����
				
				System.out.println("����Ʈ�� ������� �ʾҽ��ϴ�. ����Ʈ 100P �� �����մϴ�.");
				list.get(i).setPaymentBool(true);
				list.get(i).setUsedPoint(0);
			} else {
				if (point_use == 0) {

					dao.ticketing(user, false, 0); // DAO Ƽ�� �߱� : ����Ʈ ��� x, ������� ���� ����Ʈ 100�� ����, Ƽ�� ���� Ƚ�� ����

					System.out.println("����Ʈ�� ������� �ʾҽ��ϴ�. ����Ʈ 100P �� �����մϴ�.");
				} else if (user_point < point_use) {
					System.out.println("����� �� �ִ� ����Ʈ�� �ʰ��Ͽ����ϴ�. �ٽ� ������ �����մϴ�.");
					this.internetPay(list, user);
				} else {
					dao.ticketing(user, true, point_use); // DAO Ƽ�� �߱� : ����Ʈ ���, ������� ���� ����Ʈ ����, Ƽ�� ���� Ƚ�� ����

					System.out.println("����Ʈ " + point_use + "P �� ����Ͽ����ϴ�.");
				}
				list.get(i).setPaymentBool(true);
				list.get(i).setUsedPoint(point_use);
			}
			result.add(list.get(i));
		}
		System.out.println("������ �Ϸ�Ǿ����ϴ�.");

		return result;
	}

	private List<Ticket> fieldPay(List<Ticket> list) {
		List<Ticket> result = new ArrayList<>();
		for (Ticket t : list) {
			t.setPaymentBool(false);
			t.setUsedPoint(0);
			result.add(t);
		}

		System.out.println("���忡�� ������ �Ϸ��� �ּ���.");
		return result;
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
