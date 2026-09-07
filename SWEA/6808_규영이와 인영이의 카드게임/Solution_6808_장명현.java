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
			int[] fix = new int[9];
			int[] change = new int[9];
			int[] num = new int[19];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<9; i++) {
				fix[i] = Integer.parseInt(st.nextToken());
				num[fix[i]] = 1;
			}
			
			for (int n=1, i=0; n<=18 && i<9; n++) {
				if (num[n] == 1) continue;
				change[i++] = n;
			}
			
			int win = 0;
			int lose = 0;
			
			// next_permutation (왜 이런걸 미리 딸깍 구현을 안해놨을까요)
			// 즉 a < b인 최초의 a, b를 우측에서부터 찾음
			// a 뒤에서부터 a보다 큰 최초의 수 c를 우측에서부터 찾음
			// a, c 스왑
			// b 부터 맨 뒤까지 오름차순 정렬
			while (true) {
				int win_cnt = 0;
				int lose_cnt = 0;
				
				for (int i=0; i<9; i++) {
					if (change[i] > fix[i]) win_cnt += change[i] + fix[i];
					else lose_cnt += change[i] + fix[i];
				}
				
				if (win_cnt > lose_cnt) win++;
				else if (win_cnt < lose_cnt) lose++;
				
				
				
				int d = -1;
				
				for (int i=7; i>=0; i--) {
					if (change[i] < change[i+1]) {
						d = i;
						break;
					}
				}
				
				if (d == -1) break;
				
				for (int i=8; i>d; i--) {
					if (change[d] < change[i]) {
						int temp = change[d];
						change[d] = change[i];
						change[i] = temp;
						break;
					}
				}
				
				Arrays.sort(change, d+1, 9);
			}
			
			
			
			sb.append('#').append(test_case).append(' ');
			sb.append(lose).append(' ').append(win).append('\n');
		}
		
		System.out.println(sb);
	}
}

