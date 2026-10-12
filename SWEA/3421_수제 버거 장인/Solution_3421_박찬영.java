import java.io.*;
import java.util.*;
 
public class Solution {
    static int n, m;
    static boolean[][] isBad;       
    static boolean[] selected;     
    static int answer;            
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); 
            m = Integer.parseInt(st.nextToken()); 
 
            isBad = new boolean[n + 1][n + 1];
            selected = new boolean[n + 1];
            answer = 0;
 
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                isBad[a][b] = true;
                isBad[b][a] = true; 
            }
 
            dfs(1);
 
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
 
        System.out.print(sb);
    }
 
    static void dfs(int idx) {
        if (idx == n + 1) {
            answer++; 
            return;
        }
 
        boolean canPick = true;
        for (int prev = 1; prev < idx; prev++) {
            if (selected[prev] && isBad[idx][prev]) {
                canPick = false; 
                break;
            }
        }
 
        if (canPick) {
            selected[idx] = true;  
            dfs(idx + 1);
            selected[idx] = false; 
        }
 
        dfs(idx + 1);
    }
}