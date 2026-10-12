import java.io.*;
import java.util.*;

public class Solution_6782_김민우 {

	static int T;
	static long N;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Long.parseLong(st.nextToken());
			
			int cnt = 0;
			while(N != 2) {
		
                long sqrt = (long) Math.sqrt(N);

                // N이 완전제곱수인 경우
                if (sqrt * sqrt == N) {
                    N = sqrt;
                    cnt++;
                } else {
                    // N보다 큰 가장 가까운 제곱수
                    long nextSquare = (sqrt + 1) * (sqrt + 1);

                    // nextSquare까지 숫자를 증가시키는 횟수
                    cnt += nextSquare - N;

                    // 제곱근 연산 1회
                    N = sqrt + 1;
                    cnt++;
                }
			}
			
			sb.append("#" + test_case + " " + cnt + "\n");
		}
		System.out.print(sb);
	}

}
