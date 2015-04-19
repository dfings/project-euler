# $ elixir -r problem_3.ex -e 'Problem3.main'

defmodule Problem3 do
  def factor_with(n, i) do
    cond do
      n == 1 -> []
      (rem n, i) == 0 -> [n | factor_with(div(n, i), i)]
      true -> factor_with(n, (i + 1))
    end
  end

  def factor(n), do: factor_with(n, 2)

  def main, do: IO.puts (600851475143 |> factor |> Enum.reverse |> hd)
end
