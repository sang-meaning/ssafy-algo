import java.util.*;

// SWEA 2383. 점심 식사시간 - 부분집합(계단 배정) + 시뮬레이션
public class Solution {
    static int P, answer;
    static int[][] people;   // 사람 좌표
    static int[][] stairs;   // 계단 {행, 열, 길이}
    static int[] choice;     // 각 사람이 선택한 계단 (0 또는 1)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            List<int[]> pList = new ArrayList<>();
            stairs = new int[2][];
            int sIdx = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int v = sc.nextInt();
                    if (v == 1) pList.add(new int[]{i, j});
                    else if (v >= 2) stairs[sIdx++] = new int[]{i, j, v};
                }
            }

            P = pList.size();
            people = pList.toArray(new int[0][]);
            choice = new int[P];
            answer = Integer.MAX_VALUE;

            subset(0);
            System.out.println("#" + tc + " " + answer);
        }
    }

    // 각 사람이 0번 / 1번 계단 중 어디로 갈지 모든 경우 탐색
    static void subset(int idx) {
        if (idx == P) {
            int time = Math.max(calc(0), calc(1));
            answer = Math.min(answer, time);
            return;
        }
        choice[idx] = 0;
        subset(idx + 1);
        choice[idx] = 1;
        subset(idx + 1);
    }

    // s번 계단을 고른 사람들이 모두 내려가는 데 걸리는 시간
    static int calc(int s) {
        int sr = stairs[s][0], scol = stairs[s][1], K = stairs[s][2];

        List<Integer> arrive = new ArrayList<>();
        for (int i = 0; i < P; i++) {
            if (choice[i] == s) {
                arrive.add(Math.abs(people[i][0] - sr) + Math.abs(people[i][1] - scol));
            }
        }
        if (arrive.isEmpty()) return 0;
        Collections.sort(arrive);

        int[] end = new int[arrive.size()]; // 각 사람이 계단을 다 내려간 시각
        for (int i = 0; i < arrive.size(); i++) {
            int start = arrive.get(i) + 1;                  // 도착 후 1분 뒤 진입 가능
            if (i >= 3) start = Math.max(start, end[i - 3]); // 계단에는 최대 3명
            end[i] = start + K;
        }
        return end[arrive.size() - 1];
    }
}