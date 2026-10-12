package submission;

import java.util.Arrays;

public class Solution{

	static public int[] solution(int[] progresses, int[] speeds) {

		int N = progresses.length;

		int[] answer = new int[N];
		int index = 0;

		int s = 0;
		// 다음 배포할 프로젝트 위치
		while (s < N) {

			int count = 0; // 오늘 배포한 수
			for (int i = s; i < N; i++) { // 일하기.
				progresses[i] += speeds[i];
				
				// 배포하기로 한거 배포하면, 다음 배포할 프로젝트는 다음거.
				if (progresses[s] >= 100) {
					s++; count++;
				}
			}
			
			if (count > 0) {
				answer[index] = count;
				index++;
			}
		}

		return Arrays.copyOf(answer, index);
	}

}
