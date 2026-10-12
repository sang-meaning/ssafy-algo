import java.util.*;
import java.io.*;

public class Solution_1873_정영훈 {

    /***
     * 문자 의미
     * . 평지(전차가 들어갈 수 있다.) 0
     * 벽돌로 만들어진 벽 1
     * # 강철로 만들어진 벽 2
     * - 물(전차는 들어갈 수 없다.) 3
     * ^ 위쪽을 바라보는 전차(아래는 평지이다.)     4
     * v 아래쪽을 바라보는 전차(아래는 평지이다.) 
     * < 왼쪽을 바라보는 전차(아래는 평지이다.) 
     * > 오른쪽을 바라보는 전차(아래는 평지이다.) 
     * 
     * 
     */
    final static int BLANK=0, WALL=1, I_WALL=2, WATER=3, TANK=4;
    final static int UP=0,DOWN=1,LEFT=2,RIGHT=3;
    final static int[] dy={-1,1,0,0};
    final static int[] dx={0,0,-1,1}; 
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            int[] startPos=new int[3];
            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < W; j++) {
                    Character c=s.charAt(j);
                    int value=0;
                    if(c=='.') value=BLANK;
                    else if(c=='*') value=WALL;
                    else if(c=='#') value=I_WALL;
                    else if(c=='-') value=WATER;
                    else{
                        value=TANK;
                        if(c=='^') startPos=new int[]{i,j,0};
                        if(c=='v') startPos=new int[]{i,j,1};
                        if(c=='<') startPos=new int[]{i,j,2};
                        if(c=='>') startPos=new int[]{i,j,3};
                        
                    }
                    map[i][j]=value;
                    
                }

            }
            int n=Integer.parseInt(br.readLine());
            String op=br.readLine();
            for (int i = 0; i < op.length(); i++) {
                Character c=op.charAt(i);
                if(c=='U') startPos=move(startPos, 0, H, W);
                if(c=='D') startPos=move(startPos, 1, H, W);
                if(c=='L') startPos=move(startPos, 2, H, W);
                if(c=='R') startPos=move(startPos, 3, H, W);
                if(c=='S') shoot(startPos, H, W); 
            }
            sb.append("#"+test_case+" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(printChar(map[i][j], startPos[2]));
                    
                }
                sb.append("\n");
                
            }

        }
        System.out.println(sb.toString());
    }
    public static int[] move(int[] pos, int direct, int H, int W){
        int y=pos[0];
        int x=pos[1];
        int cDirect=pos[2];
        int[] nextPos=new int[]{y,x,direct};

        int ny=y+dy[direct];
        int nx=x+dx[direct];
        if(ny<0 || nx<0 || ny>=H || nx>=W) return nextPos;
        if(map[ny][nx]==WALL || map[ny][nx]==I_WALL || map[ny][nx]==WATER) return nextPos;
        map[ny][nx]=TANK;
        map[y][x]=BLANK;
        return nextPos=new int[]{ny, nx, direct};



    }
    public static void shoot(int[] pos, int H, int W){
        int y=pos[0];
        int x=pos[1];
        int direct=pos[2];
        int ny=y+dy[direct];
        int nx=x+dx[direct];
        while(ny>=0 && nx>=0 && ny<H && nx<W){
            if(map[ny][nx]==WALL || map[ny][nx]==I_WALL){
                if(map[ny][nx]==WALL)map[ny][nx]=BLANK;
                return;
            }
            ny+=dy[direct];
            nx+=dx[direct];
        }


    }

    public static String printChar(int value, int direct){
        String s=".";
        switch (value) {
            case BLANK:
                s=".";
                break;
            case WALL:
                s="*";
                break;
            case I_WALL:
                s="#";
                break;
            case WATER:
                s="-";
                break;
            case TANK:
                if(direct==UP) s="^";
                if(direct==DOWN) s="v";
                if(direct==LEFT) s="<";
                if(direct==RIGHT) s=">";
                break;
        }
        return s;
    }

}
