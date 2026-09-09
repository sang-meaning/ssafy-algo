import java.util.ArrayList;

public class Solution {

    int[] dr = { 1, 0, -1, 0 };
    int[] dc = { 0, 1, 0, -1 };

    char[][] map;
    int R;
    int C;

    ArrayList<Integer> answerList;

    public int[] solution(String[] maps) {

        R = maps.length;
        C = maps[0].length();

        map = new char[R][C];
        answerList = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            map[i] = maps[i].toCharArray();
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {

                if (map[r][c] != 'X') {
                    answerList.add(dfs(r, c));
                }
            }
        }

        if (answerList.isEmpty()) {
            return new int[] { -1 };
        }

        answerList.sort(null);

        return answerList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int dfs(int r, int c) {

        int sum = map[r][c] - '0';

        map[r][c] = 'X';

        for (int d = 0; d < 4; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C)
                continue;

            if (map[nr][nc] != 'X') {
                sum += dfs(nr, nc);
            }
        }

        return sum;
    }
}