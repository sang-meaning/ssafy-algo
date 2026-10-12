import java.util.*;
import java.io.*;

class Solution {
    /** 풀이
     * 크루스칼 알고리즘사용
     * 하나의 노드에서 끝노드가 아닌 전체간선의 최소값을 구ㅏㅎ는문제
     * 흠냐
     * 
     * 
     * **/ 
    static int[] parent;
    static List<Edge> edges;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n=Integer.parseInt(br.readLine());
            int[][] map=new int[n][2];
            edges=new ArrayList<>();
            parent=new int[n+1];
            for (int i = 0; i < n; i++) {
                parent[i]=i;
                
            }
            StringTokenizer st;
            for (int col = 0; col < 2; col++) {
                st=new StringTokenizer(br.readLine()," ");
                for (int row = 0; row < n; row++) {
                    map[row][col]=Integer.parseInt(st.nextToken());
                    
                }
                
            }
          
            double e=Double.parseDouble(br.readLine());
            for (int from = 0; from < n; from++) {
                for (int to = from+1; to < n; to++) {
                    long weight=calDist(map[from], map[to]);
                    edges.add(new Edge(from, to, weight));
                    
                }
                
            }
            Collections.sort(edges);

            long result=0;
            int count=0;

            for (Edge edge : edges) {
                if (union(edge.from, edge.to)) {
                    result += edge.weight;
                    count++;

                    // MST의 간선 개수는 항상 V - 1
                    if (count == n - 1) {
                        break;
                    }
                }
            }
            sb.append("#"+test_case+" "+Math.round(result*e)+"\n");

            
        }
        System.out.println(sb.toString());
        
    }
    
    public static boolean union(int a, int b){
        int rootA=find(a);
        int rootB=find(b);
        if(rootA==rootB){
            return false;
        }
         if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }

        return true;
    }
    public static int find(int child){
        if(parent[child]==child) return child;
        return parent[child]=find(parent[child]);
    }

    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        long weight;
        Edge(int from, int to, long weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o){
            return Long.compare(this.weight, o.weight);
        }
    }

    public static long calDist(int[] fromPos, int[] toPos){
        long dx = (long) fromPos[0] - toPos[0];
        long dy = (long) fromPos[1] - toPos[1];

        return dx * dx + dy * dy;
    }


}
