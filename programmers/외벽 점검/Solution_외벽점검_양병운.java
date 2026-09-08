class Solution {
    boolean[] visitedDist;
    int[] visited;
    int min;
    int N;
    public int solution(int n, int[] weak, int[] dist) {
        /**
            외벽의 둘레 n
            공사 점검 시간 한 시간으로 제한
            친구들마다 점검 가능한 거리가 다름
            시계 방향 반시계 방향으로 외벽을 따라 이동
            외벽의 길이 n, 취약 지점의 위치가 담긴 배열 weak, 각 친구가 1시간 동안 이동할 수 있는 거리가 담긴 배열  dist가 매개변수로 주어질 때
             보내야 하는 친구 수의 최소값
        */
        
        visitedDist = new boolean[dist.length];
        visited = new int[n];
        min = Integer.MAX_VALUE;
        N = n;
        for(int i=0; i<weak.length; i++){
            int weakPoint = weak[i];
            for(int j=0; j<dist.length; j++){
                visitedDist[j] = true;
                dfs(weakPoint, j, 1, weak, dist);
                visitedDist[j] = false;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    public int findNextWeak(int[] weak){
        for(int point : weak){
            if(visited[point] == 0) return point;
        }
        return -1;
    }
    public void doVisited(int start,int search){
        for(int i=start; i<=start+search; i++) {
            visited[i%N]++;
        }
    }
    public void doUnVisited(int start,int search){
        for(int i=start; i<=start+search; i++) {
            visited[i%N]--;
        }
    }
    public void dfs(int start, int people, int count, int[] weak, int[] dist){
        int search = dist[people];
        doVisited(start, search);
        int nextWeak = findNextWeak(weak);
        if(nextWeak == -1){
            min = Math.min(min, count);
        }else{
            for(int i=0; i<dist.length; i++){
                if(visitedDist[i]) continue;
                    visitedDist[i] = true;
                    dfs(nextWeak, i, count+1, weak, dist);
                    visitedDist[i] = false;
            }
        }
        doUnVisited(start, search);
    }
}