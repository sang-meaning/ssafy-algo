
import java.io.*;
import java.util.*;

public class Solution {
	static int D,W,K;
	static int[][] film;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			for(int i = 0; i <= K; i++) {
				if(comb(0, i, 0)) {
					answer = i;
					break;
				}
			}
			
			System.out.println("#" + testcase + " " + answer);
		}
	}
	// cnt개의 약을 넣는 조합 + 통과 여부 반환
	public static boolean comb(int cnt, int target, int dept) {
		if(cnt == target) {
			return checking();
		}
		
		else if(dept >= D ||(cnt + (D-dept) < target)) return false;
		
		int[] copyFilm = film[dept].clone();
		// 1로 바꿈
		Arrays.fill(film[dept], 1);
		if(comb(cnt+1, target, dept+1)) {
			film[dept] = copyFilm;
			return true;
		}
		
		// 0으로 바꿈
		Arrays.fill(film[dept], 0);
		if(comb(cnt+1, target, dept+1)) {
			film[dept] = copyFilm;
			return true;
		}
		
		// 안바꿈
		film[dept] = copyFilm;
		if(comb(cnt, target, dept+1)) {
			return true;
		}
		
		return false;
	}
	// 전체 체크
	public static boolean checking() {
		
		for(int i = 0; i < W; i++) {
			int cnt = 1;
			// 한 줄
			for(int j = 1; j < D; j++) {
				if(film[j-1][i] == film[j][i]) {
					cnt++;
					if(cnt == K) break;
				}
				else cnt=1;
			}
			
			if(cnt < K) return false;
		}
		return true;
	}
}