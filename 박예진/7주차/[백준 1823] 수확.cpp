#include <iostream>
#include <algorithm>
using namespace std;

/*
	N개의 벼와 가치 주어졌을 때 최대 이익
*/

int N;
int value[2001];
int dp[2001][2001]; // left, right

int dynamic(int left, int right, int depth) {
	if (left > right) return 0;
	if (dp[left][right] > 0) return dp[left][right];

	int n1 = value[left] * depth + dynamic(left + 1, right, depth + 1);
	int n2 = value[right] * depth + dynamic(left, right - 1, depth + 1);

	dp[left][right] = max(n1, n2);

	return dp[left][right];
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> value[i];
	}
	dynamic(0, N - 1, 1);

	cout << dp[0][N - 1];

	return 0;
}
