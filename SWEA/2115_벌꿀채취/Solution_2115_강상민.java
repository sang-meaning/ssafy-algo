import java.util.*;
import java.io.*;

/*

1)
두 일꾼의 영역을 완전탐색
2중 for문으로 1번 일꾼의 시작점을 정하고 
2번 일꾼의 일 시작점 정하기 

시간복잡도 1만

2)
한 일꾼이 m길이에서 모든 조합을 보고, 수익 최댓값 구하기
mC1 + mC2 + ... + mCm 가지의 조합에 대해 , 각 조합에서 원소의 합 < C 를 만족하는 경우
: dfs로 보며 모든 꿀 선택 과정에서 최댓값 갱신하면 됨


*/
public class Solution_2115_강상민 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static StringTokenizer st;
  static int T;
  static int N,M,C; // 크기, 일꾼길이, 벌꿀양한계
  static int[][] board;
  static int[] f1; // 1번 일꾼의 벌꿀
  static int[] f2;
  static int temp; // (일꾼 1) X (일꾼 2) 조합에서 각 일꾼의 벌꿀제곱합 최댓값 구하는데 사용하는 임시 변수
  static int result; // 최종 출력
  static boolean[] vis; // f1, f2 방문

  public static void main(String[] args) throws IOException {
    T = Integer.parseInt(br.readLine());
    for (int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());

      board = new int[N][N];
      f1 = new int[M];
      f2 = new int[M];
      temp = 0;
      result = 0;
      vis = new boolean[M];

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 세팅 완료

      for (int i=0; i<N; i++) {
        for (int j=0; j<=N-M; j++) {
          // 1번 일꾼 선택
          for (int p=0; p<M; p++) {
            f1[p] = board[i][j+p];
          }

          for (int a=0; a<N; a++) {
            for (int b=0; b<=N-M; b++) {
              // 두 일꾼이 같은 행에 있으면, b > j 이어야 함
              if (i==a && b <= (j+M)) continue;

              // 2번 일꾼 선택 
              for (int q=0; q<M; q++) {
                f2[q] = board[a][b+q];
              }

              // 두 일꾼에 대해 각각 꿀들 제곱의 합 최댓값 구하기
              temp = 0;
              dfs(0,0,0,f1);
              int first = temp;

              temp = 0;
              dfs(0,0,0,f2);
              int second = temp;

              result = Math.max(result, first+second);

            }
          }

        }
      }

      sb.append("#"+t+" "+result).append("\n");

    }

    // tc 종료
    System.out.print(sb);

  }

  // plus : 꿀들의 합, power : 꿀의 제곱의 합
  static boolean dfs(int depth, int plus, int power, int[] arr) {
    if (depth == M+1) { 
      return true;
    }

    for (int i=0; i<M; i++) {
      if (vis[i]) continue;

      int nxtPlus = plus + arr[i];
      if (nxtPlus > C) return false; // 꿀 합 > C 이면, 그 위치에서 깊이탐색 종료

      int nxtPower = power + arr[i] * arr[i];

      temp = Math.max(temp, nxtPower);
      vis[i] = true;

      if (dfs(depth+1, nxtPlus, nxtPower, arr)); // return false 면 깊이 탐색 종료

      vis[i] = false; // 백트래킹
    }

    // 다음 깊이도 볼 것이므로 true
    return true;

  }
  
}
