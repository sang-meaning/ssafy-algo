import java.io.*;
import java.util.*;

class Solution4012{
  static int choice_synerge(boolean[] permut, int[][] map, int index, int count){
    int result = Integer.MAX_VALUE;
    for (int i = index+1; i<map.length; i++){
      count += 1;
      int temp = Integer.MAX_VALUE;
      permut[i] = true;

      if(count < map.length/2){
        temp = choice_synerge(permut, map, i, count);
      }else if(count >= map.length/2){
        temp = calc_synerge(permut,map);
      }

      if (result > temp){
          result = temp;
      }

      permut[i] = false;
      count -=1;
    }
    return result;
  }

  static int calc_synerge(boolean[] permut, int[][] map){
    int syn_a = 0;
    int syn_b = 0;
    for (int i = 0; i< map.length; i++){
      for (int j = i+1; j< map.length; j++){
        if (permut[i] == permut[j]){
          if (permut[i] == true){
            syn_a += map[i][j];
            syn_a += map[j][i];
          }else{
            syn_b += map[i][j];
            syn_b += map[j][i];
          }
        }
      }
    }
    int result = Math.abs(syn_a-syn_b);
    return result;
  }

  public static void main(String[] args) throws IOException {

    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());
    int T = Integer.parseInt(st.nextToken());


    for (int te_case= 1; te_case <=T; te_case++){
      sb.append("#").append(te_case).append(" ");

      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int[][] synerge = new int[N][N];

      for (int[] row : synerge){
        st = new StringTokenizer(br.readLine());
        for (int i = 0;i<N;i++){
          row[i] = Integer.parseInt(st.nextToken());
        }
      }

      boolean[] permut = new boolean[N];
      permut[0] = true;

      int temp = choice_synerge(permut, synerge, 0, 1);


      sb.append(temp).append('\n');
    }
    System.out.print(sb);
  }
}