import java.io.*;
import java.util.*;

public class Solution_2477_임성진 {

	static int N, M, K, A, B;
	static int[] a, b, t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			a = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());

			b = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) b[j] = Integer.parseInt(st.nextToken());

			t = new int[K + 1];
			st = new StringTokenizer(br.readLine());
			for (int k = 1; k <= K; k++) t[k] = Integer.parseInt(st.nextToken());

			sb.append('#').append(tc).append(' ').append(simulate()).append('\n');
		}

		System.out.print(sb);
	}

	static int simulate() {
		int[] recep = new int[N + 1];
		int[] recepEnd = new int[N + 1];
		int[] repair = new int[M + 1];
		int[] repairEnd = new int[M + 1];

		int[] usedRecep = new int[K + 1];
		int[] usedRepair = new int[K + 1];

		Queue<Integer> recepQ = new ArrayDeque<>();
		Queue<Integer> repairQ = new ArrayDeque<>();

		int next = 1;
		int done = 0;
		int time = 0;

		while (done < K) {
			for (int i = 1; i <= N; i++) {
				if (recep[i] != 0 && recepEnd[i] == time) {
					repairQ.add(recep[i]);
					recep[i] = 0;
				}
			}

			for (int j = 1; j <= M; j++) {
				if (repair[j] != 0 && repairEnd[j] == time) {
					repair[j] = 0;
				}
			}

			while (next <= K && t[next] == time) {
				recepQ.add(next++);
			}

			for (int i = 1; i <= N; i++) {
				if (recep[i] == 0 && !recepQ.isEmpty()) {
					int c = recepQ.poll();
					recep[i] = c;
					recepEnd[i] = time + a[i];
					usedRecep[c] = i;
				}
			}

			for (int j = 1; j <= M; j++) {
				if (repair[j] == 0 && !repairQ.isEmpty()) {
					int c = repairQ.poll();
					repair[j] = c;
					repairEnd[j] = time + b[j];
					usedRepair[c] = j;
					done++;
				}
			}

			time++;
		}

		int sum = 0;
		for (int c = 1; c <= K; c++) {
			if (usedRecep[c] == A && usedRepair[c] == B) {
				sum += c;
			}
		}

		return sum == 0 ? -1 : sum;
	}
}
