import java.util.*;
import java.io.*;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
     
    static int T,N,M;
    static int[] parents;
    static int result;
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
             
            parents = new int[N+1];
             
            for(int i=0;i<N+1;i++) {
                parents[i] = i;
            }
             
            sb.append("#"+tc+" ");
             
            for(int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                 
                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                 
                if(command == 0) {
                    union(a,b);
                }else {
                    if(find(a) == find(b)) {
                        sb.append(1);
                    }else {
                        sb.append(0);
                    }
                }
            }
            sb.append("\n");
             
        }
        System.out.print(sb);
    }
    private static int find(int x) {
        if(parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
         
        if(a != b) {
            parents[b] = a;
        }
         
    }
}