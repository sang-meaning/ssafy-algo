import java.util.HashMap;
import java.util.Map;

class Solution {
	public int solution(String[] friends, String[] gifts) {
		int answer = 0;

		// 먼저 Map에 친구들 이름 넣어불고
		
		Map<String, Integer> name = new HashMap<>();

		for (int i = 0; i < friends.length; i++) {
			name.put(friends[i], i);
		}

		int[][] matrix = new int[friends.length][friends.length];

		// 누가누가 썸타나~~ (선물 교환 cnt)
		for (String g : gifts) {
			matrix[name.get(g.split(" ")[0])][name.get(g.split(" ")[1])]++;
		}

		// 선물 지수 합산합시다.
		int[] futures = new int[friends.length];

		for (int i = 0; i < futures.length; i++) {
			for (int j = 0; j < futures.length; j++) {
				futures[i] += matrix[i][j];
				futures[i] -= matrix[j][i];
			}
		}

		// 1. 양심적으로 둘 중에 더 적게 준 사람이 줍시다.
		// 2. 둘이 선물 교환 X or 맞교환 => 선물 지수(이번달 준 선물 - 받은 선물) 낮은 사람이 큰 사람에게 선물 줍시다.
		//   2.1. 선물 지수도 똑같다? 그럼 다음 달은 손절 ㄱ...
		
		int nxtMnthGft = 0;
		
		for (int i = 0; i < friends.length; i++) {
			for (int j = 0; j < friends.length; j++) {
				if(matrix[i][j] > matrix[j][i]) {
					nxtMnthGft++;
				} else if(matrix[i][j] == matrix[j][i] && i != j) {
					if(futures[i] > futures[j]) {
						nxtMnthGft++;
					}
				}
			}
			if(nxtMnthGft > answer) {
				answer = nxtMnthGft;
			}
			nxtMnthGft = 0;
		}
		
		return answer;
	}
}