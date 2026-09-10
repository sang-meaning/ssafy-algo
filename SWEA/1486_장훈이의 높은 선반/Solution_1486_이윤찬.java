import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, B;
    static int[] tall;
    static int answer;

    static void DFS(int x, int sum) {
        if (B <= sum) {
            answer = Math.min(answer, sum-B);
            return;
        }

        if(x==N) return;

        DFS(x+1,sum+tall[x]);
        DFS(x+1, sum);
    }


        public static void main (String[]args)throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            StringTokenizer st;

            for (int t = 1; t <= T; t++) {

                answer = Integer.MAX_VALUE;
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                tall = new int[N];
                st = new StringTokenizer(br.readLine());
                for (int n = 0; n < N; n++) {
                    tall[n] = Integer.parseInt(st.nextToken());
                }

                DFS(0, 0);
                System.out.println("#"+t+" "+answer);
                }
            }
        }
