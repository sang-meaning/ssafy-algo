import java.math.BigInteger;
import java.io.*;
import java.util.*;

public class SWEA3260 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

        // String 변환 사용
//			String a = st.nextToken();
//			String b = st.nextToken();
//
        // 정답을 담을 sb
//			StringBuilder sum = new StringBuilder();
//			
//			int i = a.length() - 1;
//			int j = b.length() - 1;
//			
//			int carry = 0;
//      
        // 마지막 carry가 무시당하지 않게
//			while (i >= 0 || j >= 0 || carry > 0) {
//				int aa = 0;
//				int bb = 0;
//
//				if (i >= 0) {
//					aa = a.charAt(i) - '0';
//					i--;
//				}
//
//				if (j >= 0) {
//					bb = b.charAt(j) - '0';
//					j--;
//				}
//
//				int value = aa + bb + carry;
//
//				sum.append(value % 10);
//				carry = value / 10;
//			}
//
//			sb.append("#").append(tc).append(" ").append(sum.reverse()).append("\n");
//		}

    // BigInteger 사용
			BigInteger A = new BigInteger(st.nextToken());
			
			BigInteger B = new BigInteger(st.nextToken());
			
			sb.append("#").append(tc).append(" ").append(A.add(B)).append("\n");

		}
		System.out.print(sb);

	}

}
