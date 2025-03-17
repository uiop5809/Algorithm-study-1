#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
using namespace std;

/*
    # 문이 설치된 곳으로 항상 두 곳
    . 아무 것도 없는 것으로 빛은 이 곳을 통과
    * 빛이 통과할 수 없는 벽
    ! 거울을 설치할 수 있는 위치
*/

struct Node {
  int x, y, dir, cnt;
  bool operator<(const Node &o) const { 
    return cnt > o.cnt; // 최소힙
  }
};

// 상, 우, 하, 좌
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

int N, answer = 1e9;
int dist[51][51][4];
char arr[51][51];
vector <Node> doors;

bool OOB(int x, int y) {
  return x < 0 || x >= N || y < 0 || y >= N;
}

int bfs() {
  priority_queue<Node> pq;
  int sx = doors[0].x, sy = doors[0].y;
  int ex = doors[1].x, ey = doors[1].y;

  // 거리 배열 초기화
  fill(&dist[0][0][0], &dist[50][50][4], 1e9);

  // 모든 방향에서 시작
  for (int dir = 0; dir < 4; dir++) {
    pq.push({ sx, sy, dir, 0 });
    dist[sx][sy][dir] = 0;
  }

  while (!pq.empty()) {
    Node now = pq.top();
    pq.pop();

    if (now.x == ex && now.y == ey) return now.cnt;

    // 직진
    int nx = now.x + dx[now.dir];
    int ny = now.y + dy[now.dir];
    if (!OOB(nx, ny) && arr[nx][ny] != '*') {
      if (dist[nx][ny][now.dir] > now.cnt){
        dist[nx][ny][now.dir] = now.cnt;
        pq.push({ nx, ny, now.dir, now.cnt });
      }
    }

    // 거울 설치
    if (arr[now.x][now.y] == '!') {
      // 대각선
      int left_dir = (now.dir + 1) % 4;  
      int nx = now.x + dx[left_dir];
      int ny = now.y + dy[left_dir];
      int ncnt = now.cnt + 1;
      if (!OOB(nx, ny) && arr[nx][ny] != '*') {
        if (dist[nx][ny][left_dir] > ncnt){
          dist[nx][ny][left_dir] = ncnt;
          pq.push({ nx, ny, left_dir, ncnt });
        }
      }
      // 반대 대각선
      int right_dir = (now.dir + 3) % 4; 
      nx = now.x + dx[right_dir];
      ny = now.y + dy[right_dir];
      ncnt = now.cnt + 1;
      if (!OOB(nx, ny) && arr[nx][ny] != '*') {
        if (dist[nx][ny][right_dir] > ncnt){
          dist[nx][ny][right_dir] = ncnt;
          pq.push({ nx, ny, right_dir, ncnt });
        }
      }
    }
  }
  return -1;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N;
    for (int i = 0; i < N; i++) {
      string str;
      cin >> str;
      for (int j = 0; j < N; j++) {
        arr[i][j] = str[j];
        if (arr[i][j] == '#') doors.push_back({ i, j });
      }
    }
    
    cout << bfs();
    return 0;
}
