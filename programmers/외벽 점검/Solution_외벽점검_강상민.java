class Solution {
    static int result = Integer.MAX_VALUE;
    public int solution(int n, int[] weak, int[] dist) {
        
        // 여러 시작점
        for (int s=0; s<weak.length; s++)
            dfs(0,0,0,s,weak,dist,n);
        
        
        if (result == Integer.MAX_VALUE) return -1;
        else return result;
    }
    
    // mask : weak 방문
    // mask2 : dist 방문
    static void dfs(int depth, int mask, int mask2, int s, int[] weak, int[] dist, int n) {
        int wl = weak.length;
        int dl = dist.length;
    
        if (mask == ((1<<wl)-1)) { // weak 전부 방문
            result = Math.min(result, depth);
            return;
        }
        
        if (depth +1 >= result) return; // 가지치기
        
        // 외벽 점검 시작점 index j로 고정
        int j = -1;
        for (int t=0; t<wl; t++) {
            int idx = (s+t)%wl;
            if ((mask & (1<<idx)) == 0) { // 해당 index 외벽점검 안했으면 거기서 시작
                j = idx;
                break;
            }
        }
        
        // dist 선택
        for (int i=0; i<dl; i++) {
            if ((mask2 & (1<<i)) != 0) continue;
            
            int power = dist[i];
            int newMask = mask;
            
            int start = weak[j];
            int end = start + power;
            
            for (int k=0; k<wl; k++) {
                if (end >= n) {
                    // 원형 넘어감
                    if (weak[k] >= start || weak[k] <= end-n) {
                        newMask = newMask | (1<<k);
                    }
                }
                else {
                    // 원형 안넘어감
                    if (weak[k] >= start && weak[k] <= end) {
                        newMask = newMask | (1<<k);
                    }
                }
            }
            dfs(depth+1, newMask, (mask2 | (1<<i)), s, weak, dist, n);
        }
    }
}