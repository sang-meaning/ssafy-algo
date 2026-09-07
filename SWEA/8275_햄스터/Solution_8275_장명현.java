import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static boolean rt;
	public static int N, X, M, maxi;
	public static int[] cage, permutation;
	public static int[][] query;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			query = new int[M][3];
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				query[i][0] = Integer.parseInt(st.nextToken());
				query[i][1] = Integer.parseInt(st.nextToken());
				query[i][2] = Integer.parseInt(st.nextToken());
			}
			
			rt = false;
			maxi = -1;
			cage = new int[N];
			permutation = new int[N];
			
			perm(0);
			
			sb.append('#').append(test_case).append(' ');
			
			if (maxi == -1) sb.append(maxi).append(' ');
			else for (int x : cage) sb.append(x).append(' ');
			
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void perm(int idx) {
		if (idx == N) {
			int hamsters = 0;
			for (int i=0; i<N; i++) hamsters += permutation[i];
			
			for (int i=0; i<M; i++) {
				int cnt = 0;
				for (int j=query[i][0]-1; j<query[i][1]; j++) {
					cnt += permutation[j];
				}
				if (cnt != query[i][2]) break;
				if (i == M-1 && maxi < hamsters) {
					for (int j=0; j<N; j++) cage[j] = permutation[j];
					maxi = hamsters;
				}
			}
			return;
		}
		
		for (int i=0; i<=X; i++) {
			permutation[idx] = i;
			perm(idx+1);
		}
	}
}