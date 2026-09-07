import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] guCard = new int[9];  // 규영이의 카드 (고정)
    static int[] inCard = new int[9];  // 인영이가 가진 카드 (9개)
    static int[] selected = new int[9];// 인영이가 카드를 제출할 순서 (순열)
    static boolean[] isSelected = new boolean[9]; // 순열 생성 시 사용 여부 체크
    static boolean[] usedAll = new boolean[19];   // 1~18번 카드 중 규영이가 쓴 카드 체크

    static int guWin;  // 규영이가 이기는 횟수
    static int inWin;  // 인영이가 이기는 횟수 (규영이가 지는 횟수)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            usedAll = new boolean[19]; // 사용된 카드 초기화
            for (int i = 0; i < 9; i++) {
                guCard[i] = Integer.parseInt(st.nextToken());
                usedAll[guCard[i]] = true; // 규영이가 가져간 카드 표시
            }

            // 규영이가 가져가지 않은 카드를 인영이 카드 배열에 넣기
            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (!usedAll[i]) {
                    inCard[idx++] = i;
                }
            }

            // 승패 횟수 초기화
            guWin = 0;
            inWin = 0;

            // 인영이 카드의 모든 순열 생성 (0번째 자리부터 시작)
            perm(0);

            // 결과 출력: 규영이가 이기는 횟수, 지는 횟수
            System.out.println("#" + tc + " " + guWin + " " + inWin);
        }
    }

    // 순열 생성 함수 (depth: 현재 카드를 내려놓는 라운드 index)
    static void perm(int depth) {
        // [탈출 조건] 9라운드까지 카드를 다 내려놓았을 때
        if (depth == 9) {
            playGame(); // 이번 순서 조합으로 점수 계산
            return;
        }

        // 인영이의 9개 카드 중 아직 안 쓴 카드를 하나씩 배치해보기
        for (int i = 0; i < 9; i++) {
            if (isSelected[i]) continue; // 이미 사용한 카드는 건너뜀

            isSelected[i] = true;
            selected[depth] = inCard[i]; // depth번째 라운드에 i번째 카드 배치

            perm(depth + 1); // 다음 라운드 카드 결정하러 파고들기

            isSelected[i] = false; // 원상복구 (백트래킹)
        }
    }

    // 완성된 순서(selected)로 규영이와 점수를 겨루는 함수
    static void playGame() {
        int guScore = 0;
        int inScore = 0;

        for (int i = 0; i < 9; i++) {
            int gu = guCard[i];
            int in = selected[i];

            if (gu > in) {
                guScore += (gu + in); // 규영이가 이기면 두 카드 합을 규영이가 획득
            } else if (in > gu) {
                inScore += (gu + in); // 인영이가 이기면 두 카드 합을 인영이가 획득
            }
        }

        // 총점에 따라 승패 횟수 카운트
        if (guScore > inScore) {
            guWin++;
        } else if (inScore > guScore) {
            inWin++;
        }
    }
}