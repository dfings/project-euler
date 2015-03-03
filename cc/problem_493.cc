#include <stdio.h>
#include <stdint.h>
#include <vector>

constexpr std::int8_t kNumColors = 7;
constexpr std::int8_t kNumPerColor = 10;
constexpr std::int16_t kNumToPick = 20;

struct State {
  State() : urn(kNumColors, kNumPerColor) {}
  std::int64_t total_distinct_colors = 0;
  std::int64_t total_distinct_picks = 0;
  std::vector<std::int8_t> urn;
};

void CountColors(State* state) {
  ++state->total_distinct_picks;
  for (int i = 0; i < kNumColors; ++i) {
    if (state->urn[i] != kNumPerColor) {
      ++state->total_distinct_colors;
    }
  }
}

void PickOneBall(std::int16_t balls_picked, State* state) {
  // Base case: We've picked all the balls, we need to determine how many
  // distinct colors we picked.
  if (balls_picked == kNumToPick) {
    CountColors(state);
    return;
  }
  
  std::int16_t next_balls_picked = balls_picked + 1;
  for (int i = 0; i < kNumColors; ++i) {
    if (state->urn[i] > 0) {
      --state->urn[i];
      PickOneBall(next_balls_picked, state);
      ++state->urn[i];
    }
  }
}

int main(int argc, char* argv[]) {
  State state;
  PickOneBall(0, &state);
  printf("Average = %f\n", static_cast<double>(state.total_distinct_colors) / state.total_distinct_picks);
  return 0;
}