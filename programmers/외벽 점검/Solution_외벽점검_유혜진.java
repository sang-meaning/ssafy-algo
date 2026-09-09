import java.util.*;

class Solution {
    static int N, MinDist;
    static int[] Weak, Dist;
    static int[] Choice;       // 친구들을 세울 순서 (순열 결과)
    static boolean[] Visited;  // 순열 방문 체크
    static int minAns = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        Weak = weak;
        Dist = dist;
        MinDist = dist.length;

        // 친구들을 이동 거리가 큰 순서대로 정렬 (가지치기 및 최적화를 위함)
        Arrays.sort(Dist);

        Visited = new boolean[MinDist];
        Choice = new int[MinDist];

        // 1명부터 MinDist명까지 친구 수를 늘려가며 탐색
        for (int i = 1; i <= MinDist; i++) {
            permutation(0, i);
            // 이미 i명으로 외벽 점검에 성공했다면, 최소 명수이므로 즉시 종료!
            if (minAns != Integer.MAX_VALUE) return minAns;
        }

        return -1; // 끝까지 전부 점검할 수 없는 경우
    }

    // [순열] count명의 친구를 배치하는 모든 순서를 만듦
    static void permutation(int depth, int count) {
        if (depth == count) {
            // 결정된 친구 순서로 모든 외벽 점검이 가능한지 확인
            check(count);
            return;
        }

        for (int i = MinDist - 1; i >= 0; i--) { // 거리가 긴 친구부터 뽑기
            if (!Visited[i]) {
                Visited[i] = true;
                Choice[depth] = Dist[i];
                permutation(depth + 1, count);
                Visited[i] = false; // 백트래킹 원상복구!
            }
        }
    }

    // 결정된 친구들(count명)로 모든 취약 지점 점검이 가능한지 확인
    static void check(int count) {
        int wLen = Weak.length;

        // 원형으로 연결되어 있으므로, 각 취약 지점을 '시작점'으로 모두 시도
        for (int start = 0; start < wLen; start++) {
            int friendIdx = 0; // 몇 번째 친구인지
            int finishPos = Weak[start] + Choice[friendIdx]; // 첫 친구가 커버할 수 있는 위치

            boolean success = true;

            // start점부터 출발해서 모든 취약 지점을 커버할 수 있는지 확인
            for (int i = start; i < start + wLen; i++) {
                int currentWeak = Weak[i % wLen];

                // 원형 처리를 위한 좌표 보정 (시작점보다 뒤에 있는 지점은 +N)
                if (i >= wLen && currentWeak < Weak[start]) {
                    currentWeak += N;
                }

                // 현재 취약 지점이 커버 범위를 벗어난 경우 -> 다음 친구 투입
                if (currentWeak > finishPos) {
                    friendIdx++;
                    if (friendIdx >= count) { // 준비된 친구를 다 썼는데도 커버 불가!
                        success = false;
                        break;
                    }
                    finishPos = currentWeak + Choice[friendIdx]; // 새 친구의 커버 범위
                }
            }

            if (success) {
                minAns = Math.min(minAns, count);
                return;
            }
        }
    }
}