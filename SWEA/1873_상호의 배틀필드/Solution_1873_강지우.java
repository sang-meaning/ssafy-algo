import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	static int H, W;
	static char[][] map;
	
	static int tankX, tankY;
	static int dir;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static char[] tankShape = {'^', 'v', '<', '>'};
	
    public static void main(String args[]) throws Exception
    {
    	    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	H = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
    		
        	// 맵 상태
        	map = new char[H][W];
        	
        	for (int i = 0; i < H; i++) {
        		String line = br.readLine();
        		for (int j = 0; j < W; j++) {
        			map[i][j] = line.charAt(j);
        			
        			if (map[i][j] == '^') {
                        tankX = i;
                        tankY = j;
                        dir = 0;
                    } else if (map[i][j] == 'v') {
                        tankX = i;
                        tankY = j;
                        dir = 1;
                    } else if (map[i][j] == '<') {
                        tankX = i;
                        tankY = j;
                        dir = 2;
                    } else if (map[i][j] == '>') {
                        tankX = i;
                        tankY = j;
                        dir = 3;
                    }
				}
			}
        	
        	// 사용자 입력
        	int N = Integer.parseInt(br.readLine());
        	
        	String command = br.readLine();
        	
        	for (int i = 0; i < N; i++) {
				char cmd = command.charAt(i);
				
				if (cmd == 'U') {
                    move(0);
                } else if (cmd == 'D') {
                    move(1);
                } else if (cmd == 'L') {
                    move(2);
                } else if (cmd == 'R') {
                    move(3);
                } else if (cmd == 'S') {
                    shoot();
                }
			}
        	
        	sb.append("#").append(test_case).append(" ");
        	
        	for (int i = 0; i < H; i++) {
        		for (int j = 0; j < W; j++) {
        			sb.append(map[i][j]);
        		}
        		sb.append("\n");
        	}
        }
        
        System.out.print(sb);
    }
    
    static void move(int newDir) {
    	
    	dir = newDir;
    	map[tankX][tankY] = tankShape[dir];
    	
    	int nx = tankX + dx[dir];
		int ny = tankY + dy[dir];
		
		if (nx < 0 || nx >= H || ny < 0 || ny >= W) {
            return;
        }
		
		if (map[nx][ny] == '.') {

            map[tankX][tankY] = '.';

            tankX = nx;
            tankY = ny;

            map[tankX][tankY] = tankShape[dir];
        }
    }
    
    static void shoot() {
    	
    	int nx = tankX + dx[dir];
        int ny = tankY + dy[dir];
        
        while (nx >= 0 && nx < H && ny >= 0 && ny < W) {

            // 벽돌 벽
            if (map[nx][ny] == '*') {

                map[nx][ny] = '.';
                break;
            }

            // 강철 벽
            if (map[nx][ny] == '#') {

                break;
            }

            // 다음 칸으로 이동
            nx += dx[dir];
            ny += dy[dir];
        }
    }
}