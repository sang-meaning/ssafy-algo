import java.util.*;
import java.io.*;

public class Solution_1248_정영훈 {
    /*문제
    1. 각 정점, 간선이 주어진 그래프
    End. 임의의 두 정점의 공통조상

    */

    /* 풀이
    1. 정방향 트리 및 역방향 트리 생성
    2. 정방향 트리는 찾은 공통조상의 크기를 구함
    3. 역방향 트리는 임의 두 노드의 공통조상을 찾는데 구함
    4. 구하려는 두 노드별 각각의 거리배열을 생성해서 부모인 인덱스는 카운트값을 증가시켜 표기, 나머지는 -1, 본인은 0
    5. 1노드부터 두 배열을 비교하여 공통 조상이 있다면 카운트 값을 기준으로 최소공통 조상인지 확인?
    
    
    */
    static List<Integer>[] graph;
    static List<Integer>[] reverseGraph;
    static int[] firstParent;
    static int[] secondParent;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");    
            int n=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            int first=Integer.parseInt(st.nextToken());
            int second=Integer.parseInt(st.nextToken());
            //정점은 1부터 시작하기에 n+1
            graph=new ArrayList[n+1];
            reverseGraph=new ArrayList[n+1];
            firstParent=new int[n+1];
            secondParent=new int[n+1];
            for (int i = 0; i <= n; i++) {
                reverseGraph[i]=new ArrayList<>();
                graph[i]=new ArrayList<>();
                
            }
            Arrays.fill(firstParent, -1);
            Arrays.fill(secondParent, -1);
            st=new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < e; i++) {
                int parent=Integer.parseInt(st.nextToken());
                int child=Integer.parseInt(st.nextToken());
                graph[parent].add(child);
                reverseGraph[child].add(parent);
                
            }
            findParent(first, firstParent);
            findParent(second, secondParent);
            int answer=findEqualp(n);
            int size=findSize(answer);
            sb.append("#"+test_case+" "+answer+" "+size+"\n");
        }

        System.out.println(sb.toString());
    
    }
    public static void findParent(int node, int[] parent){
        int count=1;
        while(reverseGraph[node].size()!=0){
            parent[reverseGraph[node].get(0)]=count++;
            node=reverseGraph[node].get(0);
        }
        
    }
    public static int findEqualp(int n){
        int parent=-1;
        long maxCount=Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (firstParent[i]!=-1 && secondParent[i]!=-1) {
                long sum=firstParent[i]+secondParent[i];
                if(sum<maxCount){
                    parent=i;
                    maxCount=sum;
                }    
                
            }

            
        }
        return parent;
    }
    public static int findSize(int parent){
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(parent);
        int count=1;
        while(!queue.isEmpty()){
            int current=queue.poll();
            for(int next:graph[current]){
                queue.offer(next);
                count++;
            }
        }
        return count;
    }


}