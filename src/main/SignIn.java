package main;

import java.util.List;
import java.util.Scanner;

import Database.DAO;
import Database.User;

public class SignIn {
	Scanner scan = new Scanner(System.in);
	public void run() {
		String userId = this.inputString("���̵� : ");
		String pwd = this.inputString("��й�ȣ : ");
		String name = this.inputString("���� (ex. �̼���) : ");
		String birth = this.inputString("������� (ex. 19940216) : ");
		String addr = this.inputString("�ּ� : ");
		String phoneNum = this.inputString("��ȭ��ȣ : ");
		
		// ����� ���̺� ȸ������ ������ ����ȴ� // ȸ�����Ե� ������ �ѹ� ������?
		
		
		DAO dao = DAO.sharedInstance();
		User user = new User();
		user.setUserId(userId);
		user.setPwd(pwd);
		user.setName(name);
		user.setBirth(birth);
		user.setAddr(addr);
		user.setPhoneNum(phoneNum);
		
//		boolean r = dao.InsertUser(user);
//		
//		if(r)
//			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
//		else
//			System.out.println("ȸ�������� �����Ͽ����ϴ�.");

		// ����� �� Ȯ�� �� select �غ�
		List<User> list = dao.getUserList();
		
		for(User u : list) {
			System.out.println(u);
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
