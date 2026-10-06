package 과락피하자;

import java.io.*;
import java.util.*;

public class 하나로 {

    static int T;
    static int N;

    static class island {
        long x;
        long y;
        boolean visited;
        long dist;

        island(long x, long y) {
            this.x = x;
            this.y = y;
            this.visited = false;
            this.dist = Long.MAX_VALUE;
        }
    }

    static island[] lands;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            lands = new island[N];

            // 문제에서 X좌표와 Y좌표가 각각 따로 한 줄씩 들어오므로
            // 임시 배열에 저장
            long[] x = new long[N];
            long[] y = new long[N];

            // X 좌표 입력
            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                x[i] = Long.parseLong(st.nextToken());
            }

            // Y 좌표 입력
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                y[i] = Long.parseLong(st.nextToken());
            }

            // island 객체 생성
            for (int i = 0; i < N; i++) {
                lands[i] = new island(x[i], y[i]);
            }

            // 환경 부담 세율
            double E = Double.parseDouble(br.readLine());


            // 0번 섬부터 시작
            lands[0].dist = 0;

            long sum = 0;

            // 섬을 N개 선택
            for (int cnt = 0; cnt < N; cnt++) {

                int current = -1;
                long min = Long.MAX_VALUE;

                // 1.
                // 아직 방문하지 않은 섬 중
                // dist가 가장 작은 섬 찾기
                for (int i = 0; i < N; i++) {

                    if (!lands[i].visited
                            && lands[i].dist < min) {

                        min = lands[i].dist;
                        current = i;
                    }
                }

                // 2.
                // 선택한 섬 MST에 포함
                lands[current].visited = true;

                // 선택한 간선 비용 추가
                sum += lands[current].dist;

                // 3.
                // 새로 선택한 섬을 기준으로
                // 나머지 섬들의 dist 갱신
                for (int next = 0; next < N; next++) {

                    // 이미 MST에 들어간 섬은 제외
                    if (lands[next].visited) {
                        continue;
                    }

                    // 두 섬의 좌표 차이
                    long dx =
                            lands[current].x - lands[next].x;

                    long dy =
                            lands[current].y - lands[next].y;

                    // L^2
                    long distance =
                            dx * dx + dy * dy;

                    // 기존 연결 비용보다 작다면 갱신
                    if (distance < lands[next].dist) {
                        lands[next].dist = distance;
                    }
                }
            }

            // 환경 부담금 계산
            long answer = Math.round(sum * E);

            System.out.println("#" + tc + " " + answer);
        }
    }
}