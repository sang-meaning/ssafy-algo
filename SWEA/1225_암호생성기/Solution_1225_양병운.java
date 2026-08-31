import java.util.*;
import java.io.*;
class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		for(int test_case = 1; test_case <= 10; test_case++)	{
            T=sc.nextInt();
			Queue<Integer> queue = new ArrayDeque<>();
            for(int i=0; i<8; i++) queue.add(sc.nextInt());
            int cnt = 1;
            while(queue.peek() - cnt > 0){
                int temp = queue.poll();
                queue.add(temp - cnt);
                cnt = (cnt%5)+1;
            }
            queue.poll();
            queue.add(0);
            StringBuilder sb = new StringBuilder();
            sb.append("#"+test_case+" ");
            while(!queue.isEmpty()){
                sb.append(queue.poll()).append(" ");
            }
            System.out.println(sb.toString().trim());
		}
	}
}