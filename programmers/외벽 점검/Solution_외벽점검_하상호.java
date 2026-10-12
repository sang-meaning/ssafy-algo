package prgm;

public class Solution_외벽점검_하상호 {
    static int[] weak;
    static int[] dist;

    static int weakLen;
    static int answer;

    static boolean[] visited;
    static int[] order;

    public int solution(int n, int[] weak, int[] dist) {

        this.weak = weak;
        this.dist = dist;

        weakLen = weak.length;
        answer = Integer.MAX_VALUE;

        visited = new boolean[dist.length];
        order = new int[dist.length];

        int[] extendedWeak = new int[weakLen * 2];

        for (int i = 0; i < weakLen; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + weakLen] = weak[i] + n;
        }

        permutation(0, extendedWeak);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    static void permutation(int depth, int[] extendedWeak) {

        if (depth == dist.length) {
            check(extendedWeak);
            return;
        }

        for (int i = 0; i < dist.length; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            order[depth] = dist[i];

            permutation(depth + 1, extendedWeak);

            visited[i] = false;
        }
    }

    static void check(int[] extendedWeak) {

        // 시작 취약점 위치를 하나씩 바꿈
        for (int start = 0; start < weakLen; start++) {

            int friend = 0;

            int coverage =
                    extendedWeak[start] + order[friend];

            for (int idx = start;
                 idx < start + weakLen;
                 idx++) {

                if (extendedWeak[idx] > coverage) {

                    friend++;

                    if (friend >= order.length) {
                        break;
                    }

                    coverage =
                            extendedWeak[idx] + order[friend];
                }
            }

            if (friend < order.length) {
                answer = Math.min(answer, friend + 1);
            }
        }
    }
}
