#include <vector>
#include <iostream>
#include <queue>

using namespace std;

void bfs(vector<vector<int>>& map, queue<pair<int,int>>& que){
  int dist = 0;
  vector<pair<int,int>> direc = {{1,0},{-1,0},{0,1},{0,-1}};

  while(!que.empty()){
    pair<int,int> cursor = que.front();
    que.pop();
    dist = map[cursor.first][cursor.second]+1;

    for (int i=0; i<4; i++){ // go to dircetion
      int nr = cursor.first + direc[i].first, nc = cursor.second + direc[i].second;
      if(nr < 0 || nr >= map.size() || nc < 0 || nc >= map[0].size()){ //boundary check
        continue;
      }
      
      if (map[nr][nc] == 1)  { // 길인데 방문하지 않은경우
        que.push({nr,nc});
        map[nr][nc] = dist;
      }
      
    }
  }
  if (map.back().back() == 1){ // 미도달
      map.back().back() = -1;
  }
}

int solution(vector<vector<int> > maps)
{
    int answer = 0;
    queue<pair<int,int>> que1;
    que1.push({0,0});
    bfs(maps,que1);
    answer = maps[maps.size()-1][maps[0].size()-1];

    return answer;
}