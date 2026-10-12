import java.util.*;

class Solution {
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        
        int V = n; // 노드
        int E = costs.length; // 간선
        
        parent = new int[V]; // index 0부터
        for (int i=0; i<V; i++)
            parent[i] = i;
        
        Arrays.sort(costs, (a,b) -> Integer.compare(a[2], b[2]));
        
        int sum = 0;
        int count = 0; // 선택한 간선 수
        
        for (int i=0; i<E; i++) { // 비용 작은 간선부터
            if (union(costs[i][0], costs[i][1])) {
                // 사이클 없으면 선택
                sum += costs[i][2];
                count++;
                
            }
            
            // 노드 수 - 1 개만 선택가능
            if (count == V-1) break; 
        }
        
        return sum;
        
    }
    
    static int find(int x) {
        if (parent[x] == x) return x;
        
        return parent[x] = find(parent[x]);
    }
    
    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if (pa == pb) return false;
        
        parent[pb] = pa;
        return true;
    }
}