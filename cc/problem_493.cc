#include <array>
#include <bitset>
#include <stdio.h>
#include <stdint.h>

constexpr std::int8_t kNumColors = 7;
using ColorsArray = std::array<std::int8_t, kNumColors>;
constexpr ColorsArray kEmptyColorsArray = {0, 0, 0, 0, 0, 0, 0};
constexpr std::int8_t kNumPerColor = 10;
constexpr std::int16_t kNumToPick = 11;

struct State {
  State() {
    for (int i = 0; i < kNumColors; ++i) {
      urn[i] = kNumPerColor;
    }
  }
  
  std::int64_t total_distinct_colors = 0;
  std::int64_t total_distinct_picks = 0;
  ColorsArray urn = kEmptyColorsArray;
};

void CountColors(std::int64_t num_equivalent, State* state) {
  state->total_distinct_picks += num_equivalent;
  for (int i = 0; i < kNumColors; ++i) {
    if (state->urn[i] != kNumPerColor) {
      state->total_distinct_colors += num_equivalent;
    }
  }
}

void PickOneBall_BruteForce(std::int16_t balls_picked, State* state) {
  // Base case: We've picked all the balls, we need to determine how many
  // distinct colors we picked.
  if (balls_picked == kNumToPick) {
    CountColors(1, state);
    return;
  }
  
  std::int16_t next_balls_picked = balls_picked + 1;
  for (int i = 0; i < kNumColors; ++i) {
    if (state->urn[i] > 0) {
      --state->urn[i];
      PickOneBall_BruteForce(next_balls_picked, state);
      ++state->urn[i];
    }
  }
}

void PickOneBall_Prune(std::int16_t balls_picked, std::int64_t num_equivalent, State* state) {
  // Base case: We've picked all the balls, we need to determine how many
  // distinct colors we picked.
  if (balls_picked == kNumToPick) {
    CountColors(num_equivalent, state);
    return;
  }
  
  ColorsArray next_num_equivalent = kEmptyColorsArray;
  std::bitset<kNumColors> consumed;
  for (int i = 0; i < kNumColors; ++i) {
    std::int8_t count = state->urn[i];
    if (count != 0 && !consumed[i]) {
      ++next_num_equivalent[i];
      for (int j = i + 1; j < kNumColors; ++j) {
        if (count == state->urn[j]) {
          consumed.set(j);
          ++next_num_equivalent[j];
        }
      }
    }
  }
  
  std::int16_t next_balls_picked = balls_picked + 1;
  for (int i = 0; i < kNumColors; ++i) {
    if (next_num_equivalent[i] > 0) {
      --state->urn[i];
      PickOneBall_Prune(next_balls_picked, num_equivalent * next_num_equivalent[i], state);
      ++state->urn[i];
    }
  }
}

int main(int argc, char* argv[]) {
  State state;
  PickOneBall_Prune(0, 1, &state);
  printf("Total = %lld\n", state.total_distinct_colors);
  printf("Average = %f\n", static_cast<double>(state.total_distinct_colors) / state.total_distinct_picks);
  return 0;
}