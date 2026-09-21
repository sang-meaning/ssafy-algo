import java.util.*;

class Solution_블록이동하기_임성진 {
    private int n;
    private int[][] board;

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public int solution(int[][] board) {
        this.board = board;
        this.n = board.length;

        Set<Integer> visited = new HashSet<>();
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        int[] start = {0, 0, 0, 1};
        queue.add(start);
        visited.add(key(start));

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                if ((cur[0] == n - 1 && cur[1] == n - 1) || (cur[2] == n - 1 && cur[3] == n - 1))
                    return time;
                for (int[] next : nextStates(cur))
                    if (visited.add(key(next))) queue.add(next);
            }
            time++;
        }
        return -1;
    }

    private List<int[]> nextStates(int[] cur) {
        int r1 = cur[0], c1 = cur[1], r2 = cur[2], c2 = cur[3];
        List<int[]> list = new ArrayList<>();

        // 1) 평행 이동 4가지
        for (int d = 0; d < 4; d++) {
            int nr1 = r1 + DR[d], nc1 = c1 + DC[d];
            int nr2 = r2 + DR[d], nc2 = c2 + DC[d];
            if (empty(nr1, nc1) && empty(nr2, nc2)) list.add(norm(nr1, nc1, nr2, nc2));
        }

        // 2) 회전 4가지
        if (r1 == r2) {                       // 가로 -> 위/아래로 회전
            for (int d = -1; d <= 1; d += 2) {
                if (empty(r1 + d, c1) && empty(r2 + d, c2)) {
                    list.add(norm(r1, c1, r1 + d, c1));   // 왼쪽 칸을 축으로
                    list.add(norm(r2, c2, r2 + d, c2));   // 오른쪽 칸을 축으로
                }
            }
        } else {                              // 세로 -> 좌/우로 회전
            for (int d = -1; d <= 1; d += 2) {
                if (empty(r1, c1 + d) && empty(r2, c2 + d)) {
                    list.add(norm(r1, c1, r1, c1 + d));   // 위쪽 칸을 축으로
                    list.add(norm(r2, c2, r2, c2 + d));   // 아래쪽 칸을 축으로
                }
            }
        }
        return list;
    }

    private boolean empty(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < n && board[r][c] == 0;
    }

    private int[] norm(int r1, int c1, int r2, int c2) {
        if (r1 > r2 || (r1 == r2 && c1 > c2)) return new int[]{r2, c2, r1, c1};
        return new int[]{r1, c1, r2, c2};
    }

    private int key(int[] s) {
        return ((s[0] * n + s[1]) * n * n) + (s[2] * n + s[3]);
    }
}