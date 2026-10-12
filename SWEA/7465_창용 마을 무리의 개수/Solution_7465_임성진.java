import java.io.*;

public class Solution_7465_임성진 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();

        in.nextToken(); int T = (int) in.nval;
        for (int tc = 1; tc <= T; tc++) {
            in.nextToken(); int N = (int) in.nval;
            in.nextToken(); int M = (int) in.nval;

            parent = new int[N + 1];
            for (int i = 0; i <= N; i++) parent[i] = i;

            for (int i = 0; i < M; i++) {
                in.nextToken(); int a = (int) in.nval;
                in.nextToken(); int b = (int) in.nval;
                union(a, b);
            }

            int groups = 0;
            for (int i = 1; i <= N; i++) if (find(i) == i) groups++;  // 혼자여도 1무리

            sb.append('#').append(tc).append(' ').append(groups).append('\n');
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a); b = find(b);
        if (a != b) parent[b] = a;
    }
}