import java.util.*;
import java.io.*;
class Solution {
    static List<Integer>[] childs, parents;
    static boolean[] visited;
    static int N, M;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++)  {
            /**
                두 학생 키 비교한 결과 결과의 일부
                키가 몇 번째인지 확인할 수 있는 사람이 있음
                그 사람이 몇 명이 있는지 사람 수 출력
            */
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            childs = new List[N+1];
            parents = new List[N+1];
            visited = new boolean[N+1];
            for(int i=1; i<N+1; i++) childs[i] = new ArrayList<>();
            for(int i=1; i<N+1; i++) parents[i] = new ArrayList<>();
            for(int i=0; i<M; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                //a가 b보다 작다
                childs[b].add(a);
                parents[a].add(b);
            }
            int count = 0;
            for(int i=1; i<N+1; i++){
                if(check(i)) count++;
            }
            System.out.println("#"+test_case+" "+count);
        }
    }
    public static boolean check(int student){
        boolean[] visited = new boolean[N+1];
        Queue<Integer> findChild = new ArrayDeque<>();
        findChild.add(student);
        while(!findChild.isEmpty()){
            int a = findChild.poll();
            for( int s : childs[a]){
                if(visited[s]) continue;
                visited[s] = true;
                findChild.add(s);
            }
        }
        Queue<Integer> findParent = new ArrayDeque<>();
        findParent.add(student);
        while(!findParent.isEmpty()){
            int a = findParent.poll();
            for(int p : parents[a]){
                if(visited[p]) continue;
                visited[p] = true;
                findParent.add(p);
            }
        }
        for(int i=1; i<=N; i++){
            if(i == student) continue;
            if(!visited[i]) return false;
        }
        return true;
    }
}