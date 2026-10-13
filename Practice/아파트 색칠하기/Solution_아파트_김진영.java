import java.io.*;
import java.util.*;

public class Solution_아파트_김진영 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		arr[0] = 2;
		arr[1] = 3;
		
		for(int i=2;i<N;i++) {
			arr[i] = arr[i-1] + arr[i-2];
		}
		
		System.out.println(arr[N-1]);
	}
}
