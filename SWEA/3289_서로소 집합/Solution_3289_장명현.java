import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static int[] p;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
             
            p = new int[N+1];
            for (int i=1; i<=N; i++) p[i] = i;
 
            sb.append("#").append(test_case).append(' ');
             
            for (int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                 
                if (u > v) {
                    int temp = u;
                    u = v;
                    v = temp;
                }
                 
                if (a == 0) {
                    union(u, v);
                } else {
                    int ans = isUnion(u, v);
                    sb.append(ans);
                }
            }
             
            sb.append("\n");
        }
         
        System.out.println(sb);
    }
     
    public static int find(int x) {
        if (p[x] == x) {
            return x;
        } else {
            p[x] = find(p[x]);
            return p[x];
        }
    }
     
    public static int isUnion(int u, int v) {
        return find(u) == find(v) ? 1 : 0;
    }
     
    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) p[v] = u;
    }
}