import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Person implements Comparable<Person> {
    int r, c, dist;

    public Person(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.dist, o.dist);
    }
}

class Stair {
    int r, c, length;

    public Stair(int r, int c, int length) {
        this.r = r;
        this.c = c;
        this.length = length;
    }
}

public class Solution_2383_한석호 {
    static int N, minTime;
    static ArrayList<Person> persons;
    static ArrayList<Stair> stairs;
    static int[] selectedStair; // 각 사람이 선택한 계단 번호 (0 또는 1)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            persons = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (val == 1) {
                        persons.add(new Person(i, j));
                    } else if (val >= 2) {
                        stairs.add(new Stair(i, j, val));
                    }
                }
            }

            selectedStair = new int[persons.size()];
            minTime = Integer.MAX_VALUE;

            // DFS를 통해 각 사람이 어떤 계단을 이용할지 완전탐색
            dfs(0);

            System.out.println("#" + tc + " " + minTime);
        }
    }

    static void dfs(int depth) {
        if (depth == persons.size()) {
            // 모든 사람이 계단을 선택했으면 시뮬레이션 진행
            int time = simulate();
            minTime = Math.min(minTime, time);
            return;
        }

        // 0번 계단 선택
        selectedStair[depth] = 0;
        dfs(depth + 1);

        // 1번 계단 선택
        selectedStair[depth] = 1;
        dfs(depth + 1);
    }

    static int simulate() {
        ArrayList<Person> stair0 = new ArrayList<>();
        ArrayList<Person> stair1 = new ArrayList<>();

        for (int i = 0; i < persons.size(); i++) {
            Person p = persons.get(i);
            Stair s = stairs.get(selectedStair[i]);
            // 각 계단까지의 거리 계산 (+1은 계단 입구 도착 후 1분 뒤 내려가기 시작하는 조건 반영)
            p.dist = Math.abs(p.r - s.r) + Math.abs(p.c - s.c);

            if (selectedStair[i] == 0) {
                stair0.add(p);
            } else {
                stair1.add(p);
            }
        }

        // 도착 시간 순으로 정렬
        Collections.sort(stair0);
        Collections.sort(stair1);

        int t0 = calcTime(stair0, stairs.get(0).length);
        int t1 = calcTime(stair1, stairs.get(1).length);

        return Math.max(t0, t1);
    }

    static int calcTime(ArrayList<Person> list, int stairLength) {
        if (list.isEmpty()) return 0;

        int[] stairQueue = new int[3]; // 계단 위에 있는 최대 3명 관리
        int currentTime = 0;

        for (Person p : list) {
            // 계단에 도착한 시간
            int arrival = p.dist;

            // 계단이 비어있거나, 먼저 올라간 사람이 내려간 경우
            if (currentTime < arrival + 1) {
                currentTime = arrival + 1;
            }

            // 계단 자리가 남았는지 확인
            boolean entered = false;
            for (int i = 0; i < 3; i++) {
                if (stairQueue[i] <= currentTime) {
                    stairQueue[i] = currentTime + stairLength;
                    entered = true;
                    break;
                }
            }

            // 3명이 모두 차있는 경우 가장 먼저 빠져나오는 사람의 시간에 맞춤
            if (!entered) {
                int minIdx = 0;
                int minFinish = stairQueue[0];
                for (int i = 1; i < 3; i++) {
                    if (stairQueue[i] < minFinish) {
                        minFinish = stairQueue[i];
                        minIdx = i;
                    }
                }
                stairQueue[minIdx] = minFinish + stairLength;
                currentTime = minFinish;
            }
        }

        // 마지막 사람이 계단을 완전히 내려가는 시간 계산
        int maxTime = 0;
        for (int time : stairQueue) {
            if (time > maxTime) maxTime = time;
        }

        return maxTime;
    }
}