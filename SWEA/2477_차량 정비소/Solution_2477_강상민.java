import java.util.*;
import java.io.*;

public class Solution {
  static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
  static StringTokenizer st;
  static StringBuilder sb = new StringBuilder();
  static int T, N, M, K, A, B;
  static int[] a, b, arrive;
  static int[] recDesk, repDesk; // 고객별로 이용한 접수 / 정비 창구 번호


  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine().trim());

    for (int t=1; t<=T; t++) {

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());

      a = new int[N+1];
      st = new StringTokenizer(br.readLine());
      for (int i=1; i<=N; i++) {
        a[i] = Integer.parseInt(st.nextToken());
      }

      b = new int[M+1];
      st = new StringTokenizer(br.readLine());
      for (int j=1; j<=M; j++) {
        b[j] = Integer.parseInt(st.nextToken());
      }

      arrive = new int[K+1];
      st = new StringTokenizer(br.readLine());
      for (int k=1; k<=K; k++) {
        arrive[k] = Integer.parseInt(st.nextToken());
      }

      recDesk = new int[K+1];
      repDesk = new int[K+1];

      int[] recWho = new int[N+1]; // 접수 창구에 있는 고객 번호 (0이면 빈 창구)
      int[] recEnd = new int[N+1]; // 접수 끝나는 시각
      int[] repWho = new int[M+1]; // 정비 창구에 있는 고객 번호 (0이면 빈 창구)
      int[] repEnd = new int[M+1]; // 정비 끝나는 시각

      Queue<Integer> recQ = new ArrayDeque<>(); // 접수 대기열
      Queue<Integer> repQ = new ArrayDeque<>(); // 정비 대기열

      int idx = 1;  // 다음에 도착할 고객 번호
      int done = 0; // 정비 창구 배정까지 끝난 고객 수
      int time = 0;

      while (done < K) {

        // 1. 접수 완료 -> 정비 대기열로 ,접수 창구 번호 작은 순으로 넣음
        for (int i=1; i<=N; i++) {
          if (recWho[i] != 0 && recEnd[i] == time) {
            repQ.offer(recWho[i]);
            recWho[i] = 0;
          }
        }

        // 2. 정비 완료 -> 창구 비우기
        for (int j=1; j<=M; j++) {
          if (repWho[j] != 0 && repEnd[j] == time) {
            repWho[j] = 0;
          }
        }

        // 3. 이번 시각에 도착한 고객 -> 접수 대기열로
        while (idx <= K && arrive[idx] <= time) {
          recQ.offer(idx++);
        }

        // 4. 빈 접수 창구 배정 , 창구 번호 작은 순
        for (int i=1; i<=N && !recQ.isEmpty(); i++) {
          if (recWho[i] == 0) {
            int c = recQ.poll();
            recWho[i] = c;
            recEnd[i] = time + a[i];
            recDesk[c] = i;
          }
        }

        // 5. 빈 정비 창구 배정 , 창구 번호 작은 순
        for (int j=1; j<=M && !repQ.isEmpty(); j++) {
          if (repWho[j] == 0) {
            int c = repQ.poll();
            repWho[j] = c;
            repEnd[j] = time + b[j];
            repDesk[c] = j;
            done++;
          }
        }

        time++;
      }

      int sum = 0;

      for (int k=1; k<=K; k++) {
        if (recDesk[k] == A && repDesk[k] == B) sum += k;
      }

      if (sum == 0) sum = -1;

      sb.append("#"+t+" "+sum).append("\n");


    }

    System.out.println(sb);
  }
  
}