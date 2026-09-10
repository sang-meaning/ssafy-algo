import java.io.*;
import java.util.*;

public class Solution {
	static int[][] cards;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			cards = new int[9][9]; 
			int[] used = new int[19];
			for(int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				cards[1][i] = num;
				used[num]++;
			}
			
			for(int i = 1, j = 0; i<=18; i++) {
				if(used[i] != 0) continue;
				cards[0][j++] = i;
			}
			
			// 순열 만들기
			int answer = perm(0, 0, 0);
			int total = 1;
			for(int i = 1; i<=9; i++) {
				total*=i;
			}
			System.out.println("#" + testcase + " " + answer+ " " + (total - answer));
		}
	}

	static int[] visited = new int[9];
	public static int perm(int dept, int i_sum, int k_sum) {
		if(dept == 9) {
			if(i_sum < k_sum) return 1;
			return 0;
		}
		int cnt = 0;
		for(int i = 0; i<9; i++) {
			if(visited[i] == 1) continue;
			
			visited[i] = 1;
			if(cards[0][i] < cards[1][dept]) {
				cnt += perm(dept+1, i_sum, k_sum + cards[1][dept] + cards[0][i]);
			}
			else{
				cnt += perm(dept+1, i_sum + cards[0][i] + cards[1][dept], k_sum);
			}
			visited[i] = 0;
		}
		
		return cnt; 
	}
}