class Solution {
    static int N;
    static int[] weakLine;
    static int[] friends;
    static boolean[] visited;
    static int[] order;
    static int answer;
    public int solution(int n, int[] weak, int[] dist) {
        
        N = weak.length;
        answer = Integer.MAX_VALUE;

        // 1. 원형 벽을 직선으로 펼치기
        weakLine = new int[N * 2];

        for (int i = 0; i < N; i++) {
            weakLine[i] = weak[i];
            weakLine[i + N] = weak[i] + n;
        }

        friends = dist;

        visited = new boolean[dist.length];
        order = new int[dist.length];

        // 2. 친구 이동거리의 모든 순열 생성
        permutation(0);

        return answer == Integer.MAX_VALUE ? -1 : answer;
        
    }
     public void permutation(int depth) {

        if (depth == friends.length) {

            // 만들어진 친구 순서로 모든 시작점 검사
            check();

            return;
        }

        for (int i = 0; i < friends.length; i++) {

            if (!visited[i]) {

                visited[i] = true;
                order[depth] = friends[i];

                permutation(depth + 1);

                visited[i] = false;
            }
        }
    }
    
    
    public void check(){
        
  // 취약점 각각을 시작점으로 잡는다.
        for (int start = 0; start < N; start++) {

            int friendIndex = 0;

            // 첫 번째 친구가 시작 취약점에서 출발했을 때
            int cover = weakLine[start] + order[friendIndex];

            // 시작점부터 취약점 N개 확인
            for (int i = start; i < start + N; i++) {

                // 현재 친구가 이 취약점까지 갈 수 없다면
                if (weakLine[i] > cover) {

                    // 다음 친구 투입
                    friendIndex++;

                    // 사용할 친구가 더 없다면 실패
                    if (friendIndex == friends.length) {
                        break;
                    }

                    // 새 친구는 현재 취약점에서 시작
                    cover = weakLine[i] + order[friendIndex];
                }
            }

            // friendIndex는 0부터 시작하므로 실제 친구 수는 +1
            if (friendIndex < friends.length) {
                answer = Math.min(answer, friendIndex + 1);
            }
        }
    }
}