class Solution {
    int[] newWeak;
    int[] distances;
    int[] selected;
    boolean[] visited;
    int weakCount;
    int minCount;

    public int solution(int n, int[] weak, int[] dist) {
        weakCount = weak.length;
        distances = dist;
        minCount = Integer.MAX_VALUE;

        newWeak = new int[weakCount * 2];
        selected = new int[dist.length];
        visited = new boolean[dist.length];

        // 원형을 직선으로 펼치기
        for (int i = 0; i < weakCount; i++) {
            newWeak[i] = weak[i];
            newWeak[i + weakCount] = weak[i] + n;
        }

        // 각 취약 지점을 시작점으로 설정
        for (int start = 0; start < weakCount; start++) {
            permutation(0, start);
        }

        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    // 친구 투입 순서 만들기
    public void permutation(int depth, int start) {
        if (depth == distances.length) {
            check(start);
            return;
        }

        for (int i = 0; i < distances.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            selected[depth] = i;
            permutation(depth + 1, start);
            visited[i] = false;
        }
    }

    // 현재 순서로 필요한 친구 수 검사
    public void check(int start) {
        int next = start;
        int finish = start + weakCount;

        for (int i = 0; i < distances.length; i++) {
            int end = newWeak[next] + distances[selected[i]];

            while (next < finish && newWeak[next] <= end) {
                next++;
            }

            if (next == finish) {
                minCount = Math.min(minCount, i + 1);
                return;
            }
        }
    }
}