package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		// 시뮬레이션

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] jup = new int[N][2]; // [i][0]은 고객번호, [i][1]은 처리시간 관리
			int[][] jung = new int[M][2];
			int[] a = new int[N];
			int[] b = new int[M];

			int K = Integer.parseInt(st.nextToken());

			int[] client = new int[K];
			boolean[] memo = new boolean[K + 1];
			// 고객번호는 1부터 카운트
			int k = 1;

			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < K; i++) {
				client[i] = Integer.parseInt(st.nextToken());
			}

			Queue<Integer> jupQ = new ArrayDeque<>();
			Queue<Integer> jungQ = new ArrayDeque<>();

			int answer = 0;
			int time = -1; // 0분도 처리해야 함
			int done = 0; // 처리 완료된 사람 수
			while (done < K) {
				// 그냥 분단위로 시뮬하는 게 마음 편할 듯.
				// 다음 손님이 올 시간이면 손님을 대기열에 넣기
				// 손님 동시에 올 수도 있음.
				time++;
				while (k <= K && time == client[k - 1]) {
					jupQ.offer(k++);
				}
				// 접수터 처리가 끝나면 비우고 정비터로 보냄
				// 접수터 빈자리가 있다면 손님 보냄
				// 반약 자리가 다 차 있다면 기다렸다가 넣어야.
				for (int i = 0; i < N; i++) {
					if (jup[i][0] != 0 && jup[i][1] == 0) { // 고객이 있다 && 일이 끝났다
						jungQ.offer(jup[i][0]); // 정비터큐로 보낸다
						jup[i][0] = 0; // 대기상태 된다.
					}
					if (jup[i][0] == 0) { // 고객번호가 없는 접수처
						if (!jupQ.isEmpty()) {
							jup[i][0] = jupQ.poll();// 다음 고객이 들어온다.
							if (i == A) {
								// 그 고객번호 기억해둔다.
								memo[jup[i][0]] = true;
							}
							jup[i][1] = a[i]; // 처리시간이 걸린다.
						}
					}
					if (jup[i][0] != 0 && jup[i][1] != 0) { // 고객이 있다 && 일이 안 끝났다
						jup[i][1]--; // 시간이 흐른다
					}
				}
				// 두 명 이상의 고객들이 접수 창구에서 동시에 접수를 완료하고 정비 창구로 이동한 경우,
				// 이용했던 접수 창구번호가 작은 고객이 우선 -> 적용됨.
				for (int i = 0; i < M; i++) {
					if (jung[i][0] != 0 && jung[i][1] == 0) { // 고객이 있다 && 일이 끝났다
						// 고객은 집에 갔다.
						jung[i][0] = 0; // 대기상태 된다.
					}
					if (jung[i][0] == 0) { // 고객번호가 없는 정비터
						if (!jungQ.isEmpty()) {
							jung[i][0] = jungQ.poll();// 다음 고객이 들어온다.
							// 그 고객이 만약 접수도 타겟이었다면 정답에 더한다.
							if (i == B && memo[jung[i][0]])
								answer += jung[i][0];
							// 정비접수까지 완료한 고객 수가 최대가 되면 끝
							done++;
							jung[i][1] = b[i]; // 처리시간이 걸린다.
						}
					}
					if (jung[i][0] != 0 && jung[i][1] != 0) { // 고객이 있다 && 일이 안 끝났다
						jung[i][1]--; // 시간이 흐른다
					}
				}
			}

			answer = (answer == 0) ? -1 : answer;
			System.out.println("#" + test_case + " " + answer);
		}
	}

}
