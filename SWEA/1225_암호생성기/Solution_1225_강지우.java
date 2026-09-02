import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int test_case = 1; test_case <= 10; test_case++)
		{
		
			int T = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> queue = new LinkedList<>();
			
			int[] data = new int[8];
			for (int i=0; i<8; i++) {
				data[i] = Integer.parseInt(st.nextToken());
				queue.offer(data[i]);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(T);
			
			int i = 1;
			while (true) {
				int first_data = queue.poll();
				
				int code = first_data-i;
				i++;
				if (i == 6) i = 1;
				
				if (code <= 0) {
					queue.offer(0);
					break;
				}
				queue.offer(code);
				
			}
			
			for (Integer num : queue) {
				sb.append(" ").append(num);
			}
			
			System.out.println(sb.toString());

		}
	}
}