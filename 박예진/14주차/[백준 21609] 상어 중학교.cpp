#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cmath>
#include <cstring>
using namespace std;

struct Node {
  int x, y;
};

struct Value {
  int cnt, rainbow, row, col;
  vector<Node> pos;
};

struct cmp {
  bool operator()(const Value &v1, const Value &v2) {
    if (v1.cnt != v2.cnt) return v1.cnt < v2.cnt;
    else if (v1.rainbow != v2.rainbow) return v1.rainbow < v2.rainbow;
    else if (v1.row != v2.row) return v1.row < v2.row;
    return v1.col < v2.col;
  }
};

int N, M, score;
int arr[21][21];
bool visited[21][21];
const int dx[4] = {0, -1, 0, 1};
const int dy[4] = {-1, 0, 1, 0};

bool OOB(int x, int y) {
  return x < 0 || x >= N || y < 0 || y >= N;
}

Value bfs(int x, int y, int n) {
  queue<Node> q;
  q.push({x, y});
  visited[x][y] = true;
  vector<Node> v;

  int cnt = 0, rainbowCnt = 0;
  int minRow = 21, minCol = 21;
  while(!q.empty()) {
    Node now = q.front(); q.pop(); cnt++;
    
    v.push_back({now.x, now.y});
    if (arr[now.x][now.y] == 0) rainbowCnt++;
    else {
      minRow = min(minRow, now.x);
      minCol = min(minCol, now.y);
    }
    
    for(int dir = 0; dir < 4; dir++){
      int nx = now.x + dx[dir];
      int ny = now.y + dy[dir];

      if (OOB(nx, ny) || arr[nx][ny] == -1 || visited[nx][ny]) continue;
      if (arr[nx][ny] == n || arr[nx][ny] == 0) {
        q.push({nx, ny});
        visited[nx][ny] = true;
      }
    }
  }

  return {cnt, rainbowCnt, minRow, minCol, v};
}

// 중력
void gravity(){
  for(int j = 0; j < N; j++){
    for(int i = N - 2; i >= 0; i--) {
      // 블록이 있는 경우
      if (arr[i][j] >= 0){
        int nx = i;
        while(nx + 1 < N && arr[nx + 1][j] == -2) {
          arr[nx + 1][j] = arr[nx][j];
          arr[nx][j] = -2;
          nx++;
        }
      }
    }
  }
}

// 반시계 회전
void rotate(){
  int temp[21][21];
  for(int i = 0; i < N; i++){
    for(int j = 0; j < N; j++){
      temp[N - j - 1][i] = arr[i][j];
    }
  }
  memcpy(arr, temp, sizeof(temp));
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N >> M;
    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        cin >> arr[i][j];
      }
    }

    // 블록 그룹이 존재
    while(true) {
      // 1. 가장 큰 블록 그룹 찾기
      priority_queue<Value, vector<Value>, cmp> pq;

      for(int k = 1; k <= M; k++){
        memset(visited, 0, sizeof(visited));
        for(int i = 0; i < N; i++){
          for(int j = 0; j < N; j++){
            if (!visited[i][j] && arr[i][j] == k){
              Value value = bfs(i, j, k);
              if (value.cnt >= 2) pq.push(value);
            }
          }
        }
      }

      // 2. 블록 그룹 모든 블록 제거
      if (pq.empty()) break;

      Value now = pq.top(); pq.pop();
      for(auto iter : now.pos) {
        arr[iter.x][iter.y] = -2;
      }
      score += pow(now.cnt, 2);

      // 3. 중력 작용
      gravity();

      // 4. 격자 90도 반시계 회전
      rotate();

      // 5. 중력 작용
      gravity();
    }

    cout << score;

    return 0;
}
