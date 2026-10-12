package d4;
import java.util.*;
import java.io.*;

public class Solution_4014_김민우 {
  static int T, N, X;
  static int ans;
  static int[] built;
  static int[][] map;

  //현재 위치의 값, 현 길이, 플래그
  static int curV, curL;
  static boolean flag;

  static BufferedReader br;
  static StringTokenizer st;
  public static void main(String[] args) throws IOException{
    br = new BufferedReader(new InputStreamReader(System.in));
    st = new StringTokenizer(br.readLine());

    T = Integer.parseInt(st.nextToken());
    for(int test_case = 1; test_case <= T; test_case++){
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());
      map = new int[N][N];
      ans = 0;

      for(int i = 0; i < N; i++){
        st = new StringTokenizer(br.readLine());
        for(int j = 0;  j < N; j++){
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for(int i = 0; i < N; i++){
         built = new int[N];
         curV = map[i][0];
         curL = 1;
         flag = true;
         
         for(int j = 1; j < N; j++){
          //같은 높이라면 continue
          if(map[i][j] ==  curV){
            curL+=1;
            continue;
          }
          int diff = map[i][j] - curV;
          
          //높이 차이가 2이상이라면 활주로 건설 불가
          if(Math.abs(diff) > 1){
            flag = false;
            break;
          }
          
          flag = canBuild(i*N+j, diff, 1, 0);
          if(!flag) break;
          curV = map[i][j];         
         }
         if(flag) ans++;
        }

        //아래로
        for(int i = 0; i < N; i++){
         built = new int[N];
         curV = map[0][i];
         curL = 1;
         flag = true;
         
         for(int j = 1; j < N; j++){
          //같은 높이라면 continue
          if(map[j][i] ==  curV){
            curL+=1;
            continue;
          }
          int diff = map[j][i] - curV;
          
          //높이 차이가 2이상이라면 활주로 건설 불가
          if(Math.abs(diff) > 1){
            flag = false;
            break;
          }
          
          flag = canBuild(j*N+i, diff, 0, 1);
          if(!flag) break;        
         }
         if(flag) ans++;
        }

        System.out.printf("#%d %d\n", test_case, ans);
    }
    //test_case 끝
  }

  static boolean canBuild(int idx, int diff, int hr, int vt){
    //내려가기
    int r = idx / N;
    int c = idx % N;
    int dir = (r * vt) + (c * hr);

    //내려가기
    if(diff == -1){
      int tmp = map[r][c];
      //경사로 건설길이만큼 충분한가? / 다른 경사로는 없는가? / 값이 다르진 않은가?
      for(int i = 0 ; i < X; i++){
        if(!isIn(r+(i*vt), c+(i*hr)) || (built[dir+i] != 0) || (map[r+(i*vt)][c+(i*hr)] != tmp)){
          return false;
        }
      }

      //건설시작 => -1로 표시
      for(int i = 0 ; i < X; i++){
        built[dir+i] = -1;
      }

    }

    //올라가기
    else if(diff == 1){
      //경사로를 지을만큼 충분한 길이를 가졌는가?
      //= 같은 값으로 충분히 이어졌는가?
      if(curL < X)
        return false;
      
      //같은 값으로 길이 유지된 것은 확인했기 때문에
      //건설 시작~끝 지점 중에 이미 다른 경사로가 지어진 적은 없는가?만 체크
      for(int i = 1 ; i <= X; i++){
        if(!isIn(r-(i*vt), c-(i*hr)) || built[dir-i] != 0){
          return false;
        }
      }

      //건설시작 => -1로 표시
      for(int i = 1 ; i <= X; i++){
        built[dir-i] = -1;
      }

    }
    //현재길이 초기화 및 return true
    curL = 1;
    curV = map[r][c];
    return true;
  } 

  static boolean isIn(int r, int c){
    return r >= 0 && r < N && c >= 0 && c < N;
  }

}
