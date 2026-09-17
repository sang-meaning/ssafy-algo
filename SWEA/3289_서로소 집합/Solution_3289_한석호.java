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

    // 두 원소가 속한 집합을 합치는 함수
    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
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
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }

            sb.append("#").append(tc).append(" ");

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (type == 0) {
                    // 합집합 연산
                    union(a, b);
                } else if (type == 1) {
                    // 두 원소가 같은 집합에 속해 있는지 확인
                    if (find(a) == find(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}