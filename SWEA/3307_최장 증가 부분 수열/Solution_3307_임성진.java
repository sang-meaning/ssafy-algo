import java.io.*;
import java.util.*;

public class Solution_3307_임성진 {
	public static void main(String[] args) throws IOException {
		int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
		System.out.println(solution(routes));
	}
	
	static int solution(int[][] routes) {
        int answer = 1;
        
        Arrays.sort(routes, (x,y)->Integer.compare(x[1], y[1]));
        
        int camera = routes[0][1];
        
        for (int i = 1; i < routes.length; i++) {
			if(routes[i][0] <= camera && camera  <= routes[i][1]) continue;
			
			camera = routes[i][1];
			answer++;
		}
        
        return answer;
    }
}