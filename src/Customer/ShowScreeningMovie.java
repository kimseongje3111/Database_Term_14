package Customer;

import java.util.List;

import Database.DAO;

public class ShowScreeningMovie {

	DAO dao = DAO.sharedInstance();
	
	public void run() {
		
		List<String> sc_list = dao.printMovieRating(); // DAO �� ��ȭ ��Ʈ : ������ ���� ������ ��Ʈ

		System.out.println("------------------------ �ǽð� ��ȭ ��Ʈ ------------------------");
		int count = 1;
		for (String s : sc_list) {
			String[] temp = s.split(",");
			float reserver_rate = (float) (Float.parseFloat(temp[3]) * 100.0);
			System.out.println(String.format("[%d] %s / ������ : %.2f", count, temp[0], reserver_rate) + "%");
			count++;
		}
		System.out.println();
		
	}

}
