#include <iostream>
#include <vector>
#include <set>

using namespace std;

int solution(vector<int> elements){
    int result = 0;
    set<int> result_set;
    cin.tie(0);
    for (int n = 1; n < elements.size(); n++){ // 1~n-1인 부분수열
        for (int i = 0; i < elements.size(); i++){ // 1~n-1을 시작값으로 가지는
            int temp = 0;
            int count = 0;
            while (count < n){ //부분수열 총합
                temp += elements[(count++ + i)%elements.size()];
            }
            result_set.insert(temp);
        }
    }
    int temp = 0;
    for (auto item: elements){ // 자기 자신
        temp+= item;
    }
    result_set.insert(temp);
    result = result_set.size();
    return result;
}