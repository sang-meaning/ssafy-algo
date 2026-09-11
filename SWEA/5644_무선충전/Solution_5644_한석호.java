import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5644_한석호 {

    // 이동 방향: 0(정지), 1(상), 2(우), 3(하), 4(좌)
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    // BC(무선 충전기) 정보 클래스
    static class BC {
        int x, y, c, p;

        public BC(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    static int M, A;
    static int[] pathA, pathB;
    static BC[] bcList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine().trim());
            M = Integer.parseInt(st.nextToken()); // 이동 시간
            A = Integer.parseInt(st.nextToken()); // BC 개수

            pathA = new int[M];
            pathB = new int[M];

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < M; i++) {
                pathA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < M; i++) {
                pathB[i] = Integer.parseInt(st.nextToken());
            }

            bcList = new BC[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcList[i] = new BC(x, y, c, p);
            }

            // 초기 위치 설정 (A: 1,1 / B: 10,10)
            int ax = 1, ay = 1;
            int bx = 10, by = 10;

            // 0초일 때 충전량 계산
            int totalCharge = getMaxCharge(ax, ay, bx, by);

            // M초 동안 이동하며 충전량 누적
            for (int t = 0; t < M; t++) {
                ax += dx[pathA[t]];
                ay += dy[pathA[t]];
                bx += dx[pathB[t]];
                by += dy[pathB[t]];

                totalCharge += getMaxCharge(ax, ay, bx, by);
            }

            System.out.println("#" + test_case + " " + totalCharge);
        }
    }

    // 현재 A, B의 위치에서 얻을 수 있는 최대 충전량 합 계산
    private static int getMaxCharge(int ax, int ay, int bx, int by) {
        List<Integer> availA = new ArrayList<>();
        List<Integer> availB = new ArrayList<>();

        // A와 B가 접속 가능한 BC 인덱스 수집
        for (int i = 0; i < A; i++) {
            if (getDistance(ax, ay, bcList[i].x, bcList[i].y) <= bcList[i].c) {
                availA.add(i);
            }
            if (getDistance(bx, by, bcList[i].x, bcList[i].y) <= bcList[i].c) {
                availB.add(i);
            }
        }

        int maxSum = 0;

        // 경우 1: A와 B 모두 접속 가능한 BC가 있는 경우
        if (!availA.isEmpty() && !availB.isEmpty()) {
            for (int aIdx : availA) {
                for (int bIdx : availB) {
                    int sum = 0;
                    if (aIdx == bIdx) {
                        // 같은 BC 선택 시 Split (P/2 + P/2 = P)
                        sum = bcList[aIdx].p;
                    } else {
                        // 서로 다른 BC 선택 시
                        sum = bcList[aIdx].p + bcList[bIdx].p;
                    }
                    maxSum = Math.max(maxSum, sum);
                }
            }
        } 
        // 경우 2: A만 접속 가능한 BC가 있는 경우
        else if (!availA.isEmpty()) {
            for (int aIdx : availA) {
                maxSum = Math.max(maxSum, bcList[aIdx].p);
            }
        } 
        // 경우 3: B만 접속 가능한 BC가 있는 경우
        else if (!availB.isEmpty()) {
            for (int bIdx : availB) {
                maxSum = Math.max(maxSum, bcList[bIdx].p);
            }
        }

        return maxSum;
    }

    // 맨해튼 거리 계산 |X1 - X2| + |Y1 - Y2|
    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}