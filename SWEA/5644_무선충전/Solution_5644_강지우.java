import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	static int M, A;
	static int[] moveA, moveB;
	static BC[] bcList;
	
	// 0: 그대로 1: 상 2: 우 3: 하 4: 좌
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
	
	static class BC {
		int x, y, c, p;

		BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	
    public static void main(String args[]) throws Exception
    {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
        	A = Integer.parseInt(st.nextToken());
        	
        	moveA = new int[M];
        	moveB = new int[M];
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < M; i++) {
        		moveA[i] = Integer.parseInt(st.nextToken());
			}
        	
        	st = new StringTokenizer(br.readLine());
        	for (int i = 0; i < M; i++) {
        		moveB[i] = Integer.parseInt(st.nextToken());
			}
        	
        	bcList = new BC[A];
        	
        	for (int i = 0; i < A; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		int c = Integer.parseInt(st.nextToken());
        		int p = Integer.parseInt(st.nextToken());
        		
        		bcList[i] = new BC(x, y, c, p);
			}
        	
        	int ax = 1;
        	int ay = 1;
        	
        	int bx = 10;
        	int by = 10;
        	
        	int answer = 0;
        	
        	// 0초에도 충전
        	answer += getMaxCharge(ax, ay, bx, by);
        	
    		for (int i = 0; i < M; i++) {
    			
    			ax += dx[moveA[i]];
    			ay += dy[moveA[i]];
    			
    			bx += dx[moveB[i]];
    			by += dy[moveB[i]];
    			
    			answer += getMaxCharge(ax, ay, bx, by);
			}
        	        	
    		sb.append("#").append(test_case).append(" ")
    		  .append(answer).append("\n");
        }
        
        System.out.print(sb);
    }
    
    // 현재 위치에서 BC를 사용할 수 있는지
    static boolean canUse(int x, int y, BC bc) {
    	
    	int distance = Math.abs(x - bc.x) + Math.abs(y - bc.y);
    	
    	return distance <= bc.c;
    }
    
    // 현재 위치에서 A + B가 얻을 수 있는 최대 충전량
    static int getMaxCharge(int ax, int ay, int bx, int by) {
    	int max = 0;
    	
    	// A가 사용할 BC: i
    	for (int i = 0; i < A; i++) {
    		// B가 사용할 BC: j
			for (int j = 0; j < A; j++) {
				
				boolean possibleA = canUse(ax, ay, bcList[i]);
				boolean possibleB = canUse(bx, by, bcList[j]);
				
				int charge = 0;
				
				if (possibleA && possibleB) {
					if (i == j) {
						charge = bcList[i].p;
					} else {
						charge = bcList[i].p + bcList[j].p;
					}
				} else if (possibleA) {
					charge = bcList[i].p;
				} else if (possibleB) {
					charge = bcList[j].p;
				}
				
				max = Math.max(max, charge);
				
			}
		}
    	
    	return max;
    }
}