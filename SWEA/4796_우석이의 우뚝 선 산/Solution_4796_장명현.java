import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
//		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
//			st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
			int N = sc.nextInt();

			int[] arr = new int[N];
//			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
//				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// local minimum 찾기
			ArrayList<Integer> s = new ArrayList<>();
			if (arr[0] < arr[1]) s.add(0);
			for (int i=1; i<N-1; i++) {
				if (arr[i-1] > arr[i] && arr[i] < arr[i+1]) s.add(i);
			}
			if (arr[N-2] > arr[N-1]) s.add(N-1);
			
			// local minimum 사이의 구간에서 peak 찾기
			long answer = 0;
			if (s.size() >= 2) {
				for (int i=0; i<s.size()-1; i++) {
					int l = s.get(i);
					int m = 0;
					int r = s.get(i+1);
					
					for (int j=l+1; j<r; j++) {
						if (arr[j-1] < arr[j] && arr[j] > arr[j+1]) {
							m = j;
							break;
						}
					}
					
					answer += (long)(m-l) * (long)(r-m);
				}
			}
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}