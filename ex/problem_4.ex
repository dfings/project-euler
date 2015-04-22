# $ elixir -r problem_4.ex -e 'Problem4.main'

defmodule Problem4 do
  def multiply_by_all_3_digit_numbers(n), do: Enum.map(100..999, &(&1 * n))
  def products_of_all_3_digit_numbers() do
    Enum.flat_map(100..999, &(multiply_by_all_3_digit_numbers/1))
  end

  def is_palindrome(n) do
    s = Integer.to_string(n)
    s == String.reverse(s)
  end

  def main do 
    IO.puts (products_of_all_3_digit_numbers() |> Enum.filter(&(is_palindrome/1)) |> Enum.max)
  end
end
