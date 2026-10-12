import java.util.*;

public class Solution {

    static int[] parent;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int N = sc.nextInt();
            int M = sc.nextInt();

            parent = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < M; i++) {

                int command = sc.nextInt();
                int a = sc.nextInt();
                int b = sc.nextInt();

                if (command == 0) {

                    union(a, b);

                }

                else {

                    if (findSet(a) == findSet(b)) {
                        sb.append(1);
                    }

                    else {
                        sb.append(0);
                    }
                }
            }

            System.out.println("#" + tc + " " + sb);
        }

        sc.close();
    }




    static int findSet(int x) {

        if (parent[x] == x) {
            return x;
        }

        return parent[x] = findSet(parent[x]);
    }


    static void union(int a, int b) {

        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA != rootB) {

            parent[rootB] = rootA;
        }
    }
}