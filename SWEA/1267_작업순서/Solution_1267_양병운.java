import java.util.*;
import java.io.*;
class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for(int test_case = 1; test_case <= T; test_case++) {
            /**
                해야할 V개의 작업, 선행 관계가 존재함
                선행 관계를 나타내는 그래프가 주어짐
                작업 순서를 찾는 프로그램 작성
            */
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점의 수
            int E = Integer.parseInt(st.nextToken()); // 간선의 수
             
            List<Integer>[] toConn = new List[V+1]; //내가 구독하고 있는 것
            for(int i=1; i<=V; i++) toConn[i] = new ArrayList<>();
            int[] conns = new int[V+1];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<E; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                toConn[from].add(to);
                conns[to]++;
            }
            Queue<Integer> queue = new ArrayDeque<>();
            for(int i=1; i<=V; i++) if(conns[i]==0) queue.add(i);
            List<Integer> result = new ArrayList<>();
            while(!queue.isEmpty()){
                int v = queue.poll();
                result.add(v);
                //내가 구독하고 있는 것들
                for(int x : toConn[v]) {
                    conns[x]--;
                    if(conns[x]==0) queue.add(x);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#"+test_case+" ");
            for(int i=0; i<result.size(); i++) sb.append(result.get(i)+" ");
            System.out.println(sb.toString().trim());
        }
    }
}