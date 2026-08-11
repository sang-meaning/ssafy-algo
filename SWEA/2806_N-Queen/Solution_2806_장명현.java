import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	
	public static int cnt = 0, N;
	public static int[] queen;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			cnt = 0;
			queen = new int[N];
			
			search(0);
			
			sb.append('#').append(test_case).append(' ').append(cnt).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void search(int size) {
		if (size == N) {
			cnt++;
			return;
		}
		
		int ni = size;
		for (int nj=0; nj<N; nj++) {
			boolean cont = false;
			for (int qi=0; qi<size; qi++) {
				int qj = queen[qi];
				
				if (nj == qj || Math.abs(ni-qi) == Math.abs(nj-qj)) {
					cont = true;
					break;
				}
			}
			if (cont) continue;
			
			queen[ni] = nj;
			search(size+1);
		}
	}
}