class Solution {
    int answer;
    int[] extendedWeak;
    int[] order;
    boolean[] used;

    public int solution(int n, int[] weak, int[] dist) {
        int length = weak.length;
        answer = dist.length + 1;

        extendedWeak = new int[length * 2];
        for (int i = 0; i < length; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + length] = weak[i] + n;
        }

        order = new int[dist.length];
        used = new boolean[dist.length];

        permutation(0, dist, length);

        return answer > dist.length ? -1 : answer;
    }

    void permutation(int depth, int[] dist, int length) {
        if (answer == 1) return;

        if (depth == dist.length) {
            check(length);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            order[depth] = dist[i];
            permutation(depth + 1, dist, length);
            used[i] = false;
        }
    }

    void check(int length) {
        for (int start = 0; start < length; start++) {
            int count = 1;
            int end = extendedWeak[start] + order[0];
            boolean possible = true;

            for (int i = start; i < start + length; i++) {
                if (extendedWeak[i] <= end) continue;

                count++;
                if (count > order.length) {
                    possible = false;
                    break;
                }

                end = extendedWeak[i] + order[count - 1];
            }

            if (possible) {
                answer = Math.min(answer, count);
            }
        }
    }
}