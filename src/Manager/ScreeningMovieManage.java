package Manager;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.ScreeningMovie;

public class ScreeningMovieManage {

	Scanner scan = new Scanner(System.in);
	private int screenMovieId;
	private String screenDate;
	private String movieName;
	private String screenId;
	private String screenTime;
	private int deleteThisMovie;

	DAO dao = DAO.sharedInstance();
	ScreeningMovie screeningmovie = new ScreeningMovie();

	public void run() {
		System.out.println("----------------------------- �� ��ȭ ��� -----------------------------");
		List<ScreeningMovie> screening_list = dao.getScreeningMovieList(); // DAO �� ��ȭ : �� ��ȭ ��� ��������
		for (ScreeningMovie sm : screening_list) {
			System.out.println(sm);
		}
		System.out.println();

		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.�� ��ȭ ���  2.�� ��ȭ ����  9.�ٸ� ���� ����  ");

		switch (chooseWork) {
		case 1: // ��ȭ ���
			System.out.println("> �� ��ȭ�� ����մϴ�.");
			this.addScreeningMovie();
			System.out.println();
			this.run();
			break;

		case 2: // �� ��ȭ ����
			deleteThisMovie = this.inputInt("> ������ �� ��ȭ ��ȣ (ex.1) : ");
			screeningmovie.setScreenMovieId(deleteThisMovie);

			boolean r = dao.checkScreeningMovieId(screeningmovie); // DAO �� ��ȭ : ��ȣ �ߺ� �˻�

			if (r) {
				this.deleteScreeningMovie();
				System.out.println();
			} else {
				System.out.println("> ��ġ�ϴ� �� ��ȭ�� �����ϴ�.");
			}

			this.run();
			break;

		case 9:
			System.out.println("> �� ��ȭ ������ ��Ĩ�ϴ�.");
			break;

		default:
			this.run();
		}
	}

	private void addScreeningMovie() {
		screenMovieId = this.inputInt("> ����� �� ��ȭ ��ȣ (ex.1) : ");
		screeningmovie.setScreenMovieId(screenMovieId);

		boolean b1 = dao.checkScreeningMovieId(screeningmovie); // DAO �� ��ȭ : ��ȣ �ߺ� �˻�

		if (!b1) {
			screenDate = this.inputString("> �� ��¥ (ex.20181212) : ");
			movieName = this.inputString("> ���� ��ȭ ���� (ex.ironman) : ");
			screenId = this.inputString("> ���� �󿵰� ���̵� (ex.daejoen_cgv1) : ");
			screenTime = this.inputString("> �� �ð� (ex.1315) : ");
			screeningmovie.setScreenDate(screenDate);
			screeningmovie.setMovieName(movieName);
			screeningmovie.setScreenId(screenId);
			screeningmovie.setScreenTime(screenTime);

			boolean b2 = dao.insertScreeningMovie(screeningmovie); // DAO �� ��ȭ : ����

			if (b2) {
				System.out.println("> �� ��ȭ�� ��ϵǾ����ϴ�.");
			} else {
				System.out.println("> �� ��ȭ ����� �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("> �̹� �����ϴ� �� ��ȭ ��ȣ�Դϴ�.");
		}

	}

	private void deleteScreeningMovie() {
		if (this.inputString("> �����Ͻðڽ��ϱ�? (Y/N) ").equals("Y")) {

			boolean b = dao.deleteScreeningMovie(screeningmovie); // DAO �� ��ȭ : ����

			if (b) {
				System.out.println("> �� ��ȭ�� �����Ǿ����ϴ�.");
			} else {
				System.out.println("> �� ��ȭ ������ �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("> �� ��ȭ ���� ������ ����մϴ�.");
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
