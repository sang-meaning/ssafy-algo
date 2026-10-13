import java.io.*;
import java.util.*;

public class Solution {

    static class Person {
        int r, c;

        Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Stair {
        int r, c, length;

        Stair(int r, int c, int length) {
            this.r = r;
            this.c = c;
            this.length = length;
        }
    }

    static int N;
    static List<Person> people;
    static Stair[] stairs;

    static int[] choice;

    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());

            people = new ArrayList<>();
            stairs = new Stair[2];

            int stairCount = 0;

            for (int r = 0; r < N; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int c = 0; c < N; c++) {
                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        people.add(new Person(r, c));
                    } else if (value >= 2) {
                        stairs[stairCount++] = new Stair(r, c, value);
                    }
                }
            }

            choice = new int[people.size()];
            answer = Integer.MAX_VALUE;

            dfs(0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }

    static void dfs(int idx) {
        if (idx == people.size()) {
            answer = Math.min(answer, simulate());
            return;
        }

        choice[idx] = 0;
        dfs(idx + 1);

        choice[idx] = 1;
        dfs(idx + 1);
    }

    static int simulate() {
        int totalTime = 0;

        for (int s = 0; s < 2; s++) {
            List<Integer> times = new ArrayList<>();
            Stair stair = stairs[s];

            for (int i = 0; i < people.size(); i++) {
                if (choice[i] != s) {
                    continue;
                }

                Person p = people.get(i);

                int distance = Math.abs(p.r - stair.r) + Math.abs(p.c - stair.c);

                times.add(distance + 1);
            }

            Collections.sort(times);

            for (int i = 3; i < times.size(); i++) {
                int earliest = times.get(i);
                int previousExit = times.get(i - 3) + stair.length;

                times.set(i, Math.max(earliest, previousExit));
            }

            for (int time : times) {
                totalTime = Math.max(totalTime, time + stair.length);
            }
        }

        return totalTime;
    }
}