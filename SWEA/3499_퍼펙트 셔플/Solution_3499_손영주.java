package submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 큐 사용함.

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			int N = Integer.parseInt(br.readLine());

			Queue<String> upperDeck = new ArrayDeque<>();
			Queue<String> lowerDeck = new ArrayDeque<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < N/2 + (N%2); i++) {
				upperDeck.offer(st.nextToken());
			}
			for (int i = 0; i < N/2; i++) {
				lowerDeck.offer(st.nextToken());
			}
			
			System.out.print("#" + test_case_num + " ");
			
			
			while (!upperDeck.isEmpty()) {
				System.out.print(upperDeck.poll() + " ");
				if(!lowerDeck.isEmpty()) System.out.print(lowerDeck.poll() + " ");
			}
			System.out.println();
		}
	}
}
