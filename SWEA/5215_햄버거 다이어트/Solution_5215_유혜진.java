import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_유혜진 {
    static int N;           // 재료의 수
    static int L;           // 제한 칼로리
    static int[] taste;     // 맛 점수 배열
    static int[] calorie;   // 칼로리 배열
    static int maxTaste;    // 최고 맛 점수 저장 변수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            taste = new int[N];
            calorie = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                taste[i] = Integer.parseInt(st.nextToken());
                calorie[i] = Integer.parseInt(st.nextToken());
            }

            maxTaste = 0; // 최고점 초기화
            
            // 0번 재료부터 탐색 시작 (초기 상태: 맛 0점, 칼로리 0)
            dfs(0, 0, 0);

            // 결과 출력
            System.out.println("#" + tc + " " + maxTaste);
        }
    }

    static void dfs(int index, int sumTaste, int sumCalorie) {
        // [가지치기 / 백트래킹] 제한 칼로리를 초과하면 더 이상 탐색하지 않고 종료
        if (sumCalorie > L) {
            return;
        }

        // [탈출 조건] 모든 재료의 선택(O/X)을 마쳤을 때
        if (index == N) {
            maxTaste = Math.max(maxTaste, sumTaste); // 최고 맛 점수 갱신
            return;
        }

        // 1. 현재 재료(index)를 햄버거에 '넣는' 경우
        dfs(index + 1, sumTaste + taste[index], sumCalorie + calorie[index]);

        // 2. 현재 재료(index)를 햄버거에 '안 넣는' 경우
        dfs(index + 1, sumTaste, sumCalorie);
    }
}