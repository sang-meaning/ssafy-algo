import java.util.*;
import java.io.*;

class Solution_1225_정서우 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String args[]) throws IOException {
        for (int test_case = 1; test_case <= 10; test_case++) {
            int t = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < 8; i++) {
                int a = Integer.parseInt(st.nextToken());
                q.add(a);
            }

            boolean finished = false;
            while (!finished) {
                for (int i = 1; i <= 5; i++) {
                    int N = q.poll();
                    if (N - i <= 0) {
                        q.add(0);
                        finished = true;
                        break;
                    }
                    q.add(N - i);
                }
            }

            System.out.printf("#%d ", t);

            while (!q.isEmpty()) {
                System.out.print(q.poll() + " ");
            }
            System.out.println();

        }
    }
}