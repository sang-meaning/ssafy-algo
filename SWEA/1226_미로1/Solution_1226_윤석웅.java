import java.io.*;
import java.util.*;

public class Solution {
  static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
  static int result = 0;
  static void bfs(int[][] map, ArrayDeque<int[]> queue){ // 재귀 bfs, 아마도 while로도 가능
    if(queue.isEmpty() || result == 1){
      return;
    }
    int[] cursor = queue.poll();
    for (int i =0; i<4; i++){ // 0일땐 1로 바꾸고 offer, 3일땐 result= 1 break, 나머지일땐 무시
      if (cursor[0]+dx[i] < 0 || cursor[0]+dx[i] >= 16 || cursor[1]+dy[i] < 0 || cursor[1]+dy[i] >= 16) continue;
      if(map[cursor[0]+dx[i]][cursor[1]+dy[i]] == 0){
        map[cursor[0]+dx[i]][cursor[1]+dy[i]] = 1;
        queue.offer(new int[]{cursor[0]+dx[i],cursor[1]+dy[i]});
      }else if(map[cursor[0]+dx[i]][cursor[1]+dy[i]] == 3){result = 1;return;}
    }
    bfs(map, queue);
  }
  public static void main(String[] args) throws IOException {
    int[][] map;
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    int T = 10;
    for (int test_case=1; test_case<=T; test_case++) {
      int[]start = new int[2];
      map = new int[16][16];
      sb.append("#").append(br.readLine()).append(" ");
      ArrayDeque<int[]> que = new ArrayDeque<>();

      for(int i=0;i<16;i++){
        st = new StringTokenizer(br.readLine()); 
        char[] s = st.nextToken().toCharArray();
        for(int j=0;j<16;j++){
          if ((s[j]-'0') == 2){ //시작점 설정
            start[0]= i; start[1]=j;
          }
          map[i][j] = s[j]-'0'; //
        }
      }
      que.offer(new int[]{start[0],start[1]});

      bfs(map, que);
      sb.append(result).append('\n');
      result = 0;
    }
    System.out.print(sb);
  }
}
