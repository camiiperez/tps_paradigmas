module Quality ( Quality(..), newQ, capacityQ,delayQ ) where

import Point
import City

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ str int float = Qua str int float

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua str int float) = int

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua str int float) = float

fibraOptica = newQ "Fibra Optica" 4 22.3


--capacityQ fibraOptica