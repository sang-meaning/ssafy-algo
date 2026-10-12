import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    static int N;
    static int min;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String firstLine = br.readLine();
        if (firstLine == null || firstLine.trim().isEmpty()) return;
        int T = Integer.parseInt(firstLine.trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            
            arr = new int[N][N];
            visited = new boolean[N];
            min = Integer.MAX_VALUE;
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            dfs(0, 0);
            
            System.out.println("#" + tc + " " + min);
        }
    }

    static void dfs(int idx, int count) {
        if (count == N / 2) {
            int A = 0;
            int B = 0;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    
                    if (visited[i] && visited[j]) {
                        A += arr[i][j];
                    } else if (!visited[i] && !visited[j]) {
                        B += arr[i][j];
                    }
                }
            }
            int diff = Math.abs(A - B);
            min = Math.min(min, diff);
            return;
        }
        
        for (int i = idx; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, count + 1);
                visited[i] = false;
            }
        }   
    }
}