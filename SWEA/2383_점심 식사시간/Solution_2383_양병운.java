import java.util.*;
import java.io.*;

class Solution {
    static class Person {
        int x, y;
        public Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Exit {
        int x, y, length;
        public Exit(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }

    static List<Person> persons;
    static List<Exit> exits;
    static int N, answer;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            persons = new ArrayList<>();
            exits = new ArrayList<>();
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value == 1) persons.add(new Person(i, j));
                    else if (value > 1)  exits.add(new Exit(i, j, value));
                }
            }

            for (int mask = 0; mask < (1 << persons.size()); mask++) {
                List<Integer> exit0 = new ArrayList<>();
                List<Integer> exit1 = new ArrayList<>();
                for (int i = 0; i < persons.size(); i++) {
                    Person p = persons.get(i);
                    if ((mask & (1 << i)) == 0) {
                        int dist = getDistance(p, exits.get(0));
                        exit0.add(dist);
                    } else {
                        int dist = getDistance(p, exits.get(1));
                        exit1.add(dist);
                    }
                }
                int totalTime = Math.max(getTime(exit0, exits.get(0).length), getTime(exit1, exits.get(1).length));
                answer = Math.min(answer, totalTime);
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }

    static int getDistance(Person person, Exit stair) {
        return Math.abs(person.x - stair.x)+ Math.abs(person.y - stair.y);
    }

    static int getTime(List<Integer> arrivals, int stairLength) {
        if (arrivals.isEmpty()) return 0;
        Collections.sort(arrivals);
        int size = arrivals.size();
        int[] finish = new int[size];

        for (int i = 0; i < size; i++) {
            int startTime = arrivals.get(i) + 1;
            if (i >= 3) {
                startTime = Math.max(startTime, finish[i - 3]);
            }
            finish[i] = startTime + stairLength;
        }
        return finish[size - 1];
    }
}