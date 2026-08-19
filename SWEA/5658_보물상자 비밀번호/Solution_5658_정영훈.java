import java.util.*;
import java.io.*;

public class Solution_5658_정영훈 {
    /*문제
    1. 각 변(숫자 3개)마다 큰 수 하나를 선택
    2. 회전하며 생성된 숫자를 넣음
    3. 숫자 중복 주의
    4. k번째로 큰 수를 찾음

    */

    /* 풀이
    1. Queue로 입력받은 16진수 배열을 pop, push
    2. Set으로 각 변에 생성된 n/4개의 숫자를 넣음?
    End. 정렬후 k번째 큰 숫자를 반환? 
    
    
    */
    static Deque<Character> queue;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st=new StringTokenizer(br.readLine(), " ");
            int n=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            String s=br.readLine();
            queue=new LinkedList<>();
            set=new HashSet<>();
            int size=n/4;
            for (int i = 0; i < s.length(); i++) {
                Character c=s.charAt(i);
                queue.offer(c);
            }
            for (int r = 0; r < size; r++) {

                for (int i = 0; i < 4; i++) {
                    Character[] password=new Character[size];
                    for (int j = 0; j < size; j++) {
                        password[j]=queue.pollFirst();
                    }
                    save(password, size);
                    for (int j = 0; j < size; j++) {
                        queue.offerLast(password[j]);
                        
                    }

                }
                rotate();
            }
            Integer[] pswArr=set.toArray(new Integer[set.size()]);
            Arrays.sort(pswArr);
            sb.append("#"+test_case+" "+pswArr[(pswArr.length)-k]+"\n");
            
           
            
        }
        System.out.println(sb.toString());
    }
    public static void rotate(){
        queue.offerFirst(queue.pollLast());
    }
    
    public static void save(Character[] password, int size){
        Integer passWordInteger=translator(password, size);
        set.add(passWordInteger);

    }
    public static Integer translator(Character[] password, int size){
        Integer sum=0;
        for (int i = 0; i < size; i++) {
            int pow=(size-1)-i;
            Character part= password[i];
            int num=0;
            if(!Character.isDigit(part)){
                switch(part){
                    
                    case 'A': num=10;
                    break;
                    
                    case 'B': num=11;
                    break;

                    case 'C': num=12;
                    break;

                    case 'D': num=13;
                    break;
                    
                    case 'E': num=14;
                    break;
                    
                    case 'F': num=15;
                    break;
                }
            }else num=part-'0';
            
            sum+=num*(int)Math.pow(16, pow);

        }
        return sum;
    }


}