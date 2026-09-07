import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static List<String> answer;
	static List<String> buffer;
	static List<String> buffer2;
	static List<String> buffer1;
	static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			answer = new ArrayList<>();
			buffer = new ArrayList<>();
			buffer1 =new ArrayList<>();
			buffer2 = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				buffer.add(st.nextToken());
			}
			shuffle(buffer);
			
			System.out.print("#"+t+" ");
			for(int i = 0; i< N; i++) {
				System.out.print(answer.get(i)+" ");
			}
			System.out.println();
		}
	}

	static void shuffle(List<String> buffer) {
		if (N % 2 == 0) {
			for (int i = 0; i < N; i++) {
				if (i < N / 2) {
					buffer1.add(buffer.get(i));
				} else {
					buffer2.add(buffer.get(i));
				}
			}
			for (int j = 0; j < N / 2; j++) {
				answer.add(buffer1.get(j));
				answer.add(buffer2.get(j));
			}
		} else {
			for (int i = 0; i < N; i++) {
				if (i < N / 2 +1) {
					buffer1.add(buffer.get(i));
				} else {
					buffer2.add(buffer.get(i));
				}
			}
			for (int j = 0; j < N / 2; j++) {
				answer.add(buffer1.get(j));
				answer.add(buffer2.get(j));
			}
			answer.add(buffer1.get(N/2));
		}

	}
}