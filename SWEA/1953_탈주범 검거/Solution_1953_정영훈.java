import java.util.*;
import java.io.*;

class Solution_1953_정영훈
{
     //문제
     //1. 터널끼리 연결된곳은 이동가능
     //2. 시간당 1의 거리를 움직일 수 있음
     //3. 터널 구조물 7종류
     //End. 경과된 시간이 주어질때 탈주범이 위치할 수 있는 장소의 개수를 계산

     //풀이
     //1. bfs, dfs 둘다 사용가능
     //2. 좌표값 저장 객체 생성, equals는 좌표 x, y 위치로
     //3. Set으로 저장하여 동일좌표 하나만 저장
     //4. 이동 dx dy는 각각의 인덱스에 배열을 저장해서 갈 수 있는곳 반환
     static int[][] map;
     static boolean[][] visited;
     static Set<Node> set;
     static List<int[][]> dList=new ArrayList<>();
     static List<int[]> nextPipe=new ArrayList<>();
          
	public static void main(String args[]) throws Exception
	{
          //default: 우/하/좌/상
          dList.add(new int[][]{{0,1},{1,0},{0,-1},{-1,0}});
          dList.add(new int[][]{{0,0},{1,0},{0,0},{-1,0}});
          dList.add(new int[][]{{0,1},{0,0},{0,-1},{0,0}});
          dList.add(new int[][]{{0,1},{0,0},{0,0},{-1,0}});
          dList.add(new int[][]{{0,1},{1,0},{0,0},{0,0}});
          dList.add(new int[][]{{0,0},{1,0},{0,-1},{0,0}});
          dList.add(new int[][]{{0,0},{0,0},{0,-1},{-1,0}});
          nextPipe.add(new int[]{0,2,5,6});
          nextPipe.add(new int[]{0,1,3,6});
          nextPipe.add(new int[]{0,2,3,4});
          nextPipe.add(new int[]{0,1,4,5});
          StringBuilder sb=new StringBuilder();
          BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
          int T;
          T=Integer.parseInt(br.readLine());
          for(int test_case = 1; test_case <= T; test_case++)
          {
               StringTokenizer st=new StringTokenizer(br.readLine()," ");
               int N=Integer.parseInt(st.nextToken());
               int M=Integer.parseInt(st.nextToken());
               map=new int[N][M];
               visited=new boolean[N][M];
               set=new HashSet<>();
               
               int R=Integer.parseInt(st.nextToken());
               int C=Integer.parseInt(st.nextToken());
               
               int L=Integer.parseInt(st.nextToken())-1;
               for (int i = 0; i < N; i++) {
                    st=new StringTokenizer(br.readLine()," ");
                    for (int j = 0; j < M; j++) {
                         map[i][j]=Integer.parseInt(st.nextToken());
                         
                    }
                    
               }
               visited[R][C]=true;
               dfs(0,L,R,C,N,M);
               sb.append("#"+test_case+" "+set.size()+"\n");
               



          }
          System.out.println(sb.toString());
          
        
     }    
     public static void dfs(int depth, int time, int y, int x, int maxY, int maxX){
          set.add(new Node(y, x));
          if(depth==time){
               return;
          }
          int[][] delta=dList.get(map[y][x]-1);
          for(int i=0; i<4; i++){
               int ny=y+delta[i][0];
               int nx=x+delta[i][1];
               boolean isValid=false;

               if(ny<0 || nx<0 || ny>=maxY || nx>=maxX)continue;
               if(visited[ny][nx] ||map[ny][nx]==0) continue;
               for(int j=0; j<4; j++){
                    if(nextPipe.get(i)[j]==(map[ny][nx]-1)){
                         isValid=true;
                    }
                         
               }
               if(!isValid) continue;
               visited[ny][nx]=true;
               dfs(depth+1, time, ny, nx, maxY, maxX);
               visited[ny][nx]=false;
          }


     } 

     public static class Node{
          int y;
          int x;
          Node(int y, int x){
               this.y=y;
               this.x=x;
          }

          @Override
          public boolean equals(Object o){
               Node n=(Node)o;
               return this.y==n.y && this.x==n.x;
          }

          @Override
          public int hashCode(){
               return Objects.hash(y,x); 
          }


     }
   

    


    


}