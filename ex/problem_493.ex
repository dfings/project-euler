# $ elixir -r problem_493.ex -e 'Problem493.main'

defmodule Problem493 do
  @num_colors 7
  @num_per_color 10
  @num_to_pick 20

  # Determines if we've picked all the balls we need to from an urn.
  def sum_all_picked, do: @num_colors * @num_per_color - @num_to_pick
  def all_balls_picked(urn), do: Enum.sum(urn) == sum_all_picked()

  # Counts the number of distinct colors that have been picked from the urn.
  def has_been_picked(count), do: count < @num_per_color
  def count_colors_picked(urn), do: urn |> Enum.filter(&has_been_picked/1) |> Enum.count

  def pick(urn, color) do
    urn |> Enum.with_index() |> Enum.map(fn {v, i} -> if (i == color) do v - 1 else v end end)
  end

  # Tuple support.
  def sum_tuples({a1, a2}, {b1, b2}), do: {a1 + b1, a2 + b2}

  # Computes {totalColorsPicked, totalLeaves} for leaves rooted at this subtree.
  def compute_counts(urn) do
    key = Enum.sort(urn)      
    res = Process.get(key)
    if res != nil do
      res
    else
      res = if all_balls_picked(urn) do
        # If this is a leaf, then we can just count the colors directly.
        {count_colors_picked(urn), 1} 
      else
        # Otherwise we need to sum up the values of all leaves rooted at this subtree.
        (0..@num_colors-1) 
        |> Enum.flat_map(fn color -> 
          (1..Enum.at(urn, color))
          |> Enum.map(fn _ -> compute_counts(pick(urn, color)) end) 
          end)
        |> Enum.reduce(&sum_tuples/2)
      end
      Process.put(key, res)
      res
    end  
  end

  def main do
    starting_urn = List.duplicate(@num_per_color, @num_colors)
    {total_colors_picked, total_leaves} = compute_counts(starting_urn)
    IO.puts :io_lib.format "Total colors = ~w", [total_colors_picked]
    IO.puts :io_lib.format "Total leaves = ~w", [total_leaves]
    IO.puts :io_lib.format "Average = ~w", [total_colors_picked / total_leaves]
  end
end
