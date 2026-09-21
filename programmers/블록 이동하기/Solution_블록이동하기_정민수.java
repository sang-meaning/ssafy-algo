import java.util.*;

class Solution {

    static int N;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board) {
        this.board = board;
        N = board.length;

        Queue<State> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        State start = new State(0, 0, 0, 1, 0);
        queue.offer(start);
        visited.add(key(0, 0, 0, 1));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if ((cur.x1 == N - 1 && cur.y1 == N - 1) ||
                (cur.x2 == N - 1 && cur.y2 == N - 1)) {
                return cur.count;
            }

            for (State next : getNext(cur)) {
                String key = key(next.x1, next.y1, next.x2, next.y2);

                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.offer(next);
                }
            }
        }

        return 0;
    }

    static List<State> getNext(State cur) {
        List<State> result = new ArrayList<>();

        for (int d = 0; d < 4; d++) {
            int nx1 = cur.x1 + dx[d];
            int ny1 = cur.y1 + dy[d];
            int nx2 = cur.x2 + dx[d];
            int ny2 = cur.y2 + dy[d];

            if (isValid(nx1, ny1) && isValid(nx2, ny2)) {
                result.add(new State(
                    nx1, ny1,
                    nx2, ny2,
                    cur.count + 1
                ));
            }
        }

        if (cur.x1 == cur.x2) {
            for (int dir : new int[]{-1, 1}) {
                int nx1 = cur.x1 + dir;
                int nx2 = cur.x2 + dir;

                if (isValid(nx1, cur.y1) &&
                    isValid(nx2, cur.y2)) {

                    result.add(new State(
                        cur.x1, cur.y1,
                        nx1, cur.y1,
                        cur.count + 1
                    ));

                    result.add(new State(
                        cur.x2, cur.y2,
                        nx2, cur.y2,
                        cur.count + 1
                    ));
                }
            }
        } else {
            for (int dir : new int[]{-1, 1}) {
                int ny1 = cur.y1 + dir;
                int ny2 = cur.y2 + dir;

                if (isValid(cur.x1, ny1) &&
                    isValid(cur.x2, ny2)) {

                    result.add(new State(
                        cur.x1, cur.y1,
                        cur.x1, ny1,
                        cur.count + 1
                    ));

                    result.add(new State(
                        cur.x2, cur.y2,
                        cur.x2, ny2,
                        cur.count + 1
                    ));
                }
            }
        }

        return result;
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && x < N &&
               y >= 0 && y < N &&
               board[x][y] == 0;
    }

    static String key(int x1, int y1, int x2, int y2) {
        if (x1 > x2 || (x1 == x2 && y1 > y2)) {
            int tx = x1;
            int ty = y1;

            x1 = x2;
            y1 = y2;
            x2 = tx;
            y2 = ty;
        }

        return x1 + "," + y1 + "," + x2 + "," + y2;
    }

    static class State {
        int x1, y1, x2, y2, count;

        State(int x1, int y1, int x2, int y2, int count) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
        }
    }
}