import System.Environment
import System.IO  
  
main = do  
  args <- getArgs
  handle <- openFile (args !! 0) ReadMode  
  contents <- hGetContents handle  
  putStrLn $ take 10 (show (foldl1 (+) (map read (lines contents))))
  hClose handle  
