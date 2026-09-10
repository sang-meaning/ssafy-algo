import java.util.*;
import java.io.*;

public class Solution_2805_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T,N;
  static int[][] board;


  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());

    for (int t=1; t<=T; t++) {

      
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];

      for (int i=0; i<N; i++) {
        String s = br.readLine();

        for (int j=0; j<N; j++) {
          board[i][j] = s.charAt(j) - '0';
        }
      }

      int[][] dia = new int[N][N];
      int mid = N/2;

      // mid, mid 좌표로부터 길이가 mid인 곳만 1로
      for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
          int dist = Math.abs(i-mid) + Math.abs(j-mid);

          if (dist <= mid) dia[i][j] = 1;
        }
      }

      int sum = 0;

      for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
          if (dia[i][j] == 1) sum += board[i][j];
          
        }
      }

      sb.append("#"+t+" "+sum).append("\n");


    }

    System.out.println(sb);
  }
  
}
