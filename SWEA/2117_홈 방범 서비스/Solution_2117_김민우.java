import java.io.*;
import java.util.*;

public class Solution_2117_김민우 {
  static BufferedReader br;
  static StringTokenizer st;

  //N = 배열 크기, M = 집 당 지불 비용
  static int T, N, M;
  static int[][] map;
  static List<int[]> home;
  public static void main(String[] args) throws IOException{
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    //운영비용 : K * K + (K - 1) * (K - 1)
    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      int maxK = 2*N;
      int ans = 0;
      map = new int[N][N];
      home = new ArrayList<>();

      for(int i = 0; i < N; i++){
        st = new StringTokenizer(br.readLine());
        for(int j = 0 ; j < N; j++){
          map[i][j] = Integer.parseInt(st.nextToken());
          if(map[i][j] == 1)
            home.add(new int[]{i, j});
        }
      }

      for(int r = 0; r < N; r++){
        for (int c = 0; c < N; c++){
          int tmp = calc_pay(maxK, r, c);
          ans = (tmp > ans)? tmp:ans;
        }
      }

      System.out.printf("#%d %d\n", test_case, ans);
    }
  }

  public static int calc_pay(int maxK, int r, int c){
    int cnt = 0;
    
    for(int i = 1; i <= maxK; i++){
      int loss = (i*i) + ((i-1)*(i-1));
      int home_pay = 0;
      
      int tmp = 0;
      for(int[] loc : home){
        int dist = Math.abs(r-loc[0]) + Math.abs(c-loc[1]);
        if(dist < i){
          home_pay += M;
          tmp++;
        }
      }
      int total = home_pay - loss;
      if(total >= 0){
        cnt = (tmp>cnt)?tmp:cnt;
      }
    }
    
    return cnt;
  }

}
