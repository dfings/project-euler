# $ elixir -r problem_2.ex -e 'Problem2.main'

defmodule Problem2 do
  def is_even i do (rem i, 2) == 0 end
  def gen_fib a, b do
    if b >= 4000000 do []
    else [b | gen_fib b, a + b] end
  end

  def main do IO.puts ((gen_fib 1, 1) |>  (Enum.filter &is_even/1) |> Enum.sum) end
end
