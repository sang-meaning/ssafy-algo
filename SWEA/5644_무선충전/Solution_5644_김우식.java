import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int M, A;

    // 0: 제자리, 1: 상, 2: 우, 3: 하, 4: 좌
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    static int[] moveA;
    static int[] moveB;

    // BC 정보
    // bc[i][0] = x
    // bc[i][1] = y
    // bc[i][2] = 충전 범위 C
    // bc[i][3] = 성능 P
    static int[][] bc;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moveA = new int[M];
            moveB = new int[M];

            // A 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }

            // B 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            bc = new int[A][4];

            // BC 정보 입력
            for (int i = 0; i < A; i++) {

                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                // 우리는 row, col로 사용할 것이므로 y, x 순서로 저장
                bc[i][0] = y;
                bc[i][1] = x;
                bc[i][2] = c;
                bc[i][3] = p;
            }

            // A 시작 위치
            int ar = 1;
            int ac = 1;

            // B 시작 위치
            int brPos = 10;
            int bcPos = 10;

            int total = 0;

            // 0초 위치에서도 충전
            total += getMaxCharge(ar, ac, brPos, bcPos);

            // M초 동안 이동
            for (int t = 0; t < M; t++) {

                // A 이동
                int dirA = moveA[t];
                ar += dr[dirA];
                ac += dc[dirA];

                // B 이동
                int dirB = moveB[t];
                brPos += dr[dirB];
                bcPos += dc[dirB];

                // 현재 위치에서 최대 충전량 계산
                total += getMaxCharge(ar, ac, brPos, bcPos);
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(total)
              .append("\n");
        }

        System.out.print(sb);
    }

    static int getMaxCharge(int ar, int ac, int br, int bcPos) {

    int max = 0;

    // i : A가 사용할 BC 번호
    // j : B가 사용할 BC 번호
    // 마지막 번호(A)는 "아무 BC도 사용하지 않음"
    for (int i = 0; i <= A; i++) {
        for (int j = 0; j <= A; j++) {

            int chargeA = 0;
            int chargeB = 0;

            // --------------------
            // A의 충전량 구하기
            // --------------------

            // i == A 이면 A는 아무 BC도 사용하지 않음
            if (i != A) {

                int distanceA =
                        Math.abs(ar - bc[i][0])
                        + Math.abs(ac - bc[i][1]);

                // A가 선택한 BC 범위 밖이면
                // 이 조합은 불가능
                if (distanceA > bc[i][2]) {
                    continue;
                }

                // 범위 안이면 해당 BC의 충전량 저장
                chargeA = bc[i][3];
            }


            // --------------------
            // B의 충전량 구하기
            // --------------------

            // j == A 이면 B는 아무 BC도 사용하지 않음
            if (j != A) {

                int distanceB =
                        Math.abs(br - bc[j][0])
                        + Math.abs(bcPos - bc[j][1]);

                // B가 선택한 BC 범위 밖이면
                // 이 조합은 불가능
                if (distanceB > bc[j][2]) {
                    continue;
                }

                // 범위 안이면 해당 BC의 충전량 저장
                chargeB = bc[j][3];
            }


            int sum;

            // 둘 다 같은 BC를 사용하는 경우
            if (i == j && i != A) {

                // 같은 BC를 나눠 사용하므로
                // 둘의 충전량 합은 BC 성능 그대로
                sum = bc[i][3];

            } else {

                // 서로 다른 BC를 사용하거나
                // 한 명만 충전하는 경우
                sum = chargeA + chargeB;
            }

            // 현재까지 가장 큰 충전량 저장
            max = Math.max(max, sum);
        }
    }

    return max;
}
}