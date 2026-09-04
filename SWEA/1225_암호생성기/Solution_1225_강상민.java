import java.util.*;
import java.io.*;

public class Solution_1225_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) throws IOException {
        T = 10;

        for (int t=1; t<=T; t++) {
            int tresh = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            int[] arr = new int[8];

            int min = Integer.MAX_VALUE;

            for (int i=0; i<8; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i]);
            }

            // 5 사이클 지나면 모든 수 15씩 뺌

            int iter = (min / 15) - 1; // 최솟값이 15의 배수일 수 있으므로 iter 하나 안전하게 빼두기

            for (int i=0; i<8; i++) {
                arr[i] -= iter * 15;
            }

            // 남은 수로 1~5 뺌

            Deque<Integer> q = new ArrayDeque<>();

            for (int temp : arr) q.add(temp);
            iter = 1;

            while(true) {
                int cur = q.poll();

                int nxt = cur - iter;
                if (nxt <= 0) nxt = 0;

                q.add(nxt);
                iter++;
                if (iter == 6) iter = 1;

                if (nxt == 0) break;

            }

            sb.append("#"+t+" ");

            while(!q.isEmpty()) {
                sb.append(q.poll()+" ");
            }

            sb.append("\n");


        }

        System.out.println(sb);

        
    }
    
}
