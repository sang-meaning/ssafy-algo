import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int T,N;
	
	static Deque<Integer> q;
	
	public static void main(String[] args) throws Exception{

		while(true) {
			T = Integer.parseInt(br.readLine());
			
			q = new ArrayDeque<>();

			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<8;i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			int n=1;
			int value = 1;
			while(n != 0) {
				n = q.pollFirst();
				
				if(n-value <= 0) {
					q.addLast(0);
					break;
				}
				q.addLast(n-value);
				value %= 5;
				value++;
			}
			
			sb.append("#");
			sb.append(T);
			sb.append(" ");
			while(!q.isEmpty()) {
				sb.append(q.poll()+ " ");
			}

			sb.append("\n");
			
			if(T == 10) {
				System.out.print(sb);
				break;
			}
		}
		
	}
	
}

