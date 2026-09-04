import java.util.*;
import java.io.*;


public class Solution_9229_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 세팅 완료

            Arrays.sort(arr);

            int a = 0;
            int b = N-1;

            int max = -1;

            while(a < b) {
                if (a<0 || b<0) break;

                int sum = arr[a]+arr[b];

                if (sum < M) {
                    a++;
                    max = Math.max(max, sum);
                } else if (sum > M) {
                    b--;
                } else {
                    max = sum;
                    break; // 일찍 종료

                }

                //System.out.println("a: "+a+" b: "+b);

                
            }

            sb.append("#"+t+" "+max).append("\n");

        }

        System.out.println(sb);
        
    }
    
}
