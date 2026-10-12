import java.io.*;
import java.util.*;

/*
ex) tree: 4 2 3 1 2 1 1

최대값 - tree 해서 need 배열 생성 : 0 2 1 3 2 3 3

0이 아닌 것 중, 홀 4 짝 2

홀수만큼 (1,2) 순서쌍 무조건 필요

(1,2) * 4,  → 8일 가져가고 need 배열에서 -1 *4, -2* 4 실행

먼저 홀수에 대해 1씩 뺌 0 2 0 2 2 2 2

홀 0 짝 5됨

여기서 무조건 짝수만 남는데, 8만큼 감소 시킬 여력 따로 저장했음

따로 저장한 감소 시킬 여력보다 짝수의 합이 적으면 result-- 하고 출력, continue
(원래 -1, -2, -1, -2 수행하는건데, 맨 마지막 -1 하는 순간 끝나기 때문에 -2 하는 시행 제외)

짝수 총합 10인데 8빼면 2남음

결국

2 만 남고, 

(2 / 3) * 2 만큼 result 더하고

2%3 = 2 되고, 

이 값이 2이면 result 에 2, 1이면 result 에 1
*/

public class Solution_14510_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int T;
  public static void main(String[] args) throws IOException{
    T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      int N;
      N = Integer.parseInt(br.readLine());

      int[] tree = new int[N];
      st = new StringTokenizer(br.readLine());

      int tallest = 0;

      for (int i=0; i<N; i++) {
          tree[i] = Integer.parseInt(st.nextToken());
          if (tree[i] > tallest) tallest = tree[i];
      }

      // 가장 큰 높이 - tree[] 배열 새로 만들기 : 더 커야하는 양

      int odd = 0;
      
      int[] need = new int[N];
      for (int i=0; i<N; i++) {
          need[i] = tallest - tree[i];
          
          if (need[i] % 2 == 1) odd++;
      }

      int result = 0; // 소모 날짜

      int sumOfEven = 0;

      for (int i=0; i<N; i++) {
        if (need[i] %2 == 1) need[i]--;
      }

      for (int i=0; i<N; i++) {
        if (need[i]%2 == 0) sumOfEven+= need[i];
      }

      result += odd * 2;

      // 이제 짝수만 남음

      int savedTwo = odd * 2; // 저장한 2만큼 빼야할 것
      if (sumOfEven < savedTwo) {
        result--;
        System.out.println("#"+t+" "+result);
        continue;
      }

      sumOfEven -= savedTwo;

      result += (sumOfEven / 3) *2;
      int rest = sumOfEven % 3;

      if (rest == 2) result += 2;
      else if (rest == 1) result += 1;


      System.out.println("#"+t+" "+result);


    }

    
  }
  
  
}



