import java.util.Scanner;

public class Solution {
    static int N;
    static int[][] customer;
    static boolean[] visited;
    static int homeX, homeY;
    static int min;

    static void dfs(int count, int x, int y, int sum) {
        if(sum >= min) {
            return;
        }

        if(count == N) {
            int distance = Math.abs(x-homeX) + Math.abs(y-homeY);
            min = Math.min(min, sum + distance);
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;

                int distance = Math.abs(x-customer[i][0]) + Math.abs(y-customer[i][1]);

                dfs(count+1, customer[i][0], customer[i][1], sum+distance);

                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();

            int companyX = sc.nextInt();
            int companyY = sc.nextInt();
            homeX = sc.nextInt();
            homeY = sc.nextInt();

            customer = new int[N][2];
            visited = new boolean[N];
            min = Integer.MAX_VALUE;

            for(int i=0; i<N; i++) {
                customer[i][0] = sc.nextInt();
                customer[i][1] = sc.nextInt();
            }

            dfs(0, companyX, companyY, 0);

            System.out.println("#" + tc + " " + min);
        }
    }
}