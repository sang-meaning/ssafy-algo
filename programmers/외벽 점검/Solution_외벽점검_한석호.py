import java.util.Arrays;

class Solution_외벽점검_한석호 {
    static int minFriends;
    static int numWeak, numDist;
    static int[] expandedWeak;
    static int[] selectedDist;
    static boolean[] isVisited;

    public int solution(int n, int[] weak, int[] dist) {
        numWeak = weak.length;
        numDist = dist.length;

        // 원형 -> 선형으로 2배 확장
        expandedWeak = new int[numWeak * 2];
        for (int i = 0; i < numWeak; i++) {
            expandedWeak[i] = weak[i];
            expandedWeak[i + numWeak] = weak[i] + n;
        }

        // 친구 이동거리 오름차순 정렬
        Arrays.sort(dist);

        minFriends = Integer.MAX_VALUE;
        selectedDist = new int[numDist];
        isVisited = new boolean[numDist];

        // 친구들을 배치하는 순열 탐색 (dist에서 1명~numDist명 선택)
        perm(0, dist);

        return minFriends == Integer.MAX_VALUE ? -1 : minFriends;
    }

    // 친구들 투입 순서(순열) 생성
    static void perm(int depth, int[] dist) {
        if (depth == numDist) {
            checkCoverage();
            return;
        }

        for (int i = 0; i < numDist; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                selectedDist[depth] = dist[i];
                perm(depth + 1, dist);
                isVisited[i] = false;
            }
        }
    }

    // 현재 친구 순서(selectedDist)로 모든 weak 지점을 커버할 수 있는지 확인
    static void checkCoverage() {
        // 취약 지점 각각을 시작점으로 지정
        for (int start = 0; start < numWeak; start++) {
            int friendIdx = 0; // 현재 투입된 친구 인덱스
            
            // 첫 번째 친구가 커버할 수 있는 최대 위치
            int coverablePos = expandedWeak[start] + selectedDist[friendIdx];

            // start부터 시작해서 numWeak개의 점검 필요 지점을 확인
            for (int i = start; i < start + numWeak; i++) {
                // 현재 친구의 커버 범위를 벗어난 경우 -> 다음 친구 투입
                if (expandedWeak[i] > coverablePos) {
                    friendIdx++;
                    
                    // 더 이상 투입할 친구가 없으면 탐색 종료
                    if (friendIdx == numDist) {
                        break;
                    }
                    
                    // 다음 친구의 커버 범위 갱신
                    coverablePos = expandedWeak[i] + selectedDist[friendIdx];
                }
            }

            // 모든 취약 지점을 점검한 경우 (friendIdx < numDist 인 경우)
            if (expandedWeak[start + numWeak - 1] <= coverablePos) {
                minFriends = Math.min(minFriends, friendIdx + 1);
            }
        }
    }
}