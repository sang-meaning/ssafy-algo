import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int test_case = 1; test_case <= 10; test_case++) {
			
			int T;
			T = Integer.parseInt(br.readLine());
			Queue<Integer> queue = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 8; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}

			int minus = 1;
			while (true) {
				int num = queue.poll();
				num -= minus;

				if (num <= 0) {
					queue.offer(0);
					break;
				}

				queue.offer(num);

				minus++;

				if (minus > 5) {
					minus = 1;
				}
			}

			System.out.print("#" + T);
			for (int j = 0; j < 8; j++) {
				System.out.print(" " + queue.poll());
			}
			System.out.println();
		}
	}
}