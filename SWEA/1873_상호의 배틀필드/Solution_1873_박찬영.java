import java.io.*;
import java.util.*;

public class Solution {
    

    static int[] dr = {-1, 0, 1, 0}; 
    static int[] dc = {0, 1, 0, -1};
    static final String TANK_SYMBOLS = "^>v<"; 
    static final String DIRS = "URDL";
    
    static int H, W, r, c, dir;
    static char[][] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= TC; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            map = new char[H][W];
            
            for (int i = 0; i < H; ++i) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; ++j) {
                    int idx = TANK_SYMBOLS.indexOf(map[i][j]);
                    if (idx != -1) {
                        r = i;
                        c = j;
                        dir = idx;
                        map[i][j] = '.';
                    }
                }
            }
            
            br.readLine();
            char[] commands = br.readLine().trim().toCharArray();
            

            for (char cmd : commands) {
                if (cmd == 'S') {
                    shoot();
                } else {
                    move(cmd);
                }
            }
            

            map[r][c] = TANK_SYMBOLS.charAt(dir);

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < H; ++i) {
                sb.append(map[i]).append("\n");
            }
        }
        
        System.out.print(sb);
    }
    
    static void move(char cmd) {
        dir = DIRS.indexOf(cmd);
        
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        
        if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
            r = nr;
            c = nc;
        }
    }
    
 
    static void shoot() {
        int br = r + dr[dir];
        int bc = c + dc[dir];
        
        while (br >= 0 && br < H && bc >= 0 && bc < W) {

            if (map[br][bc] == '*') {
                map[br][bc] = '.';
                break;
            }

            if (map[br][bc] == '#') {
                break;
            }
            br += dr[dir];
            bc += dc[dir];
        }
    }
}