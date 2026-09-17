package samsung01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution1 {
	static int N;
	public static void main(String args[]) throws Exception{
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


//	        int T= Integer.parseInt(br.readLine());

	        for(int t=1;t<=10;t++) {

	        	  StringTokenizer st = new StringTokenizer(br.readLine());

	              int V = Integer.parseInt(st.nextToken());//정점
	              int E = Integer.parseInt(st.nextToken());//간선
	              
	              st = new StringTokenizer(br.readLine());
	              int[] D = new int[V + 1]; // 진입 차수 배열 1부터니까 +1 
	              List<Integer>[] graph = new ArrayList[V + 1];
	              
	              for (int i = 1; i <= V; i++) {
	                  graph[i] = new ArrayList<>();//배열리스트 생성 
	              }
	              
	              for(int i=0;i<E;i++) {
	            	  int A = Integer.parseInt(st.nextToken());
	            	  int B = Integer.parseInt(st.nextToken());
	            	  
	            	  graph[A].add(B);//1개씩 받아 연결
	            	  D[B]++;// 앞에 정점이 있는 애들은 받는 만큼 ++
	              }
	              
	              Queue<Integer> queue = new ArrayDeque<>();
	              
	              for(int i=1;i<=V;i++) {
	            	  if(D[i]==0)
	            		  queue.add(i);
	              }
	              
	              StringBuilder sb = new StringBuilder();
	              sb.append("#").append(t).append(" ");
	              while(!queue.isEmpty()) {
	            	  
	            	  int cur=queue.poll();
	            	  sb.append(cur).append(" ");
	            	  
	            	  for (int next : graph[cur]) {  
	            			  D[next]--;
	            		  
	            		  if(D[next]==0)
	            			  queue.add(next);
	            	  }
	              }
	              
	              /*
	               * 위상정렬
	               * 순서대로 나아가야함
	               * 자신에게 간선이 없는애들 -> 전제조건이 없어 먼저 실행이 가능한 애들
	               * D[i]가 0일때 큐에 넣고, 끝낸 노드와 연결된 다음 노드 d[next]--;
	               * 진입차수가 줄어 0이 되면 큐에 넣기
	               * */
	              
	              System.out.println(sb);
	        }
	}
}
