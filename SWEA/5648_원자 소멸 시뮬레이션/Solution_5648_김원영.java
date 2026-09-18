import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc = 1; tc<= T; tc++) {
			
			int N = sc.nextInt();
			int energySum = 0;
			boolean[] crashed = new boolean[N];
			int[][] infAtom = new int[N][4];
			Map<Integer, ArrayList<Integer>> map = new HashMap<>();
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<4; j++) {
					infAtom[i][j] = sc.nextInt();
					if(j == 0 || j == 1) {
						infAtom[i][j] = infAtom[i][j] * 2 + 2000;
					}
				}
			}
			for(int i =0 ; i<4000; i++) {
				for(int j =0; j<N; j++) {
					if(crashed[j]) {
						continue;
					}
					infAtom[j][0] += dx[infAtom[j][2]];
					infAtom[j][1] += dy[infAtom[j][2]];
					if(infAtom[j][0]<0 || infAtom[j][0]>4000 || infAtom[j][1]<0 || infAtom[j][1] > 4000) {
						crashed[j] = true;
						continue;
					}
					
					int key = infAtom[j][0] * 4001 + infAtom[j][1];
					if(!map.containsKey(key)) {
						map.put(key, new ArrayList<>());
					}
					map.get(key).add(j);
				}
				for(ArrayList<Integer> element : map.values()) {
					if(element.size() >= 2) {
						for(int k =0; k< element.size();k++) {
							crashed[element.get(k)] = true;
							energySum += infAtom[element.get(k)][3];
						}
					}
				}
				map.clear();
			}
			System.out.println("#"+tc+" "+energySum);
		}
	}
}