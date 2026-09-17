import java.util.*;

class Solution_외벽점검_정서우 {
    private int answer;
    private int n;
    private int[] weak;
    private int[] dist;
    private int[] extendedWeak;
    private boolean[] visited;

    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        this.answer = dist.length + 1;
        this.visited = new boolean[dist.length];

        int weakLen = weak.length;
        extendedWeak = new int[weakLen * 2];
        for (int i = 0; i < weakLen; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + weakLen] = weak[i] + n;
        }

        int[] perm = new int[dist.length];
        generatePermutations(0, perm);

        return answer > dist.length ? -1 : answer;
    }

    private void generatePermutations(int depth, int[] perm) {
        if (answer == 1)
            return;

        if (depth == dist.length) {
            check(perm);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm[depth] = dist[i];
                generatePermutations(depth + 1, perm);
                visited[i] = false;
            }
        }
    }

    private void check(int[] perm) {
        int weakLen = weak.length;

        for (int start = 0; start < weakLen; start++) {
            int count = 1;
            int position = extendedWeak[start] + perm[count - 1];

            for (int index = start; index < start + weakLen; index++) {
                if (position < extendedWeak[index]) {
                    count++;
                    if (count > dist.length || count >= answer) {
                        break;
                    }
                    position = extendedWeak[index] + perm[count - 1];
                }
            }

            answer = Math.min(answer, count);
        }
    }
}