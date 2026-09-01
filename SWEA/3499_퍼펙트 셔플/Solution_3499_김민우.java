import java.util.*;
import java.io.*;

class Solution
{
static BufferedReader br;
  static StringTokenizer st;
  
  static int T, N;
  static Queue<String> que1, que2;
  public static void main(String[] args) throws IOException{
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());

      que1 = new LinkedList<>(); que2 = new LinkedList<>();
      
      st = new StringTokenizer(br.readLine());

      int div = (N%2 == 0)?(N/2):(N/2+1);
      for(int i = 0; i < div; i++){
        que1.offer(st.nextToken());
      }
      for(int i = 0; i < (N-div); i++){
        que2.offer(st.nextToken());
      }

      System.out.printf("#%d ", test_case);
      while(!que1.isEmpty() || !que2.isEmpty()){
        if(!que1.isEmpty())
          System.out.printf("%s ", que1.poll());
        if(!que2.isEmpty())
          System.out.printf("%s ", que2.poll());
      }
      System.out.println();
    }
  }
}