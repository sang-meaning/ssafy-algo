#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    cin.tie(0);
    vector<int> answer;
    int len = arr.size();
    int temp;
    for (int i = 0; i<len; i++){
        temp = arr[i];
        if (answer.size() == 0 || answer[answer.size()-1] != temp){ //같은 숫자를 만나지 않으면
            answer.push_back(temp); // 삽입
        }
    }
    return answer;
}