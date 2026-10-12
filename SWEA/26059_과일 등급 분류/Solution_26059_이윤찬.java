package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_26059_이윤찬 {

	static int T;

	static int N;

	static int lo, hi;

	static double k1, k2;
	static List<Integer> fruits;
	static int best;
	static int middle;
	static int worst;
	static int max, min;
	static int MinValue;
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int t = 1; t <= T; t++) {

			MinValue = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());

			lo = Integer.parseInt(st.nextToken());

			hi = Integer.parseInt(st.nextToken());

			fruits = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {

				fruits.add(Integer.parseInt(st.nextToken()));
			}

			fruits.sort((a, b) -> a - b);

			for (int j = 0; j < fruits.size(); j++) {
	
				k1 = (double) fruits.get(j) + 0.5;
				
//				System.out.println("k1 "+ k1);

				for (int p = j + 1; p < N; p++) {

					k2 = (double) fruits.get(p) + 0.5;
					best = 0;

					middle = 0;

					worst = 0;
					
					
					if( k1== k2) continue;

//					System.out.println("k2 "+k2);

					for (int f : fruits) {
											
						if (f < k1)
							worst++;

						if (f >= k1 && f < k2) {
							middle++;
			
						} else if (f > k2) {
							best++;
						}
					}
					if (worst < lo || worst > hi)
						continue;
					
//					if(middle ==0 || best == 0) continue;

					if (middle < lo || middle > hi)
						continue;

					if (best < lo || best > hi)
						continue;
					
//					System.out.println("worst : " + worst);
//					System.out.println("middle " + middle);
//					System.out.println("best : " + best);

					min = Math.min(worst, Math.min(middle, best));
					max = Math.max(worst, Math.max(middle, best));
					
//					System.out.println(min + " " + max);
					MinValue = Math.min(MinValue, (max - min));

				}
				if(MinValue ==0) {break;}
				
			}
			System.out.print("#"+t+" ");
			System.out.println(( MinValue ==INF )? -1 : MinValue);
		}
//		if (MinValue == 0) {
//			System.out.println(-1);
//		} else {
//		}
	}

}
