package main;

import java.util.Scanner;

import Customer.Customer;
import Database.DAO;
import Database.User;
import Manager.Manager;

public class LogIn {
	
	Scanner scan = new Scanner(System.in);
	DAO dao = DAO.sharedInstance();

	public void run() {
		String id = this.inputString("아이디 : ");
		String pw = this.inputString("비밀번호 : ");

		if (id.equals("root") && pw.equals("admin")) {
			System.out.println("관리자로 로그인 합니다.");
			new Manager().run();
		} else {
			
			User user = new User();
			user.setUserId(id);
			user.setPwd(pw);
			
			boolean r = true; // DAO 로그인 : 아이디, 비밀번호 확인
			
			if (r) {
				System.out.println("환영합니다 고객님.");
				new Customer().run(user);
			} else {
				System.out.println("로그인 실패. 아이디 또는 비밀번호를 다시 확인하세요.");
				this.run();
			}
			
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
