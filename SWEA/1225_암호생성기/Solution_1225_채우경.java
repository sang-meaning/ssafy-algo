import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int t = 1; t <= 10; t++) {
            
            String tcStr = br.readLine();
            if (tcStr == null) break; // 입력이 끝나면 종료
            int tc = Integer.parseInt(tcStr.trim());

            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Integer> queue = new ArrayDeque<>();

            for (int i = 0; i < 8; i++) {
                queue.add(Integer.parseInt(st.nextToken()));
            }

            int cnt = 0;
            while (true) {
                int first = queue.poll();
                cnt = (cnt % 5) + 1;
                first -= cnt;

                if (first <= 0) {
                    first = 0;
                    queue.offer(first);
                    break;
                }

                queue.offer(first);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc);
            for (int num : queue) {
                sb.append(" ").append(num);
            }
            
            System.out.println(sb.toString());
        }
    }
}