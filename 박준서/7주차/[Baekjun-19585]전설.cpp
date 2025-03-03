#include <iostream>
#include <map>
#include <set>
using namespace std;

struct Trie {
 public:
  bool isEndOfString;
  map<char, Trie *> next;

  Trie() {
    isEndOfString = false;
    next.clear();
  }
  ~Trie() {
    for (auto &it : next) {
      delete it.second;
    }
  }

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
Trie *color = new Trie;
set<string> team;

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