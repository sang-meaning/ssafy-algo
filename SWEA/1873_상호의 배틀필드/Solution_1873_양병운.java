import java.util.*;
import java.io.*;

class Solution {
    static int H, W;
    static String[][] matrix;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            matrix = new String[H][W];
            int[] current = new int[3];

            for(int i = 0; i < H; i++) {
                String[] split = br.readLine().split("");
                for(int j = 0; j < W; j++) {
                    matrix[i][j] = split[j];
                    if(matrix[i][j].equals("^")) {
                        current[0] = i;
                        current[1] = j;
                        current[2] = 0;
                    } else if(matrix[i][j].equals("v")) {
                        current[0] = i;
                        current[1] = j;
                        current[2] = 1;
                    } else if(matrix[i][j].equals("<")) {
                        current[0] = i;
                        current[1] = j;
                        current[2] = 2;
                    } else if(matrix[i][j].equals(">")) {
                        current[0] = i;
                        current[1] = j;
                        current[2] = 3;
                    }
                }
            }

            int moveCnt = Integer.parseInt(br.readLine());
            String[] move = br.readLine().split("");
            for(int i = 0; i < moveCnt; i++) {
                String m = move[i];
                if(m.equals("U")) {
                    up(current);
                } else if(m.equals("D")) {
                    down(current);
                } else if(m.equals("L")) {
                    left(current);
                } else if(m.equals("R")) {
                    right(current);
                } else if(m.equals("S")) {
                    shoot(current);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(test_case).append(" ");
            for(int i = 0; i < H; i++) {
                for(int j = 0; j < W; j++) {
                    sb.append(matrix[i][j]);
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }
    }

    public static void up(int[] current) {
        current[2] = 0;
        matrix[current[0]][current[1]] = "^";
        int nx = current[0] + dx[0];
        int ny = current[1] + dy[0];
        if(nx < 0 || ny < 0 || nx >= H || ny >= W) return;
        if(!matrix[nx][ny].equals(".")) return;
        matrix[current[0]][current[1]] = ".";
        matrix[nx][ny] = "^";
        current[0] = nx;
        current[1] = ny;
    }

    public static void down(int[] current) {
        current[2] = 1;
        matrix[current[0]][current[1]] = "v";
        int nx = current[0] + dx[1];
        int ny = current[1] + dy[1];
        if(nx < 0 || ny < 0 || nx >= H || ny >= W) return;
        if(!matrix[nx][ny].equals(".")) return;
        matrix[current[0]][current[1]] = ".";
        matrix[nx][ny] = "v";
        current[0] = nx;
        current[1] = ny;
    }

    public static void left(int[] current) {
        current[2] = 2;
        matrix[current[0]][current[1]] = "<";
        int nx = current[0] + dx[2];
        int ny = current[1] + dy[2];
        if(nx < 0 || ny < 0 || nx >= H || ny >= W) return;
        if(!matrix[nx][ny].equals(".")) return;
        matrix[current[0]][current[1]] = ".";
        matrix[nx][ny] = "<";
        current[0] = nx;
        current[1] = ny;
    }

    public static void right(int[] current) {
        current[2] = 3;
        matrix[current[0]][current[1]] = ">";
        int nx = current[0] + dx[3];
        int ny = current[1] + dy[3];
        if(nx < 0 || ny < 0 || nx >= H || ny >= W) return;
        if(!matrix[nx][ny].equals(".")) return;
        matrix[current[0]][current[1]] = ".";
        matrix[nx][ny] = ">";
        current[0] = nx;
        current[1] = ny;
    }

    public static void shoot(int[] current) {
        int shootX = current[0];
        int shootY = current[1];
        int direction = current[2];
        while(true) {
            shootX += dx[direction];
            shootY += dy[direction];
            if(shootX < 0 || shootY < 0 || shootX >= H || shootY >= W) break;
            if(matrix[shootX][shootY].equals("*")) {
                matrix[shootX][shootY] = ".";
                break;
            }
            if(matrix[shootX][shootY].equals("#")) break;
        }
    }
}