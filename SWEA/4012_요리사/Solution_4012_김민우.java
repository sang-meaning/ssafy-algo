import java.util.*;
import java.io.*;

public class Solution_4012_김민우 {
  static int[][] ingred;
  static int T, N, min;
  static List<Integer> listA;
  static List<Integer> listB;
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      ingred = new int[N][N];

      // A, B 음식에 사용할 재료 리스트 
      listA = new ArrayList<>();
      listB = new ArrayList<>();

      for(int i = 0; i < N; i++){
        //처음 초기화할 때는 B음식(=listB)쪽에 모든 재료를 할당
        //이후 A음식(=listA)에 재료들을 할당하면서, 이를 B음식(=listB)으로부터 제거할 예정
        listB.add(i);
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
    //A음식이 사용하는 재료 개수(=cnt)가 N/2일 때
    //각 음식의 시너지 및 차이 계산 => 이후 최소값 갱신
    if(cnt == N/2){
      System.out.println(listA);
      System.out.println(listB);
      int foodA = cook(listA);
      int foodB = cook(listB);
      int diff = Math.abs(foodA-foodB);
      min = (diff < min)?diff:min;
      return;
    }

    else if(idx == N-1 && cnt != N/2){
      return;
    }

    //0~N-1번째 재료까지, A음식(=listA)이 해당 재료를 사용할 때와 사용하지 않을 때를 나눠 dfs 재귀호출

    //1.
    //A음식(=listA)이 idx+1번째 재료를 사용한다 했을 때
    //B음식(=listB)은 해당 재료를 사용하지 못하므로 remove를 통해 제거
    listA.add(idx+1);
    listB.remove(Integer.valueOf(idx+1));
    dfs(idx+1, cnt+1);
    
    //2.
    //A음식(=listA)이 idx+!번째 재료를 사용하지 않는다 했을 때,
    //앞서 A음식(=listA)에 포함됐었던 해당 재료를 다시 제거한 뒤, B음식(=listB)에 다시 할당
    listA.remove(Integer.valueOf(idx+1));
    listB.add(idx+1);
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
