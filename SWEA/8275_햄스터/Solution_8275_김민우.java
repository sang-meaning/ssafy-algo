import java.util.*;
import java.io.*;

public class Solution_8275_김민우 {

	static int T, N, X, M;
	static int[] answer;
	static int[][] memos;
	static int max;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 0 = l, 1 = r, 2 = s
			memos = new int[M][3];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				memos[i][0] = Integer.parseInt(st.nextToken());
				memos[i][1] = Integer.parseInt(st.nextToken());
				memos[i][2] = Integer.parseInt(st.nextToken());
			}
			
			max = -1;
			answer = new int[N];
			
			int[] arr = new int[N];
			dfs(0, arr);
			
			System.out.printf("#%d ", test_case);
			if(max == -1)
				System.out.println(-1);
			else {
				for(int i = 0; i < N; i++)
					System.out.printf("%d ", answer[i]);
				System.out.println();
			}
		}//test_case 끝
	}//main 끝
	
	public static void dfs(int cnt, int[] arr) {
		//우리를 다 만들었는가?
		if(cnt == N) {
			//주어진 l, r 범위의 값 합과 s를 비교
			//하나라도 맞지 않으면, 조건 충족이 안된 것이므로 돌아가기
			for(int[] memo : memos) {
				int chk = 0;
				for(int i = memo[0]-1 ; i < memo[1]; i++)
					chk += arr[i];
				if(chk != memo[2])
					return;
			}
			//총 햄스터 개수 계산
			int curSum = 0;
			for(int i = 0; i < N; i++)
				curSum += arr[i];
			
			//정답 우리의 햄스터 합보다 많은가?
			if(curSum > max) {
				change(arr);
				max = curSum;
			}
			//만약 둘이 같은 값일 경우
			else if(curSum == max) {
				//사전식 정렬을 위해 비교
				boolean flag = true;
				for(int i = 0; i < N; i++) {
					if(arr[i] > answer[i]) {
						flag = false;
						break;
					}
				}
				//새롭게 만든 우리가 사전식 정렬에 있어 앞순번일 경우,
				//이를 정답 우리로 선정
				if(flag) {
					change(arr);
					max = curSum;
				}
			}
		}
		
		else {
			for(int i = 0; i <= X; i++) {
				arr[cnt] = i;
				dfs(cnt+1, arr);
			}
		}
	}//dfs 끝

	//새롭게 만든 햄스터 우리가 정답보다 좋으면 change
	public static void change(int[] arr) {
		for(int i = 0; i < N; i++)
			answer[i] = arr[i];
	}//change 끝
}
