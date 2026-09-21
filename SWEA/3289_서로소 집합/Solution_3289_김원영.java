import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int M;
    static int[] parent;

    static int find(int x) {
        if(parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot) {
            parent[bRoot] = aRoot;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];

            for(int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            StringBuilder result = new StringBuilder();

            for(int i = 0; i < M; i++) {

                st = new StringTokenizer(br.readLine());

                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(command == 0) {
                    union(a, b);
                }
                else {
                    if(find(a) == find(b)) {
                        result.append(1);
                    }
                    else {
                        result.append(0);
                    }
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }
}