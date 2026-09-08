import java.util.*;
import java.io.*;

public class Solution_1225_임성진 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();                                

			Deque<Integer> q = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}

			outer:
			while (true) {
				for (int k = 1; k <= 5; k++) {            
					int front = q.poll() - k;

					if (front <= 0) {
						q.offer(0);                       
						break outer;                      
					}
					q.offer(front);
				}
			}

			sb.append('#').append(tc);
			while (!q.isEmpty()) {
				sb.append(' ').append(q.poll());
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}