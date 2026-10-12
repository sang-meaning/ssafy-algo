import java.util.*;

class Solution {

    static int N;
    static int[][] map;

    static int[][] dir = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    static class Robot {
        int x1, y1;
        int x2, y2;
        int count;

        Robot(int x1, int y1, int x2, int y2, int count) {

            // 좌표 순서 통일
            if (x1 > x2 || (x1 == x2 && y1 > y2)) {
                int tx = x1;
                int ty = y1;

                x1 = x2;
                y1 = y2;

                x2 = tx;
                y2 = ty;
            }

            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
        }

        String key() {
            return x1 + "," + y1 + "," + x2 + "," + y2;
        }
    }


    public int solution(int[][] board) {

        N = board.length;
        map = board;

        ArrayDeque<Robot> queue = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();

        Robot start = new Robot(0, 0, 0, 1, 0);

        queue.offer(start);
        visited.add(start.key());


        while (!queue.isEmpty()) {

            Robot now = queue.poll();

            // 목적지 도착
            if ((now.x1 == N - 1 && now.y1 == N - 1)
                    || (now.x2 == N - 1 && now.y2 == N - 1)) {

                return now.count;
            }


            // 1. 상하좌우 이동
            for (int i = 0; i < 4; i++) {

                int nx1 = now.x1 + dir[i][0];
                int ny1 = now.y1 + dir[i][1];

                int nx2 = now.x2 + dir[i][0];
                int ny2 = now.y2 + dir[i][1];


                if (!check(nx1, ny1) || !check(nx2, ny2))
                    continue;


                Robot next = new Robot(
                        nx1,
                        ny1,
                        nx2,
                        ny2,
                        now.count + 1
                );


                if (visited.contains(next.key()))
                    continue;


                visited.add(next.key());
                queue.offer(next);
            }


            // 2. 회전
            ArrayList<Robot> rotations = getRotation(now);

            for (int i = 0; i < rotations.size(); i++) {

                Robot next = rotations.get(i);

                if (visited.contains(next.key()))
                    continue;

                visited.add(next.key());
                queue.offer(next);
            }
        }

        return -1;
    }


    static ArrayList<Robot> getRotation(Robot now) {

        ArrayList<Robot> list = new ArrayList<>();

        if (now.x1 == now.x2) {

            // 위 / 아래
            for (int d = -1; d <= 1; d += 2) {

                int nx1 = now.x1 + d;
                int nx2 = now.x2 + d;

                // 두 공간이 모두 비어 있어야 회전 가능
                if (!check(nx1, now.y1)
                        || !check(nx2, now.y2))
                    continue;


                // 첫 번째 블록을 축으로 회전
                list.add(new Robot(
                        now.x1,
                        now.y1,
                        nx1,
                        now.y1,
                        now.count + 1
                ));


                // 두 번째 블록을 축으로 회전
                list.add(new Robot(
                        now.x2,
                        now.y2,
                        nx2,
                        now.y2,
                        now.count + 1
                ));
            }
        } else {

            // 왼쪽 / 오른쪽
            for (int d = -1; d <= 1; d += 2) {

                int ny1 = now.y1 + d;
                int ny2 = now.y2 + d;


                if (!check(now.x1, ny1)
                        || !check(now.x2, ny2))
                    continue;


                list.add(new Robot(
                        now.x1,
                        now.y1,
                        now.x1,
                        ny1,
                        now.count + 1
                ));


                list.add(new Robot(
                        now.x2,
                        now.y2,
                        now.x2,
                        ny2,
                        now.count + 1
                ));
            }
        }

        return list;
    }


    static boolean check(int x, int y) {

        if (x < 0 || x >= N)
            return false;

        if (y < 0 || y >= N)
            return false;

        if (map[x][y] == 1)
            return false;

        return true;
    }
}