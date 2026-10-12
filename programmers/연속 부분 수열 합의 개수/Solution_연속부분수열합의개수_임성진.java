import java.util.*;
import java.io.*;

public class Solution_연속부분수열합의개수_임성진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
        
		int[] elements = new int[list.size()];
		
		elements = list.stream().mapToInt(i -> i).toArray();
		
		System.out.println(solution(elements));
	}
	
	static int solution(int[] elements) {
	    int n = elements.length;
	    int[] arr = new int[n * 2];
	    for (int i = 0; i < n * 2; i++) arr[i] = elements[i % n]; 

	    Set<Integer> set = new HashSet<>();
	    for (int len = 1; len <= n; len++) {
	        int sum = 0;
	        for (int i = 0; i < len; i++) sum += arr[i];
	        set.add(sum);

	        for (int i = 1; i < n; i++) {
	            sum = sum - arr[i - 1] + arr[i + len - 1];
	            set.add(sum);
	        }
	    }
	    return set.size();
	}
}