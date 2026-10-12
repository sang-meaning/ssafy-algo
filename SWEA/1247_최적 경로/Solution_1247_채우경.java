package samsung01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1 {

    static int N;
    static int[] start = new int[2]; // 회사 [x, y]
    static int[] end = new int[2];   // 집 [x, y]
    static int[][] cs;               // 고객 N명의 [x, y]
    static boolean[] visited;
    static int minDistance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            
            visited = new boolean[N];
            cs = new int[N][2];
            minDistance = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());

            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            
            for (int i = 0; i < N; i++) {
                cs[i][0] = Integer.parseInt(st.nextToken());
                cs[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, start, 0);

            System.out.println("#" + t + " " + minDistance);
        }
    }

    static void dfs(int count, int[] current, int currentDist) {
        // 가지치기
    	
        if (currentDist >= minDistance) {
            return;
        }

        if (count == N) {
            int totalDist = currentDist + getDist(current, end);
            minDistance = Math.min(minDistance, totalDist);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nextDist = currentDist + getDist(current, cs[i]);
                
                dfs(count + 1, cs[i], nextDist);
                
                visited[i] = false;
            }
        }
    }

    static int getDist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}