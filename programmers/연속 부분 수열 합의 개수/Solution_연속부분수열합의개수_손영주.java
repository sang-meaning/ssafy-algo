package submission;

public class Solution {

	static public int solution(int[] elements) {
		int s = 0; // 시작위치의 인덱스.
		
		int totalsum = 0; // 총합.
		for (int num:elements) {
			totalsum +=num;
		}
		
		boolean[] flag = new boolean[totalsum]; // 중복 확인용
		
		int count = 1; // 총합 1개.

		while (s < elements.length) {
			int sum = 0;
			int idx = s;
			for (int i = 0; i < elements.length - 1; i++) { // n-1번 반복.
				idx = ++idx % elements.length;
				sum += elements[idx];
				if(flag[sum] !=true) {
					flag[sum] = true;
					count++;
				}
			}
			s++;
		}
		return count;
	}
}
