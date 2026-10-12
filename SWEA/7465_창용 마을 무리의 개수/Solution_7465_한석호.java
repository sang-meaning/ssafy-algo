import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Solution {
    static int[] parent;

    // 대표(부모) 노드를 찾는 함수 (경로 압축 적용)
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // 두 사람의 무리를 합치는 함수 (합쳐지면 true, 이미 같은 무리면 false 반환)
    public static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
            return true; // 두 무리가 하나로 합쳐짐
        }
        return false; // 이미 같은 무리
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 부모 배열 초기화
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            int groupCount = n; // 초기 무리의 개수는 N개

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                // 서로 다른 무리가 합쳐질 때마다 전체 무리 개수를 1 감소
                if (union(u, v)) {
                    groupCount--;
                }
            }

            sb.append("#").append(tc).append(" ").append(groupCount).append("\n");
        }

        System.out.print(sb.toString());
    }
}