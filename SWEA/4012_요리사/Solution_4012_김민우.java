import java.util.*;
import java.io.*;

public class Solution_4012_김민우 {
  static int[][] ingred;
  static int T, N, min;
  static List<Integer> listA;
  static List<Integer> listAll;
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      ingred = new int[N][N];

      listA = new ArrayList<>();
      listAll = new ArrayList<>();

      for(int i = 0; i < N; i++){
        listAll.add(i);
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < N; j++){
          ingred[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      min = Integer.MAX_VALUE;

      dfs(-1, 0);
      System.out.printf("#%d %d", test_case, min);
    }//test_case 끝
  }//main 끝

  public static void dfs(int idx, int cnt){
    if(cnt == N/2){
      System.out.println(listA);
      System.out.println(listAll);
      int foodA = cook(listA);
      int foodB = cook(listAll);
      int diff = Math.abs(foodA-foodB);
      min = (diff < min)?diff:min;
      return;
    }

    else if(idx == N-1 && cnt != N/2){
      return;
    }
    //포함 했을 때
    listA.add(idx+1);
    listAll.remove(Integer.valueOf(idx+1));
    dfs(idx+1, cnt+1);
    //포함 안했을 때
    listA.remove(Integer.valueOf(idx+1));
    listAll.add(idx+1);
    dfs(idx+1, cnt);
  }

  public static int cook(List<Integer> list){
    int result = 0;
    
    for(int i = 0; i < N/2; i++){
      int idx1 = list.get(i);
      for(int j = 0; j < N/2; j++){
        int idx2 = list.get(j);
        if(idx2 == idx1)
          continue;
        
        result += ingred[idx1][idx2];
      }
    }
    return result;
  }
}
