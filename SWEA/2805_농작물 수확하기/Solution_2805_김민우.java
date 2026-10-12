import java.util.*;
import java.io.*;

class Solution
{
	  static BufferedReader br;
  static StringTokenizer st;

  static int T, N;
  //static int[][] farm;

  public static void main(String[] args) throws IOException{
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());

      //farm = new int[N][N];
      
      int midr = N/2, midc = N/2, dist = N/2+1;
      int sum = 0;
      for(int i = 0; i < N; i++){
        st = new StringTokenizer(br.readLine());
        String line = st.nextToken();
        for(int j = 0; j < N; j++){
          if(Math.abs(i-midr) + Math.abs(j-midc) < dist){
            int val = line.charAt(j) - '0';
            sum+= val;
          }
        }
      }

      System.out.printf("#%d %d\n", test_case, sum);
    }
  }
}