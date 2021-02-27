import System.Environment
import System.IO

main = do
  args <- getArgs
  handle <- openFile (head args) ReadMode
  contents <- hGetContents handle
  putStrLn $ take 10 (show (sum (map read (lines contents))))
  hClose handle
