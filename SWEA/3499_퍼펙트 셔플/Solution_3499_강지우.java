import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int N = Integer.parseInt(br.readLine());
			
			String[] card_name = new String[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				card_name[i] = st.nextToken();
			}
			
			int mid = (int) Math.round(N/2.0);
			int len = card_name.length;
			
			String[] card_A = new String[mid];
			for (int i=0; i<mid; i++) {
				card_A[i] = card_name[i];
			}
			
			String[] card_B = new String[len - mid];
			for (int i=mid; i<len; i++) {
				card_B[i - mid] = card_name[i];
			}
			
			int idx = 0;
			String[] new_card = new String[N];
			for (int i=0; i<mid; i++) {
				if (i < card_A.length) {
					new_card[idx++] = card_A[i];
				}
				if (i < card_B.length) {
					new_card[idx++] = card_B[i];
				}
			}
			System.out.println("#" + test_case + " " + String.join(" ", new_card));

		}
	}
}