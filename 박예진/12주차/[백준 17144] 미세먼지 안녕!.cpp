#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
using namespace std;

struct Node {
	int x, y;
};

int R, C, T;
int arr[51][51];

const int dx[4] = { -1, 0, 1, 0 };
const int dy[4] = { 0, -1, 0, 1 };

vector<Node> airPurifier;

bool OOB(int x, int y) {
	return x < 0 || x >= R || y < 0 || y >= C;
}

// 1. 미세먼지 상하좌우 확산
void dustDiffusion() {
	// temp arr 복사
	int temp[51][51];
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			temp[i][j] = arr[i][j];
		}
	}

	// 미세먼지 확산
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (arr[i][j] > 0) {
				int diffusionN = arr[i][j] / 5; // 확산값
				int diffusionSum = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + dx[dir];
					int ny = j + dy[dir];

					if (!OOB(nx, ny) && arr[nx][ny] != -1) {
						temp[nx][ny] += diffusionN;
						diffusionSum += diffusionN;
					}
				}
				temp[i][j] -= diffusionSum; // 확산되고 남은 거
			}
		}
	}

	// arr에 temp 넣기
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			arr[i][j] = temp[i][j];
		}
	}
}

// 반시계 방향
void rotate1(int sx, int sy) {
	int temp = arr[sx][sy];

	// 위에서 아래
	for (int i = sx; i > 0; i--) {
		arr[i][0] = arr[i - 1][0];
	}
	// 오른쪽에서 왼쪽
	for (int j = 0; j < C; j++) {
		arr[0][j] = arr[0][j + 1];
	}
	// 아래에서 위
	for (int i = 0; i < sx; i++) {
		arr[i][C - 1] = arr[i + 1][C - 1];
	}
	// 왼쪽에서 오른쪽
	for (int j = C - 1; j > 0; j--) {
		arr[sx][j] = arr[sx][j - 1];
	}

	arr[sx][sy] = temp;
	arr[sx][sy + 1] = 0;
}

// 시계 방향
void rotate2(int sx, int sy) {
	int temp = arr[sx][sy]; // -1 부분

	// 아래에서 위(행바뀜)
	for (int x = sx; x < R - 1; x++) {
		arr[x][0] = arr[x + 1][0];
	}
	// 오른쪽에서 왼쪽(열바뀜)
	for (int y = 0; y < C - 1; y++) {
		arr[R - 1][y] = arr[R - 1][y + 1];
	}
	// 위에서 아래(행바뀜)
	for (int x = R - 1; x > sx; x--) {
		arr[x][C - 1] = arr[x - 1][C - 1];
	}
	// 왼쪽에서 오른쪽(열바뀜)
	for (int y = C - 1; y > 0; y--) {
		arr[sx][y] = arr[sx][y - 1];
	}

	arr[sx][sy] = temp;
	arr[sx][sy + 1] = 0;
}

// 2, 공기청정기 작동 -> 미세먼지 바람 방향 한칸씩 이동
void functioning() {
	// 공기청정기 윗부분 : 반시계
	Node func1 = airPurifier[0];
	rotate1(func1.x, func1.y);

	// 공기청정기 아랫부분 : 시계
	Node func2 = airPurifier[1];
	rotate2(func2.x, func2.y);
}

// 총 미세먼지 양
int answer() {
	int sum = 0;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (arr[i][j] != -1) {
				sum += arr[i][j];
			}
		}
	}
	return sum;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0); cout.tie(0);

	cin >> R >> C >> T;
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			cin >> arr[i][j];
			// 공기청정기 위치
			if (arr[i][j] == -1) {
				airPurifier.push_back({ i, j });
			}
		}
	}

	while (T-- > 0) {
		dustDiffusion();
		functioning();
	}

	cout << answer();

}
