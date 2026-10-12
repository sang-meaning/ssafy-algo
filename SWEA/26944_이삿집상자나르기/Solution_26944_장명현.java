import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;		
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] box = new int[N];
			st = new StringTokenizer(br.readLine()); 
			for (int i=0; i<N; i++) box[i] = Integer.parseInt(st.nextToken());

			int[] person = new int[M];
			st = new StringTokenizer(br.readLine()); 
			for (int i=0; i<M; i++) person[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(box);
			Arrays.sort(person);
			
			int answer = 0;
			for (int i=M-1, j=N-1; i>=0 && j>=0;) {
				if (box[j] <= person[i]) {
					answer += box[j];
					i--; j--;
				} else j--;
			}
			
			sb.append('#').append(test_case).append(' ');
			sb.append(answer).append('\n');
		}
		
		System.out.println(sb);
	}
}