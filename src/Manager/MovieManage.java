package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Movie;

public class MovieManage {

	Scanner scan = new Scanner(System.in);
	private String movieId;
	private String movieName;
	private String director;
	private String casting;
	private String rating;
	private String keyInfo;
	private String fixThisMovie;
	private String deleteThisMovie;

	DAO dao = DAO.sharedInstance();
	Movie movie = new Movie();

	public void run() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.��ȭ ���  2.��ȭ ���� ����  3.��ȭ ����  9.�ٸ� ���� ����  ");

		switch (chooseWork) {
		case 1: // ��ȭ ���
			System.out.println("��ȭ�� ����մϴ�.");
			this.addMovie();
			System.out.println();
			this.run();
			break;

		case 2: // ��ȭ ���� ����
			fixThisMovie = this.inputString("������ ��ȭ ���� : ");
			movie.setMovieName(fixThisMovie);

			boolean r1 = dao.checkMovieName(movie); // DAO ��ȭ : ���� �ߺ� �˻�

			if (r1) {
				this.fixMovieInfo();
				System.out.println();
			} else {
				System.out.println("��ġ�ϴ� ��ȭ�� �����ϴ�.");
			}

			this.run();
			break;

		case 3: // ��ȭ ����
			deleteThisMovie = this.inputString("������ ��ȭ ���� : ");
			movie.setMovieName(deleteThisMovie);

			boolean r2 = dao.checkMovieName(movie); // DAO ��ȭ : ���� �ߺ� �˻�

			if (r2) {
				this.deleteMovie();
				System.out.println();
			} else {
				System.out.println("��ġ�ϴ� ��ȭ�� �����ϴ�.");
			}

			this.run();
			break;

		case 9:
			System.out.println("��ȭ ������ ��Ĩ�ϴ�.");
			break;

		default:
			this.run();
		}
	}

	private void addMovie() {
		movieId = this.inputString("����� ��ȭ ���̵� (ex.Movie1) : ");
		movie.setMovieId(movieId);

		boolean b1 = dao.checkMovieId(movie); // DAO ��ȭ : ���̵� �ߺ� �˻�

		if (!b1) {
			movieName = this.inputString("����� ��ȭ ���� (ex.ironman) : ");
			director = this.inputString("����� ��ȭ�� ������ : ");
			casting = this.inputString("����� ��ȭ�� �⿬�� : ");
			rating = this.inputString("����� ��ȭ�� ��� : ");
			keyInfo = this.inputString("����� ��ȭ�� �ֿ� ���� : ");
			movie.setMovieName(movieName);
			movie.setDirector(director);
			movie.setCast(casting);
			movie.setRating(rating);
			movie.setKeyInfo(keyInfo);

			boolean b2 = dao.insertMovie(movie); // DAO ��ȭ : ����

			if (b2) {
				System.out.println("��ȭ�� ��ϵǾ����ϴ�.");
			} else {
				System.out.println("��ȭ ����� �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("�̹� �����ϴ� ��ȭ�Դϴ�.");
		}

	}

	private void fixMovieInfo() {
		System.out.println("������ ������ �Է��ϼ���.");
		String newdirector = this.inputString("���ο� ��ȭ ���� : ");
		String newcast = this.inputString("���ο� ��ȭ �⿬�� : ");
		String newrating = this.inputString("���ο� ��ȭ ��� : ");
		String newkeyInfo = this.inputString("���ο� ��ȭ �ֿ� ���� : ");

		movie.setDirector(newdirector);
		movie.setCast(newcast);
		movie.setRating(newrating);
		movie.setKeyInfo(newkeyInfo);

		boolean b = dao.updateMovieInfo(movie);

		if (b) {
			System.out.println("��ȭ ������ ����Ǿ����ϴ�.");
		} else {
			System.out.println("��ȭ ���� ������ �����Ͽ����ϴ�.");
		}

	}

	private void deleteMovie() {
		if (this.inputString("�����Ͻðڽ��ϱ�? (Y/N) ").equals("Y")) {

			boolean b = dao.deleteMovie(movie); // DAO ��ȭ : ����

			if (b) {
				System.out.println("��ȭ�� �����Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ ������ �����Ͽ����ϴ�.");
			}
		} else {
			System.out.println("��ȭ ���� ������ ����մϴ�.");
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
