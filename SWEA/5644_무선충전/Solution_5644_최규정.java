import java.util.Scanner;

public class Solution {
    static int chargerCount;
    static int[] chargerX, chargerY, range, power;

    static int ax, ay, bx, by;

    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int moveCount = sc.nextInt();
            chargerCount = sc.nextInt();

            int[] moveA = new int[moveCount + 1];
            int[] moveB = new int[moveCount + 1];

            for (int i = 1; i <= moveCount; i++) {
                moveA[i] = sc.nextInt();
            }

            for (int i = 1; i <= moveCount; i++) {
                moveB[i] = sc.nextInt();
            }

            chargerX = new int[chargerCount + 1];
            chargerY = new int[chargerCount + 1];
            range = new int[chargerCount + 1];
            power = new int[chargerCount + 1];

            for (int i = 1; i <= chargerCount; i++) {
                chargerX[i] = sc.nextInt();
                chargerY[i] = sc.nextInt();
                range[i] = sc.nextInt();
                power[i] = sc.nextInt();
            }

            ax = 1;
            ay = 1;
            bx = 10;
            by = 10;

            int answer = 0;

            for (int time = 0; time <= moveCount; time++) {
                ax += dx[moveA[time]];
                ay += dy[moveA[time]];

                bx += dx[moveB[time]];
                by += dy[moveB[time]];

                answer += charge();
            }

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static int charge() {
        boolean[] availableA = new boolean[chargerCount + 1];
        boolean[] availableB = new boolean[chargerCount + 1];

        availableA[0] = true;
        availableB[0] = true;

        for (int i = 1; i <= chargerCount; i++) {
            int distanceA = Math.abs(ax - chargerX[i]) + Math.abs(ay - chargerY[i]);
            int distanceB = Math.abs(bx - chargerX[i]) + Math.abs(by - chargerY[i]);

            availableA[i] = distanceA <= range[i];
            availableB[i] = distanceB <= range[i];
        }

        int maxCharge = 0;
        for (int a = 0; a <= chargerCount; a++) {
            if (!availableA[a]) {
                continue;
            }

            for (int b = 0; b <= chargerCount; b++) {
                if (!availableB[b]) {
                    continue;
                }

                int total;

                if (a == b) {
                    total = power[a];
                } else {
                    total = power[a] + power[b];
                }

                maxCharge = Math.max(maxCharge, total);
            }
        }
        return maxCharge;
    }
}