
import java.io.*;
import java.util.*;
public class Solution {
	static int N, M;
	static boolean[][] no;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			no = new boolean[N+1][N+1];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				no[a][b] = true;
				no[b][a] = true;
			}
			
			comb = new boolean[N+1];
			int answer = makeComb(1);
			System.out.println("#"+testcase + " " + answer);
		}
	}
	static boolean comb[];
	public static int makeComb(int idx) {
		if(idx == N+1) {
			return 1;
		}
		
		int cnt = 0;
		cnt += makeComb(idx + 1);
		
		boolean flag = false;
		for(int i = 1; i <= idx; i++) {
			if(comb[i] && no[i][idx]) {
				flag = true;
				break;
			}
		}
		
		if(!flag) {
			comb[idx] = true;
			cnt += makeComb(idx+1);
			comb[idx] = false;
		}
		
		return cnt;
	}
}