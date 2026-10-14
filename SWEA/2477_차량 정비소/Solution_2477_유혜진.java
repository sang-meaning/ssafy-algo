import java.util.*;

// SWEA 2477. 차량 정비소 - 시간 단위 시뮬레이션
public class Solution_2477_유혜진 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 접수 창구 수
            int M = sc.nextInt(); // 정비 창구 수
            int K = sc.nextInt(); // 고객 수
            int A = sc.nextInt(); // 지갑 두고 간 고객이 이용한 접수 창구
            int B = sc.nextInt(); // 지갑 두고 간 고객이 이용한 정비 창구

            int[] a = new int[N + 1];
            int[] b = new int[M + 1];
            int[] t = new int[K + 1];
            for (int i = 1; i <= N; i++) a[i] = sc.nextInt();
            for (int i = 1; i <= M; i++) b[i] = sc.nextInt();
            for (int i = 1; i <= K; i++) t[i] = sc.nextInt();

            int[] usedA = new int[K + 1]; // 고객이 이용한 접수 창구 번호
            int[] usedB = new int[K + 1]; // 고객이 이용한 정비 창구 번호

            int[] recvCust = new int[N + 1]; // 접수 창구에 있는 고객 (0이면 비어있음)
            int[] recvEnd = new int[N + 1];  // 접수 끝나는 시각
            int[] repCust = new int[M + 1];
            int[] repEnd = new int[M + 1];

            Queue<Integer> waitRecv = new LinkedList<>(); // 접수 대기
            Queue<Integer> waitRep = new LinkedList<>();  // 정비 대기

            int done = 0;

            // 도착 시간 순 → 같으면 고객 번호 순
            Integer[] order = new Integer[K];
            for (int i = 0; i < K; i++) order[i] = i + 1;
            Arrays.sort(order, (x, y) -> t[x] != t[y] ? t[x] - t[y] : x - y);
            int oIdx = 0;

            for (int time = 0; done < K; time++) {
                // 1. 접수 끝난 고객 → 정비 대기 (같은 시각이면 접수 창구 번호 작은 순)
                for (int i = 1; i <= N; i++) {
                    if (recvCust[i] != 0 && recvEnd[i] == time) {
                        waitRep.offer(recvCust[i]);
                        recvCust[i] = 0;
                    }
                }

                // 2. 이번 시각에 도착한 고객 → 접수 대기
                while (oIdx < K && t[order[oIdx]] == time) {
                    waitRecv.offer(order[oIdx++]);
                }

                // 3. 빈 접수 창구에 고객 배정 (창구 번호 작은 순)
                for (int i = 1; i <= N && !waitRecv.isEmpty(); i++) {
                    if (recvCust[i] == 0) {
                        int c = waitRecv.poll();
                        recvCust[i] = c;
                        recvEnd[i] = time + a[i];
                        usedA[c] = i;
                    }
                }

                // 4. 정비 끝난 고객 처리
                for (int j = 1; j <= M; j++) {
                    if (repCust[j] != 0 && repEnd[j] == time) {
                        repCust[j] = 0;
                        done++;
                    }
                }

                // 5. 빈 정비 창구에 고객 배정 (창구 번호 작은 순)
                for (int j = 1; j <= M && !waitRep.isEmpty(); j++) {
                    if (repCust[j] == 0) {
                        int c = waitRep.poll();
                        repCust[j] = c;
                        repEnd[j] = time + b[j];
                        usedB[c] = j;
                    }
                }
            }

            int sum = 0;
            for (int i = 1; i <= K; i++) {
                if (usedA[i] == A && usedB[i] == B) sum += i;
            }
            System.out.println("#" + tc + " " + (sum == 0 ? -1 : sum));
        }
    }
}