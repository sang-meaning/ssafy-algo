import java.util.*;
import java.io.*;
class Solution {
    static boolean[] visited;
    static int[][] matrix;
    static int N;
    static int min;
	public static void main(String args[]) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine().trim());
            matrix = new int[N][N];
            visited = new boolean[N];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, new ArrayList<>(), new ArrayList<>());
            System.out.println("#"+test_case+" "+min);
		}
	}
    public static void dfs(int depth, List<Integer> left, List<Integer> right){
        if(depth == N) {
            min = Math.min(min, calculate(left, right));
            return;
        }
        if(left.size() <= (N/2)){
            left.add(depth);
            dfs(depth+1, left, right);
            left.remove(left.size()-1);
        }
        //
        if(right.size() <= (N/2)){
          	right.add(depth);
			dfs(depth+1, left, right);
            right.remove(right.size()-1);  
        }
    }
    public static int calculate(List<Integer> left, List<Integer> right){
        int leftSum = 0;
        int rightSum = 0;
        for(int i=1; i<left.size(); i++){
            int a = left.get(i);
            for(int j=0; j<i; j++){
                int b = left.get(j);
                leftSum += matrix[a][b] + matrix[b][a];
            }
        }
        for(int i=1; i<right.size(); i++){
            int a = right.get(i);
            for(int j=0; j<i; j++){
                int b = right.get(j);
                rightSum += matrix[a][b] + matrix[b][a];
            }
        }
        return Math.abs(leftSum - rightSum);
    }
}