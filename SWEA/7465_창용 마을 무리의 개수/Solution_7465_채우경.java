
import java.io.*;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
   
	static int[] p;

    public static void main(String[] args) throws Exception {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        
        for (int t = 1; t <= T; t++) {
        	int cnt=0;
        	st=new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            p=new int[N+1];
            for(int i=1;i<=N;i++) {
            	p[i]=i;
            }
            
            
            for(int i=0;i<M;i++) {
            	st=new StringTokenizer(br.readLine());
            	
            	int x = Integer.parseInt(st.nextToken());
            	int y = Integer.parseInt(st.nextToken());
            	
            	union(x,y);
            }
            
            for(int i=1;i<=N;i++) {
            	if(find(i)==i)
            		cnt++;
            }
            
            
            
            
            System.out.println("#" + t + " " + cnt);
        }

           
    }
    
    static int find(int x) {
    	if(p[x]==x)
    		return x;
    	
    	return p[x]=find(p[x]);
    }
    
    static void union(int x,int y) {
    	
    	if(find(x)!=find(y))
    		p[find(y)]=find(x);
    	
    }
    
}