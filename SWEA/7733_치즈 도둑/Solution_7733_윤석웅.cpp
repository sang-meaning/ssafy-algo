#include <iostream>
#include <vector>
#include <queue>
#define ff first
#define ss second

using namespace std;

int dr[4] = {1,-1, 0, 0};
int dc[4] = {0, 0, 1, -1};


void BFS(vector<vector<int>>& board, vector<vector<bool>>& isEat, int r, int c){
    queue<pair<int,int>> worQ;
    worQ.push({r,c});
    isEat[r][c] = false;
    while(!worQ.empty()){
        pair<int,int> idx = worQ.front();
        worQ.pop();
        for (int i = 0 ; i< 4; i++){
            int nr = idx.ff+dr[i]; int nc = idx.ss+dc[i];
            if (nr >= isEat.size() || nr < 0 || nc >= isEat.size() || nc < 0 || !isEat[nr][nc]){
                continue;
            }
            worQ.push({nr,nc});
            isEat[nr][nc] = false;
        }
    }
}

int findBuncheese(vector<vector<int>>& board, vector<vector<bool>> isEat){ //덩어리 수 구하기
    int answer = 0;
    for (int i = 0; i< board.size(); i++){
        for (int j = 0; j< board[0].size(); j++){
            if (isEat[i][j]){
                BFS(board, isEat, i, j);
                answer++;
            }
        }
    }
    return answer;
}

int solve(int max, vector<vector<int>>& board){
    int day = 0, answer = 0;
    vector<vector<bool>> isEat(board.size(), vector<bool>(board.size(), true));
    while (day <= max){ // 날짜가 최대일수보다 적은경우, n일째 낮에 카운트 n일째 밤에 카운트
        day++;
        int temp = findBuncheese(board, isEat);
        for (int i = 0; i< board.size(); i++){
            for (int j = 0; j< board[0].size(); j++){
                if (board[i][j] == day){
                    isEat[i][j] = false;
                }
            }
        }
        if (temp >= answer){
            answer = temp;
        }
    }

    return answer;
}

int main(){
    int T, N;
    cin >> T;

    for (int tc =1; tc<=T; tc++){
        cin >> N;
        int maxDay = 0; //최대 일수
        vector<vector<int>> cheeseMap(N,vector<int>(N,0));
        for (auto& row: cheeseMap){
            for (auto& item : row){
                cin >> item;
                if (maxDay < item){
                    maxDay = item;
                }
            }
        }
        int answer = solve(maxDay, cheeseMap);
        cout << "#" << tc << " " << answer << "\n";
    }
}