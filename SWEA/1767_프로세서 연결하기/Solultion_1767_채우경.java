
import java.util.*;
import java.io.*;


public class Solution
{

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N;
    static int[][] arr;
    static List<int[]> cores;
    static int maxCore=0;
    static int minLen=Integer.MAX_VALUE;


    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int T= Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++) {
            maxCore = 0;
            minLen=Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            arr= new int[N][N];
            cores= new ArrayList<>();

            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    arr[i][j]=Integer.parseInt(st.nextToken());

                    if(arr[i][j]==1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue;

                        cores.add(new int[] {i,j});
                    }
                }
            }

            dfs(0,0,0);


            System.out.println("#"+t+" "+minLen);

        }

    }

    static void dfs(int idx, int coreCnt,int sum) {

        if(idx==cores.size()) {
            if(coreCnt>maxCore) {
                maxCore = coreCnt;
                minLen=sum;
            }else if(coreCnt==maxCore) {
                if(sum<minLen)
                    minLen=sum;
            }
            return;
        }


        int[] cur = cores.get(idx);
        int x = cur[0];
        int y = cur[1];


        for(int d=0;d<4;d++) {
            boolean flag=true;
            int nx=x;
            int ny=y;
            int cnt=0;
            while(true) {

                nx+=dx[d];
                ny+=dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) // 이떈 계산은함
                    break;

                if(arr[nx][ny]==1) {// 이땐 백트래킹 계산도 안함
                    flag=false;
                    break;
                }
                cnt++;

            }
            if(flag) {
                nx = x;
                ny = y;
                for(int i=0;i<cnt;i++) {
                    nx+=dx[d];
                    ny+=dy[d];
                    arr[nx][ny]=1;
                }

                dfs(idx+1,coreCnt+1,sum+cnt);
                nx = x;
                ny = y;
                for(int i=0;i<cnt;i++) {
                    nx+=dx[d];
                    ny+=dy[d];
                    arr[nx][ny]=0;
                }

            }

        }

        dfs(idx+1,coreCnt,sum);

    }



}