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
		String id = this.inputString("���̵� : ");
		String pw = this.inputString("��й�ȣ : ");

		if (id.equals("root") && pw.equals("admin")) {
			System.out.println("�����ڷ� �α��� �մϴ�.");
			new Manager().run();
		} else {
			
			User user = new User();
			user.setUserId(id);
			user.setPwd(pw);
			
			boolean r = true; // DAO �α��� : ���̵�, ��й�ȣ Ȯ��
			
			if (r) {
				System.out.println("ȯ���մϴ� ����.");
				new Customer().run(user);
			} else {
				System.out.println("�α��� ����. ���̵� �Ǵ� ��й�ȣ�� �ٽ� Ȯ���ϼ���.");
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
