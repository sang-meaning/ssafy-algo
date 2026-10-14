import java.util.*;
import java.io.*;

public class Solution_3307_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, N;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());

            arr = new long[N];
            long[] tail = new long[N];

            st = new StringTokenizer(br.readLine());

            for (int i=0; i<N; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            int len = 0;
            for (long x : arr) {
                int lo = 0;
                int hi = len;

                while(lo < hi) {
                    int mid = (lo + hi)/2;
                    if (tail[mid] < x) lo = mid + 1;
                    else hi = mid;
                }

                tail[lo] = x;
                if (len == lo) len++; // 갱신 x가 맨 뒤에 붙었을 때

            }
            sb.append("#"+t+" "+len).append("\n");
        }
        System.out.println(sb);

        
    }
    
}
