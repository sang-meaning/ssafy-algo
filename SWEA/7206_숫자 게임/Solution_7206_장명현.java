import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N, answer;
	public static int[] turn;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		turn = new int[100000];
		for (int i=10; i<100000; i++) turn[i] = -1;
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			turn[N] = cur(N);
			answer = turn[N];
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int cur(int x) {
		if (turn[x] >= 0) return turn[x];
		
		String s = String.valueOf(x);
		int n = s.length();
		
		// 1 2 3 4 5 => 숫자가 5개면 가운데는 4개, 전부 연결하는 경우는 빼야하니 2^(l-1)-1
		
		int temp = 0;
		for (int i=0; i<(1<<(n-1))-1; i++) {
			int mul = 1;
			int now = s.charAt(0) - '0';
			
			// 비트가 1이면 연결, 0이면 띄우고 곱하기 (2^(n-1)-1에서 마지막이 빠졌으니 비트가 1일때 연결해야 됨)
			// n번째는 무조건 0비트이니 마무리 곱셈
			for (int j=0; j<n; j++) {
				if ((i & (1 << j)) != 0) {
					now = now * 10 + (s.charAt(j+1) - '0');
				} else {
					mul *= now;
					now = j+1 == n ? 0 : (s.charAt(j+1) - '0');
				}
			}
			
			turn[mul] = cur(mul);
			temp = Math.max(temp, turn[mul]);
		}
		
		return temp+1;
	}
}