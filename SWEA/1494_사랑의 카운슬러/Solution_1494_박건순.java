import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
    static int N;
    static int[][] worms;
    static int[] selected;
    static long minSum;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());

            worms = new int[N][2];
            selected = new int[N / 2];
            minSum = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                worms[i][0] = x;
                worms[i][1] = y;
            }

            combin(0, 0);

            System.out.println("#" + test_case + " " + minSum);
        }
    }

    static void combin(int start, int count) {

        if (count == N / 2) {
            compute();
            return;
        }

        for (int i = start; i < N; i++) {
            selected[count] = i;
            combin(i + 1, count + 1);
        }
    }

    static void compute() {

        long sumX = 0;
        long sumY = 0;

        boolean[] visited = new boolean[N];

        for (int i = 0; i < N / 2; i++) {
            visited[selected[i]] = true;
        }

        for (int i = 0; i < N; i++) {

            if (visited[i]) {
                sumX -= worms[i][0];
                sumY -= worms[i][1];
            } else {
                sumX += worms[i][0];
                sumY += worms[i][1];
            }
        }

        long result = sumX * sumX + sumY * sumY;

        minSum = Math.min(minSum, result);
    }
}