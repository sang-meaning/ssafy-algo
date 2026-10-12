import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int T,N;
	
	static Deque<String> q;
	static Deque<String> d1;
	static Deque<String> d2;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		// a c e
		// b d f
		for(int test_case=1;test_case<=T;test_case++) {
			
			N = Integer.parseInt(br.readLine());
			
			q = new ArrayDeque<>();

			d1 = new ArrayDeque<>();
			d2 = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<(N+1)/2;i++) {
				d1.add(st.nextToken());
			}
			for(int i=(N+1)/2;i<N;i++) {
				d2.add(st.nextToken());
			}
			
			int index = 0;
			
			while(!q.isEmpty()) {
				if(index % 2 == 0) {
					d1.add(q.poll());
				}else {
					d2.add(q.poll());
				}
				index++;
			}
			
			sb.append("#"+test_case+" ");
			while(!d1.isEmpty()) {
				String temp = d1.poll();
				sb.append(temp+" ");
				if(!d2.isEmpty()) {
					temp = d2.poll();
					sb.append(temp+" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
}

