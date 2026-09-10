import java.util.*;
import java.io.*;


public class Solution_4796_강상민 {
  //static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T,N;
  static int[] arr;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    T = sc.nextInt();

    for (int t=1; t<=T; t++) {
      N = sc.nextInt();
      arr = new int[N];

      //st = new StringTokenizer(br.readLine());
      

      for (int i=0; i<N; i++) {
        arr[i] = sc.nextInt();
      }

      // 좌우보다 높은 것을 봉우리
      // 좌우보다 낮은 것을 골짜기
      
      // 봉우리에 대해 순차적으로 접근
      // 봉우리의 좌, 우의 골짜기 찾기

      int[] p = new int[N];
      ArrayList<Integer> ap = new ArrayList<>();

      for (int i=1; i<N-1; i++) {
        if (arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
          p[i] = 1;
          ap.add(i);
        }
        else if (arr[i] < arr[i-1] && arr[i] < arr[i+1]) p[i] = -1;
      }

      // 봉우리 1 골짜기 -1

      // 첫 봉우리부터 순차적으로 검사
      // 좌측 골짜기와 우측 골짜기의 index

      int sum = 0;
      
      if (ap.size() == 0) {
        sb.append("#"+t+" "+0).append("\n");
        continue;
      }

      for (int i=0; i<ap.size(); i++) {
        int p_idx = ap.get(i); // 봉우리 index
        
        // 왼쪽 검사
        int left = p_idx-1;
        int l_idx = 0;
        while(true) {
          if (left < 0) break;

          if (p[left] == -1) {
            l_idx = left;
            break;
          }

          left--;
        }

        // 오른쪽 검사
        int right = p_idx+1;
        int r_idx = N-1;
        while(true) {
          if (right == N-1) break;

          if (p[right] == -1) {
            r_idx = right;
            break;
          }

          right++;
        }

        sum += (p_idx - l_idx) * (r_idx - p_idx);

      }

      sb.append("#"+t+" "+sum).append("\n");


    }

    System.out.println(sb);
  }
}
