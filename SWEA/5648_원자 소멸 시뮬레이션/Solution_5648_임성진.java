import java.io.*;
import java.util.*;

public class Solution_5648_임성진 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();

        final int[] dx = {0, 0, -1, 1};   // 0:상 1:하 2:좌 3:우
        final int[] dy = {1, -1, 0, 0};
        final int LIMIT = 2000;           // 원래 범위 -1000~1000 의 2배

        in.nextToken(); int T = (int) in.nval;
        for (int tc = 1; tc <= T; tc++) {
            in.nextToken(); int N = (int) in.nval;

            int[] x = new int[N], y = new int[N], dir = new int[N], k = new int[N];
            boolean[] alive = new boolean[N];
            for (int i = 0; i < N; i++) {
                in.nextToken(); x[i] = (int) in.nval * 2;
                in.nextToken(); y[i] = (int) in.nval * 2;
                in.nextToken(); dir[i] = (int) in.nval;
                in.nextToken(); k[i] = (int) in.nval;
                alive[i] = true;
            }

            long energy = 0;
            int remain = N;
            HashMap<Integer, Integer> count = new HashMap<>();

            while (remain > 0) {
                count.clear();

                // 1) 이동 -> 범위 이탈이면 제거, 남은 원자는 위치별 개수 집계
                for (int i = 0; i < N; i++) {
                    if (!alive[i]) continue;
                    x[i] += dx[dir[i]];
                    y[i] += dy[dir[i]];
                    if (x[i] < -LIMIT || x[i] > LIMIT || y[i] < -LIMIT || y[i] > LIMIT) {
                        alive[i] = false; remain--; continue;
                    }
                    int key = (x[i] + LIMIT) * 4001 + (y[i] + LIMIT);
                    Integer c = count.get(key);
                    count.put(key, c == null ? 1 : c + 1);
                }

                // 2) 같은 칸에 2개 이상 모였으면 모두 소멸하며 에너지 방출
                for (int i = 0; i < N; i++) {
                    if (!alive[i]) continue;
                    int key = (x[i] + LIMIT) * 4001 + (y[i] + LIMIT);
                    if (count.get(key) >= 2) {
                        energy += k[i];
                        alive[i] = false;
                        remain--;
                    }
                }
            }
            sb.append('#').append(tc).append(' ').append(energy).append('\n');
        }
        System.out.print(sb);
    }
}