import java.util.*;
import java.io.*;

class Solution {


    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        for (int test_case = 1; test_case <= 1; test_case++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int v=Integer.parseInt(st.nextToken());
            int e=Integer.parseInt(st.nextToken());
            List<Integer>[] edges=new ArrayList[v+1];
            int[] vList=new int[v+1];
            for (int i = 0; i < v+1; i++) {
                edges[i]=new ArrayList<>();
            }
            st=new StringTokenizer(br.readLine()," ");
            for (int i = 0; i < e; i++) {
                int from=Integer.parseInt(st.nextToken());
                int to=Integer.parseInt(st.nextToken());
                edges[from].add(to);
                vList[to]+=1;
            }
            Queue<Integer> queue=new ArrayDeque<>();
            boolean[] visited=new boolean[v+1];
            for (int i = 1; i < vList.length; i++) {
                if(vList[i]==0){
                    queue.offer(i);
                }
            }
            sb.append("#"+test_case+" ");
            while(!queue.isEmpty()){
                int current=queue.poll();
                sb.append(current+" ");
                for(int next : edges[current]){
                    if(vList[next]-1==0){
                        queue.offer(next);
                        visited[next]=true;
                        vList[next]=0;
                    }else{
                        vList[next]-=1;
                    }
                }

            }
            sb.append("\n");


        }
        System.out.println(sb.toString());


    }
    

}