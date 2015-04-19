# $ elixir -r problem_1.ex -e 'Problem1.main'

defmodule Problem1 do
  def sieve(i), do: rem(i, 3) == 0 or rem(i, 5) == 0
  def main, do: IO.puts (1..999 |> Enum.filter(&sieve/1) |> Enum.sum)
end
