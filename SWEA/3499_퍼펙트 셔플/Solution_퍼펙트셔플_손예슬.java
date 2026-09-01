
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            String[] arr = new String[n];
            for(int i = 0; i<n; i++)
                arr[i] = st.nextToken();

            int mid = (n+1)/2;
            System.out.print("#" + testcase + " ");
            for(int i = 0; i<mid; i++){
                System.out.print(arr[i] + " ");
                if(mid + i < n)
                    System.out.print(arr[mid+i] + " ");
            }
            System.out.println("");
        }
    }
}
