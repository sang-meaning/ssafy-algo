
import java.io.BufferedReader;
import java.util.*;
import java.io.InputStreamReader;

public class P1 {//프로세서 연결하기

    
	public static void main(String args[]) throws Exception{
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		 	int T =Integer.parseInt(st.nextToken());
	        for(int t=1;t<=T;t++) {
	        	
	        	
	      	  st = new StringTokenizer(br.readLine());

              int N = Integer.parseInt(st.nextToken());//정점
              int M = Integer.parseInt(st.nextToken());//간선
              
              
              List<Integer>[] graph = new ArrayList[N+1];
              
              for (int i = 1; i <= N; i++) {
            	    graph[i] = new ArrayList<>();
              }
              
              int[] D = new int[N+1];
              
              for(int i=0;i<M;i++) {
            	  st = new StringTokenizer(br.readLine());
            	  int a = Integer.parseInt(st.nextToken());
            	  int b = Integer.parseInt(st.nextToken());
            	  graph[a].add(b);//4->1 , 1->2
            	  D[b]++;
              }
              
              Queue<Integer> q = new ArrayDeque<>();
              
              for(int i=1;i<=N;i++) {
            	   if(D[i]==0)
            		   q.add(i);
              }
              
              StringBuilder sb = new StringBuilder();
              sb.append("#"+t+" ");
              while(!q.isEmpty()) {
            	  
            	  int cur=q.poll();
            	  sb.append(cur).append(" ");
            	  
            	  for(int i=0;i<graph[cur].size();i++) {
            		  int next=graph[cur].get(i);
            		   D[next]--;
            		   
            		   if(D[next]==0)
            			   q.add(next);
            	  }
            	 
            	  
              }
              System.out.println(sb);
              
	        }
		
	
	
	}
}
