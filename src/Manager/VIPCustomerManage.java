package Manager;

import java.util.Scanner;

import Database.DAO;

public class VIPCustomerManage {
	
	Scanner scan = new Scanner(System.in);
	
	DAO dao = DAO.sharedInstance();
	
	public void run() {
		// ���̾��Ǵ� �Ⱓ �߿� ������ ���� ���� ���� 10���� ���
	}
	
	
	private String inputString(String string) {
		System.out.print(string);
		return scan.nextLine();
	}
	
}
