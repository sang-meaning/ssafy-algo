import java.io.*;
import java.util.*;

class Processer {
    public int X;
    public int Y;

    public Processer(int x, int y) {
        this.X = x;
        this.Y = y;
    }
}

public class Solution_1767_손영주 {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new InputStreamReader(new
        // FileInputStream("C:/ssafy/workspace/algorithm/input.txt")));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            int[][] map = new int[N][N];

            List<Processer> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        list.add(new Processer(i, j));
                    }
                }
            }

            maxProcessor = 0;
            minWire = Integer.MAX_VALUE;

            dfs(0, 0, 0, list, map, N);
            int answer = minWire;
            System.out.println("#" + test_case + " " + answer);
        }
    }

    static int[] dx = { 0, 1, 0, -1 }; // 우 하 상 좌
    static int[] dy = { 1, 0, -1, 0 };

    // 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구하고자 한다.
    // 단, 여러 방법이 있을 경우, 전선 길이의 합이 최소가 되는 값을 구하라.

    static int maxProcessor = 0;
    static int minWire = Integer.MAX_VALUE;

    public static void dfs(int index, int count, int sum, List<Processer> list, int[][] map, int N) {
        // 인덱스, 와이어의 합, 프로세서의 개수, 프로세서 리스트, 맵, 맵 크기

        // 종료조건
        // 만약 앞으로의 프로세서를 다 더해도 최대값이 안 된다면
        // 현재까지 연결된 개수 + 앞으로의 프로세서 개수 < 기록된 프로세서 개수
        if (count + list.size() - index < maxProcessor) {
            return;
        }
        // 다 순회하였으면 갱신
        if (index >= list.size()) {
            if (count > maxProcessor) {
                maxProcessor = count;
                minWire = sum;
            } else if (maxProcessor == count) {
                minWire = Math.min(minWire, sum);
            }
            return;
        }

        // 전파조건
        // 가장자리에 있다면 이미 연결되어 있다.
        int x = list.get(index).X; // processor 위치.
        int y = list.get(index).Y;
        if ((x == N - 1) || (x == 0) || (y == N - 1) || (y == 0)) {
            dfs(index + 1, count + 1, sum, list, map, N);
        } else {
            // 4방향 각각 전선을 연결한 다음 탐색해 본다.
            for (int dir = 0; dir < 4; dir++) {
                x = list.get(index).X; // processor 위치.
                y = list.get(index).Y;
                boolean isCantDraw = false;
                while (!((x == N - 1) || (x == 0) || (y == N - 1) || (y == 0))) { // 검사
                    // 다음 칸으로 가기.
                    x += dx[dir];
                    y += dy[dir];
                    if (map[x][y] == 9 || map[x][y] == 1) {
                        isCantDraw = true; // 만약 그릴 수 없다면, 다음 방향 탐색
                        break;
                    }
                }
                if (isCantDraw)
                    continue;
                x = list.get(index).X; // processor 위치.
                y = list.get(index).Y;
                int wire = 0;
                while (!((x == N - 1) || (x == 0) || (y == N - 1) || (y == 0))) { // 그리기
                    x += dx[dir];
                    y += dy[dir];

                    map[x][y] = 9;
                    wire++;
                }
                dfs(index + 1, count + 1, sum + wire, list, map, N);
                // 복귀 후
                x = list.get(index).X; // processor 위치.
                y = list.get(index).Y;
                while (!((x == N - 1) || (x == 0) || (y == N - 1) || (y == 0))) { // 지우기
                    x += dx[dir];
                    y += dy[dir];
                    map[x][y] = 0;
                }
            } // 연결하지 않고 넘기는 가능성
            dfs(index + 1, count, sum, list, map, N);
        }
    }
}