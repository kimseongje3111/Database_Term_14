package main;

import java.util.Scanner;

public class LogInSignIn {
	private Scanner scan = new Scanner(System.in);
	private boolean first = false;
	
	public void run() {
		if(first) {
			System.out.println();System.out.println();
		} else
			first = true;
				
		int loginORsignin = inputInt("DB TERM PROJECT�� ���Ű� ȯ���մϴ�. (1.�α���  2.ȸ������  9.����) ");
		if(loginORsignin==1) {
			System.out.println("�α����� ���ּ���.");
			new LogIn().run();
		} else if(loginORsignin==2) {
			System.out.println("ȸ�������� ���ּ���.");
			new SignIn().run();
			this.run();
		} else if(loginORsignin==9)
			System.out.println("�����մϴ�.");
		else
			this.run();
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
