import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static char[][] gamemap;
    static int[] dx = {1,-1,0,0};
    static int[] dy ={ 0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        for(int t= 1; t<=T; t++){
            st= new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int curx= 0;
            int cury=0;
            int idx = 0 ;
            gamemap = new char[H][W];
            for(int i = 0; i<H; i++){
                String m = br.readLine();
                for(int j =0; j<W; j++){
                    gamemap[i][j] = m.charAt(j);
                    if(m.charAt(j)=='>'){
                        curx = j;
                        cury = i;
                        idx =0;
                        continue;
                    }
                    if(m.charAt(j)=='<'){
                        curx =j;
                        cury = i;
                        idx =1;
                        continue;
                    }
                    if(m.charAt(j)=='v'){
                        curx=j;
                        cury= i;
                        idx= 2;
                        continue;
                    }
                    if(m.charAt(j)=='^'){
                        curx=j;
                        cury=i;
                        idx=3;
                    }
                }
            }
            int len = Integer.parseInt(br.readLine());
            String  order = br.readLine();
            for(int i= 0 ; i< len; i++) {
                char od = order.charAt(i);
                if (od == 'U') {
                    idx = 3;
                    gamemap[cury][curx] = '^';
                    int nx = curx + dx[idx];
                    int ny = cury + dy[idx];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                    if (gamemap[ny][nx] == '.') {
                        gamemap[cury][curx] = '.';
                        gamemap[ny][nx] = '^';
                        curx = nx;
                        cury = ny;
                        continue;
                    }
                }
                if (od == 'D') {
                    idx = 2;
                    gamemap[cury][curx] = 'v';
                    int nx = curx + dx[idx];
                    int ny = cury + dy[idx];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                    if (gamemap[ny][nx] == '.') {
                        gamemap[cury][curx] = '.';
                        gamemap[ny][nx] = 'v';
                        curx = nx;
                        cury = ny;
                        continue;
                    }
                }
                if (od == 'L') {
                    idx = 1;
                    gamemap[cury][curx] = '<';
                    int nx = curx + dx[idx];
                    int ny = cury + dy[idx];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                    if (gamemap[ny][nx] == '.') {
                        gamemap[cury][curx] = '.';
                        gamemap[ny][nx] = '<';
                        curx = nx;
                        cury = ny;
                        continue;
                    }
                }
                if (od == 'R') {
                    idx = 0;
                    gamemap[cury][curx] = '>';
                    int nx = curx + dx[idx];
                    int ny = cury + dy[idx];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H) continue;
                    if (gamemap[ny][nx] == '.') {
                        gamemap[cury][curx] = '.';
                        gamemap[ny][nx] = '>';
                        curx = nx;
                        cury = ny;
                        continue;
                    }
                }
                if (od == 'S') {
                    int bufferx = curx;
                    int buffery = cury;
                    while (true) {
                        int nx = bufferx + dx[idx];
                        int ny = buffery + dy[idx];
                        if (nx < 0 || ny < 0 || nx >= W || ny >= H) break;
                        if (gamemap[ny][nx] == '#') break;

                        if (gamemap[ny][nx] == '*') {
                            gamemap[ny][nx] = '.';
                            break;
                        }
                        bufferx = nx;
                        buffery = ny;
                    }
                }
            }
            StringBuilder sb= new StringBuilder();
            sb.append("#"+t+" ");
            for(int i =0; i<H;i++){
                for(int j =0; j<W; j++){
                    sb.append(gamemap[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }
}
