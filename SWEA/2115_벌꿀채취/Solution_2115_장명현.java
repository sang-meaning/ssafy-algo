import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];
            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;

            // 두 줄 탐색
            // 열 선택
            for (int i=0; i<N; i++) {
                for (int j=i+1; j<N; j++) {
                    // 1번 행 선택
                    for (int a=0; a+M<=N; a++) {
                        // 1번 M개 선택
                        ArrayList<Integer> sel1 = new ArrayList<>();

                        for (int ai=a; ai<a+M; ai++) {
                            sel1.add(arr[i][ai]);
                        }

                        int sum1 = comb(sel1, C);

                        // 2번 행 선택
                        for (int b=0; b+M<=N; b++) {
                            // 2번 M개 선택
                            ArrayList<Integer> sel2 = new ArrayList<>();
                            for (int bi=b; bi<b+M; bi++) {
                                sel2.add(arr[j][bi]);
                            }

                            int sum2 = comb(sel2, C);

                            answer = Math.max(answer, sum1 + sum2);
                        }
                    }
                }
            }

            // 한 줄 탐색
            // 열 선택
            for (int i=0; i<N; i++) {
                for (int aj=0; aj+2*M<=N; aj++) {
                    ArrayList<Integer> sel1 = new ArrayList<>();
                    for (int ai=aj; ai<aj+M; ai++) {
                        sel1.add(arr[i][ai]);
                    }

                    int sum1 = comb(sel1, C);

                    for (int bj=aj+M; bj+M<=N; bj++) {
                        ArrayList<Integer> sel2 = new ArrayList<>();
                        for (int bi=bj; bi<bj+M; bi++) {
                            sel2.add(arr[i][bi]);
                        }

                        int sum2 = comb(sel2, C);

                        answer = Math.max(answer, sum1 + sum2);
                    }
                }
            }

            sb.append('#').append(test_case).append(' ');
            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    static int comb(ArrayList<Integer> sel, int C) {
        int max = 0;
        int size = sel.size();

        for (int mask = 0; mask < (1 << size); mask++) {
            int cnt = 0;
            int sum = 0;

            for (int i = 0; i < size; i++) {
                if ((mask & (1 << i)) != 0) {
                    int x = sel.get(i);
                    cnt += x;
                    sum += x * x;
                }
            }

            if (cnt <= C) {
                max = Math.max(max, sum);
            }
        }

        return max;
    }
}