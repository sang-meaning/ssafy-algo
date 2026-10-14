import java.io.*;
import java.util.*;


public class 점심식사시간 {
    static int T, n,l;
    static ArrayList<int[]> people;
    static ArrayList<int[]> door; // 문 좌표
    static int[] dept =new int[2]; // 계단의 깊이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int testcase = 1; testcase <= T; testcase++){
            n= Integer.parseInt(br.readLine());
            StringTokenizer st;
            door = new ArrayList<>(); // 문 좌표 저장
            people = new ArrayList<>(); // 사람 좌표 저장

            // 입력 처리 -> 문, 사람 좌표 저장
            for(int i = 0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    int num = Integer.parseInt(st.nextToken());

                    if(num > 1){
                        door.add(new int[]{i, j});
                        dept[door.size()-1] = num;
                    }
                    if(num == 1) {
                        people.add(new int[]{i, j});
                    }
                }
            }

            //조합 생성 
            min = Integer.MAX_VALUE;
            l = people.size();
            dist = new int[l][2]; // 거리 좌표
            d1 = new int[l];
            d2 = new int[l];
            makeComb(0, 0, 0);
            System.out.println("#" + testcase + " " + min);
        }
    }
    
    
    // 부분 집합 생성
    static int min;
    static int[] d1;
    static int[] d2;
    static int[][] dist; // 사람과 문의 거리(이자 시간)
    static void makeComb(int idx, int d1_idx, int d2_idx){
        // 끝까지 돌았으면 시간 책정
        if(idx == l){
            int[] a = Arrays.copyOf(d1, d1_idx);
            int[] b = Arrays.copyOf(d2, d2_idx);
            Arrays.sort(a);
            Arrays.sort(b);
            min = Math.min(min, Math.max(getTime(a, dept[0]), getTime(b, dept[1])));
            return;
        }
        
        // 두 문까지의 맨해튼 거리 구함
        dist[idx] = new int[]{getDist(idx, 0), getDist(idx, 1)};
        
        // 조합 DFS
        d1[d1_idx] = dist[idx][0];
        makeComb(idx+1, d1_idx+1, d2_idx);
        d1[d1_idx] = 0;
        
        d2[d2_idx] = dist[idx][1];
        makeComb(idx+1, d1_idx, d2_idx+1);
        d2[d2_idx] = 0;
    }
    
    // 맨해튼 거리 구함
    static int getDist(int p, int d){
        return Math.abs(people.get(p)[0] - door.get(d)[0]) + Math.abs(people.get(p)[1] - door.get(d)[1]);
    }

    // 스케줄러처럼^^;;
    static int getTime(int[] wait, int dept){
        Queue<Integer> waiting = new ArrayDeque<>();
        Queue<Integer> using = new ArrayDeque<>();

        for(int arrival : wait){
            waiting.add(arrival + 1);
        }

        int t = 0;
        while(true){
            if(!using.isEmpty() && (using.peek() <= t)){
                using.poll();
            }
            
            if(!waiting.isEmpty() && using.size() < 3 && waiting.peek() <= t){
                waiting.poll();
                using.add(t+dept);
            }
            
            if(waiting.isEmpty() && using.isEmpty()) break;
            t++;
        }
        return t;
    }
}
// 2^n
// 한 조합 생성 끝 -> 각 처리 큐를 처리함 (도착시간만 있으면 됨)
// 그 시간 -> 맨해튼 거리 + 1 
