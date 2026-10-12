import java.io.*;
import java.util.*;

public class Solution_1247_김민우 {
	static int T, N;
	static int answer;
	
	static int[] home;
	static int[] company;
	static int[][] customer;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			company = new int[2];
			home = new int[2];
			customer = new int[N][2];
			visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < 2; i++) {
				company[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0; i < 2; i++) {
				home[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				int first = Math.abs(company[0] - customer[i][0]) + Math.abs(company[1] - customer[i][1]);
				visited[i] = true;
				delivery(i, 1, first);
				visited[i] = false;
			}
			
			sb.append("#" + test_case + " " + answer + "\n");
		}//test_case 끝
		System.out.println(sb);
	}//main 끝

	public static void delivery(int idx, int cnt, int sum) {
		//가지치기 => 더 돌아봤자 경로가 짧아질 수 없을 때
		if(sum > answer)
			return;
		
		if(cnt ==  N) {
			int toHome = Math.abs(home[0] - customer[idx][0]) + Math.abs(home[1] - customer[idx][1]);
			sum += toHome;
			answer = (sum < answer)?sum:answer;
			return;
		}
		//순열 만들기
		for(int i = 0; i < N; i++) {
			if(visited[i])
				continue;
			int dist = Math.abs(customer[idx][0] - customer[i][0]) + Math.abs(customer[idx][1] - customer[i][1]);
			visited[i] = true;
			delivery(i, cnt+1, sum + dist);
			visited[i] = false;
		}
	}
	
}//solution 끝
