

import java.io.BufferedReader;
import java.util.*;
import java.io.InputStreamReader;

public class Solution {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N;
    static int K;
    static int[][] arr;
    static List<int[]> cores;
    static int maxLen;
    static boolean[][] visited;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            cores = new ArrayList<>();
            int max = 0;
            arr = new int[N][N];
            visited = new boolean[N][N];
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] > max) {
                        max = arr[i][j];
                    }
                }
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == max) {
                        cores.add(new int[] {i, j});
                    }
                }
            }
            
            // 테스트 케이스마다 maxLen 초기화
            maxLen = 0;
            
            for (int i = 0; i < cores.size(); i++) {
                int[] cur = cores.get(i);
                int curx = cur[0];
                int cury = cur[1];
                
                visited[curx][cury] = true;
                dfs(curx, cury, 1, false); // 시작 길이 1부터 시작
                visited[curx][cury] = false;
            }
            
            System.out.println("#" + t + " " + maxLen);
        }
    }

    static void dfs(int x, int y, int len, boolean isCarved) {
    	
        maxLen = Math.max(maxLen, len);

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])//내가 간길을 돌아갈수있으니 방문체크
                continue;

            // 1. 그냥 갈 수 있는 경우 (다음 칸이 현재 칸보다 낮을 때)
            if (arr[nx][ny] < arr[x][y]) { // 
                visited[nx][ny] = true;
                dfs(nx, ny, len + 1, isCarved);
                visited[nx][ny] = false; // 백트래킹
            }
            
            // 2. 갈 수 없지만, 아직 공사를 안 했고 K만큼 깎아서 갈 수 있는 경우
            else if (!isCarved && arr[nx][ny] - K < arr[x][y]) {
                visited[nx][ny] = true;
                
                for (int k = 1; k <= K; k++) {
                    int originalHeight = arr[nx][ny];
                    arr[nx][ny] -= k;
                    
                    if (arr[nx][ny] < arr[x][y]) {
                        dfs(nx, ny, len + 1, true);
                    }
                    
                    arr[nx][ny] = originalHeight; // 원상복구
                }
                
                visited[nx][ny] = false; // 방문 해제
            }
        }
    }
}