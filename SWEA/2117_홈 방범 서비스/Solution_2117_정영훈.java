import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_2117_정영훈 {
    /* 문제
    1. n = map의 크기 / m = 방범구역에 해당하는 각 집들이 내는 비용
    2. f(k) = 보안구역 범위 =운영비용 / k는 1씩증가
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
    static List<int[]> houseList=new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int test_case=1; test_case<=T; test_case++){
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int[][] map=new int[n][n];
            houseList=new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st=new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < n; j++) {
                    int node=Integer.parseInt(st.nextToken());
                    if(node==1)
                      houseList.add(new int[]{i,j});
                    map[i][j]=node;
                    
                }
                
            }
            int maxCount=0;
            for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                int y=i;
                int x=j;
                int count=findHouse(n,m,y,x,map);
                maxCount=Math.max(maxCount, count);
              }
            }
            // for (int[] pos : houseList) {
            //   int y=pos[0];
            //   int x=pos[1];
            //   int count=findHouse(n,m,y,x,map);
            //   maxCount=Math.max(maxCount, count);
            // }
            sb.append("#"+test_case+" "+maxCount+"\n");
          }
          System.out.println(sb.toString());
        }

        public static int findHouse(int n, int m, int posY, int posX, int[][] map){
          int maxCountHouse=0;
          //하위
          for(int k=1; k<=n*2; k++){
            int countHouse=0;
            
            for(int y=posY; y<posY+k; y++){
              int diff=(y-posY);
              for(int x=(posX-k)+1+diff; x<(posX+k)-diff; x++){
                if(y<0 || x<0 || y>=n || x>=n) continue;
                if(map[y][x]==1)countHouse++;
            //     if(posY==3 && posX==3){
            //       System.out.println("k, y, x: "+k+" "+y+" "+x);
            // }
              }
            }
            //상위
            for(int y=posY-1; y>posY-k; y--){
              int diff=posY-1-y;
              for(int x=(posX-k)+2+diff; x<(posX+k)-diff-1; x++){
                if(y<0 || x<0 || y>=n || x>=n) continue;
                if(map[y][x]==1)countHouse++;
                // if(posY==3 && posX==3){
                //   System.out.println("k, y, x: "+k+" "+y+" "+x);
              //}
            }

            
          }
          int cost=checkCost(k,m,countHouse);
            if(cost>=0)
              maxCountHouse=countHouse;

        }
          return maxCountHouse;
          
        }

        public void checkIndex(int y,int x){
          
        }
        public static int checkCost(int K, int m, int countHouse){
          int cost=countHouse*m-(K * K + (K - 1) * (K - 1));
          return  cost;
        }
}


