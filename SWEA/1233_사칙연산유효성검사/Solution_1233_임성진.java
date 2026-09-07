import java.util.*;
import java.io.*;

public class Solution_1233_임성진 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
		    int n = Integer.parseInt(br.readLine().trim());
		    int answer = 1;

		    for (int i = 0; i < n; i++) {
		        StringTokenizer st = new StringTokenizer(br.readLine());
		        int tokenCount = st.countTokens();
		        st.nextToken();
		        String value = st.nextToken();
		        boolean isNumber = Character.isDigit(value.charAt(0));

		        if (isNumber && tokenCount != 2) answer = 0;
		        if (!isNumber && tokenCount != 4) answer = 0;
		    }
		    sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.print(sb);
	}
}