import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Solution {
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력받기
			st = new StringTokenizer(br.readLine());
			int N 		= Integer.parseInt(st.nextToken());
			int low 	= Integer.parseInt(st.nextToken());
			int high 	= Integer.parseInt(st.nextToken());
			
			int[] fruit = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				fruit[i] = Integer.parseInt(st.nextToken());
			}
			
			// 정렬하기
			SortedMap<Integer, Integer> map = new TreeMap<>();
			for (int i=0; i<N; i++) {
				if (map.get(fruit[i]) == null) map.put(fruit[i], 1);
				else {
					int m = map.get(fruit[i]);
					map.put(fruit[i], m+1);
				}
			}
	
			// 누적합 배열 생성
			int msize = map.size();
			int[] sorted = new int[msize+1];
			
			int t=1;
			for (Integer value : map.values()) {
				sorted[t] = sorted[t-1] + value;
				t++;
			}

			// 가능한 경우 전부 검사
			int answer = 10000;
			for (int i=1; i<=msize; i++) {
				for (int j=1; j<=msize; j++) {
					int l = sorted[i] - sorted[0];
					int m = sorted[j] - sorted[i];
					int h = sorted[msize] - sorted[j];
					
					if (l < low || high < l) continue;
					if (m < low || high < m) continue;
					if (h < low || high < h) continue;
					
					int mini = Math.min(Math.min(l, m), h);
					int maxi = Math.max(Math.max(l, m), h);
					
					answer = Math.min(answer, maxi-mini);
				}
			}

			if (answer == 10000) answer = -1;
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		System.out.println(sb);
	}
}