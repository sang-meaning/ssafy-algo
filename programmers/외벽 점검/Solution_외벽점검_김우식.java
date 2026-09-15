import java.util.*;

class Solution {

    static int answer;
    static int len;
    static int[] weak2;

    public int solution(int n, int[] weak, int[] dist) {

        len = weak.length;

        // 원형 외벽을 직선처럼 보기 위해 취약점 배열을 2배로 늘림
        // 예) n = 12, weak = [1, 5, 6, 10]
        // -> [1, 5, 6, 10, 13, 17, 18, 22]
        weak2 = new int[len * 2];

        for (int i = 0; i < len; i++) {
            weak2[i] = weak[i];
            weak2[i + len] = weak[i] + n;
        }

        // 친구를 모두 써도 못 막는 경우를 구분하기 위해
        // 가능한 최대 친구 수보다 1 크게 초기화
        answer = dist.length + 1;

        // selected : 친구를 투입할 순서
        // visited : 순열 생성 시 이미 선택한 친구인지 확인
        int[] selected = new int[dist.length];
        boolean[] visited = new boolean[dist.length];

        // 친구들의 모든 투입 순서를 순열로 확인
        permutation(0, dist, selected, visited);

        // 끝까지 answer가 바뀌지 않았다면 모든 친구를 써도 불가능
        if (answer == dist.length + 1) {
            return -1;
        }

        return answer;
    }

    static void permutation(int cnt, int[] dist, int[] selected, boolean[] visited) {

        // 친구들의 투입 순서 하나가 완성된 경우
        if (cnt == dist.length) {

            // 어느 취약점부터 점검을 시작할지 모든 경우 확인
            for (int start = 0; start < len; start++) {

                // 첫 번째 친구부터 시작
                int friendCnt = 1;

                // 현재 친구가 어디까지 점검할 수 있는지
                // 첫 번째 친구는 start 위치에서 출발
                int position = weak2[start] + selected[0];

                // start부터 취약점 개수만큼 확인하면 원형 한 바퀴를 확인하게 됨
                for (int i = start; i < start + len; i++) {

                    // 현재 친구가 이 취약점까지 도달하지 못하는 경우
                    if (weak2[i] > position) {

                        // 다음 친구 투입
                        friendCnt++;

                        // 모든 친구를 써도 부족한 경우
                        if (friendCnt > selected.length) {
                            break;
                        }

                        // 다음 친구는 아직 점검하지 못한 현재 취약점에서 출발
                        position = weak2[i] + selected[friendCnt - 1];
                    }
                }

                // 지금까지 구한 최소 친구 수와 비교
                answer = Math.min(answer, friendCnt);
            }

            return;
        }

        // 친구들의 투입 순서를 순열로 생성
        for (int i = 0; i < dist.length; i++) {

            // 이미 선택한 친구는 다시 선택하지 않음
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            selected[cnt] = dist[i];

            // 다음 순서의 친구 선택
            permutation(cnt + 1, dist, selected, visited);

            // 다른 순열을 만들기 위해 선택 해제
            visited[i] = false;
        }
    }
}