package Manager;

import java.util.Scanner;

import Database.DAO;
import Database.Movie;

public class MovieManage {

	Scanner scan = new Scanner(System.in);
	private String movieId;
	private String movieName;
	private String director;
	private String cast;
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
			fixThisMovie = this.inputString("������ ��ȭ : ");
			movie.setMovieName(fixThisMovie);

			boolean r1 = true; // DAO ��ȭ : ���� �ߺ� �˻�

			if (r1) {
				this.fixMovieInfo();
				System.out.println();
			} else {
				System.out.println("��ġ�ϴ� ��ȭ�� �����ϴ�.");
			}

			this.run();
			break;

		case 3: // ��ȭ ����
			deleteThisMovie = this.inputString("������ ��ȭ : ");
			movie.setMovieName(deleteThisMovie);

			boolean r2 = true; // DAO ��ȭ : ���� �ߺ� �˻�

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
		movieId = this.inputString("����� ��ȭ ���̵� : ");
		movie.setMovieId(movieId);

		boolean b1 = false; // DAO ��ȭ : ���̵� �ߺ� �˻�

		if (!b1) {
			movieName = this.inputString("����� ��ȭ ���� : ");
			director = this.inputString("����� ��ȭ�� ������ : ");
			cast = this.inputString("����� ��ȭ�� �⿬�� : ");
			rating = this.inputString("����� ��ȭ�� ��� : ");
			keyInfo = this.inputString("����� ��ȭ�� �ֿ� ���� : ");
			movie.setMovieName(movieName);
			movie.setDirector(director);
			movie.setCast(cast);
			movie.setRating(rating);
			movie.setKeyInfo(keyInfo);

			boolean b2 = true; // DAO ��ȭ : ����

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
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.��ȭ ����  2.����  3.�⿬��  4.���  5.�ֿ� ����  9.���� ����  ");

		switch (chooseWork) {
		case 1: // ��ȭ ����
			String newmovieName = this.inputString("���ο� ��ȭ ���� : ");
			movie.setPremovieName(fixThisMovie);
			movie.setMovieName(newmovieName);
			
			boolean b1 = false; // DAO ��ȭ : ���� �ߺ� �˻�
			
			if (!b1) {
				
				boolean b2 = true; // DAO ��ȭ : ���� ������Ʈ (������ ��ȭ ������ ����)
				
				if (b2) {
					System.out.println("��ȭ ������ ����Ǿ����ϴ�.");
				} else {
					System.out.println("��ȭ ���� ������ �����Ͽ����ϴ�.");
				}
			} else {
				System.out.println("�̹� �����ϴ� ��ȭ�Դϴ�.");
			}
			
			this.fixMovieInfo();
			break;

		case 2: // ����
			String newdirector = this.inputString("���ο� ��ȭ ���� : ");
			movie.setDirector(newdirector);
			
			boolean b3 = true; // DAO ��ȭ : ���� ������Ʈ
			
			if (b3) {
				System.out.println("��ȭ ������ ����Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ ���� ������ �����Ͽ����ϴ�.");
			}
			
			this.fixMovieInfo();
			break;

		case 3: // �⿬��
			String newcast = this.inputString("���ο� ��ȭ �⿬�� : ");
			movie.setCast(newcast);
			
			boolean b4 = true; // DAO ��ȭ : ���� ������Ʈ
			
			if (b4) {
				System.out.println("��ȭ �⿬���� ����Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ �⿬�� ������ �����Ͽ����ϴ�.");
			}
			
			this.fixMovieInfo();
			break;
			
		case 4: // ���
			String newrating = this.inputString("���ο� ��ȭ ��� : ");
			movie.setRating(newrating);
			
			boolean b5 = true; // DAO ��ȭ : ���� ������Ʈ

			if (b5) {
				System.out.println("��ȭ ����� ����Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ ��� ������ �����Ͽ����ϴ�.");
			}
			
			this.fixMovieInfo();
			break;
			
		case 5: // �ֿ� ����
			String newkeyInfo = this.inputString("���ο� ��ȭ �ֿ� ���� : ");
			
			boolean b6 = true; // DAO ��ȭ : ���� ������Ʈ
			
			if (b6) {
				System.out.println("��ȭ �ֿ� ������ ����Ǿ����ϴ�.");
			} else {
				System.out.println("��ȭ �ֿ� ���� ������ �����Ͽ����ϴ�.");
			}
			
			this.fixMovieInfo();
			break;
			
		case 9: // ���� ����
			System.out.println("�󿵰� ���� ������ ��Ĩ�ϴ�.");
			break;

		default:
			this.fixMovieInfo();
		}
	}

	private void deleteMovie() {
		if (this.inputString("�����Ͻðڽ��ϱ�? (Y/N) ").equals("Y")) {

			boolean b = true; // DAO ��ȭ : ����

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
