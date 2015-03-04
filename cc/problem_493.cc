#include <algorithm>
#include <array>
#include <bitset>
#include <map>
#include <stdio.h>
#include <stdint.h>

struct Counts {
  std::int64_t total_distinct_colors = 0;
  std::int64_t total_distinct_picks = 0;
};

constexpr std::int8_t kNumColors = 7;
using ColorsArray = std::array<std::int8_t, kNumColors>;
constexpr ColorsArray kEmptyColorsArray = {0, 0, 0, 0, 0, 0, 0};
constexpr std::int8_t kNumPerColor = 10;
constexpr std::int16_t kNumToPick = 20;

struct State {
  State() {
    for (int i = 0; i < kNumColors; ++i) {
      urn[i] = kNumPerColor;
    }
  }
  
  ColorsArray urn = kEmptyColorsArray;
  std::map<int, Counts> memoized;
};

int Encode(const ColorsArray& colors) {
  ColorsArray sorted = colors;
  std::sort(sorted.begin(), sorted.end());
  int encoded = 0;
  int factor = 1;
  for (int i = 0; i < kNumColors; ++i) {
    encoded += sorted[i] * factor;
    factor *= kNumPerColor;
  }
  return encoded;
}

int CountColors(const ColorsArray& colors) {
  int count = 0;
  for (int i = 0; i < kNumColors; ++i) {
    if (colors[i] != kNumPerColor) {
      ++count;
    }
  }
  return count;
}

Counts PickOneBall(std::int16_t balls_picked, State* state) {
  int encoded = Encode(state->urn);
  
  if (balls_picked == kNumToPick) {
    Counts counts;
    counts.total_distinct_picks = 1;
    counts.total_distinct_colors = CountColors(state->urn);
    state->memoized.emplace(encoded, counts);
    return counts;
  }
  
  auto it = state->memoized.find(encoded);
  if (it != state->memoized.end()) {
    return it->second;
  } 
  
  Counts counts = {};
  std::int16_t next_balls_picked = balls_picked + 1;
  for (int i = 0; i < kNumColors; ++i) {
    if (state->urn[i] > 0) {
      --state->urn[i];
      Counts returned = PickOneBall(next_balls_picked, state);
      counts.total_distinct_colors += returned.total_distinct_colors;
      counts.total_distinct_picks += returned.total_distinct_picks;
      ++state->urn[i];
    }
  }
  
  state->memoized.emplace(encoded, counts);
  return counts;
}

int main(int argc, char* argv[]) {
  State state;
  Counts counts = PickOneBall(0, &state);
  printf("Total colors = %lld\n", counts.total_distinct_colors);
  printf("Total picks = %lld\n", counts.total_distinct_picks);
  printf("Average = %0.9f\n", static_cast<double>(counts.total_distinct_colors) / counts.total_distinct_picks);
  return 0;
}