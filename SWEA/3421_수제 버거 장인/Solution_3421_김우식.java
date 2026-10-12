package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3421 {

    static int T, N, M, answer;
    static int[] a, b;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            a = new int[M];
            b = new int[M];
            selected = new boolean[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                a[i] = Integer.parseInt(st.nextToken());
                b[i] = Integer.parseInt(st.nextToken());
            }

            answer = 0;

            subset(1);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }

    static void subset(int cnt) {

        if (cnt == N + 1) {

            for (int i = 0; i < M; i++) {

                if (selected[a[i]] && selected[b[i]]) {
                    return;
                }
            }

            answer++;
            return;
        }

        selected[cnt] = true;
        subset(cnt + 1);

        selected[cnt] = false;
        subset(cnt + 1);
    }
}