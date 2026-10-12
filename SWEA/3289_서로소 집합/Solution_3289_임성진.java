import java.io.*;

public class Solution_3289_임성진 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();

        in.nextToken(); int T = (int) in.nval;
        for (int tc = 1; tc <= T; tc++) {
            in.nextToken(); int n = (int) in.nval;
            in.nextToken(); int m = (int) in.nval;

            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) parent[i] = i;

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < m; i++) {
                in.nextToken(); int op = (int) in.nval;
                in.nextToken(); int a = (int) in.nval;
                in.nextToken(); int b = (int) in.nval;
                if (op == 0) union(a, b);
                else sb.append(find(a) == find(b) ? 1 : 0);   // 공백 없이 이어서 출력
            }
            sb.append('\n');
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