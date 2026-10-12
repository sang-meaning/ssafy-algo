import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_유혜진 {
    static int N;
    static int[][] S;
    static boolean[] selected;
    static int minDiff; // 맛 차이의 최솟값 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            S = new int[N][N];
            selected = new boolean[N];
            minDiff = Integer.MAX_VALUE; // 최솟값을 구하기 위해 큰 값으로 초기화

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 0번 재료는 A팀으로 고정 (중복 탐색 절반으로 제거)
            selected[0] = true;

            // 0번은 이미 뽑았으므로(count=1), 1번 재료부터(start=1) 순회 시작
            comb(1, 1);

            System.out.println("#" + tc + " " + minDiff);
        }
    }

    // 직관적인 for문 기반 DFS 조합 함수
    static void comb(int start, int count) {
        // [기저 조건] A팀 재료가 절반(N/2개) 채워지면 맛 차이 계산
        if (count == N / 2) {
            calculateDiff();
            return;
        }

        // start 번호부터 끝(N-1)까지 순서대로 돌면서 A팀 재료 고르기
        for (int i = start; i < N; i++) {
            selected[i] = true;     // i번 재료를 A팀으로 선택
            comb(i + 1, count + 1); // 다음 재료(i+1) 보러 가고, A팀 개수 +1
            selected[i] = false;    // 계산 끝나고 돌아오면 A팀에서 제거 (원상복구)
        }
    }

    // A팀과 B팀의 시너지 합을 구하고 최솟값 갱신하는 함수
    static void calculateDiff() {
        int sumA = 0;
        int sumB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue; // 같은 재료끼리의 시너지는 계산 안 함

                // 두 재료가 모두 A팀(true)이면 sumA에 합산
                if (selected[i] && selected[j]) {
                    sumA += S[i][j];
                }
                // 두 재료가 모두 B팀(false)이면 sumB에 합산
                else if (!selected[i] && !selected[j]) {
                    sumB += S[i][j];
                }
            }
        }

        // 두 팀의 맛 차이 절댓값 계산 후 최솟값 갱신
        int diff = Math.abs(sumA - sumB);
        minDiff = Math.min(minDiff, diff);
    }
}