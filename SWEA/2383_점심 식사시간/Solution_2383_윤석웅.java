import java.io.*;
import java.util.*;

class Solution {
    static int N, ans;
    static ArrayList<int[]> people;
    static int[][] stair;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            people = new ArrayList<>();
            stair = new int[2][3];

            int idx = 0;

            for(int r=0; r<N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int c=0; c<N; c++) {
                    int x = Integer.parseInt(st.nextToken());

                    if(x == 1)
                        people.add(new int[]{r, c});
                    else if(x > 1) {
                        stair[idx][0] = r;
                        stair[idx][1] = c;
                        stair[idx][2] = x;
                        idx++;
                    }
                }
            }

            ans = Integer.MAX_VALUE;
            dfs(0, new ArrayList<>(), new ArrayList<>());

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int idx, ArrayList<Integer> a, ArrayList<Integer> b) {
        if(idx == people.size()) {
            ans = Math.min(ans, Math.max(calc(a, 0), calc(b, 1)));
            return;
        }

        a.add(idx);
        dfs(idx+1, a, b);
        a.remove(a.size()-1);

        b.add(idx);
        dfs(idx+1, a, b);
        b.remove(b.size()-1);
    }

    static int calc(ArrayList<Integer> list, int s) {
        if(list.isEmpty())
            return 0;

        ArrayList<Integer> time = new ArrayList<>();

        for(int p : list) {
            int[] cur = people.get(p);
            time.add(Math.abs(cur[0]-stair[s][0]) + Math.abs(cur[1]-stair[s][1]));
        }

        Collections.sort(time);

        int[] finish = new int[time.size()];

        for(int i=0; i<time.size(); i++) {
            int start = time.get(i) + 1;

            if(i >= 3)
                start = Math.max(start, finish[i-3]);

            finish[i] = start + stair[s][2];
        }

        return finish[finish.length-1];
    }
}
