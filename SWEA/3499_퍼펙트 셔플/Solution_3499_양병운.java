import java.util.*;
import java.io.*;
class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<String> q1 = new ArrayDeque<>();
            Queue<String> q2 = new ArrayDeque<>();
            Queue<String> result= new ArrayDeque<>();
            if(N%2==0){
                for(int i=0; i<N/2; i++) q1.add(st.nextToken());
                for(int i=0; i<N/2; i++) q2.add(st.nextToken());
            }else{
                for(int i=0; i<(N/2)+1; i++) q1.add(st.nextToken());
                for(int i=0; i<N/2; i++) q2.add(st.nextToken());
            }
            while(!q1.isEmpty() || !q2.isEmpty()){
                if(!q1.isEmpty()) result.add(q1.poll());
                if(!q2.isEmpty()) result.add(q2.poll());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#"+test_case+" ");
            while(!result.isEmpty()){
                sb.append(result.poll()+" ");
            }
            System.out.println(sb.toString().trim());
		}
	}
}