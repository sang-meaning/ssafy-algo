import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
            List<int[]> stair = new ArrayList<>();
            List<Integer> stairtime = new ArrayList<>();
            List<int[]> human = new ArrayList<>();
            List<Integer> teamA = new ArrayList<>();
            List<Integer> teamB = new ArrayList<>();
            int[][] map = new int[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j] == 1) {
                        human.add(new int[] {i, j});
                    }else if(map[i][j] > 1) {
                        stair.add(new int[] {i, j});
                        stairtime.add(map[i][j]);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for(int i=0; i<(1 << human.size()); i++) {
                teamA.clear();
                teamB.clear();

                for(int j=0; j<human.size(); j++) {
                    if((i & (1 << j)) != 0) {
                        teamA.add(Math.abs(stair.get(1)[0]-human.get(j)[0])
                                +Math.abs(stair.get(1)[1]-human.get(j)[1]));
                    }else {
                        teamB.add(Math.abs(stair.get(0)[0]-human.get(j)[0])
                                +Math.abs(stair.get(0)[1]-human.get(j)[1]));
                    }
                }

                Collections.sort(teamA);
                Collections.sort(teamB);

                int count = 0;
                while(count < teamA.size()) {
                    if(count >= 3) {
                        if(teamA.get(count-3) > teamA.get(count)+1) {
                            teamA.set(count, teamA.get(count-3)+stairtime.get(1));
                        }else {
                            teamA.set(count, teamA.get(count)+1+stairtime.get(1));
                        }
                    }else {
                        teamA.set(count, teamA.get(count)+1+stairtime.get(1));
                    }
                    count++;
                }

                count = 0;
                while(count < teamB.size()) {
                    if(count >= 3) {
                        if(teamB.get(count-3) > teamB.get(count)+1) {
                            teamB.set(count, teamB.get(count-3)+stairtime.get(0));
                        }else {
                            teamB.set(count, teamB.get(count)+1+stairtime.get(0));
                        }
                    }else {
                        teamB.set(count, teamB.get(count)+1+stairtime.get(0));
                    }
                    count++;
                }

                int timeA = teamA.isEmpty() ? 0 : teamA.get(teamA.size()-1);
                int timeB = teamB.isEmpty() ? 0 : teamB.get(teamB.size()-1);

                min = Math.min(min, Math.max(timeA, timeB));
            }
            System.out.println("#"+tc+" "+min);
        }
    }
}