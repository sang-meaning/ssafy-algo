import java.util.*;
import java.io.*;

class Solution_1767_정영훈
{
     /* 문제
     1. NxN크기
     2. 코어를 상하좌우 직선인 전선을 연결해서 가장자리에 연결
     3. 가장자리에 붙어있는 코어는 이미 연결됨
     End. 전선의 합이 최소가 되는부분
     */

     /* 풀이
     1. 각 코어의 위치를 list에 저장
     2. 가장자리 코어는 이미 연결된 것이므로 그리드에 길을 막기위한 표시만 해둠
     3. 백트래킹을 이용할것인데 각 코어가 연결될때의 vistied를 그렸다 지우는 함수 생성
     4. 전역변수 maxCount에 업데이트
     5. answer2는 코어를 끝까지 사용하지 못했을때의 최대값
      */
     final static int CORE=1, LINE=2, BLANK=0;
     static int[][] map;
     static int[] dy=new int[]{0,1,0,-1};
     static int[] dx=new int[]{1,0,-1,0};
     static int answer, answer2,  maxCoreCount;
     static boolean isEnd;
     static int lineCount=0;
//  2 0/2 4
     static List<Node> list;
	public static void main(String args[]) throws Exception{
          BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
          StringBuilder sb=new StringBuilder();
          int T=Integer.parseInt(br.readLine());
          for (int test_case = 1; test_case <= T; test_case++) {
               int n=Integer.parseInt(br.readLine());
               map=new int[n][n];
               list=new ArrayList<>();
               answer2=0;
               isEnd=false;
               answer=Integer.MAX_VALUE;
               maxCoreCount=0;
               for (int i = 0; i < n; i++) {
                    StringTokenizer st=new StringTokenizer(br.readLine()," ");
                    for (int j = 0; j < n; j++) {
                         int node=Integer.parseInt(st.nextToken());
                         if(node==CORE){
                              if(i!=0 && j!=0 && i!=n-1 && j!=n-1){
                                   list.add(new Node(i, j));
                              }
                              
                         }
                         map[i][j]=node;
                         
                    }
                    
               }                  
               if(list.size()==0)
                    answer=0;
               bt(0,n,0,0);

               if(answer==Integer.MAX_VALUE)
                    answer=answer2;
               sb.append("#"+test_case+" "+answer+"\n");
          }
          System.out.println(sb.toString());
         
     



        
     }

     public static boolean visitedCheck(int y, int x, int n, int direct){
          int ny=y+dy[direct];
          int nx=x+dx[direct];
          while(ny>=0 && nx>=0 && ny<n && nx<n){
               lineCount++;
               if(map[ny][nx]==LINE || map[ny][nx]==CORE)
                    return true;
               ny+=dy[direct];
               nx+=dx[direct];
          }
          return false;
     }
     public static void vistedLine(boolean isTrue, int direct, int y, int x, int n){
          int fill=BLANK;
          if(isTrue)
               fill=LINE;
          int ny=y+dy[direct];
          int nx=x+dx[direct];
          while(ny>=0 && nx>=0 && ny<n && nx<n){
               map[ny][nx]=fill;
               ny+=dy[direct];
               nx+=dx[direct];
          }

     }
     public static void bt(int depth, int n, int sum, int start){
          if(depth>maxCoreCount){
               answer2=sum;
               maxCoreCount=depth;
          }else if(depth==maxCoreCount){
               answer2=Math.min(answer2, sum);
          }
        
          if(depth==list.size()){
               isEnd=true;
               answer=Math.min(answer, sum);
               return;
          }
          for(int i=start; i<list.size(); i++){
               Node node=list.get(i);
               int y=node.y;
               int x=node.x;
               for(int j=0; j<4; j++){
                    lineCount=0;
                    if(visitedCheck(y, x, n, j)) continue;
                    vistedLine(true, j,y,x,n);
                    bt(depth+1, n, sum+lineCount, i+1);
                    vistedLine(false, j,y,x,n);

               }
          }
     }
     
     static class Node{
          int y;
          int x;
          Node(int y, int x){
               this.y=y;
               this.x=x;
          }
     }

   

    


    


}