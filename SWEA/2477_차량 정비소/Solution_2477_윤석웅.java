import java.io.*;
import java.util.*;

class Solution {
    static int N, M, K, A, B;
    static int[] receptionTime, repairTime, arrival;
    static int[] receptionUser, receptionEnd;
    static int[] repairUser, repairEnd;
    static int[] usedReception, usedRepair;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            receptionTime = new int[N+1];
            repairTime = new int[M+1];
            arrival = new int[K+1];

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) receptionTime[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=M; i++) repairTime[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++) arrival[i] = Integer.parseInt(st.nextToken());

            int ans = simulate();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static int simulate() {
        receptionUser = new int[N+1];
        receptionEnd = new int[N+1];
        repairUser = new int[M+1];
        repairEnd = new int[M+1];
        usedReception = new int[K+1];
        usedRepair = new int[K+1];

        Queue<Integer> receptionWait = new ArrayDeque<>();
        Queue<Integer> repairWait = new ArrayDeque<>();

        int time = 0;
        int nextCustomer = 1;
        int done = 0;

        // 매 시간마다 완료 -> 도착 -> 새 배정 순서로 상태를 갱신한다.
        while(done < K) {
            // 같은 시각 접수 완료 고객은 접수 창구 번호가 작은 순서로 정비 대기
            for(int i=1; i<=N; i++) {
                if(receptionUser[i] != 0 && receptionEnd[i] == time) {
                    repairWait.offer(receptionUser[i]);
                    receptionUser[i] = 0;
                }
            }

            // 정비 완료 고객 수를 세고 창구를 비운다.
            for(int i=1; i<=M; i++) {
                if(repairUser[i] != 0 && repairEnd[i] == time) {
                    repairUser[i] = 0;
                    done++;
                }
            }

            // 현재 시각에 도착한 고객은 고객 번호 순서대로 접수 대기
            while(nextCustomer <= K && arrival[nextCustomer] == time) {
                receptionWait.offer(nextCustomer);
                nextCustomer++;
            }

            // 빈 접수 창구는 번호가 작은 순서로 배정
            for(int i=1; i<=N && !receptionWait.isEmpty(); i++) {
                if(receptionUser[i] == 0) {
                    int customer = receptionWait.poll();
                    receptionUser[i] = customer;
                    receptionEnd[i] = time + receptionTime[i];
                    usedReception[customer] = i;
                }
            }

            // 정비 대기 순서대로 번호가 작은 빈 정비 창구에 배정
            for(int i=1; i<=M && !repairWait.isEmpty(); i++) {
                if(repairUser[i] == 0) {
                    int customer = repairWait.poll();
                    repairUser[i] = customer;
                    repairEnd[i] = time + repairTime[i];
                    usedRepair[customer] = i;
                }
            }

            time++;
        }

        int sum = 0;

        // A 접수 창구와 B 정비 창구를 모두 이용한 고객 번호의 합
        for(int i=1; i<=K; i++) {
            if(usedReception[i] == A && usedRepair[i] == B)
                sum += i;
        }

        return sum == 0 ? -1 : sum;
    }
}
