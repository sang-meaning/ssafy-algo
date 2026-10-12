import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_한빈이와_이윤찬 {
	static int N;
	static int[] snackweight;
	static int M;

	static int sum;
	static int TC;
	static int MAX;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb ;
		TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int T = 1; T <= TC; T++) {
			sb= new StringBuilder();
			MAX = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snackweight = new int[N];
			st= new StringTokenizer(br.readLine());
			
			for(int i = 0 ; i < N; i++) {
				snackweight[i]= Integer.parseInt(st.nextToken());
			}
			for(int j = 0 ; j< N; j++) {
				sum = snackweight[j];
				for( int k = j+1 ; k < N-2; k++) {
					if(sum+snackweight[k]>M)continue;
					MAX = Math.max(MAX, sum+snackweight[k]);
					sum = snackweight[j];
				}
			}
			sb.append("#"+T+" ").append(MAX != Integer.MIN_VALUE ? MAX : -1);
			
			System.out.println(sb);
			
			
		}
	}
}
