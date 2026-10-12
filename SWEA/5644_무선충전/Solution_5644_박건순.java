import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static class AP {
        int x;
        int y;
        int c;
        int p;

        AP(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    static int[] dr = { 0, 0, 1, 0, -1 };
    static int[] dc = { 0, -1, 0, 1, 0 };
    static int M;
    static int A;
    static int[] commandA;
    static int[] commandB;
    static AP[] aps;
    static int ar = 1;
    static int ac = 1;
    static int br = 10;
    static int bc = 10;

    public static void main(String args[]) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T;
        T = Integer.parseInt(in.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            ar = 1;
            ac = 1;
            br = 10;
            bc = 10;
            st = new StringTokenizer(in.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            commandA = new int[M + 1];
            commandB = new int[M + 1];
            st = new StringTokenizer(in.readLine());
            for (int i = 1; i <= M; i++) {
                commandA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine());
            for (int i = 1; i <= M; i++) {
                commandB[i] = Integer.parseInt(st.nextToken());
            }
            aps = new AP[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(in.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                aps[i] = new AP(x, y, c, p);
            }
            int sum = 0;
            for (int i = 0; i <= M; i++) {
                sum += sumCharing(i);
            }

            System.out.println("#" + test_case + " " + sum);
        }
    }

    static int sumCharing(int T) {
        int maxSum = 0;
        ar = ar + dr[commandA[T]];
        ac = ac + dc[commandA[T]];

        br = br + dr[commandB[T]];
        bc = bc + dc[commandB[T]];
        boolean possibleA;
        boolean possibleB;
        int sum;
        for (int i = 0; i < A; i++) {
            sum = 0;
            possibleA = isCharing(ar, ac, aps[i]);
            for (int j = 0; j < A; j++) {
                possibleB = isCharing(br, bc, aps[j]);
                if ((i == j) && possibleA && possibleB) {
                    sum = aps[j].p;
                } else if (possibleA && possibleB) {
                    sum = aps[i].p + aps[j].p;
                } else if (possibleA && !possibleB) {
                    sum = aps[i].p;
                } else if (possibleB && !possibleA) {
                    sum = aps[j].p;
                } else {
                    sum = 0;
                }

                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    static boolean isCharing(int x, int y, AP ap) {
        int distance = Math.abs(x - ap.x) + Math.abs(y - ap.y);

        if (distance <= ap.c) {
            return true;
        } else {
            return false;
        }
    }
}