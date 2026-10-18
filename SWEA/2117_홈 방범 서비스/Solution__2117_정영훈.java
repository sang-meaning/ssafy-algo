import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution__2117_정영훈 {
    /* 문제
    1. n = map의 크기 / m = 방범구역에 해당하는 각 집들이 내는 비용
    2. f(k) = 보안구역 범위/ k는 1씩증가
    3. f(k) = k*k+(k-1)*(k-1)
    4. 보안구역내에 포함되는 집들은 m만큼의 운영비용 내줌
    End. 운영비용이 마이너스가 되지 않는 범위에서 최대한 많은 집들을 포함
     */

    /* 풀이
    1. 집들 기준 운영비용 탐색으로할 때 공백부분이 기준일때가 더 비용이 낮을수있음
    2. 이중for문으로 완탐
    3. calValidCost에서 음수값나올시 false반환후 해당 인덱스 탐색 종료
    4. 마름모 그리는걸 k값 증가마다 새로 그리기보다 해당 index가 visited면 break;

     */
    static int countHouse;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int test_case=1; test_case<=T; test_case++){
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int[][] map=new int[n][n];
            boolean[][] visited=new boolean[n][n];
            
            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < n; j++) {
                    map[i][j]=Integer.parseInt(st.nextToken());
                    
                }
                
            }

            int maxCount=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    countHouse=0;
                    int count=checkRhomb(false, n, m, i, j, map, visited);
                    maxCount=Math.max(maxCount, count);
                }
                
            }
            sb.append("#"+test_case+" "+maxCount+"\n");



        }
        System.out.println(sb.toString());
    }

    public static  int checkRhomb(boolean isVisited, int n, int m, int y, int x, int[][] map, boolean[][] visited){
        int k=1;
        int maxCount=0;
        int initX=x-(k-1);
        boolean visit=true;
        if(!isVisited) visit=false;
        
        boolean isEnd=false;

        while(!isEnd){
            //마름모 하위 채우기 및 집 갯수 체크
            int maxY=y+k;
            int minY=y-(k-1);
            int maxX=initX+(k*2-1);
            for(int currentY=y; currentY<maxY; currentY++){
                int i=0;
                for(int currentX=initX; currentX<maxX-i++; currentX++){
                    if(currentY<0 || currentX<0 || currentY>=n || currentX>=n)
                        break;
                    if(visited[currentY][currentX])
                        break;
                    visited[currentY][currentX]=visit;
                    if(map[currentY][currentX]==1)countHouse++;
                }
            }
            //마름모 상위 채우기 및 집 갯수 체크
            for(int currentY=y; currentY>minY; currentY--){
                int i=0;
                for(int currentX=initX; currentX<maxX-i++; currentX++){
                    if(currentY<0 || currentX<0 || currentY>=n || currentX>=n)
                        break;
                    if(visited[currentY][currentX])
                        break;
                    visited[currentY][currentX]=visit;
                    if(map[currentY][currentX]==1)countHouse++;
                }
            }
            int calValidCostt=calValidCost(k, m);
            if(calValidCost(k, m)<0)
                isEnd=true;
            else{
                maxCount=countHouse;
                k++;
            }
            System.out.println("cal:"+calValidCostt);
            System.out.println("countHouse: "+countHouse);

        }
        return maxCount;


       
            
    }
    
    public static int calValidCost(int k, int m){
        return countHouse*m-k*k+(k-1)*(k-1);
    }



}


