import java.io.*;
import java.util.*;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static PriorityQueue<int[]> pq1;
    static PriorityQueue<int[]> pq2;
    static PriorityQueue<int[]> pq3;
    static ArrayList<int[]>[] graph;
    static int INF = 0x3f3f3f3f / 4;
    static int[] dist;
    static int[] dist2;
    static int[] dist3;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        int T = fares.length;
        dist = new int[n+1];
        dist2 = new int[n+1];
        dist3 = new int[n+1];
        Arrays.fill(dist,INF);
        Arrays.fill(dist2,INF);
        Arrays.fill(dist3,INF);
        
        for(int i=0;i<T;i++){
            int aa = fares[i][0];
            int bb = fares[i][1];
            int cc = fares[i][2];
            
            graph[aa].add(new int[] {bb,cc});
            graph[bb].add(new int[] {aa,cc});
        }

        pq1 = new PriorityQueue<>((aa,bb) -> Integer.compare(aa[1],bb[1]));
        pq2 = new PriorityQueue<>((aa,bb) -> Integer.compare(aa[1],bb[1]));
        pq3 = new PriorityQueue<>((aa,bb) -> Integer.compare(aa[1],bb[1]));
        
        //dist1 -A-
        dist[a] = 0;
        pq1.add(new int[] {a,0});
        
        while(!pq1.isEmpty()){
            int temp[] = pq1.poll();
            int cur = temp[0];
            int cur_cost = temp[1];
            
            for(int[] edge : graph[cur]){
                int next = edge[0];
                int next_cost = edge[1];
                
                int price = dist[cur] + next_cost;
                if(dist[next] > price){
                    dist[next] = price;
                    pq1.add(new int[] {next,price});
                }
            }
        }
        
        
        //dist2 -B-
        dist2[b] = 0;
        pq2.add(new int[] {b,0});
        
        while(!pq2.isEmpty()){
            int temp[] = pq2.poll();
            int cur = temp[0];
            int cur_cost = temp[1];
            
            for(int[] edge : graph[cur]){
                int next = edge[0];
                int next_cost = edge[1];
                
                int price = dist2[cur] + next_cost;
                if(dist2[next] > price){
                    dist2[next] = price;
                    pq2.add(new int[] {next,price});
                }
            }
        }
        
        //dist3 -S-
        dist3[s] = 0;
        pq3.add(new int[] {s,0});
        
        while(!pq3.isEmpty()){
            int temp[] = pq3.poll();
            int cur = temp[0];
            int cur_cost = temp[1];
            
            for(int[] edge : graph[cur]){
                int next = edge[0];
                int next_cost = edge[1];
                
                int price = dist3[cur] + next_cost;
                if(dist3[next] > price){
                    dist3[next] = price;
                    pq3.add(new int[] {next,price});
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        //S->중간 지점까지.
        for(int i=1;i<n+1;i++){
            int car1 = dist3[i];
            
            int car2 = dist[i];
            int car3 = dist2[i];
            
            answer = Math.min(answer,car1+car2+car3);
            //중간지점부터 A까지
            //중간지점부터 B까지
            
            //S->중간지점 + 중간지점+A + 중간지점+B (합계 최소)
        }

        return answer;
    }
}
