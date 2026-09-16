package ssafy.swea.kjw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1267_김정원 {
	
	static int V,E;
	static boolean[][] R;
	static List<Integer> startWork;
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testCase = 1; testCase <= 10; testCase++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	R = new boolean[V + 1][V + 1];
        	startWork = new ArrayList<>();
        	st = new StringTokenizer(br.readLine(), " ");
        	for (int i = 0; i < E; i++) {
        		int start = Integer.parseInt(st.nextToken());
        		int end = Integer.parseInt(st.nextToken());
        		R[start][end] = true;
        	}


        	// 시작 작업을 찾기
        	// 시작 작업은 열 기준으로 전부 false 즉 사전 작업이 없는 작업이다
        	for (int c = 1; c <= V; c++) {
        		boolean flag = true;
        		for (int r = 1; r <= V; r++) {
        			if (R[r][c] == true) {
        				flag = false;
        				break;
        			}
        		}
        		if (flag) startWork.add(c);
        	}
        	System.out.print("#" + testCase + " ");
        	boolean[] visited = new boolean[V+1];
        	for (int work : startWork) {
        		bfs_work(work, visited);
        	}
        	System.out.println();
        	// 끝까지 수행했다면 다음 시작 작업을 수행 -> 반복
//        	for (int i = 1; i <= V; i++) {
//        		for (int j = 1; j <= V; j++) {        			
//        			System.out.print(R[i][j] + " ");
//        		}
//        		System.out.println();
//        	}
            
        }
    }
    
    static void bfs_work(int start, boolean[] visited) {
    	
    	Queue<Integer> queue_work = new LinkedList<>();
    	queue_work.add(start);
    	
    	// 사전 작업이 필요없는 작업만 수행
    	while (!queue_work.isEmpty()) {
    		// 작업 큐에서 현재 수행할 작업을 꺼내온다
    		int work = queue_work.poll();
    		System.out.print(work + " ");
    		// 작업 상태를 수행 완료로 변경
    		visited[work] = true;
        	for (int next_work = 1; next_work <= V; next_work++) {
        		// 다음 작업이 있다면 그리고 수행되지 않은 작업인지
    			if (R[work][next_work] == true && visited[next_work] == false) {
    				// 다음 작업의 사전 작업이 있는지 여부를 확인
            		boolean flag = true;
            		for (int r = 1; r <= V; r++) {
            			// 현재 작업은 제외한 다른 사전 작업이 있는지 확인
            			if (r == work) continue;
            			// 사전 작업이 있다면
            			// 그 사전 작업의 상태가 완료되지 않았다면
            			if (R[r][next_work] == true && visited[r] == false) {
            				flag = false;
            				break;
            			}
            		}
            		// 사전 작업이 있다면 수행하지 않는다
            		if (flag == false) continue;
            		// 없으므로 큐에 넣는다
            		queue_work.add(next_work);
    			}
        	}
    	}
    }
}