#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    vector<int> baggage;
    for (auto& item: moves){
        int i =0;
        for (;i<board.size();i++){ // 크레인 내리기
            if (board[i][item-1] !=0){ // 0이 아니라면 인형이 있음
                break;
            }
        }
        int temp;
        if(i >= board.size()){ // 땅바닥까지 크레인이 내려가면
            continue; // 다음 무브로
        }else{
            temp = board[i][item-1]; // 인형 꺼내기
            board[i][item-1] = 0; //꺼낸 공간 비우기
            if (!baggage.empty() && baggage.back() == temp){ // 바구니에 인형이 있고 바구니의 마지막이 인형과 같다면
                answer += 2;
                baggage.pop_back();
                continue;
            }else{
                baggage.push_back(temp);
            }
        }
    }
    return answer;
}