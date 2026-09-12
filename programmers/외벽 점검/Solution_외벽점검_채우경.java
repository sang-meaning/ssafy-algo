import java.util.Arrays;

class Solution {
    static int answer;
    static int N;
    static int[] Weak, Dist;

    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        Weak = weak;
        Dist = dist;
        answer = Integer.MAX_VALUE;

        // 이동 거리가 큰 친구부터 사용하기 위해 정렬
        Arrays.sort(Dist);

        // 원형 구조를 일자 형태로 2배 확장
        int len = weak.length;
        int[] extendedWeak = new int[len * 2];
        for (int i = 0; i < len; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + len] = weak[i] + n;
        }

        // 투입할 친구의 수 (1명부터 dist.length명까지)
        for (int cnt = 1; cnt <= dist.length; cnt++) {
            int[] selectedFriends = new int[cnt];
            boolean[] visited = new boolean[dist.length];
            
            // 이동 거리가 큰 친구들부터 cnt명 선택하는 순열 구하기
            perm(0, cnt, selectedFriends, visited, extendedWeak, len);
            
            // 답을 찾은 경우 최소 인원이므로 즉시 반환
            if (answer != Integer.MAX_VALUE) {
                return answer;
            }
        }

        return -1;
    }

    // 순열 생성 후 점검 가능 여부 체크
    private void perm(int depth, int targetCnt, int[] selected, boolean[] visited, int[] extendedWeak, int len) {
        if (answer != Integer.MAX_VALUE) return; // 이미 최솟값을 찾았으면 가지치기

        if (depth == targetCnt) {
            // 모든 시작점에 대해 점검 가능한지 확인
            for (int start = 0; start < len; start++) {
                int friendIdx = 0;
                int coverage = extendedWeak[start] + selected[friendIdx];

                boolean success = true;
                for (int i = start; i < start + len; i++) {
                    // 현재 친구가 커버하지 못하는 취약 지점인 경우
                    if (extendedWeak[i] > coverage) {
                        friendIdx++;
                        // 준비된 친구를 모두 소진했으면 실패
                        if (friendIdx == targetCnt) {
                            success = false;
                            break;
                        }
                        // 다음 친구 투입
                        coverage = extendedWeak[i] + selected[friendIdx];
                    }
                }

                if (success) {
                    answer = targetCnt;
                    return;
                }
            }
            return;
        }

        // 이동 거리가 큰 친구들(배열 뒤쪽)부터 순열 탐색
        for (int i = Dist.length - 1; i >= 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = Dist[i];
                perm(depth + 1, targetCnt, selected, visited, extendedWeak, len);
                visited[i] = false;
            }
        }
    }
}