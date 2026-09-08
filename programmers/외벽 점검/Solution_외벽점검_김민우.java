class Solution {
static int N;
    static int[] weak;
    static int[] dist;

    static int[] weakLine;
    static int[] order;
    static boolean[] visited;

    static int answer;

    public int solution(int n, int[] weak, int[] dist) {

        N = n;
        this.weak = weak;
        this.dist = dist;

        answer = dist.length + 1;

        // 1. 원형 weak를 2배 길이로 늘려서 일자로 표현
        makeWeakLine();

        // 2. 친구 투입 순서의 모든 순열 생성
        visited = new boolean[dist.length];
        order = new int[dist.length];

        permutation(0);

        // 모든 친구를 사용해도 불가능
        if (answer == dist.length + 1)
            return -1;

        return answer;
    }

    // 원형 외벽을 일자로 펴기
    static void makeWeakLine() {

        int len = weak.length;

        weakLine = new int[len * 2];

        for (int i = 0; i < len; i++) {
            weakLine[i] = weak[i];
            weakLine[i + len] = weak[i] + N;
        }
    }

    // 친구들의 투입 순서 만들기
    static void permutation(int depth) {

        if (depth == dist.length) {
            check();
            return;
        }

        for (int i = 0; i < dist.length; i++) {

            if (visited[i])
                continue;

            visited[i] = true;
            order[depth] = dist[i];

            permutation(depth + 1);

            visited[i] = false;
        }
    }

    // 현재 친구 순서(order)로 외벽 점검 가능한지 검사
    static void check() {

        int weakCnt = weak.length;

        // 각 취약점을 시작점으로 설정
        for (int start = 0; start < weakCnt; start++) {

            int friendIdx = 0;

            // 첫 번째 친구가 커버할 수 있는 마지막 위치
            int cover = weakLine[start] + order[friendIdx];

            for (int idx = start; idx < start + weakCnt; idx++) {

                // 현재 친구가 이 취약점을 커버하지 못함
                if (weakLine[idx] > cover) {

                    // 다음 친구 투입
                    friendIdx++;

                    // 모든 친구를 다 사용함
                    if (friendIdx == dist.length)
                        break;

                    // 새 친구가 현재 취약점부터 출발
                    cover = weakLine[idx] + order[friendIdx];
                }
            }

            /*
             * friendIdx는 0부터 시작
             * 따라서 실제 사용 친구 수는 friendIdx + 1
             */
            if (friendIdx < dist.length) {
                answer = Math.min(answer, friendIdx + 1);
            }
        }
    }
}