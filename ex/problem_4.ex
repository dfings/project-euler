# $ elixir -r problem_4.ex -e 'Problem4.main'

defmodule Problem4 do
  def products_of_all_3_digit_numbers() do
    for x <- 100..999, y <- 100..999, do: x * y
  end

  def is_palindrome(n) do
    s = Integer.to_string(n)
    s == String.reverse(s)
  end

  def main do 
    IO.puts (products_of_all_3_digit_numbers() |> Enum.filter(&(is_palindrome/1)) |> Enum.max)
  end
end
