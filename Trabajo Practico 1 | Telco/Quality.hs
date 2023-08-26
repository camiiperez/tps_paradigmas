module Quality ( Quality, newQ, capacityQ,delayQ ) where

import Point
import City

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ str int float = Qua str int float

capacityQ :: Quality -> Int 
capacityQ (Qua str int float) = int

delayQ :: Quality -> Float 
delayQ (Qua str int float) = float
