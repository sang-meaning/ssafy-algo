import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int testcase = 1; testcase <= 10; testcase++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> q = new ArrayDeque<>();
			for(int i=0; i<8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			int i = 1;
			while(true) {
				if(i > 5) i = 1;
				int last = q.poll() - (i++);
				if(last <= 0) {
					last = 0;
					q.offer(last);
					break;
				}
				q.offer(last);
			}
			
			StringBuilder sb = new StringBuilder();
			for(int j: q) {
				sb.append(j).append(" ");
			}
			System.out.println("#" + testcase + " " + sb);
		}
	}
}
