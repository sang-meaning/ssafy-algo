import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] friends, String[] gifts) {

		// 선물을 주고받은 기록이 있다 = 선물목록에 "A B" 또는 "B A"가 있다 -> "A B" "B A" 의 개수를 비교해서, 더 많은
		// 쪽에게 선물++

		// 선물을 주고받은 기록이 없다
		// A B 각각 선물 지수를 계산한다. 선물 지수 = "A ?"의 합 - "? A"의 합. -> 계산 해두기?
		// - "? A"의 수. 선물지수가 더 큰 쪽에게 선물++

		// "A ?"의 수의 덧셈의 의미: A B가 있다면 -> A B 쓰기, A B 가 없다면 "A ?"의 수의 덧셈

		Map<String, Integer> memo = new HashMap<>();
		// Integer 라서.......

		for (int i = 0; i < gifts.length; i++) {

			if (memo.get(gifts[i]) == null)
				memo.put(gifts[i], 0);
			memo.put(gifts[i], memo.get(gifts[i]) + 1);

			String[] pairNm = gifts[i].split(" ");
			if (memo.get(pairNm[0]) == null)
				memo.put(pairNm[0], 0);
			memo.put(pairNm[0], memo.get(pairNm[0]) + 1);
			if (memo.get(pairNm[1]) == null)
				memo.put(pairNm[1], 0);
			memo.put(pairNm[1], memo.get(pairNm[1]) - 1);
		}
		// 기록
		//

		int[] nextMonthGift = new int[friends.length];

		for (int a = 0; a < friends.length - 1; a++) {
			for (int b = a + 1; b < friends.length; b++) { // 이건 nC2네

				int AB = memo.get(friends[a] + " " + friends[b]) == null ? 0 : memo.get(friends[a] + " " + friends[b]);
				int BA = memo.get(friends[b] + " " + friends[a]) == null ? 0 : memo.get(friends[b] + " " + friends[a]);
				;
				// ???? 뭐하는 짓이지
				if (AB > BA) {
					nextMonthGift[a] += 1;
				} else if (BA > AB) {
					nextMonthGift[b] += 1;
				} else {
					int scoreA = memo.get(friends[a]) == null ? 0 : memo.get(friends[a]);
					int scoreB = memo.get(friends[b]) == null ? 0 : memo.get(friends[b]);
					if (scoreA > scoreB) {
						nextMonthGift[a] += 1;
					} else if (scoreB > scoreA) {
						nextMonthGift[b] += 1;
					} else {
						continue;
					}
				}
			}
		}
		
		// 이게 뭐하는 짓이지
		
		int answer = 0;

		for (int i = 0; i < friends.length; i++) {
			answer = Math.max(answer, nextMonthGift[i]);
		}
		return answer;
	}
}

// 속도 차이가 많이 남.
// 
