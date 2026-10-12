import java.util.*;
import java.io.*;

public class Solution_5644_정영훈 {
    /*문제
    1. 10x10격자 안에서 충전기의 가용범위 안에서 충전 가능
    2. 같은 충전범위에 다른 사용자가 있을시 충전량 절반으로 받음
    3. 첫번째부터 충전받을수 있음
    End. 사용자들의 최대 총 충전량 계산

    */

    /* 풀이
    1. 여러 사용자가 같은 BC충전기에 들어왔을때의 분기설정
    2. 한 사용자가 근처 BC충전기 탐색
        2-1. 근처에 BC충전기가 존재하고 접속 가능 -> 접속
        2-2. 근처에 BC충전기가 존재하지만 이미 다른 사용자가 접속중 -> visted배열의 해당 BC인덱스에 넣어놓고 마지막 BC까지 탐색이후에도 빈 BC가 없다면 flag가 있는 BC충전기중 가장 높은 충전기로 할당
    3. 어차피 사용자가 두명이니까 visited배열 두개로 관리가능할것같음
        3-1 현재 가능한 visited의 배열 두개로 완탐?을 함, 사용자 1,2가 둘 다 a일때, 사용자 1 a 사용자 2가 b일때 등?
    
    
    */
    //제자리, 상, 우, 하, 좌
    final static int[] dy={0,-1,0,1,0};
    final static int[] dx={0,0,1,0,-1};
    static List<int[]> bcList=new ArrayList<>();
    static int[] aUsrDirect;
    static int[] bUsrDirect;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){    
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            bcList=new ArrayList<>();
            int M=Integer.parseInt(st.nextToken());
            int A=Integer.parseInt(st.nextToken());

            aUsrDirect=new int[M+1];
            bUsrDirect=new int[M+1];
            aUsrDirect[0]=0;
            bUsrDirect[0]=0;
            st=new StringTokenizer(br.readLine()," ");
            for (int i = 1; i <= M; i++) {
                aUsrDirect[i]=Integer.parseInt(st.nextToken());
                
            }

            st=new StringTokenizer(br.readLine()," ");
            for (int i = 1; i <= M; i++) {
                bUsrDirect[i]=Integer.parseInt(st.nextToken());
                
            }

            for (int i = 0; i < A; i++) {
                st=new StringTokenizer(br.readLine()," ");
                int x=Integer.parseInt(st.nextToken())-1;
                int y=Integer.parseInt(st.nextToken())-1;
                int c=Integer.parseInt(st.nextToken());
                int p=Integer.parseInt(st.nextToken());
                
                bcList.add(new int[]{y,x,c,p});
            }

            int[] aUsrPos={0,0};
            int[] bUsrPos={9,9};
            boolean[] aUsrVisited=new boolean[A];
            boolean[] bUsrVisited=new boolean[A];
            int sumCharge=0;
            //이동시작
            for (int t = 0; t <= M; t++) {
                aUsrPos=movePos(aUsrDirect[t], aUsrPos);
                bUsrPos=movePos(bUsrDirect[t], bUsrPos);
                check(aUsrPos, aUsrVisited);
                check(bUsrPos, bUsrVisited);
                int tempCharge=bf(aUsrVisited, bUsrVisited, t);
                sumCharge+=tempCharge;
                
            }

            sb.append("#"+test_case+" "+ sumCharge+"\n");

        }
        System.out.println(sb.toString());
        
    }
    public static void check(int[] usrPos, boolean[] usrVisited){
        int y=usrPos[0];
        int x=usrPos[1];
        for(int i=0; i<bcList.size(); i++){
            int[] bc=bcList.get(i);
            int bc_y=bc[0];
            int bc_x=bc[1];
            int bc_distance=bc[2];
            int calDistacne=calDistance(y, bc_y, x, bc_x);
            if(calDistacne<=bc_distance){
                usrVisited[i]=true;
            }else{
                usrVisited[i]=false;
            }
        }

    }
    public static int bf(boolean[] aVisited, boolean[] bVisited, int t){
        int maxCharge=0;
        for (int a = 0; a < aVisited.length; a++) {
            int sum=0;
            for (int b = 0; b < bVisited.length; b++) {
                if(aVisited[a] && bVisited[b] && a==b){
                    sum=bcList.get(a)[3];
                }else if(aVisited[a] && bVisited[b] && a!=b){
                    sum=bcList.get(a)[3]+bcList.get(b)[3];
                }else if(aVisited[a]){
                    sum=bcList.get(a)[3];
                }else if(bVisited[b]){
                    sum=bcList.get(b)[3];
                }
                
                maxCharge=Math.max(maxCharge, sum);
            
            }
            
            
        }
        return maxCharge;

    }
    public static int calDistance(int y1, int y2, int x1, int x2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);

    }
    public static int[] movePos(int direct, int[] usrPos){
        usrPos[0]+=dy[direct];
        usrPos[1]+=dx[direct];
        return usrPos;
    }

   
}