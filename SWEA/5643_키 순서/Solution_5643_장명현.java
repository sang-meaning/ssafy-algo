import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine().trim());
			int M = Integer.parseInt(br.readLine().trim());
			boolean[][] arr = new boolean[N+1][N+1];

			// arr[a][b]: a번 학생이 b번 학생보다 키가 작음
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[a][b] = true;
			}

			// i < k이고 k < j이면 i < j도 알 수 있음
			for (int k=1; k<=N; k++) {
				for (int i=1; i<=N; i++) {
					if (!arr[i][k]) continue;
					for (int j=1; j<=N; j++) {
						if (arr[k][j]) arr[i][j] = true;
					}
				}
			}

			int answer = 0;
			for (int i=1; i<=N; i++) {
				int count = 0;
				for (int j=1; j<=N; j++) {
					if (arr[i][j] || arr[j][i]) count++;
				}

				// 다른 모든 학생과 키를 비교할 수 있으면 순서가 확정됨
				if (count == N-1) answer++;
			}

			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}
}
