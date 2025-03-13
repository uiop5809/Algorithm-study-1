#include <iostream>
#include <algorithm>
using namespace std;

/*
	N일간 임무 수행
*/

int N, M, answer;
int arr[2][3];

void dfs(int x, int y, int depth, int sum, int px, int py) {
	if (depth == N) {
		if (sum >= M) answer++;
		return;
	}

	// j가 이전 값과 같으면 진척도 / 2
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 3; j++) {
			if (y == j) {
				dfs(i, j, depth + 1, sum + arr[i][j] / 2, x, y);
			}
			else {
				dfs(i, j, depth + 1, sum + arr[i][j], x, y);
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < 3; j++) {
			cin >> arr[i][j];
		}
	}
	dfs(-1, -1, 0, 0, -1, -1);
	cout << answer;

	return 0;
}
