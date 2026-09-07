import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.BufferedReader;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int middle = (int) Math.ceil((double) N / 2);
			Queue<String> oddQueue = new ArrayDeque<>();
			Queue<String> evenQueue = new ArrayDeque<>();
			List<String> answerList = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				if (i < middle) {
					oddQueue.offer(st.nextToken());
				} else {
					evenQueue.offer(st.nextToken());
				}
			}

			for (int i = 1; i <= N; i++) {
				if (i % 2 == 0) {
					if (!evenQueue.isEmpty())
						answerList.add(evenQueue.poll());
				} else {
					if (!oddQueue.isEmpty())
						answerList.add(oddQueue.poll());
				}
			}
			System.out.println("#" + test_case + " " + String.join(" ", answerList));
		}
	}
}