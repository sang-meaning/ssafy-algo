import java.util.*;
import java.io.*;

public class Solution_2112_김민우 {
	
	static int T, D, W, K;
	static int[][] film;
	static int answer;
	static boolean[] visited;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++)
					film[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//처음 주어지는 필름상태가 이미 검정기준을 충족하는 경우
			if(check(film)) {
				System.out.printf("#%d 0\n", test_case, 0);
				continue;
			}
			
			flag = false;
			answer = D;
			for(int i = 1; i <= D; i++) {
				visited = new boolean[D];
				improveFilm(0, 0, 0, i);
				improveFilm(0, 1, 0, i);
			}
			
			System.out.printf("#%d %d\n", test_case, answer);
		}//test_case 끝
	}//main 끝
	
	//testfilm : 필름 / med : 약품 종류(1 or 0) / cnt : 현재 약품 투입횟수 / limit : 총 약품 투입횟수
	public static void improveFilm(int start, int med, int cnt, int limit) {
		//약품 투입을 끝마쳤을 때 필름이 검정기준 충족하는지 체크
		if(flag)
			return;
		
		if(cnt == limit) {
			if(check(film)) {
				answer = cnt;
				flag = true;
			}
			return;
		}
		
		int remain = limit-cnt;
		
		//약품 투입할 곳 정하기
		for(int i = start; i <= D-remain; i++) {
			int[] backup = Arrays.copyOf(film[i], W);
			
			//약품 투입하기
			for(int j = 0; j < W; j++)
				film[i][j] = med;
			
			improveFilm(i+1, 0, cnt+1, limit);
			if(flag)
				return;
			improveFilm(i+1, 1, cnt+1, limit);
			
			//원복
			film[i] = Arrays.copyOf(backup, W);
			
			if(flag)
				return;
		
		}
		
	}
	
	//필름이 검정기준 충족하는지 체크
	public static boolean check(int[][] testfilm) {
		int total = 0;
		for(int i = 0; i < W; i++) {
			int cnt = 1;
			boolean flag = false;
			for(int j = 1; j < D; j++) {
				if(testfilm[j][i] == testfilm[j-1][i])
					cnt++;
				else
					cnt = 1;	
				if(cnt == K) {
					total++;
					flag = true;
					break;
				}
			}
			if(!flag)
				return false;
		}
		
		return true;
	}//check 끝

}
