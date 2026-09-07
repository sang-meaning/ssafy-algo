import java.util.*;
import java.io.*;

public class Solution_다리를지나는트럭_임성진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			int bridgeLenght = Integer.parseInt(br.readLine());
			int weight = Integer.parseInt(br.readLine());
			
			List<Integer> list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int[] truckWeights = new int[list.size()];
			
			truckWeights = list.stream().mapToInt(i -> i).toArray();
			
			System.out.println(solution(bridgeLenght,  weight, truckWeights));
			
		}
		
	}

	static int solution(int bridgeLength, int weight, int[] truckWeights) {
		Deque<Integer> bridge = new ArrayDeque<>();
		for (int i = 0; i < bridgeLength; i++)
			bridge.offer(0);

		int time = 0;
		int load = 0;
		int idx = 0;

		while (idx < truckWeights.length) {
			time++;
			load -= bridge.poll();

			if (load + truckWeights[idx] <= weight) {
				load += truckWeights[idx];
				bridge.offer(truckWeights[idx++]);
			} else {
				bridge.offer(0);
			}
		}
		return time + bridgeLength;
	}
}
