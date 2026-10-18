import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            boolean[][] graph = new boolean[N+1][N+1];

            for(int i=0; i<M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = true;
            }

            // 다른 사람과의 차이로 알아내는 경우
            for(int k=1; k<=N; k++) {
                for(int i=1; i<=N; i++) {
                    for(int j=1; j<=N; j++) {
                        if(graph[i][k] && graph[k][j])
                            graph[i][j] = true;
                    }
                }
            }

            int ans = 0;

            for(int i=1; i<=N; i++) {
                boolean possible = true;

                for(int j=1; j<=N; j++) {
                    if(i == j) continue;

                    // 서로의 대소 관계를 알 수 없는 학생
                    if(!graph[i][j] && !graph[j][i]) {
                        possible = false;
                        break;
                    }
                }

                if(possible) ans++;
            }

            sb.append("#").append(tc).append(" ")
              .append(ans).append("\n");
        }

        System.out.print(sb);
    }
}