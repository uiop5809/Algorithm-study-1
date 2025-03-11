#include <iostream>
#include <algorithm>
#include <cstring>
#include <cmath>
#include <vector>
using namespace std;

/*
	2^N 격자, 얼음의 양
	
	파이어스톰 단계 L
	2^L 크기 부분 격자로 나누고 모든 격자 90도 시계 회전

	1. 격자 나누기
	2. 회전 함수
	3. 상하좌우 얼음 없는 칸 2개 이하일 경우, 인접하지 않은 칸 얼음 - 1
	   상하좌우 얼음 없는 칸 3개 이상일 경우, 유지
*/

int N, Q, arrSize; // 격자 크기, 파이어스톰 횟수
int arr[65][65];
bool visited[65][65];

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

// 시계 방향
void rotate(int sx, int sy, int size) {
	int temp[65][65];

	// 90도 시계 방향
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			temp[sx + j][sy + size - 1 - i] = arr[sx + i][sy + j];
		}
	}
	for (int i = sx; i < sx + size; i++) {
		for (int j = sy; j < sy + size; j++) {
			arr[i][j] = temp[i][j];
		}
	}
}

void meltIce() {
	int temp[65][65];
	memcpy(temp, arr, sizeof(arr));

	for (int i = 0; i < arrSize; i++) {
		for (int j = 0; j < arrSize; j++) {
			if (arr[i][j] > 0) {
				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (nx < 0 || nx >= arrSize || ny < 0 || ny >= arrSize) continue;
					if (arr[nx][ny] > 0) cnt++;
				}
				if (cnt < 3) temp[i][j]--;
			}
		}
	}
	memcpy(arr, temp, sizeof(arr));
}

// 남은 얼음의 합
int solution() {
	int sum = 0;
	for (int i = 0; i < arrSize; i++) {
		for (int j = 0; j < arrSize; j++) {
			if (arr[i][j] > 0) sum += arr[i][j];
		}
	}
	return sum;
}

// 덩어리가 가장 큰 칸
int dfs(int x, int y) {
	visited[x][y] = true;
	int count = 1;

	for (int dir = 0; dir < 4; dir++) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];

		if (nx < 0 || nx >= arrSize || ny < 0 || ny >= arrSize) continue;
		if (!visited[nx][ny] && arr[nx][ny] > 0) {
			count += dfs(nx, ny);
		}
  }
	return count;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> Q;
	arrSize = pow(2, N);
	for (int i = 0; i < arrSize; i++) {
		for (int j = 0; j < arrSize; j++) {
			cin >> arr[i][j];
		}
	}

	while (Q-- > 0) {
		int L;
		cin >> L;

		L = pow(2, L);  // 부분 격자 크기
		for (int i = 0; i < arrSize; i += L) {
			for (int j = 0; j < arrSize; j += L) {
				rotate(i, j, L); // sx, sy, size
			}
		}
		
		// 회전후 녹이기
		meltIce();
	}

	cout << solution() << "\n";

	int res = 0;
	for (int i = 0; i < arrSize; i++) {
		for (int j = 0; j < arrSize; j++) {
			if (!visited[i][j] && arr[i][j] > 0) {
				visited[i][j] = true;
				res = max(res, dfs(i, j));
			}
		}
	}
	cout << res;


	return 0;
}
