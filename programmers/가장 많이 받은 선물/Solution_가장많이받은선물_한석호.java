package practice;

import java.util.*;

public class Solution_가장많이받은선물_한석호 {
	public int solution(String[] friends, String[] gifts) {

        Map<String, Integer> a = new HashMap<>();

		int N = friends.length;

		for (int i = 0; i < N; i++) {
			a.put(friends[i], i);
		}

		int[][] give_take = new int[N][N];
		int[] present_jisu = new int[N];
		int[] results = new int[N];

		for (int i = 0; i < gifts.length; i++) {

			// muzi frodo, muzi, frodo 형식으로 나옴
			String[] str = gifts[i].split(" ");

			int give = a.get(str[0]);
			int take = a.get(str[1]);

			give_take[give][take] += 1;

			present_jisu[give] += 1;
			present_jisu[take] -= 1;

		}

		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {

				if (i == j) {
					continue;
				}
				
//				System.out.printf("반복 확인 %d, %d", i, j);
//				System.out.println();

				if (give_take[i][j] > give_take[j][i]) {

					results[i] += 1;

				}

				else if (give_take[i][j] < give_take[j][i]) {
					results[j] += 1;
				}

				else {
					if (present_jisu[i] > present_jisu[j]) {
						results[i] += 1;
					}

					else if (present_jisu[i] < present_jisu[j]) {
						results[j] += 1;
					}

				}
			}
		}

//		for (int i=0; i<N; i++)
//		{
//			for(int j=0; j<N; j++)
//			{
//				System.out.print(give_take[i][j] + " ");
//			}
//			
//			System.out.println();
//		}
//		
		int answer = 0;
				
		for(int i=0; i<N; i++)
		{
			answer = Math.max(answer, results[i]);
		}
		
        return answer;
		
	}

}
