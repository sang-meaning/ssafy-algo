import java.io.*;
import java.util.*;
class Solution {
    static int N, lo, hi, min;
    static int[] arr;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			/**
            	상: K2보다 큰 과일
                중: K1 <= a <k2
                하: a < k1
                각 등급마다 개수 lo이상 hi이하여야 함
                가장 과일이 많이 속한 등급과 과일이 가장 적게 속한 등급의 개수 차이가 최소로 되도록 하고 차이의 최소값
                조건을 만족하는 분류 값이 없다면 -1
            */
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            lo = Integer.parseInt(st.nextToken());
            hi = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr);
            min = Integer.MAX_VALUE;
            for(int i=0; i<N-1; i++){
                for(int j=i+1; j<N; j++){
                    dfs(arr[i], arr[j]);
                    dfs(arr[i]+0.5, arr[j]);
                    dfs(arr[i], arr[j]+0.5);
                    dfs(arr[i]+0.5, arr[j]+0.5);
                }
            }
            System.out.println("#"+test_case+" "+(min==Integer.MAX_VALUE?-1:min));
		}
	}
    public static void dfs(double k1, double k2){
        int[] c = new int[3];
        for(int i=0; i<N; i++){
            if(arr[i] < k1) c[0]++;
            else if(arr[i] >= k1 && arr[i] < k2) c[1]++;
            else c[2]++;
        }
        for(int i=0; i<3; i++){
            if(c[i] < lo || c[i] > hi) return;
        }
        int local_max = Math.max(c[0], Math.max(c[1], c[2]));
        int local_min = Math.min(c[0], Math.min(c[1], c[2]));
        min = Math.min(min, local_max-local_min);
    }
}