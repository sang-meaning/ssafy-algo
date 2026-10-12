import java.util.*;
import java.io.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;

    static int[] indgree;
    static List<Integer>[] link;
    static Deque<Integer> q;

    public static void main(String[] args) throws Exception {

    	for(int tc=1;tc<=10;tc++) {
    		
    		st = new StringTokenizer(br.readLine());
    		
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());
    		
    		st = new StringTokenizer(br.readLine());
    		link = new ArrayList[N+1];
    		indgree = new int[N+1];
    		q = new ArrayDeque<>();
    		
    		for(int i=0;i<N+1;i++) {
    			link[i] = new ArrayList<>();
    		}
    		
    		for(int i=0;i<M;i++) {
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			
    			link[a].add(b);
    			
    			indgree[b]++;
    		}
    		
    		sb.append("#"+tc+ " ");
    		
    		for(int i=1;i<N+1;i++) {
    			if(indgree[i] == 0) {
    				q.add(i);
    				sb.append(i+" ");
    			}
    		}
    		
    		while(!q.isEmpty()) {
    			int temp = q.poll();
    			
    			for(int N : link[temp]) {
    				indgree[N]--;
    				if(indgree[N] == 0) {
    					q.add(N);
    					sb.append(N+" ");
    				}
    			}
    		}
    		sb.append("\n");
    	}
    	System.out.print(sb);
        
    }
}