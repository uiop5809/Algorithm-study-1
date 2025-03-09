#include <iostream>
#include <map>
#include <set>
using namespace std;

// Trie 구조체 정의
struct Trie {
 public:
  bool isEndOfString;      // 문자열의 끝을 나타내는 플래그
  map<char, Trie *> next;  // 다음 문자에 대한 포인터를 저장하는 맵

  // 생성자
  Trie() {
    isEndOfString = false;
    next.clear();
  }
  // 소멸자 - 메모리 누수 방지를 위해 모든 자식 노드 삭제
  ~Trie() {
    for (auto &it : next) {
      delete it.second;
    }
  }

  // 문자열을 Trie에 삽입하는 함수
  void push(string input) {
    Trie *nowNode = this;
    for (int i = 0; i < input.size(); i++) {
      if (nowNode->next.find(input[i]) == nowNode->next.end())
        nowNode->next[input[i]] = new Trie;
      nowNode = nowNode->next[input[i]];
    }
    nowNode->isEndOfString = true;
  }
};

string str;
int N, M, K;
Trie *color = new Trie;  // 색상을 저장할 Trie
set<string> team;        // 팀 이름을 저장할 set

// 입력된 문자열이 유효한 이름인지 확인하는 함수
bool naming(string input) {
  Trie *colorNode = color;
  set<int> point;

  for (int i = 0; i < input.size(); i++) {
    if (colorNode->isEndOfString && team.find(input.substr(i)) != team.end())
      return true;
    if (colorNode->next.find(input[i]) == colorNode->next.end()) break;

    colorNode = colorNode->next[input[i]];
  }
  return false;
}

int main() {
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  cin >> N >> M;

  while (N--) {
    cin >> str;
    color->push(str);
  }

  while (M--) {
    cin >> str;
    team.insert(str);
  }

  cin >> K;
  while (K--) {
    cin >> str;
    if (naming(str))
      cout << "Yes\n";
    else
      cout << "No\n";
  }
  return 0;
}
