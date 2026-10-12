import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N, M, answer;
	public static int[] selected;
	public static boolean[][] arr;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new boolean[N][N];
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				arr[x][y] = true;
				arr[y][x] = true;
			}
			
			answer = 0;
			selected = new int[N];
			comb(-1, 0);
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void comb(int last, int size) {
		if (last+1 == N) {
			answer++;
			return;
		}
		
		boolean add = true;
		for (int j=0; j<size; j++) {
			int s = selected[j];
			if (arr[s][last+1]) {
				add = false;
				break;
			}
		}
		
		if (add) {
			selected[size] = last+1;
			comb(last+1, size+1);
		}
		
		comb(last+1, size);
	}
}