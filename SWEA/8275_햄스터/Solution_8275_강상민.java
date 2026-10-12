import java.util.*;
import java.io.*;


public class Solution_8275_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int N,X,M; // 우리수, 각 우리는 1~X, 기록수
    static int[] l,r,s; // l~r번 햄스터 총 s마리
    static int[] hamster; // dfs 에서 사용할 값, 각 우리의 햄스터 수
    static int[] result;
    static boolean flag; // 한번이라도 result 갱신되면 true 이고, false 면 -1 출력
    static int sumHamster;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            l = new int[M];
            r = new int[M];
            s = new int[M];

            hamster = new int[N];
            result = new int[N];
            sumHamster = -1;
            flag = false;

            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());

                l[i] = Integer.parseInt(st.nextToken());
                r[i] = Integer.parseInt(st.nextToken());
                s[i] = Integer.parseInt(st.nextToken());


            }

            // 세팅완료

            // 햄스터 최대로 하되, 앞 우리에 수가 최대한 적도록

            dfs(0);

            sb.append("#"+t+" ");

            if (sumHamster == -1) {
                sb.append(-1);
            } else {
                for (int r : result) {
                    sb.append(r+" ");
                }
            }

            sb.append("\n");
        
        
        }

        // tc 종료
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if (depth == N) {
            // 모든 l,r,s 에 대해 hamster가 조건을 만족 하고, 햄스터 수 최대일 때 정답 갱신

            boolean ok = true; // 불만족인 경우 false로 바꿈

            for (int i=0; i<M; i++) {
                // l, r, s 가지고
                int ll = l[i]-1;
                int rr = r[i]-1;

                int ham = 0;
                for (int j=ll; j<=rr; j++) {
                    ham += hamster[j];
                }

                if (ham != s[i]) { // 그만 보기
                    ok = false;
                    break;
                }
            }

            if (ok) { // M개의 조건 만족하고
                // sumHamster 보다 전체 햄스터 수가 크면
                int sum = 0;

                for (int h : hamster) sum += h;

                if (sum > sumHamster) { // >= 이면 사전순 위배
                    sumHamster = sum;
                    // result 갱신
                    for (int aa=0; aa<N; aa++) {
                        result[aa] = hamster[aa];
                    }

                }

            }

            return;

        }


        for (int i=0; i<=X; i++) {
            // 맨 앞 우리 수가 최대한 적도록해야 사전순
            hamster[depth] = i;
            dfs(depth+1);
        }
    }
    
}
