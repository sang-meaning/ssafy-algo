import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int day, month, third, year, answer;
	public static int[] plan;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			third = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
		
			plan = new int[12];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = year;
			perm(0, 0);
			
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void perm(int now, int fee) {
		if (now >= 12) {
			answer = Math.min(answer, fee);
			return;
		}
		
		if (plan[now] == 0) {
			perm(now+1, fee);
			return;
		}
		
		perm(now+1, fee + day * plan[now]);
		perm(now+1, fee + month);
		perm(now+3, fee + third);
	}
}