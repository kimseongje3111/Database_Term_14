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
	
	DAO dao = DAO.sharedInstance();
	Movie movie = new Movie();
	
	public void run() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.��ȭ ���  2.��ȭ ���� ����  3.��ȭ ����  9.�ٸ� ���� ���� ");
		
		switch (chooseWork) {
		case 1: // ��ȭ ���
			System.out.println("��ȭ�� ����մϴ�.");
			this.addMovie();
			System.out.println();
			this.run();
			break;
			
		case 2: // ��ȭ ���� ����
			String fixThisMovie = this.inputString("������ ��ȭ : ");
			movie.setMovieId(fixThisMovie);
			
			if(fixThisMovie.equals("�갡 �����ϸ�"))
				this.fixMovieInfo();
			else
				System.out.println("��ġ�ϴ� ��ȭ�� �����ϴ�.");
			break;
			
		case 3 : // ��ȭ ����
			// ��ȭ����Ʈ�� ������
			String deleteThisMovie = this.inputString("������ ��ȭ : ");
			this.deleteMovie();
			break;
			
		case 9:
			System.out.println("��ȭ�� ������ ��Ĩ�ϴ�.");
			break;
			
		default :
			this.run();
		}
	}

	private void addMovie() {
		movieId = this.inputString("����� ��ȭ ���̵� : ");
		movie.setMovieId(movieId);

		boolean b1 = false; // DAO ��ȭ ���̵� �ߺ� �˻�
		
		if(!b1) {
			
		} else {
			System.out.println("�̹� �����ϴ� ��ȭ�Դϴ�.");
		}
		
		movieName = this.inputString("����� ��ȭ ���� : ");
		director = this.inputString("����� ��ȭ�� ������ : ");
		cast = this.inputString("����� ��ȭ�� �⿬�� : ");
		rating = this.inputString("����� ��ȭ�� ��� : ");
		keyInfo = this.inputString("����� ��ȭ�� �ֿ� ���� : ");
		
		// ��ȭ �߰�
		System.out.println("��ȭ�� ��ϵǾ����ϴ�.");
	}
	
	private void fixMovieInfo() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.�󿵰� ��ȣ  2.�󿵰��� �ִ� �¼� ��  9.���� ���� ");
		
		switch(chooseWork) {
		case 1 : // �󿵰� ��ȣ
			String newScreenId = this.inputString("���ο� ��ȭ�� ��ȣ : ");
			// < �󿵰� ��ȣ ���� >
			this.fixMovieInfo();
			break;
		
		case 2 : // �󿵰� �ִ� �¼� ��
			int newAvailSeat = this.inputInt("���ο� �󿵰� �¼� �� : ");
			// < �¼��� ���� > - ���� ó�� : ���� �� ���� ���� �Է��� ������ Ŭ ��??
			this.fixMovieInfo();
			break;
			
		case 9: // ���� ����
			System.out.println("�󿵰� ���� ������ ��Ĩ�ϴ�.");
			break;
			
		default :
			this.fixMovieInfo();
		}
	}
	
	private void deleteMovie() {
		// ����
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