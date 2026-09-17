import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution
{
	
	static int N, maxCore, minLength, totalCnt;
	static int[][] map;
	
	static ArrayList<int[]> list;
	
	// 상 하 좌 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	
    public static void main(String args[]) throws Exception
    {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	N = Integer.parseInt(br.readLine());
        	
        	map = new int[N][N];
        	list = new ArrayList<>();
        	
        	maxCore = 0;
        	minLength = Integer.MAX_VALUE;
        	
        	for (int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
        		for (int j = 0; j < N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        			
        			// 가장자리가 아닌 코어는 리스트에 추가
        			if (map[i][j] == 1) {
        				if (i != 0 && i != N-1 && j != 0 && j != N-1) {
        					
        					list.add(new int[]{i, j});
        				}
        			}
				}
			}
    		
        	totalCnt = list.size();  // 가장자리가 아닌 코어 개수
        	        	
        	dfs(0, 0, 0);
        	        	
    		sb.append("#")
    		.append(test_case)
    		.append(" ")
    		.append(minLength)
    		.append("\n");
        }
        
        System.out.print(sb);
    }

	static void dfs(int idx, int connected, int length) {
		
		if (connected + totalCnt-idx<maxCore) return;  // totalCnt-idx: 남은 코어수
		
		if (idx == totalCnt) {
			if (connected > maxCore) {
				maxCore = connected;
				minLength = length;
			} else if (connected == maxCore) {
				minLength = Math.min(minLength, length);
			}
			return;
		}
		
		int[] cur = list.get(idx);
		
		int x = cur[0];
		int y = cur[1];
		
		for (int d = 0; d < 4; d++) {
			if (canConnect(x, y, d)) {
				// 전선 설치
				int wireLength = setStatus(x, y, d, 2);
				
				// 다음 프로세서로 이동
				dfs(idx+1, connected+1, length+wireLength);
				
				// 원상복구
				setStatus(x, y, d, 0);
			}
		}
		
		// 이 프로세서를 연결하지 않는 경우
        dfs(idx + 1, connected, length);
	}

	static boolean canConnect(int x, int y, int d) {
    	int nx = x + dx[d];
    	int ny = y + dy[d];
    	
    	while (nx>=0 && nx<N && ny>=0 && ny<N) {
    		if (map[nx][ny] != 0) return false;
    		
    		nx += dx[d];
    		ny += dy[d];    		
    	}
    	
    	return true;
    }
	
	static int setStatus(int x, int y, int d, int s) {
		int nx = x + dx[d];
    	int ny = y + dy[d];
    	
    	int length = 0;
    	
    	while (nx>=0 && nx<N && ny>=0 && ny<N) {
    		
    		map[nx][ny] = s;
    		length++;
    		
    		nx += dx[d];
    		ny += dy[d];    		
    	}
    	
    	return length;
	}
}