#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int dp(int arr[], bool isCounted[], int limt){
  int answer = 0;
  for (int i = 1; i<21; i++){
    if (arr[i] == 0){
      break;
    }
    for(int j = i*10000; j>=0; j--){
      if (isCounted[j] == true){
        isCounted[j+arr[i]] = true;
      }
    }
  }
  
  int idx = limt;
  while (idx<200001){
    if(isCounted[idx] == true){
      return idx;
    }
    idx++;
  }

  return idx;
}

int main(){
  int N, T, limt;
  cin >> T;
  for (int tc = 1; tc<=T;tc++){
    int ans = 0;
    int height[21]{0};
    cin >> N >> limt;
    for (int i = 1; i<=N; i++){
      cin >> height[i];
    }
    bool isCounted[201001]{false};
    isCounted[0] = true;
    ans = dp(height, isCounted, limt);
    cout << '#'<< tc <<" "<< ans-limt << "\n";
  }  
  return 0;
}