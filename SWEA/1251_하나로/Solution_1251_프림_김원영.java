import java.util.Scanner;

public class Solution {

    static int N;
    static long[] x;
    static long[] y;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            N = sc.nextInt();

            x = new long[N];
            y = new long[N];

            for(int i=0; i<N; i++) {
                x[i] = sc.nextLong();
            }

            for(int i=0; i<N; i++) {
                y[i] = sc.nextLong();
            }

            double E = sc.nextDouble();

            boolean[] visited = new boolean[N];
            long[] minDistance = new long[N];

            for(int i=0; i<N; i++) {
                minDistance[i] = Long.MAX_VALUE;
            }

            minDistance[0] = 0;

            long total = 0;

            for(int i=0; i<N; i++) {

                int minVertex = -1;
                long min = Long.MAX_VALUE;

                // 아직 선택되지 않은 섬 중
                // 현재 가장 싸게 연결할 수 있는 섬 찾기
                for(int j=0; j<N; j++) {
                    if(!visited[j] && minDistance[j] < min) {
                        min = minDistance[j];
                        minVertex = j;
                    }
                }

                visited[minVertex] = true;
                total += min;

                // 새로 들어온 섬을 기준으로
                // 나머지 섬들의 최소 연결 비용 갱신
                for(int j=0; j<N; j++) {

                    if(!visited[j]) {

                        long dx = x[minVertex] - x[j];
                        long dy = y[minVertex] - y[j];

                        long distance = dx * dx + dy * dy;

                        if(distance < minDistance[j]) {
                            minDistance[j] = distance;
                        }
                    }
                }
            }

            long answer = Math.round(total * E);

            System.out.println("#" + tc + " " + answer);
        }
    }
}