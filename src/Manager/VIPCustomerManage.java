package Manager;

import java.util.Scanner;

import Database.DAO;

public class VIPCustomerManage {
	
	Scanner scan = new Scanner(System.in);
	
	DAO dao = DAO.sharedInstance();
	
	public void run() {
		// 브이아피는 기간 중에 예매한 수가 많은 상위 10명을 출력
	}
	
	
	private String inputString(String string) {
		System.out.print(string);
		return scan.nextLine();
	}
	
}
