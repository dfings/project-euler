# $ elixir -r problem_1.ex -e 'Problem1.main'

defmodule Problem1 do
  def sieve i do (rem i, 3) == 0 or (rem i, 5) == 0 end
  def main do IO.puts Enum.sum Enum.filter 1..999, &sieve/1 end
end
