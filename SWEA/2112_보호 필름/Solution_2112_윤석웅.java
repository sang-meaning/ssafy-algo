import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution{
  static int T, limit, row, col, ans = 0;
  static int[][] map;
  static int[] chem;
  public static void main(String[] args) throws IOException{
    StringBuilder sb= new StringBuilder();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());

    T= Integer.parseInt(st.nextToken());
    for(int tc = 1; tc<=T; tc++){
      st = new StringTokenizer(in.readLine());
      row = Integer.parseInt(st.nextToken());
      col = Integer.parseInt(st.nextToken());
      limit = Integer.parseInt(st.nextToken());
      chem = new int[row];
      ans = row;
      Arrays.fill(chem, -1);

      sb.append("#").append(tc).append(" ");
      map = new int[row][col];

      for (int i =0; i<row; i++){
        st = new StringTokenizer(in.readLine());
        for (int j = 0; j<col; j++){
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      dfs(0, 0);
      sb.append(ans).append('\n');
      ans = 0;
    }
    System.out.print(sb);
  }

  static void dfs(int inject, int idx){
    if (ans != -1 && ans <= inject){
      return;
    }
    if (inspect() == true){
      if (ans > inject) ans = inject;
      return;
    }
    if (idx >= row) return;
    dfs(inject, idx+1);
    chem[idx] = 0;
    dfs(inject+1, idx+1);
    chem[idx] = 1;
    dfs(inject+1, idx+1);
    chem[idx] = -1;
  }

  static boolean inspect(){
    int idx =0, cnt = 0;
    boolean answer = true;
    while (idx < col){
      for (int i =0; i<row-1; i++){
        int tmp1, tmp2;
        if (chem[i] != -1) {tmp1 = chem[i];}
        else {tmp1 = map[i][idx];}

        if (chem[i+1] != -1) {tmp2 = chem[i+1];}
        else {tmp2 = map[i+1][idx];}

        if (tmp1 == tmp2){
          cnt++;
        }else{
          cnt = 0;
        }

        if (limit -1 == cnt){
          break;
        }
      }
      if (limit -1 > cnt){
        answer = false;
        break;
      }
      cnt = 0;
      idx++;
    }
    return answer;
  }

}