package swea;
import java.util.*;
public class Solution_2383_김현호 {
	static int[][] map;
	static boolean[] stairchoice;
	static int[][] people;
	static int[] stair;
	static int min;
	static void combination(int depth) {
		if (depth == stairchoice.length) {
	        // 조합마다 원래 거리를 복사
	        int[][] temp = new int[people.length][2];
	        for (int i = 0; i < people.length; i++) {
	            temp[i] = people[i].clone();
	        }

	        Queue<Integer> stair1 = new LinkedList<>();
	        Queue<Integer> stair2 = new LinkedList<>();

	        int flag = 0;
	        int count = 0;

	        while (flag != people.length
	                || !stair1.isEmpty()
	                || !stair2.isEmpty()) {

	            flag = 0;
	            count++;

	            int size = stair1.size();
	            for (int i = 0; i < size; i++) {
	                int value = stair1.poll();

	                if (value >= stair[0]) {
	                    continue;
	                }

	                stair1.offer(value + 1);
	            }

	            size = stair2.size();
	            for (int i = 0; i < size; i++) {
	                int value = stair2.poll();

	                if (value >= stair[1]) {
	                    continue;
	                }

	                stair2.offer(value + 1);
	            }

	            for (int i = 0; i < stairchoice.length; i++) {
	                if (stairchoice[i] == false) {
	                    if (temp[i][0] > 0) {
	                        temp[i][0]--;
	                    } else if (temp[i][0] == 0) {
	                        if (stair1.size() < 3) {
	                            stair1.offer(1);
	                            temp[i][0] = -1;
	                        }
	                    } else {
	                        flag++;
	                    }
	                } else {
	                    if (temp[i][1] > 0) {
	                        temp[i][1]--;
	                    } else if (temp[i][1] == 0) {
	                        if (stair2.size() < 3) {
	                            stair2.offer(1);
	                            temp[i][1] = -1;
	                        }
	                    } else {
	                        flag++;
	                    }
	                }
	            }
	        }

	        min = Math.min(min, count);
	        return;
	    }

	    stairchoice[depth] = false; // 추가
	    combination(depth + 1);

	    stairchoice[depth] = true;
	    combination(depth + 1);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			map = new int[N][N];
			stair = new int[2];
			min = Integer.MAX_VALUE;
			int count = 0;
			int stair_count = 0;
			int[][] stairxy = new int[2][2];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] != 0) {
						if(map[i][j] == 1) {
							count++;
						}else {
							stair[stair_count] = map[i][j];
							stairxy[stair_count][0] = i;
							stairxy[stair_count++][1] = j;
						}
					}
				}
			}
			people = new int[count][2];
			stairchoice = new boolean[count];
			int people_count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						people[people_count][0] = Math.abs(i - stairxy[0][0]) + Math.abs(j - stairxy[0][1]);
						people[people_count][1] = Math.abs(i - stairxy[1][0]) + Math.abs(j - stairxy[1][1]);
						people_count++;
					}
				}
			}
			combination(0);
			System.out.println("#" + test_case + " " + min);
		}
	}
}
