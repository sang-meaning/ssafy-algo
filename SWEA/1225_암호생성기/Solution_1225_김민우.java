import java.util.*;
import java.io.*;

class Solution
{
    static BufferedReader br;
    static StringTokenizer st;
    static Queue<Integer> que;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++)
		{
			que = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 8; j++){
                int a= Integer.parseInt(st.nextToken());
            	que.offer(a);
            }
            
            boolean zero_flag = false;
            int dec = 1;
            
            while(!zero_flag){
                int frontNum = que.poll();
                frontNum = (frontNum-dec <= 0)?0:(frontNum-dec);
                zero_flag = (frontNum==0)?true:false;
                que.offer(frontNum);
                dec = (dec == 5)?1:(++dec);
            }
            
            System.out.printf("#%d ", t);
            while(!que.isEmpty()){
                int a = que.poll();
                System.out.printf("%d ", a);
            }
            System.out.println();
		}
            
	}
}