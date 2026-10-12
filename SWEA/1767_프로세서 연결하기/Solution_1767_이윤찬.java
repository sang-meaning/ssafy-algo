package swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_이윤찬 {
    //맵
    static int[][]map;
    //최대연결컨넥트, 최소전선수
    static int maxConnect, minLine;
    static int N;
    //코어
    static List<int[] > core;
    //방향델타변수
    static int[] dx ={-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 1; t<=T; t++){
            core = new ArrayList<>();

            N = Integer.parseInt(br.readLine());

            map= new int[N][N];

            maxConnect =0;

            minLine = Integer.MAX_VALUE;

            for(int y=0; y<N ; y++){
                st = new StringTokenizer(br.readLine());
                for(int x =0; x<N; x++){
                    int number = Integer.parseInt(st.nextToken()); // 0 or 1
                    map[y][x] = number; // 0 or 1
                    // 가장자리 core dfs 제외
                    if(y!=0 && x!=0 & x!=N-1 && y!=N-1 && number==1){ // N =5
                        core.add(new int[]{x,y});   // List  [    [1, 1]  [2 , 2]  [X, Y ]  [X1,Y2]   ]
                    }
                }
            }
            // dfs -> 관리해야할 거 몇 번째 코어인가, 연결된 코어의 수 , 전선의 길이 --> 목표
            dfs(0,0,0);

            System.out.println("#"+t+" "+  minLine);

        }


    }
    public static void dfs(int idx, int connect, int lenLine){
        //가지치기 조건 0.  남아있는 core를 연결해도 maxCore보다 작으면 더 이상 dfs 돌 필요없다.
        if(connect+(core.size()-idx)<maxConnect){ // CORE.SZIE()  - IDX  = REMAIN
            return;
        }

        // 조건1. 모든 코어를 다 돌았을 때 끝나야함
        if(idx == core.size()){
            // 조건2. 최대한 많은 core를 전원에 연결해야한다. 그때의 전선의수도 넣어야함.
            if(connect > maxConnect){
                maxConnect= connect;
                minLine = lenLine;
            }
            // 조건3.연결된 core수가 같을때 전선의길이 최소를 선정
            else if(connect == maxConnect){

                minLine = Math.min(minLine,lenLine);

            }
            return;
        }

        //1. 코어 idx번째 위치 좌표 (x,y) //  IDX0 =[ X, Y ]
        int[] position = core.get(idx);

        int  coreX = position[0];
        int  coreY = position[1];


        // 코어를 기준으로 4방향 순회

        for(int dir = 0 ; dir < 4; dir++){

            // 전선을 깔 수 있는지 확인해야함.. 전선을 깔면 백트래킹이 들어가야함..
            if(!iscanConnect(coreX,coreY,dir)){
                continue;
            }
            // 깔수있으니 전선"2"를 맵에다가 깔아본다 그리고 전선의길이를 리턴받는다
            int lineLen = setWire(coreX,coreY,dir,2);

            // 다음 코어의 시작을 위해 dfs 확인
            dfs(idx+1,connect+1,lineLen+lenLine);

            // 백트래킹 해야함 전선 제거
            setWire(coreX,coreY, dir,0);

        }

        // connect 연결 안되구 시작
        dfs(idx + 1, connect, lenLine);
    }
    //전선을 깔 수 있는지 확인해주는 함수
    static boolean iscanConnect(int x, int y, int dir){

        int nx = x +dx[dir];
        int ny = y + dy[dir];
        while(ny>=0 && nx>=0 && ny<N && nx<N) {
            if(map[ny][nx]!=0){  // 1 코어 2 전선
                return false;
            }
            nx+=dx[dir];
            ny+=dy[dir];
        }
        return true;
    }

    //전선을 깔아주는 함수
    static int setWire(int x, int y, int dir , int value){
        int nx =x +dx[dir];
        int ny = y +dy[dir];

        int len = 0;

        while(ny>=0 && nx>=0 && ny<N && nx<N) {
            map[ny][nx] = value; // 2    [  0  1  0 0  0]  -->
            len++;

            ny += dy[dir];
            nx += dx[dir];
        }
        return len;
    }
}