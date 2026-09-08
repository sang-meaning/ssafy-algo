import java.util.Scanner;

public class Solution_6808_한석호 {
    static int[] guCard = new int[9];     // 규영이 카드
    static int[] inCard = new int[9];     // 인영이 카드
    static int[] selected = new int[9];   // 인영이가 낼 카드 순서
    static boolean[] isVisited = new boolean[9]; // 순열 방문 체크
    static int winCount, loseCount;       // 규영이의 승/패 카운트

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            boolean[] used = new boolean[19]; // 1~18번 카드 사용 여부

            // 규영이 카드 입력받기
            for (int i = 0; i < 9; i++) {
                guCard[i] = sc.nextInt();
                used[guCard[i]] = true;
            }

            // 인영이 카드 구하기 (규영이가 가지지 않은 카드)
            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (!used[i]) {
                    inCard[idx++] = i;
                }
            }

            winCount = 0;
            loseCount = 0;

            // 순열 탐색 시작 (인영이 카드의 순서 정하기)
            perm(0);

            System.out.println("#" + t + " " + winCount + " " + loseCount);
        }
        sc.close();
    }

    // 인영이 카드의 9! 가지 순열을 만드는 DFS 함수
    static void perm(int depth) {
        // 9장의 카드 순서가 모두 정해진 경우
        if (depth == 9) {
            game();
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                selected[depth] = inCard[i]; // 카드 선택
                perm(depth + 1);             // 다음 카드 선택
                isVisited[i] = false;        // 원상복구 (백트래킹)
            }
        }
    }

    // 승패 판정 및 점수 계산
    static void game() {
        int guScore = 0;
        int inScore = 0;

        for (int i = 0; i < 9; i++) {
            if (guCard[i] > selected[i]) {
                guScore += guCard[i] + selected[i];
            } else if (guCard[i] < selected[i]) {
                inScore += guCard[i] + selected[i];
            }
        }

        // 규영이 기준 승/패 카운트 (무승부는 카운트하지 않음)
        if (guScore > inScore) {
            winCount++;
        } else if (guScore < inScore) {
            loseCount++;
        }
    }
}