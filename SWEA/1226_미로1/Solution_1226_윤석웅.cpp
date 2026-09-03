#include <iostream>
#include <vector>
#include <queue>
#include <string>

using namespace std;

int bfs(vector<vector<int>> map, queue<pair<int,int>> que){
  int result = 0;
  vector<pair<int,int>> direc = {{1,0},{-1,0},{0,1},{0,-1}};
  while (!que.empty()){
    pair<int,int> cursor = que.front();
    que.pop();

    for (int i = 0; i < 4; i++){
      int nr = cursor.first+direc[i].first, nc = cursor.second+direc[i].second;
      if (nr < 0 || nr >= 16 ||nc < 0 || nc >= 16){ // boundary 검증
        continue;
      }

      if (map[nr][nc] == 0){ // 탐색한 곳이 길인 경우
        map[nr][nc] = 1; // 길 방문 체크
        que.push({nr,nc}); // 길에 대한 queue 누적
      }else if (map[nr][nc] == 3){ // 도착점에서는 바로 out
        result = 1;
        return result;
      } // 나머지 pass
    }
  }
  return result;
}

int main(int argc, char** argv){
  cin.tie(0);
  int tc =0;
  vector<vector<int>> map(16,vector<int>(16,0));
  
  for(int te_case = 0; te_case<10; te_case++){ // tc 10번
    string s1;
    getline(cin, s1);
    tc = stoi(s1); //dummy
    vector<int> start(2,0);
    for (int i =0; i<16; i++){ // map 정보 입력
      string s;
      getline(cin,s);
      for (int j = 0; j<16; j++){
        int temp = s[j] - '0';
        if (temp == 2){
          start = {i,j};
        }
        map[i][j] = temp;
      }
    }
    cout << '#' << tc <<" ";
    queue<pair<int,int>> que1;
    que1.push({start[0],start[1]});

    cout << bfs(map,que1) << '\n';
  }


  return 0;
}