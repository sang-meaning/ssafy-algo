import java.io.*;
import java.util.*;
 
public class Solution {
     
    static class Core {
        int r, c;
        Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
 
    static int N;
    static int[][] map;
    static List<Core> cores;
    static int maxCore, minWireLen;
     
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            cores = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                            cores.add(new Core(i, j));
                        }
                    }
                }
            }
 
            maxCore = 0;
            minWireLen = Integer.MAX_VALUE;
 
            dfs(0, 0, 0);
 
            sb.append("#").append(tc).append(" ").append(minWireLen).append("\n");
        }
 
        System.out.print(sb);
    }
 
    // idx: 현재 판단 중인 코어 번호
    // connectedCore: 지금까지 연결 성공한 코어 수
    // wireLen: 지금까지 놓은 전선 길이 합
    static void dfs(int idx, int connectedCore, int wireLen) {
        if (connectedCore + (cores.size() - idx) < maxCore) {
            return;
        }
 
        if (idx == cores.size()) {
            if (connectedCore > maxCore) {
                maxCore = connectedCore;
                minWireLen = wireLen;
            } else if (connectedCore == maxCore) {
                minWireLen = Math.min(minWireLen, wireLen);
            }
            return;
        }
 
        Core cur = cores.get(idx);
 
        for (int d = 0; d < 4; d++) {
            if (canConnect(cur.r, cur.c, d)) {
                int len = setWire(cur.r, cur.c, d, 2); 
                dfs(idx + 1, connectedCore + 1, wireLen + len);
                setWire(cur.r, cur.c, d, 0);          
            }
        }
 
        dfs(idx + 1, connectedCore, wireLen);
    }
 
    static boolean canConnect(int r, int c, int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];
 
        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            if (map[nr][nc] != 0) {
                return false;
            }
            nr += dr[d];
            nc += dc[d];
        }
        return true;
    }
 
    static int setWire(int r, int c, int d, int value) {
        int len = 0;
        int nr = r + dr[d];
        int nc = c + dc[d];
 
        while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
            map[nr][nc] = value;
            len++;
            nr += dr[d];
            nc += dc[d];
        }
        return len;
    }
}