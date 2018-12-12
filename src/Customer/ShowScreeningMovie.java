package Customer;

import java.util.List;

import Database.DAO;

public class ShowScreeningMovie {

	DAO dao = DAO.sharedInstance();
	
	public void run() {
		// 현재 상영중인 영화를 예매율순으로 출력
		
		List<String> sc_list = dao.printMovieRating(); // DAO 상영 영화 차트 : 예매율 높은 순으로 차트

		System.out.println("------------------------ 실시간 영화 차트 ------------------------");
		int count = 1;
		for (String s : sc_list) {
			String[] temp = s.split(",");
			float reserve_rate = Float.parseFloat(temp[2]) / Float.parseFloat(temp[1]);
			System.out.println(String.format("[%d] %s / 예매율 : %.2f", count, temp[0], reserve_rate));
			count++;
		}
		System.out.println();
		
	}

}
