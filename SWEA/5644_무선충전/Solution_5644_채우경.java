import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    // 이동 방향: 이동없음(0), 상(1), 우(2), 하(3), 좌(4)
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    static class BC {
        int x, y, c, p;
        public BC(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            int[] pathA = new int[M + 1];
            int[] pathB = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                pathA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                pathB[i] = Integer.parseInt(st.nextToken());
            }

            BC[] bcs = new BC[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcs[i] = new BC(x, y, c, p);
            }

            int ax = 1, ay = 1;
            int bx = 10, by = 10;
            int totalCharge = 0;

            for (int time = 0; time <= M; time++) {
                ax += dx[pathA[time]];
                ay += dy[pathA[time]];
                bx += dx[pathB[time]];
                by += dy[pathB[time]];

                List<Integer> listA = new ArrayList<>();
                List<Integer> listB = new ArrayList<>();

                for (int i = 0; i < A; i++) {
                    if (Math.abs(ax - bcs[i].x) + Math.abs(ay - bcs[i].y) <= bcs[i].c) {
                        listA.add(i);
                    }
                    if (Math.abs(bx - bcs[i].x) + Math.abs(by - bcs[i].y) <= bcs[i].c) {
                        listB.add(i);
                    }
                }

                int maxCharge = 0;

                if (listA.isEmpty() && listB.isEmpty()) {
                    maxCharge = 0;
                } else if (listA.isEmpty()) {
                    for (int bIdx : listB) {
                        maxCharge = Math.max(maxCharge, bcs[bIdx].p);
                    }
                } else if (listB.isEmpty()) {
                    for (int aIdx : listA) {
                        maxCharge = Math.max(maxCharge, bcs[aIdx].p);
                    }
                } else {
                    for (int aIdx : listA) {
                        for (int bIdx : listB) {
                            int sum = 0;
                            if (aIdx == bIdx) {
                                sum = bcs[aIdx].p;
                            } else {
                                sum = bcs[aIdx].p + bcs[bIdx].p;
                            }
                            maxCharge = Math.max(maxCharge, sum);
                        }
                    }
                }

                totalCharge += maxCharge;
            }

            sb.append("#").append(t).append(" ").append(totalCharge).append("\n");
        }
        System.out.print(sb);
    }
}