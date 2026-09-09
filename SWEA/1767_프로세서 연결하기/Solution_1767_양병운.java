import java.util.*;
 
class Solution {
    static int N;
    static int maxConnected;
    static int minLength;
    static int[][] matrix;
    static List<int[]> cores;
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int testCase = 1; testCase <= T; testCase++) {
            N = sc.nextInt();
            matrix = new int[N][N];
            cores = new ArrayList<>();
 
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    matrix[x][y] = sc.nextInt();
                    if (matrix[x][y] == 1&& x != 0 && x != N - 1&& y != 0 && y != N - 1) {
                        cores.add(new int[]{x, y});
                    }
                }
            }
 
            maxConnected = -1;
            minLength = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            System.out.println("#" + testCase + " " + minLength);
        }
        sc.close();
    }
 
    static void dfs(int idx, int connected, int length) {
        if (idx == cores.size()) {
            if (connected > maxConnected) {
                maxConnected = connected;
                minLength = length;
            } else if (connected == maxConnected) minLength = Math.min(minLength, length);
            return;
        }
        if (connected + cores.size() - idx < maxConnected)  return;
 
        int x = cores.get(idx)[0];
        int y = cores.get(idx)[1];
 
        for (int direction = 0; direction < 4; direction++) {
            if (!canConnect(x, y, direction)) continue;
            int wireLength = setWire(x, y, direction, 2);
            dfs(idx + 1, connected + 1, length + wireLength);
            setWire(x, y, direction, 0);
        }
        dfs(idx + 1, connected, length);
    }
 
    static boolean canConnect(int x, int y, int direction) {
        int nx = x + dx[direction];
        int ny = y + dy[direction];
 
        while (isInside(nx, ny)) {
            if (matrix[nx][ny] != 0) return false;
            nx += dx[direction];
            ny += dy[direction];
        }
        return true;
    }
 
    static int setWire(int x, int y, int direction, int value) {
        int length = 0;
        int nx = x + dx[direction];
        int ny = y + dy[direction];
 
        while (isInside(nx, ny)) {
            matrix[nx][ny] = value;
            length++;
            nx += dx[direction];
            ny += dy[direction];
        }
        return length;
    }
 
    static boolean isInside(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}