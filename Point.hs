module Point ( Point(), newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x y) (Poi x' y') = sqrt((fromIntegral(x-x'))**2 + (fromIntegral(y-y'))** 2)

punto1 = (newP 2 3) 
punto2 = (newP 4 5)