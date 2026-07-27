package SWEA;
import java.io.*;
import java.util.*;

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
