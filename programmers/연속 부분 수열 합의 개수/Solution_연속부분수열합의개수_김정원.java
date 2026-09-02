package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_연속부분수열합의개수_김정원 {
	public static void main(String[] args) {
		solution(new int[] {7,9,1,1,4});
	}
	
    public static int solution(int[] elements) {
        int answer = 0;
        // 오늘 배운 투 포인터 쓰기!
        int start = 0;
        // 중복 체크를 배열로 할깡?
        int[] visited = new int[1000*1000];
        // 투 포인터로 이동하면서 중복된것인지 확인하고
        int sum = 0;
        int maxSize = elements.length;
        List<Integer> numbers = new ArrayList<>();
        int windowSize = 1;
        while (windowSize <= maxSize) {
        	// 현재 구간의 합을 구한다
        	for (int k = 0; k < maxSize; k++) {
        		sum = 0;
        		//System.out.println(String.format("window: %d, k:%d", windowSize, k));
        		for (int i = k; i < k + windowSize; i++) {
        			sum += elements[i % maxSize];
        		}
        		// 구간의 합이 중복이라면
            	if (visited[sum] == 1) continue;
        		// 중복 처리
        		visited[sum] = 1;
                // 카운트를 올린다 
        		answer++;
        		// 
        		numbers.add(sum);
        	}
        	windowSize++;
//        	System.out.print(String.format("window: %d ", windowSize));
//        	System.out.println(numbers);
        }

        return answer;
    }
}
