
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Solution{
  static int T, N, row, col;
  static int[] cursor = new int[2];
  static char[][] map;
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};
  static String clist;

  public static void main(String[] args) throws IOException{
    StringBuilder sb = new StringBuilder();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(in.readLine());
    T = Integer.parseInt(st.nextToken());
    
    for (int tc = 1; tc <= T; tc++) {
      
      st = new StringTokenizer(in.readLine(), " ");
      row = Integer.parseInt(st.nextToken());
      col = Integer.parseInt(st.nextToken());
      map = new char[row][col];

      for (int i = 0; i<row; i++){
        st = new StringTokenizer(in.readLine(), " ");
        String s = st.nextToken();

        for ( int j = 0; j<col; j++){
          map[i][j] = s.charAt(j);

          if ("^v<>".indexOf(s.charAt(j)) >= 0){
            cursor[0] = i;
            cursor[1] = j;
          }
        }
      }

      st = new StringTokenizer(in.readLine());
      N = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(in.readLine());
      clist = st.nextToken();

      command();

      sb.append('#').append(tc).append(' ');

      for (int i = 0; i<row; i++){
        for ( int j = 0; j<col; j++){
          sb.append(map[i][j]);
        }
        sb.append('\n');
      }
    }
    System.out.print(sb);
  }

  static void command(){
    for (int i = 0; i < clist.length(); i++){
      char cs = clist.charAt(i);
      switch(cs){
        case 'U':
          map[cursor[0]][cursor[1]] = '^';
          if (cursor[0]-1 >= 0 && map[cursor[0]-1][cursor[1]] == '.'){
            map[cursor[0]][cursor[1]] = '.';
            map[--cursor[0]][cursor[1]] = '^';
          }
          break;

        case 'D':
          map[cursor[0]][cursor[1]] = 'v';
          if (cursor[0]+1 < row && map[cursor[0]+1][cursor[1]] == '.'){
            map[cursor[0]][cursor[1]] = '.';
            map[++cursor[0]][cursor[1]] = 'v';

          }
          break;

        case 'L':
          map[cursor[0]][cursor[1]] = '<';
          if (cursor[1]-1 >= 0 && map[cursor[0]][cursor[1]-1] == '.'){
            map[cursor[0]][cursor[1]] = '.';
            map[cursor[0]][--cursor[1]] = '<';
          }
          break;

        case 'R':
          map[cursor[0]][cursor[1]] = '>';
          if (cursor[1]+1 < col && map[cursor[0]][cursor[1]+1] == '.'){
            map[cursor[0]][cursor[1]] = '.';
            map[cursor[0]][++cursor[1]] = '>';
            
          }
          break;

        case 'S':
          int direc = "v^><".indexOf(map[cursor[0]][cursor[1]]);
          int nr = cursor[0], nc = cursor[1];
          
          while (true) { 
            nr += dx[direc]; nc +=dy[direc];
            if (nr <0|| nr>=row ||nc <0|| nc>=col){
              break;
            }

            if ("*#".indexOf(map[nr][nc]) >= 0){
              if(map[nr][nc] == '*'){
                map[nr][nc] = '.';
              }
              break;
            }
          }
          break;
      }

    }
  }
}