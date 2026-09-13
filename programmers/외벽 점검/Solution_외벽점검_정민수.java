import java.util.*;

class Solution {
    static int answer;
    static int[] weakLine;
    static int[] dist;
    static boolean[] visited;
    static int[] order;
    static int weakSize;

    public int solution(int n, int[] weak, int[] dist) {
        this.dist = dist;
        weakSize = weak.length;
        answer = dist.length + 1;

        weakLine = new int[weakSize * 2];

        for (int i = 0; i < weakSize; i++) {
            weakLine[i] = weak[i];
            weakLine[i + weakSize] = weak[i] + n;
        }

        visited = new boolean[dist.length];
        order = new int[dist.length];

        permutation(0);

        if (answer == dist.length + 1) {
            return -1;
        }

        return answer;
    }

    static void permutation(int depth) {
        if (depth == dist.length) {
            check();
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            order[depth] = dist[i];

            permutation(depth + 1);

            visited[i] = false;
        }
    }

    static void check() {
        for (int start = 0; start < weakSize; start++) {
            int friend = 0;
            int position = weakLine[start] + order[friend];

            for (int i = start; i < start + weakSize; i++) {
                if (weakLine[i] > position) {
                    friend++;

                    if (friend >= order.length) {
                        break;
                    }

                    position = weakLine[i] + order[friend];
                }
            }

            answer = Math.min(answer, friend + 1);
        }
    }
}