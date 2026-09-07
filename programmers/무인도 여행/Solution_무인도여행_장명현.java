import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(String[] maps) {
    	int N = maps.length;
    	int M = maps[0].length();
    	int[] dx = {0, 1, 0, -1};
    	int[] dy = {1, 0, -1, 0};
    	int[][] arr = new int[N][M];
    	
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<M; j++) {
    			arr[i][j] = maps[i].charAt(j) == 'X' ? -1 : maps[i].charAt(j) - '0';
    		}
    	}
    	
        ArrayList<Integer> ans = new ArrayList<>();
    	
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<M; j++) {
    			if (arr[i][j] == -1) continue;

    			int cnt = arr[i][j];
    			ArrayDeque<int[]> q = new ArrayDeque<>();
    			q.add(new int[] {i, j});
    			arr[i][j] = -1;

    			while (!q.isEmpty()) {
    				int[] f = q.poll();
    				int x = f[0];
    				int y = f[1];
    				
    				for (int d=0; d<4; d++) {
    					int nx = x + dx[d];
    					int ny = y + dy[d];
    					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
    					if (arr[nx][ny] == -1) continue;
    					
    					q.add(new int[] {nx, ny});
    					cnt += arr[nx][ny];
    					arr[nx][ny] = -1;
    				}
    			}
    			ans.add(cnt);
    		}
    	}
    	
    	if (ans.isEmpty()) ans.add(-1);
    	Collections.sort(ans);
    	
    	int[] answer = new int[ans.size()];
    	for (int i=0; i<ans.size(); i++) {
    		answer[i] = ans.get(i);
    	}
        
        return answer;
    }
}