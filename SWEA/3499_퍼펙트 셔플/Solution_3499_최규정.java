import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N;
            N = sc.nextInt();
            int half;
            half = (N+1) / 2;
            
            Queue<String> answer = new ArrayDeque<>();
            Queue<String> q1 = new ArrayDeque<>();
            Queue<String> q2 = new ArrayDeque<>();

            
            for(int i = 0; i < N; i ++){
                String temp = sc.next();
                
				if(i < half){
                	q1.offer(temp);
                }else{
                	q2.offer(temp);
                }
            }
            
            while(!q1.isEmpty() || !q2.isEmpty()){
            	if(!q1.isEmpty()){
                	answer.offer(q1.poll());
                }
                if(!q2.isEmpty()){
                	answer.offer(q2.poll());
                }
            }
            
          System.out.print("#"+test_case);  
			while (!answer.isEmpty()) {
 			   System.out.print(" " + answer.poll());
			}
            System.out.println();  
            
		}
	}
}