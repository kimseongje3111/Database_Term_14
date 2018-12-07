package Manager;

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
	
	DAO dao = DAO.sharedInstance();
	ScreeningMovie screeningmovie = new ScreeningMovie();
	
	public void run() {
		System.out.println("������ ������ �����ϼ���.");
		int chooseWork = this.inputInt("1.�� ��ȭ ���  2.�� ��ȭ ����  9.�ٸ� ���� ����");
		
		switch (chooseWork) {
		case 1: // ��ȭ ���
			System.out.println("�� ��ȭ�� ����մϴ�.");
			this.addScreeningMovie();
			System.out.println();
			this.run();
			break;
			
		case 2: // �� ��ȭ ����
			String fixThisMovie = this.inputString("������ �� ��ȭ : ");
			screeningmovie.setMovieName(fixThisMovie);
			
			if(fixThisMovie.equals("�갡 �����ϸ�"))
				this.deleteScreeningMovie();
			else
				System.out.println("��ġ�ϴ� �� ��ȭ�� �����ϴ�.");
			break;

		case 9:
			System.out.println("�� ��ȭ ������ ��Ĩ�ϴ�.");
			break;
			default :
				this.run();
		}
	}

	private void addScreeningMovie() {
		System.out.println("�� ��ȭ�� ��ϵǾ����ϴ�.");
	}

	private void deleteScreeningMovie() {
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
