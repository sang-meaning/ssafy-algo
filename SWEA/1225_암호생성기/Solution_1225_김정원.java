package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1225_김정원 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> cry = new ArrayList<>();
		int temp = 0;
		int move = 0;
		for (int n = 0; n < 10; n++) {
			int testCase = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				cry.add(Integer.parseInt(st.nextToken()));
			}
			while (true) {
				if (cry.get(cry.size() - 1) <= 0) {
					cry.remove(cry.size() - 1);
					cry.add(0);
					break;
				}
				temp = cry.get(0) - (move % 5 + 1);
				move++;
				cry.remove(0);
				cry.add(temp);
			}
			System.out.print("#" + testCase);
			cry.forEach(num -> System.out.print(" " + num));
			System.out.println();
			cry.clear();
		}
	}
}
 