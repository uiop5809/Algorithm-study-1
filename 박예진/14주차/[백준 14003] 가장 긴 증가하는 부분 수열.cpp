#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

/*
  이분탐색, dp, prev 경로 추적
*/

int N, cnt;
int arr[1000001], dp[1000001];
vector<int> v, ans;
 
int main(){
	cin >> N;
	for (int i = 1; i <= N; i++){
    cin >> arr[i];
  }

	v.push_back(arr[1]);
	for (int i = 2; i <= N; i++){
		if (v[cnt] < arr[i]){
			v.push_back(arr[i]);
			cnt++;
			dp[i] = cnt;
		}
		else {
			int idx = lower_bound(v.begin(), v.end(), arr[i]) - v.begin();
			v[idx] = arr[i];
			dp[i] = idx;
		}
	}
 
	int LIS = cnt;
	for (int i = N; i >= 0; i--){
		if (dp[i] == LIS){
			ans.push_back(arr[i]);
			LIS--;
		}
	}
 
	cout << ans.size() << '\n';
  reverse(ans.begin(), ans.end());
  for(auto iter : ans) {
    cout << iter << " ";
  }
}
