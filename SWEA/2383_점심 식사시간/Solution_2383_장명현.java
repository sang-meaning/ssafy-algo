import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 부르트포스
class Solution {

	// TC 내부 변수
	public static int N, pcnt, scnt, answer;
	public static int[] person, stair, len;
	public static int[][] dist;
	public static ArrayList<Integer> stair1, stair2;
	
	
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		// Execute TC
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			// 변수 저장 및 선언
			N = Integer.parseInt(br.readLine());
			pcnt = 0;
			scnt = 0;
			answer = Integer.MAX_VALUE;
			
			person = new int[10];
			stair = new int[2];
			len = new int[2];
			dist = new int[2][10];
			
			// 사람 위치와 계단 길이, 위치 저장
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					int x = Integer.parseInt(st.nextToken());
					if (x == 1) {
						person[pcnt++] = i*10 + j;
					} else if (x > 0) {
						len[scnt] = x;
						stair[scnt++] = i*10 + j;
					}
				}
			}
			
			// 각 계단과 사람 사이 거리 계산
			for (int i=0; i<2; i++) {
				for (int j=0; j<pcnt; j++) {
					dist[i][j] = Math.abs(stair[i]/10 - person[j]/10) + Math.abs(stair[i]%10 - person[j]%10);
				}
			}
			
			// 각 계단에 도착하는 모든 경우의 수 고려
			for (int t=0; t<Math.pow(2, pcnt); t++) {
				stair1 = new ArrayList<>();
				stair2 = new ArrayList<>();
				
				// 각 계단으로 사람 배정
				for (int j=0; j<pcnt; j++) {
					if (((t >> j) & 1) == 1) stair1.add(dist[0][j] + 1);
					else stair2.add(dist[1][j] + 1);

				}
				
				// 각 경우에서, 두 계단에 도착하는 시간 정렬
				Collections.sort(stair1);
				Collections.sort(stair2);
				

				int time1, time2;
				
				// 각 계단의 모든 사람이 내려가는 시간 고려
				if (!stair1.isEmpty()) {
					// 3명까지는 전부 들어가도 되니, 앞의 최대 3명이 내려간 시간이 걸린 시간 
					time1 = stair1.get(Math.min(2, stair1.size()-1)) + len[0];
					
					// 이후에는 맨 앞 사람이 전부 통과하는 시간에 더해주기
					for (int i=3; i<stair1.size(); i++) {
						int now = Math.max(stair1.get(i), stair1.get(i-3) + len[0]);
						time1 = now + len[0];
					}
				} else time1 = 0;
				
				// 다른 계단도 마찬지로 계산
				if (!stair2.isEmpty()) {
					time2 = stair2.get(Math.min(2, stair2.size()-1)) + len[1];
					for (int i=3; i<stair2.size(); i++) {
						int now = Math.max(stair2.get(i), stair2.get(i-3) + len[1]);
						time2 = now + len[1];
					}
				} else time2 = 0;
				
				// 두 계단 중 더 오래걸린 시간의 최솟값을 갱신
				answer = Math.min(answer, Math.max(time1, time2));
			}
			
			// Add Output
			sb.append('#').append(test_case).append(' ').append(answer).append('\n');
		}
		
		// Print Output
		System.out.println(sb);
	}
}