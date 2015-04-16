# $ elixir -r problem_493.ex -e 'Problem493.main'

defmodule Problem493 do
  @num_colors 7
  @num_per_color 10
  @num_to_pick 20

  # Determines if we've picked all the balls we need to from an urn.
  def sum_all_picked do @num_colors * @num_per_color - @num_to_pick end
  def all_balls_picked urn do (Enum.sum urn) == sum_all_picked() end

  # Counts the number of distinct colors that have been picked from the urn.
  def has_been_picked count do count < @num_per_color end
  def count_colors_picked urn do
    (Enum.filter urn, &has_been_picked/1) |> Enum.count
  end

  # Gets the next urn states giving that we're considering picking a ball at 
  # the given position that has the given number currentlyl left in the urn.
  def get_next_states head, ball_count, tail do
    cond do
      ball_count == 0 -> []
      tail == [] -> List.duplicate head ++ [ball_count - 1], ball_count
      true -> List.duplicate head ++ [ball_count - 1] ++ tail, ball_count
    end
  end

  # Generates one new urn state for each possible ball that could be picked.
  def child_gen head, ball_count, tail do
    next_states = get_next_states head, ball_count, tail
    case tail do
      [] -> next_states 
      [nextball_count | next_tail] -> 
        next_states ++ child_gen head ++ [ball_count], nextball_count, next_tail
    end
  end
 
  # Helper for using childGenerator on an urn.
  def generate_children [first_ball_count | tail] do
    child_gen [], first_ball_count, tail
  end

  # Tuple support.
  def sum_tuples {a1, a2}, {b1, b2} do {a1 + b1, a2 + b2} end

  # Computes {totalColorsPicked, totalLeaves} for leaves rooted at this subtree.
  def compute_counts urn do
    key = Enum.sort urn      
    res = Process.get key
    if res == nil do
      if all_balls_picked urn  do
        # If this is a leaf, then we can just count the colors directly.
        res = {(count_colors_picked urn), 1} 
      else
        # Otherwise we need to sum up the values of all leaves rooted at this subtree.
        child_counts = Enum.map (generate_children urn), &compute_counts/1
        res = List.foldl child_counts, {0, 0}, &sum_tuples/2
      end
      Process.put key, res
    end
    res    
  end

  def main do
    starting_urn = List.duplicate @num_per_color, @num_colors
    {total_colors_picked, total_leaves} = compute_counts starting_urn
    IO.puts :io_lib.format "Total colors = ~w", [total_colors_picked]
    IO.puts :io_lib.format "Total leaves = ~w", [total_leaves]
    IO.puts :io_lib.format "Average = ~w", [total_colors_picked / total_leaves]
  end
end
