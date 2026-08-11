import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int testcase = 1; testcase <= T; testcase++){
            int N = Integer.parseInt(br.readLine());
         	StringTokenizer st = new StringTokenizer(br.readLine());
            int[] trees = new int[N];
            int target = 0;
            // 나무 배열 완성 + 목표 나무 높이 구하기
            for(int i = 0; i < N; i++){
                trees[i] = Integer.parseInt(st.nextToken());
                target = Math.max(target, trees[i]);
            }
            // 필요한 1, 2 개수 구하기
            int one = 0;
            int two = 0;
            for(int tree : trees){
                int diff = target - tree;
                one += diff % 2; // 한 tree에서 one은 1개 또는 0개
                two += diff / 2;
            }
            // 분포 고르게 -> 2 = 1 + 1
            while(one + 2 <= two){ // one이 two 보다 2개 이상 적을 때 바꿈
                one += 2;
                two--;
            }
            // 날짜 구하기
            int answer = 0;
            if(one > two) answer = one *2 -1;
            else answer = two *2;

            System.out.println("#" + testcase + " " + answer);
        }
    }
}

// 각 나무에서 목표 수까지 1, 2로 쪼갬, 필요한 개수 구함
// 1과 2를 고르게 분포해야 최적화됨 -> 2로 끝날떄(2가 많을 때) 앞의 1이 2개 이상 있으면 2를 1로 바꿈
// 1 두개를 2로? -> 안됨, 이때 나온 두개 1은 다른 숫자에서 파생됨 (맨처음 쪼갤때 한 숫자당 1은 1개 이하만 나옴)

