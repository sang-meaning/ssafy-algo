import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static int N, M, K, A, B;
	public static int[] a, b, t;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			a = new int[N+1];
			b = new int[M+1];
			t = new int[K+1];

			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=N; i++) a[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=M; i++) b[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=K; i++) t[i] = Integer.parseInt(st.nextToken());

			// 창구마다 [고객번호, 종료 시간] 저장. 고객번호 0이면 빈 창구
			int[][] reception = new int[N+1][2];
			int[][] repair = new int[M+1][2];
			int[] used = new int[K+1];
			Queue<Integer> q = new ArrayDeque<>();

			int time = 0, idx = 1, count = 0, answer = 0;
			while (count < K) {
				// 접수 완료. 같은 시간에 끝나면 접수 창구번호 순서로 대기열에 추가
				for (int i=1; i<=N; i++) {
					if (reception[i][0] == 0 || reception[i][1] != time) continue;
					q.add(reception[i][0]);
					reception[i][0] = 0;
				}

				// 정비 완료. 이번 시간에 바로 다음 고객을 받을 수 있도록 창구 비우기
				for (int i=1; i<=M; i++) {
					if (repair[i][0] == 0 || repair[i][1] != time) continue;
					repair[i][0] = 0;
					count++;
				}

				// 도착한 고객 중 고객번호가 작은 순서로, 번호가 작은 빈 접수 창구에 배정
				for (int i=1; i<=N; i++) {
					if (idx > K || t[idx] > time) break;
					if (reception[i][0] != 0) continue;
					reception[i][0] = idx;
					reception[i][1] = time + a[i];
					used[idx] = i;
					idx++;
				}

				// 먼저 기다린 고객부터, 번호가 작은 빈 정비 창구에 배정
				for (int i=1; i<=M; i++) {
					if (q.isEmpty()) break;
					if (repair[i][0] != 0) continue;
					int customer = q.poll();
					repair[i][0] = customer;
					repair[i][1] = time + b[i];
					if (used[customer] == A && i == B) answer += customer;
				}

				time++;
			}

			if (answer == 0) answer = -1;
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}

		System.out.print(sb);
	}
}
