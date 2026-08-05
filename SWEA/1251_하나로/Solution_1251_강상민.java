import java.util.*;
import java.io.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int N;
    static double E;
    static int[] parent;
    static int[][] board;
    static long[][] edges;

    public static void main(String[] args) throws IOException {
        // 섬 1000개, 두 섬 잇는 Combination 1000 * 999 / 2 개
        // 섬 0~n-1 인덱스 붙이고, parent 에 초기화 및 edges[n*(n-1)/2][3] 초기화 (long 타입)
        

        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());

            int com = N*(N-1) / 2; // parent의 크기
            parent = new int[N+1];
            board = new int [2][N];
            edges = new long[com][3];

            for (int a=0; a<N; a++) parent[a] = a; // 초기 본인 값

            for (int a=0; a<2; a++) {
                st = new StringTokenizer(br.readLine());
                // N 개만큼 점 주어짐
                for (int b=0; b<N; b++) {
                    board[a][b] = Integer.parseInt(st.nextToken());
                }
            }

            int iter = 0;
            for (int i=0; i<N-1; i++) {
                for (int j=i+1; j<N; j++) {
                    int x1 = board[0][i];
                    int y1 = board[1][j];

                    int x2 = board[0][j];
                    int y2 = board[1][j];

                    long v = (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);

                    edges[iter] = new long[] {i,j,v};
                    iter++;
                }
            }

            Arrays.sort(edges, (a,b) -> Long.compare(a[2],b[2]));

            // 세팅 완료

            long sum = 0;
            int count = 0;

            for (long[] l : edges) {
                if (union(l[0],l[1])) {
                    sum+=l[2];
                    count++;
                }

                if (count == N-1) break;
            }

            sb.append("#"+t+" "+sum*E).append("\n");



        }

        // tc 완료
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find((int)parent[x]);
    }

    static boolean union(long a, long b){
        int pa = find((int)a);
        int pb = find((int)b);

        if (pa == pb) return false;

        parent[pb] = pa;
        return true;
    }
}
