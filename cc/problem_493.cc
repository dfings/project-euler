#include <algorithm>
#include <map>
#include <stdio.h>
#include <vector>

constexpr int kNumColors = 7;
constexpr int kNumPerColor = 10;
constexpr int kNumToPick = 20;

using Counts = std::pair<double, double>;
using Urn = std::vector<int>;

class Solver {
public:
  Solver() : urn_(kNumColors, kNumPerColor) {}

  int CountColors() {
    int count = 0;
    for (auto val : urn_) {
      if (val != kNumPerColor) {
        ++count;
      }
    }
    return count;
  }

  Counts ComputeCounts() {
    if (balls_picked_ == kNumToPick) {
      return Counts{1, CountColors()};
    }

    Urn encoded = urn_;
    std::sort(encoded.begin(), encoded.end());
    auto it = memoized_.find(encoded);
    if (it != memoized_.end()) {
      return it->second;
    }

    Counts counts{0, 0};
    ++balls_picked_;
    for (int i = 0; i < kNumColors; ++i) {
      for (int j = 0; j < kNumPerColor; ++j) {
        if (urn_[i] > j) {
          --urn_[i];
          Counts returned = ComputeCounts();
          counts.first += returned.first;
          counts.second += returned.second;
          ++urn_[i];
        }
      }
    }
    --balls_picked_;
    memoized_.emplace(encoded, counts);
    return counts;
  }

private:
  int balls_picked_ = 0;
  Urn urn_;
  std::map<Urn, Counts> memoized_;
};

int main(int argc, char *argv[]) {
  Solver solver;
  Counts counts = solver.ComputeCounts();
  printf("Total colors = %0.0f\n", counts.second);
  printf("Total picks = %0.0f\n", counts.first);
  printf("Average = %0.9f\n", counts.second / counts.first);
  return 0;
}